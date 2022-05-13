package application.samples;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This is a test class to move a scene.
 * @author Khabib.
 */
public class MoveScreen {
    private static double xOffset =0;
    private static double yOffset =0;

    /**
     * Class constructor.
     * @param root ir root.
     * @param stage is parent stage.
     */
    public static void moveScreen(Parent root, Stage stage){
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }
}
