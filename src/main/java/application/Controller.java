package application;
import application.api.WeatherAPI;
import application.components.admin.AdminControl;
import application.components.registration.RegisterAdmin;
import application.components.flight.*;
import application.components.initialize.InitializeFXM;
import application.components.user.*;
import application.components.login.ShowPasswordField;
import application.components.flight.Book;
import application.components.ticket.PurchaseHandler;
import application.components.ticket.UserHistory;
import application.components.registration.RegistrationUser;
import application.config.Config;
import application.eventHandler.AdminEvent;
import application.eventHandler.UserEvent;
import application.games.*;
import application.api.Db;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import worldMapAPI.World;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * #move ---> There is methods that can be separated from controller to new or exiting class.
 * This class is main class which connects all components, methods and variables together with FXML.
 */
public class Controller {

    //<editor-fold desc="========= GLOBAL VARIABLES =========" >
    //<editor-fold desc="======== LOGIN WINDOW VARIABLES ========">
    @FXML public TextField login_pass, login_email, show_password_field_login;
    @FXML public Label error_message_lbl, success_msg_lbl; // ERRORS HANDLER
    @FXML private Button forgot_password_login;
    @FXML public AnchorPane login_anchorpane, forgot_password_anchorpane;
    @FXML public CheckBox show_pasword_login;
    public static boolean explore_mode = true; // If explore mode is off, it means user is logged in.

    //</editor-fold>

    //</editor-fold>

    //<editor-fold desc="========= ADMIN VARIABLES =========">
    //<editor-fold desc="======== REGISTER WINDOW VARIABLES (admin) ========">
    @FXML public CheckBox       is_admin_checkbox;
    @FXML public TextField      first_name_reg_admin, last_name_reg_admin, address_reg_admin, emailaddress_reg_admin, phone_number_reg_admin;
    @FXML public PasswordField  password_reg_admin, confirm_password_reg_admin;
    @FXML public Label          name_issue_reg_admin,last_name_issue_reg_admin, address_issue_reg_admin, email_issue_reg_admin, phone_number_issue_reg_admin,
                                password_issue_reg_admin, confirm_password_issue_reg_admin, registration_error_admin;
    //</editor-fold>
    //<editor-fold desc="======== DASHBOARD VARIABLES ========">
    @FXML public AnchorPane     admin_flights_anchorpane, admin_tickets_anchorpane, admin_members_anchorpane, admin_register_anchorpane;
    @FXML public Button         admin_flights_button, admin_members_button, admin_tickets_button, admin_logout_button, registerCommitBtn_admin,
                                registerMemberBtn_admin, returnToMemberListBtn_admin, refreshMembersBtn_admin,
                                prev_tur_date_flight_admin, next_tur_date_flight_admin;
    //</editor-fold>
    //<editor-fold desc="======== TABLE VARIABLES ========">

    // ticket table variables
    public ObservableList<UserHistory> fetchedList_ticket_admin, items_ticket_admin;
    @FXML public Button addTicketBtn_ticket_admin, deleteTicketBtn_ticket_admin, refreshTicketsBtn_ticket_admin;
    @FXML public TableView<UserHistory> table_tickets;
    @FXML public CheckBox select_col_ticket_admin;

    // members table variables
    public ObservableList<User> fetchedList_member_admin, items_member_admin;
    @FXML public Button delet_btn_mbr_admin, deletS_btn_mbr_admin;
    @FXML public TableView<User> table_member_admin;
    @FXML public CheckBox select_col_mbr_admin;

    //flights table variables
    public ObservableList<Flight> fetchedList_flight_admin, items_flight_admin;
    @FXML public Button delete_singelFlightBtn_admin, delete_allFlightsBtn_admin, refreshFlightsBtn_admin, addFlightsBtn_admin, search_input_flight_admin;
    @FXML public TableView<Flight> table_flight_admin;
    @FXML public CheckBox  select_col_flight_admin;
    @FXML public TextField from_input_flight_admin, disc_input_flight_admin;
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="========= USER DASHBOARD VARIABLES =========" >
    //<editor-fold desc="========= DEFAULT VARIABLES =========" >
    public static boolean is_editing_profile_image = false;
    public static boolean is_editing_profile;
    public File profile_image_directory;
    public File[] profile_image_files;
    public CreateWorld create_world;
    public static World world_map;
    public Stage main_stage;
    public Scene main_scene;
    public static User user;
    public Parent root;
    //public String rfc;
    public String rfc = String.valueOf(new AtomicInteger());

    //</editor-fold>
    //<editor-fold desc="========= FXML VARIABLES =========" >

    // LOADER ICON
    @FXML public ImageView login_loader_flight;

    // ERROR
    @FXML public Label err_advanced_search;
    @FXML public ScrollPane world_map_scrollpane, flights_scrollpane;
    @FXML public Label username_lbl;
    @FXML public VBox flight_display_vbox;

    // ===== PROFILE EDITING =====
    // General
    @FXML public ImageView profile_image_imageview, profile_image_preview_imageview;
    @FXML public TextField profile_first_name_lbl, profile_last_name_lbl, profile_email_lbl, profile_address_lbl,
                           profile_phone_lbl, profile_new_password_textfield, profile_confirm_password_textfield;
    @FXML public Button profile_cancel_btn, profile_edit_btn;
    @FXML public GridPane profile_profile_image_gridpane;
    @FXML public PasswordField profile_old_password_passwordfield;

    // PROFILES
    @FXML public Label edit_pfp_fname_issue, edit_pfp_lname_issue, edit_pfp_address_issue, edit_pfp_email_issue,
                       edit_pfp_phone_issue, edit_pfp_old_pwd_issue, edit_pfp_new_pwd_issue, edit_pfp_new_c_pwd_issue, pfp_display_msg;


    //  MENU BUTTONS ETC.
    @FXML
    public Button menu_profile_btn, menu_flight_btn, menu_history_btn, menu_entertainment_btn, menu_support_btn,
                  menu_ceo_btn, menu_my_tickets_btn, booking_close_btn, booking_close_second_page_btn, owner1_btn, owner2_btn, owner3_btn, owner4_btn;
    @FXML public AnchorPane profile_anchorpane, history_anchorpane, flight_anchorpane, entertainment_anchorpane, support_anchorpane, ceo_anchorpane, my_ticket_anchorpane;
    @FXML public Pane menu_highlight_color_support, menu_highlight_color_ceo, menu_highlight_color_my_ticket, menu_highlight_color_flight,
                      menu_highlight_color_history, menu_highlight_color_entertainment;
    @FXML public Pane menu_admin_highlight_customers, menu_admin_highlight_flights, menu_admin_highlight_tickets; // Admin side
    @FXML public HBox owner1_work, owner2_work, owner3_work, owner4_work;

    //</editor-fold>
    //<editor-fold desc="========= WEATHER VARIABLES =========" >
    @FXML public static Label lblForecastA,lblForecastB,lblForecastC,lblForecastD,lblForecastE,lblForecastF;
    @FXML public static ImageView weatherIcon, play_button_image;
    @FXML public static Pane weatherPane, weatherPaneBase;

    @FXML public Button weather_btn;
    public static boolean weatherMenu;
    //</editor-fold>
    //<editor-fold desc="======== TICKET-PROCESS VARIABLES ========">
    @FXML public AnchorPane success_purchase_anchorpane, booking_seat_anchorpane, booking_passenger_anchorpane,
            flight_seats_eco_anchorpane, flights_seats_business_anchorpane, payment_anchorpane;
    @FXML public TextField  card_nbr, card_fname, card_lname, card_month, card_year, card_cvc;
    @FXML public Button     card_prev_btn, card_purchase_btn, seat_next_btn,logout_ticket_purchase_btn , success_purchase_to_dashboard_button, show_ticket_purchase_btn;     // FINAL BUTTONS AFTER PURCHASING THE TICKET
    @FXML public Label      card_counter_nbr, payment_err_msg;
    @FXML public ScrollPane business_seat_scrollpane, eco_seat_scrollpane;
    @FXML public TextField  booking_last_name_textfield, booking_first_name_textfield, booking_four_digits_textfield, booking_email_textfield;
    @FXML public Label      booking_seat_number_lbl, booking_msg_lbl, booking_flight_number_lbl, booking_retur_seat_number_lbl,
            booking_price_lbl, booking_departure_lbl, booking_destination_lbl,
            booking_departure_extra_lbl, booking_destination_extra_lbl, booking_is_retur_lbl;
    @FXML public VBox       booking_info_vbox;
    @FXML public ImageView  support_menu_user_image, ceo_menu_user_image, my_tickets_menu_user_image,
            booking_profile_image, map_menu_user_image, history_menu_user_image, entertainment_menu_user_image;
    @FXML public Label map_menu_user_lbl, history_menu_user_lbl, entertainment_menu_user_lbl, support_menu_user_lbl, ceo_menu_user_lbl, my_tickets_menu_user_lbl;
    @FXML public Label customers_menu_admin_lbl, flights_menu_admin_lbl, tickets_menu_admin_lbl; //Admin path
    @FXML public ImageView customers_menu_admin_img, flights_menu_admin_img, tickets_menu_admin_img; //Admin path
    public ArrayList<String> taken_seat_business = new ArrayList<>();
    public ArrayList<String> taken_seat_economy = new ArrayList<>();
    public ArrayList<Flight> round_trip_flights = new ArrayList<>();
    public final GridPane economy_seat_gridpane = new GridPane();
    public final GridPane business_seat_gridpane = new GridPane();
    public String departure_seat, return_seat;
    public boolean has_return_flight = false;
    public double seat_price = 0.0;

    //</editor-fold>
    //<editor-fold desc="======== HISTORY VARIABLES ========">
    @FXML private Button history_multiple_delete_button;
    @FXML private Button history_single_delete_button;
    @FXML private Button history_display_flight_path_button;
    @FXML
    public Button detailes_btn_histroy;
    @FXML public TableColumn<Book, String> from_col_table_historik,to_col_table_historik;
    ObservableList<UserHistory> history_items_list, history_flights;
    @FXML public TableView<UserHistory> history_tableview;
    @FXML private CheckBox history_select_all_checkbox;
    @FXML public Label history_reference_number_lbl, rfc_smp_history;

    //</editor-fold
    //<editor-fold desc="======== SEARCH VARIABLES ========">
    @FXML public Button date_previous_day_button, date_next_day_button, date_previous_day_return_button, date_next_day_return_button;
    @FXML public ListView<String> search_list_suggestion, search_list_appear_second, search_list_appear_third;
    @FXML public TextField from_input_flight_textfield, display_input_flights, search_f_name;
    @FXML public Label nbr_of_available_flights, search_flight_error_lbl;
    public ArrayList<Flight> available_flights_list = new ArrayList<>();
    @FXML public DatePicker date_input_flight,dateR_input_flight;
    @FXML public CheckBox round_trip_checkbox;
    @FXML public HBox return_date_pick_hbox;
    //</editor-fold>
    //<editor-fold desc="======== REGISTER WINDOW VARIABLES ========">
    @FXML public TextField  registration_first_name, registration_last_name, registration_address, registration_email,
            registration_phone_number, registration_password, registration_confirm_password;
    @FXML public Label      name_issue_reg, last_name_issue_reg, address_issue_reg, email_issue_reg, phone_number_issue_reg,
            password_issue_reg, confirm_password_issue_reg, registration_error_lbl;
    //</editor-fold>
    //<editor-fold desc="======== SUPPORT WINDOW VARIABLES ========">
    @FXML public Button issue_btn_sup, feedback_btn_sup, contact_btn_sup, send_fb_btn_sup, send_issue_btn_sup, send_contact_btn_sup;
    @FXML public TextField subject_fb_txt_sup, email_fb_txt_sup, subject_contact_txt_sup, email_contact_txt_sup, title_issue_txt_sup,
            email_issue_txt_sup;
    @FXML public TextArea msg_issue_txt_sup, msg_fb_txt_sup, msg_contact_txt_sup;
    @FXML public Label sup_report_display_msg, sup_contact_display_msg, sup_feedback_display_msg;
    @FXML public AnchorPane issue_panel_sup, contact_panel_sup, feedback_panel_sup;
    //</editor-fold
    //<editor-fold desc="======== SUPPORT WINDOW VARIABLES ========">
    @FXML public Label from_myticket, to_myticket, seat_myticket,err_msg_myticket,
            rfc_muticket, flightno_myticket, terminal_myticket, dep_date_myticket,
            dep_time_myticket, des_date_myticket, des_time_myticket, airline_myticket, ttl_price_myticket;
    @FXML public Button checka_btn_myticket, cancel_btn_my_ticket, returnD_btn_checking, returnT_btn_checking;
    @FXML public AnchorPane checkning_pnl;

    //</editor-fold>

    //</editor-fold>

    //<editor-fold desc="========= INSTANCES OF CLASSES =========" >

    public FlightsViewManager flightsViewManager;
    public RegistrationUser registrationUser;
    public PurchaseHandler purchaseHandler;
    public ProfileManager profileManager;
    public RegisterAdmin registerAdmin;
    public InitializeFXM initializeFXM;
    public ShowPasswordField password;
    public AdminControl adminControl;
    public ErrorHandler errorHandler;
    public UserControl userControl;
    public FlightPaths flightPaths;
    public AdminEvent adminEvent;
    public WeatherAPI weatherAPI;
    public UserEvent userEvent;
    public BgMusic bgMusic;
    public Support support;
    public SystemSound systemSound;
    public Config config;
    public Search search;
    public Db db;
    //</editor-fold>

    /**
     * Constructor to Controller class
     * @author Khabib
     */
    public Controller(){
        db = new Db();
        config = new Config(root, main_stage);
        support = new Support(this);
        errorHandler = new ErrorHandler(this);
        search = new Search(this, db, errorHandler);
        registrationUser = new RegistrationUser(this, db, config);
        registerAdmin = new RegisterAdmin(this, db, config);
        userControl = new UserControl(this, root, db);
        initializeFXM = new InitializeFXM(this, db);
        flightPaths = new FlightPaths(this);
        weatherAPI = new WeatherAPI();
        profileManager = new ProfileManager();
        password = new ShowPasswordField();
        adminControl = new AdminControl(this, db);
        adminEvent = new AdminEvent();
        purchaseHandler = new PurchaseHandler();
        flightsViewManager = new FlightsViewManager(this);
        userEvent = new UserEvent(this, flightsViewManager);
        bgMusic = new BgMusic(this);
        systemSound = new SystemSound(this);
    }

    //<editor-fold desc="============= SWITCH WINDOWS">

    /**
     * the method will switch the user to the Home page
     * @param e event
     * @author Khabib
     */
    public void switchToHome(ActionEvent e) {
        config.render(e,"Home", "Home");
    }

    /**
     * the method will switch the user to the dashboard page
     * navigate to dashboard pages.
     * @param e event.
     * @author Khabib developed by Kasper.
     */
    public void switchToUserDashboard(ActionEvent e) {
        explore_mode = false;
        userControl.switchToUserDashboard(e,this);
    }

    /**
     * the method will switch the user to the login page
     * @param e event
     * @author Khabib
     */
    public void switchToLogin(ActionEvent e) {
        explore_mode = true;
        this.root = config.render(e, "user/Login", "Login");
        success_msg_lbl = (Label) root.lookup("#success_msg_lbl");
        login_anchorpane = (AnchorPane) root.lookup("#login_anchorpane");
        forgot_password_anchorpane = (AnchorPane) root.lookup("#forgot_password_anchorpane");
        if(user != null) {
            playSystemSound("Logout", "sounds/logout.wav");
        } else {
            user = null;
        }
    }

    /**
     * This method will handle forget password ....
     * ...
     * @param e event.
     * @author Sossio.
     */
    public void bringForgotPasswordToFront(ActionEvent e) {
        forgot_password_anchorpane.toFront();
    }

    /**
     * This method will ....
     * ...
     * @param e event.
     * @author Sossio.
     */
    public void bringLoginToFront(ActionEvent e) {
        login_anchorpane.toFront();
    }


    /**
     * the method will switch the user to register page.
     * @param e event.
     * @author Khabib developed by Sossio.
     */
    public void switchToRegistration(ActionEvent e) {
        playSystemSound("Next page", "sounds/next_page.wav");
        this.root = config.render(e, "user/Registration", "Registration");
    }


    /**
     * the method will render dashboard page for user.
     * @param e event.
     * @param user instance of User class.
     * @author Khabib developed by Kasper.
     */
    public void renderDashboard(ActionEvent e, User user) {
        userControl.renderDashboard(e, user,this);
    }

    /**
     * Navigate to user dashboard without login.
     * @param e event.
     * @author Khabib developed by Sossio.
     */
    public void noLoginRequired(ActionEvent e) {
        explore_mode = true;
        userControl.noLoginRequired(e,this);
    }
    //</editor-fold>

    //<editor-fold desc="============= GAME METHODS"

    /**
     * Creates and animates flightpath on world map.
     * @author Kasper
     */
    public void displayFlightPaths() {
        flightPaths.start();
        flight_anchorpane.toFront();
    }

    /***
     * Fetches weather for selected country and displays in gui.
     * @param country is selected country.
     * @throws IOException io exception
     * @throws InterruptedException
     * @author Kasper.
     */
    public void forecast(String country) throws IOException, InterruptedException {
        weatherAPI.setInformation(this,country);
    }

    /***
     * Opens and closes weather menu in gui.
     * @author Kasper.
     */
    public void weatherButton() {
        weatherAPI.weatherMenu(this);
    }


    /***
     * Starts Pong game.
     * @author Kasper.
     */
    public void playPong(){
        Pong pong = new Pong();
        try { Stage primary = new Stage(); pong.start(primary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Starts Geo Quiz game.
     * @author Kasper.
     */
    public void playGeoQuiz(){
        Geography geography = new Geography();
        try { Stage primary = new Stage(); geography.start(primary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /***
     * Starts Music Quiz game.
     * @author Kasper.
     */
    public void playQuiz(){
        MPlayer mPlayer = new MPlayer();
        try { Stage primary = new Stage(); mPlayer.start(primary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Starts Piano game.
     * @author Kasper.
     */
    public void playPiano(){
        Piano piano = new Piano();
        try { Stage primary = new Stage(); piano.start(primary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Starts 2048 game.
     * @author Sossio.
     */
    public void play2048(){
        Game2048Main game2048Main = new Game2048Main();
        try { Stage primary = new Stage(); game2048Main.start(primary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold desc="============= Dashboard event and update">

    /**
     * User dashboard event handler take care of events in dashboard class.
     * @param e e event is passed here from GFX and will be passed to new class
     * @author Habib
     */
    public void userDashboardEventHandler(ActionEvent e) throws ParseException {
        userEvent.userDashboardEventHandler(e,this);
    }

    /**
     * separated method to use multiple times
     * it will update the historic table in user dashboard everytime an action happen or user want to navigate to the panel etc.
     * @author Habib
     */
    public void updateDashboardInfo(){
        ArrayList<UserHistory> list = db.searchDataForTableHistory(Integer.parseInt(user.getUserId()), null , false);
        history_items_list = FXCollections.observableArrayList(list);
        userControl.fillMyTicket(list);
        history_tableview.setItems(history_items_list);
    }

    //</editor-fold>

    //<editor-fold desc="============= Login methods + system sound">

    /**
     * Login operation (show password )
     * @param e event
     * @author Khabib.
     */
    public void showPassword(ActionEvent e){
        password.showPassword(e,this);
    }

    /**
     * The method calls from login page, and it will redirect to password class to make password visible for user.
     * @author Khabib.
     */
    public void showPassFieldLogin(){
        password.showPassFieldLogin(this);
    }

    /**
     * the method will play the background music in application.
     * @param soundName name of file/music.
     * @param src file/music path.
     * @author Sossio.
     */
    public void playSystemSound(String soundName, String src) {
        systemSound.playSystemSound(soundName, src);
    }

    /**
     * The method will register the user and return to login page.
     * @param e event.
     * @author Khabib.
     */
    public void registerUserButton(ActionEvent e) {
        registrationUser.registerUserBtnAction(e,this);
    }

    //</editor-fold>

    //<editor-fold desc="============= Ticket purchase + seat">

    /**
     * The method take a list of flights and fill up in the flight list in the application.
     * @param flights a list of flights from database
     * @author Khabib
     */
    public void fetchFlights(ArrayList <Flight> flights) {
        flightsViewManager.fetchFlights(flights);
    }

    /***
     * Executes an asynchronous task to fill flights from DB.
     * @param country is the country to fetch flights from.
     * @Author Kasper.
     */
    public void prepareFlightList(String country) {
        flightsViewManager.fillFlights(country, db);
    }


    /**
     * The method validate and confirm the tickets purchase.
     * @param rfc reference number to ticket.
     * @author Khabib.
     */
    public void confirmPurchase(String rfc) {
        purchaseHandler.confirmPurchase(rfc,this);
    }

    //</editor-fold>

    //<editor-fold desc="============= Profile">

    /**
     * This method edits profile information to later save in the database.
     * @throws SQLException if any error occurs.
     * @author Kasper. Huge modified and developed by Sossio.
     */
    public void editProfile() throws SQLException {
        profileManager.editProfile(this);
    }


    /**
     * This method cancels editing profile.
     * @throws SQLException if any error occurs.
     * @author Sossio.
     */
    public void editProfileCancel() throws SQLException {
        profileManager.editProfileCancel(this);
    }


    /**
     * Shows available profile images using a grid.
     * @author Kasper.
     */
    public void changeProfileImage() {
        profileManager.changeImage(this);
    }


    /**
     * Edits the profile picture of a user.
     * @param event event.
     * @author Kasper. Developed by Sossio.
     */
    public void profileImageClickGrid(MouseEvent event) {
        profileManager.clickGrid(event,this);
    }


    /***
     * Plays or pauses music on dashboard.
     * @param e event.
     * @author Kasper.
     */
    public void mediaHandler(ActionEvent e) {
        bgMusic.mediaHandler(e);
    }

    //</editor-fold>

    //<editor-fold desc="============= Search">


    /**
     * The method search flights based on filters and date.
     * @param e event.
     * @author Khabib.
     */
    public void advanceSearch(ActionEvent e) {
        search.advanceSearch();
    }


    /**
     * The method handle the checkbox for return trip.
     * @param e event.
     * @author Khabib
     */
    public void checkboxEvent(ActionEvent e){
        search.checkboxEvent(e);
    }



    /**
     * The method called from GUI on enter and mouse clicked.
     * @author Khabib.
     */
    public void searchHit(){
        search.searchHit();
    }


    /**
     * The method change value of departure with destination.
     * @author Khabib.
     */
    public void changeSearchInfo(){
        if (from_input_flight_textfield != null || display_input_flights != null ){
            String from = from_input_flight_textfield.getText();
            String to = display_input_flights.getText();
            from_input_flight_textfield.setText(to);
            display_input_flights.setText(from);
        }else {
            errorHandler.displayMessage(err_advanced_search, "Search fields are empty!", true);
        }

    }

    /**
     * The method validate and control search recommendation on key pressed.
     * @author Khabib.
     */
    public void searchAlternativeSuggestion(){
        if (search_f_name != null){
            ObservableList<String> searchAprear = FXCollections.observableList(propareSearchTerm(search_f_name.getText().toLowerCase()));
            if (!searchAprear.isEmpty()){
                if (search_list_suggestion != null){
                    search_list_suggestion.getItems().removeAll();
                }
                search_list_suggestion.setVisible(true);
                search_list_suggestion.setItems(searchAprear);
                search_list_suggestion.getSelectionModel().selectedItemProperty().addListener(e ->{
                    search_f_name.setText(search_list_suggestion.getSelectionModel().getSelectedItem());
                        search_list_suggestion.setVisible(false);
            });
            }
        }

        hidePopupSearch(search_f_name.getText(), search_list_suggestion);
    }

    /**
     * The method handle the departure search recommendation.
     * @author Khabib.
     */
    public void departureSuggestions(){
        if (from_input_flight_textfield != null){
            ObservableList<String> searchAprear = FXCollections.observableList(propareSearchTerm(from_input_flight_textfield.getText().toLowerCase()));
            System.out.println("Typed ===== " + from_input_flight_textfield.getText());
            if (!searchAprear.isEmpty()){
                System.out.println(searchAprear.get(0) + " appeared search");
                if (search_list_appear_second != null){
                    search_list_appear_second.getItems().removeAll();
                }
                search_list_appear_second.setVisible(true);
                search_list_appear_second.setItems(searchAprear);
                search_list_appear_second.getSelectionModel().selectedItemProperty().addListener(e ->{
                    from_input_flight_textfield.setText(search_list_appear_second.getSelectionModel().getSelectedItem());
                        search_list_appear_second.setVisible(false);
            });
            }
        }

        hidePopupSearch(from_input_flight_textfield.getText(), search_list_appear_second);
    }

    /**
     * The method handle the destination search recommendation.
     * @author Khabib.
     */
    public void destinationSuggestions(){
        if (display_input_flights != null){
            ObservableList<String> searchAprear = FXCollections.observableList(propareSearchTerm(display_input_flights.getText().toLowerCase()));
            if (!searchAprear.isEmpty()){
                if (search_list_appear_third != null){
                    search_list_appear_third.getItems().removeAll();
                }
                search_list_appear_third.setVisible(true);
                search_list_appear_third.setItems(searchAprear);
                search_list_appear_third.getSelectionModel().selectedItemProperty().addListener(e ->{
                    display_input_flights.setText(search_list_appear_third.getSelectionModel().getSelectedItem());
                        search_list_appear_third.setVisible(false);
            });
            }
        }

        hidePopupSearch(display_input_flights.getText(), search_list_appear_third);
    }

    /**
     * The method hide/visible the popup search recommendation on search key.
     * @param text search term.
     * @param popupWindow javaFX element : ListView
     * @author Khabib and Sossio.
     */
    public void hidePopupSearch(String text, ListView popupWindow) {
        if(text == "") {
            popupWindow.setVisible(false);
        }
    }

    /**
     * The method validate search key.
     * @param searchkey search key.
     * @return list of country name.
     * @author Khabib
     */
    private ArrayList<String> propareSearchTerm(String searchkey){
        ArrayList<String> obs;
        if (searchkey.length() > 1){
            String searchTarget = searchkey.substring(0, 1).toUpperCase() + searchkey.substring(1); // convert first character to Uppercase
            obs = compareSearchKey(searchTarget);
        }else {
            String searchTarget = searchkey.toUpperCase();
            obs = compareSearchKey(searchTarget);
        }
        return obs;
    }

    /**
     * The method compare searched key and recommend a country name based on country list.
     * @param searchTargetKey searched key.
     * @return list of names.
     * @author Khabib.
     */
    private ArrayList<String> compareSearchKey(String searchTargetKey) {
        ArrayList<String> obs  = new ArrayList<>();
        for (Enum item : CountryList.values()) {
            if (item.toString().contains(searchTargetKey)){
                obs.add(item.toString());
                System.out.println(item + " /" + searchTargetKey);
            }
        }
        return obs;
    }

    /**
     * Resets the text in text-field from-to in advanced search - user dashboard.
     * @author Sossio.
     */
    public void resetSearchFromTo() {
        flightsViewManager.resetSearchFromTo();
    }

    /**
     * Resets the text in text-field country name.
     * @author Sossio.
     */
    public void resetSearchCountry() {
        flightsViewManager.resetSearchCountry();
    }

    //</editor-fold>

    //<editor-fold desc="============= Support">
    /**
     * This method redirect us to support class which handle support events.
     * @param e events
     * @author Habib
     */
    public void support_event_handler(ActionEvent e){
        support.supportInfo(e);
    }

    //</editor-fold>

    //<editor-fold desc="============= History">


    /**
     * #move
     * The method fill information into user history table in user dashboard.
     * @author Habib.
     */
    public void setInfoIntoTableHistorik(){ // the method calls from user dashboard to load everything.
        history_single_delete_button = (Button) root.lookup("#history_single_delete_button");
        detailes_btn_histroy = (Button) root.lookup("#detailes_btn_histroy");
        history_display_flight_path_button = (Button) root.lookup("#history_display_flight_path_button");
        history_multiple_delete_button = (Button) root.lookup("#history_multiple_delete_button");
        history_tableview = (TableView<UserHistory>) root.lookup("#history_tableview");
        history_select_all_checkbox = (CheckBox) root.lookup("#history_select_all_checkbox");
        history_tableview.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("no_col_table_historik"));
        history_tableview.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("company_col_table_historik"));
        history_tableview.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model_col_table_historik"));
        history_tableview.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("rfc_col_table_historik"));
        history_tableview.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("flightid_col_table_historik"));
        history_tableview.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("from_col_table_historik"));
        history_tableview.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("to_col_table_historik"));
        history_tableview.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("seatno_col_table_historik"));
        history_tableview.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("date_col_table_historik"));
        history_tableview.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("price_col_table_historik"));
        history_tableview.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("select_col_table_historik"));
        history_tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        history_tableview.getSelectionModel().selectedItemProperty().addListener((ObservableList, oldValue, newValue) ->{
            if (newValue != null){
                if (newValue.getSelect_col_table_historik().isSelected()){
                    newValue.getSelect_col_table_historik().setSelected(false);
                    history_display_flight_path_button.setDisable(true);
                    history_single_delete_button.setDisable(true);
                    detailes_btn_histroy.setDisable(true);
                    rfc_smp_history.setText(null);
                }if (!newValue.getSelect_col_table_historik().isSelected()){
                newValue.getSelect_col_table_historik().setSelected(true);
                history_display_flight_path_button.setDisable(false);
                history_single_delete_button.setDisable(false);
                detailes_btn_histroy.setDisable(false);
                //rfc = newValue.getRfc_col_table_historik();
                rfc_smp_history.setText(newValue.getRfc_col_table_historik());
                System.out.println("true");
                }
                if (rfc_smp_history == null){
                    System.out.println("Rfc is null");
                    history_display_flight_path_button.setDisable(false);
                    history_single_delete_button.setDisable(false);
                    detailes_btn_histroy.setDisable(false);
                }
            }
        });
        updateDashboardInfo();
        history_select_all_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                history_flights = history_tableview.getItems();
                boolean selectedAllItems = false; // selected or not

                for (UserHistory item : history_flights){
                    if (history_select_all_checkbox.isSelected()){
                        selectedAllItems = true;
                        item.getSelect_col_table_historik().setSelected(true);
                    }else {
                        selectedAllItems = false;
                        item.getSelect_col_table_historik().setSelected(false);
                    }
                }

                if (selectedAllItems){ // check if all items are selected
                    history_multiple_delete_button.setDisable(false);
                }else {
                    history_multiple_delete_button.setDisable(true);
                    System.out.println("no content in the list");
                }

            }
        });

    }

    /**
     * This method display the actual ticket in "My Ticket" window for checking or further more functions that a user can do with it's ticket.
     * @author Habib
     */
    public void pickTicketForDetailes(){
        System.out.println("rfc: " + rfc_smp_history.getText());
        ArrayList<UserHistory> list = db.searchDataForTableHistory( -1, rfc_smp_history.getText(), false);
        if (list.size() >0){
            userControl.fillMyTicket(list);
            my_ticket_anchorpane.toFront();
        }
    }


    /**
     * #move
     * The method will handle delete option in history panel.
     * @param e event
     * @author Habib.
     */
    public void userRemoveHistory(ActionEvent e){
        if (history_tableview.getItems().size() > 0){ // check if there is any items before running the operation.
            if (e.getSource() == history_single_delete_button){ // if single remove button clicked
                history_flights = history_tableview.getItems(); // get the whole tables items into an observable list to compare.
                if (history_flights != null){ // if observable items has item
                    // show a confirmation message to user
                    boolean confirmed = errorHandler.confirmThisAction("Confirm to delete selected item", "Do you want to proceed?", "The selected items will be deleted!");
                    if (confirmed){ // if user confirm the action
                        for (UserHistory item: history_flights){ // loop through all historic items
                            if (item.getSelect_col_table_historik().isSelected()){ // check if the checkbox for one or more item is selected
                                boolean ok = db.deleteHistoryByRFC(item.getRfc_col_table_historik()); // send the actual reference number as an argument to database to compare and delete
                                if (ok){ // if database succeed to delete the item runs this statement
                                    updateDashboardInfo(); // historic table updates
                                    System.out.println("Item has been deleted successfully!"); // show a success message for user
                                }
                            }
                        }
                    }else { // if user not confirm the action
                        System.out.println("not deleted screen message");
                    }
                }
            }else if(e.getSource() == history_multiple_delete_button){ // if remove all button clicked
                history_flights = history_tableview.getItems(); // get the whole tables items into an observable list to compare.
                if (history_flights != null){ // if observable items has item
                    // show a confirmation message to user
                    boolean confirmed = errorHandler.confirmThisAction("Confirm to delete the item", "Do you want to proceed?", history_flights.size() +" items will be deleted.");
                    if (confirmed){ // if user confirm the action
                        for (UserHistory item: history_flights){ // loop through all historic items
                            boolean ok = db.deleteHistoryByRFC(item.getRfc_col_table_historik()); // send the actual reference number as an argument to database to compare and delete
                            if (ok){ // if database succeed to delete the item runs this statement
                                updateDashboardInfo(); // historic table updates
                                System.out.println("Item has been deleted successfully!"); // show a success message for user
                            }
                        }
                    }else {
                        System.out.println("The action is canceled! screen message"); // canceled message to user
                    }
                }

            }

        }
    }


    //</editor-fold>

    // ADMIN PAGE
    //<editor-fold desc="************* ADMIN METHODS *************"

    /**
     * The method navigate to admin dashboard.
     * @param e event.
     * @throws IOException javaIO exception.
     * @author Obed.
     */
    public void switchToAdminView(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin/AdminView.fxml")));
        adminControl.switchToAdminView(e, this);
    }

    /**
     * The method handle admin events.
     * @param e event.
     * @throws SQLException sql exception.
     * @author Obed.
     */
    public void adminDashboardEventHandler(ActionEvent e) throws SQLException {
        adminEvent.adminDashboardEventHandler(e,this);
    }


    /**
     * The method register a user from admin page
     * @param e event
     * @throws SQLException exception
     * @author Obed
     */
    public void registerUserAdminBtnAction(ActionEvent e) throws SQLException {
        registerAdmin.registerUserAdminBtnAction(e,this);
    }


    /**
     * #move
     * This method deletes member from database if delete-button is selected.
     * @param e event.
     * @throws SQLException sql exception.
     * @author Obed.
     */
    public void removeMember_admin(ActionEvent e) throws SQLException {
        if (table_member_admin.getItems().size() > 0) { // check if there is any items before running the operation.
            if (e.getSource() == deletS_btn_mbr_admin) { // if single remove button clicked
                items_member_admin = table_member_admin.getItems(); // get the whole tables items into an observable list to compare.
                if (items_member_admin != null) { // if observable items has item
                    // show a confirmation message to user
                    boolean confirmed = errorHandler.confirmThisAction("Confirm to delete selected item", "Do you want to proceed?", "The selected items will be deleted!");
                    if (confirmed) { // if user confirm the action
                        for (User item : items_member_admin) { // loop through all historic items
                            if (item.getBox().isSelected()) { // check if the checkbox for one or more item is selected
                                boolean ok = db.deleteMember(item.getUserId()); // send the actual reference number as an argument to database to compare and delete
                                if (ok) { // if database succeed to delete the item runs this statement
                                    adminControl.updateMemberTable(); // historic table updates
                                    System.out.println("Item has been deleted successfully!"); // show a success message for user
                                }
                            }
                        }
                    } else { // if user not confirm the action
                        System.out.println("not deleted screen message");
                    }
                }
            }
        }
    }

    /**
     * The method delete a ticket by admin.
     * @param e event.
     * @throws SQLException sql exception.
     * @author Obed.
     */
    public void removeTicket_admin(ActionEvent e) throws SQLException {
         if (table_tickets.getItems().size() > 0) { // check if there is any items before running the operation.
               if (e.getSource() == deleteTicketBtn_ticket_admin) { // if single remove button clicked
                   items_ticket_admin = table_tickets.getItems(); // get the whole tables items into an observable list to compare.
                   if (items_ticket_admin != null) { // if observable items has item
                       // show a confirmation message to user
                       boolean confirmed = errorHandler.confirmThisAction("Confirm to delete selected item", "Do you want to proceed?", "The selected items will be deleted!");
                       if (confirmed) { // if user confirm the action
                           for (UserHistory item : items_ticket_admin) { // loop through all historic items
                               if (item.getSelect_col_table_historik().isSelected()) { // check if the checkbox for one or more item is selected
                                   boolean ok = db.deleteTicket(item.getRfc_col_table_historik()); // send the actual reference number as an argument to database to compare and delete
                                   if (ok) { // if database succeed to delete the item runs this statement
                                       adminControl.updateTicketTabel(); // historic table updates
                                       System.out.println("Item has been deleted successfully!"); // show a success message for user
                                   }
                               }
                           }
                       } else { // if user not confirm the action
                           System.out.println("not deleted screen message");
                       }
                   }
               }

           }
       }
    /**
     * The method delete a flight by admin.
     * @param e event.
     * @throws SQLException sql exception.
     * @author Obed.
     */
    public void removeFlight_admin(ActionEvent e) throws SQLException {
       if (table_flight_admin.getItems().size() > 0) { // check if there is any items before running the operation.
            if (e.getSource() == delete_singelFlightBtn_admin) { // if single remove button clicked
                items_flight_admin = table_flight_admin.getItems(); // get the whole tables items into an observable list to compare.
                if (items_flight_admin != null) { // if observable items has item
                    // show a confirmation message to user
                    boolean confirmed = errorHandler.confirmThisAction("Confirm to delete selected item", "Do you want to proceed?", "The selected items will be deleted!");
                    if (confirmed) { // if user confirm the action
                        for (Flight item : items_flight_admin) { // loop through all historic items
                            if (item.getFlightBox().isSelected()) { // check if the checkbox for one or more item is selected
                                boolean ok = db.deleteFlight(item.getId()); // send the actual reference number as an argument to database to compare and delete
                                if (ok) { // if database succeed to delete the item runs this statement
                                    adminControl.updateFlightTable(); // historic table updates
                                    System.out.println("Item has been deleted successfully!"); // show a success message for user
                                }
                            }
                        }
                    } else { // if user not confirm the action
                        System.out.println("not deleted screen message");
                    }
                }
            }
        }
    }

    //</editor-fold>
}
