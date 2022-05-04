package application.model;

public class Coordinates {
    public double[] convert(String s){
        double[] coordinates = new double[2];
        switch(s){
            case "Andorra": coordinates = setCoordinates(0,0); break;
            case "United_Arab_Emirates": coordinates = setCoordinates(0,0); break;
            case "Afghanistan": coordinates = setCoordinates(0,0); break;
            case "Antigua_And_Barbuda": coordinates = setCoordinates(0,0); break;
            case "Anguilla": coordinates = setCoordinates(0,0); break;
            case "Albania": coordinates = setCoordinates(0,0); break;
            case "Armenia": coordinates = setCoordinates(400,210); break;
            case "Angola":coordinates = setCoordinates(340,320); break;
            case "Argentina":coordinates = setCoordinates(200,380); break;
            case "American_Samoa": coordinates = setCoordinates(0,0); break;
            case "Austria": coordinates = setCoordinates(340,205); break;
            case "Australia": coordinates = setCoordinates(550,340); break;
            case "Aruba": coordinates = setCoordinates(0,0); break;
            case "Åland": coordinates = setCoordinates(0,0); break;
            case "Azerbaijan": coordinates = setCoordinates(400,210); break;
            case "Bosnia_And_Herzegovina": coordinates = setCoordinates(350,210); break;
            case "Barbados": coordinates = setCoordinates(0,0); break;
            case "Bangladesh":coordinates = setCoordinates(0,0); break;
            case "Belgium": coordinates = setCoordinates(325,184); break;
            case "Burkina_Faso": coordinates = setCoordinates(310,270); break;
            case "Bulgaria": coordinates = setCoordinates(360,160); break;
            case "Bahrain": coordinates = setCoordinates(0,0); break;
            case "Burundi": coordinates = setCoordinates(370,300); break;
            case "Benin": coordinates = setCoordinates(320,275); break;
            case "Saint_Barthelemy": coordinates = setCoordinates(0,0); break;
            case "Brunei_Darussalam": coordinates = setCoordinates(0,0); break;
            case "Bolivia": coordinates = setCoordinates(200,320); break;
            case "Bermuda": coordinates = setCoordinates(0,0); break;
            case "Bonaire": coordinates = setCoordinates(0,0); break;
            case "Brazil": coordinates = setCoordinates(230,308); break;
            case "Bahamas":coordinates = setCoordinates(180,280); break;
            case "Bhutan": coordinates = setCoordinates(0,0); break;
            case "Bouvet_Island": coordinates = setCoordinates(0,0); break;
            case "Botswana": coordinates = setCoordinates(360,320); break;
            case "Belarus": coordinates = setCoordinates(360,200); break;
            case "Belize": coordinates = setCoordinates(0,0); break;
            case "Canada": coordinates = setCoordinates(130,160); break;
            case "Cocos_Islands": coordinates = setCoordinates(0,0); break;
            case "Democratic_Republic_of_the_Congo": coordinates = setCoordinates(350,300); break;
            case "Central_African_Republic": coordinates = setCoordinates(350,275); break;
            case "Congo_Republic": coordinates = setCoordinates(350,300); break;
            case "Switzerland": coordinates = setCoordinates(330,200); break;
            case "Cote_D’Ivoire": coordinates = setCoordinates(300,275); break;
            case "Cook_Islands": coordinates = setCoordinates(0,0); break;
            case "Chile": coordinates = setCoordinates(0,0); break;
            case "Cameroon": coordinates = setCoordinates(340,275); break;
            case "China": coordinates = setCoordinates(500,220); break;
            case "Colombia": coordinates = setCoordinates(180,300); break;
            case "Costa_Rica": coordinates = setCoordinates(0,0); break;
            case "Cuba": coordinates = setCoordinates(180,280); break;
            case "Cape_Verde": coordinates = setCoordinates(0,0); break;
            case "Curaçao": coordinates = setCoordinates(0,0); break;
            case "Christmas Island": coordinates = setCoordinates(0,0); break;
            case "Cyprus": coordinates = setCoordinates(0,0); break;
            case "Czech Republic": coordinates = setCoordinates(0,0); break;
            case "Germany": coordinates = setCoordinates(333,180); break;
            case "Djibouti": coordinates = setCoordinates(0,0); break;
            case "Denmark": coordinates = setCoordinates(0,0); break;
            case "Dominica": coordinates = setCoordinates(0,0); break;
            case "Dominican_Republic": coordinates = setCoordinates(180,280); break;
            case "Algeria": coordinates = setCoordinates(320,250); break;
            case "Ecuador": coordinates = setCoordinates(180,320); break;
            case "Egypt": coordinates = setCoordinates(370,240); break;
            case "Estonia": coordinates = setCoordinates(360,190); break;
            case "Western_Sahara": coordinates = setCoordinates(290,230); break;
            case "Eritrea": coordinates = setCoordinates(0,0); break;
            case "Spain": coordinates = setCoordinates(310,210); break;
            case "Ethiopia": coordinates = setCoordinates(380,280); break;
            case "Finland": coordinates = setCoordinates(0,0); break;
            case "Fiji": coordinates = setCoordinates(0,0); break;
            case "Falkland_Islands": coordinates = setCoordinates(0,0); break;
            case "Micronesia": coordinates = setCoordinates(0,0); break;
            case "Faroe_Islands": coordinates = setCoordinates(0,0); break;
            case "France": coordinates = setCoordinates(320,215); break;
            case "United_Kingdom": coordinates = setCoordinates(315,180); break;
            case "Georgia": coordinates = setCoordinates(400,210); break;
            case "Grenada": coordinates = setCoordinates(0,0); break;
            case "French_Guiana": coordinates = setCoordinates(215,280); break;
            case "Guernsey": coordinates = setCoordinates(0,0); break;
            case "Ghana": coordinates = setCoordinates(320,275); break;
            case "Gibraltar": coordinates = setCoordinates(0,0); break;
            case "Greenland": coordinates = setCoordinates(200,80); break;
            case "Gambia": coordinates = setCoordinates(0,0); break;
            case "Guinea": coordinates = setCoordinates(0,0); break;
            case "Gabon": coordinates = setCoordinates(340,300); break;
            case "Guadeloupe": coordinates = setCoordinates(0,0); break;
            case "Equatorial_Guinea": coordinates = setCoordinates(0,0); break;
            case "Greece": coordinates = setCoordinates(360,220); break;
            case "South_Georgia_Island": coordinates = setCoordinates(0,0); break;
            case "Guatemala": coordinates = setCoordinates(0,0); break;
            case "Guam": coordinates = setCoordinates(0,0); break;
            case "Guinea-Bissau": coordinates = setCoordinates(0,0); break;
            case "Guyana": coordinates = setCoordinates(215,280); break;
            case "Hong_Kong": coordinates = setCoordinates(0,0); break;
            case "Heard_And_Mc_Donald_Islands": coordinates = setCoordinates(0,0); break;
            case "Honduras": coordinates = setCoordinates(0,0); break;
            case "Croatia": coordinates = setCoordinates(0,0); break;
            case "Haiti": coordinates = setCoordinates(180,280); break;
            case "Hungary": coordinates = setCoordinates(350,200); break;
            case "Indonesia": coordinates = setCoordinates(500,290); break;
            case "Ireland": coordinates = setCoordinates(300,180); break;
            case "Israel": coordinates = setCoordinates(380,230); break;
            case "Isle_of_Man": coordinates = setCoordinates(0,0); break;
            case "India": coordinates = setCoordinates(0,0); break;
            case "British_Indian_Ocean_Territory": coordinates = setCoordinates(0,0); break;
            case "Iraq": coordinates = setCoordinates(395,210); break;
            case "Iran": coordinates = setCoordinates(400,210); break;
            case "Iceland": coordinates = setCoordinates(280,160); break;
            case "Italy": coordinates = setCoordinates(340,210); break;
            case "Jersey": coordinates = setCoordinates(0,0); break;
            case "Jamaica": coordinates = setCoordinates(180,280); break;
            case "Jordan": coordinates = setCoordinates(380,230); break;
            case "Japan": coordinates = setCoordinates(0,0); break;
            case "Juan_de_Nova_Island": coordinates = setCoordinates(0,0); break;
            case "Kenya": coordinates = setCoordinates(380,290); break;
            case "Kyrgyzstan": coordinates = setCoordinates(0,0); break;
            case "Cambodia": coordinates = setCoordinates(0,0); break;
            case "Kiribati": coordinates = setCoordinates(0,0); break;
            case "Comoros": coordinates = setCoordinates(0,0); break;
            case "Saint_Kitts_And_Nevis": coordinates = setCoordinates(0,0); break;
            case "North Korea": coordinates = setCoordinates(0,0); break;
            case "South Korea": coordinates = setCoordinates(0,0); break;
            case "Kosovo": coordinates = setCoordinates(350,210); break;
            case "Kuwait": coordinates = setCoordinates(0,0); break;
            case "Cayman_Islands": coordinates = setCoordinates(0,0); break;
            case "Kazakhstan": coordinates = setCoordinates(490,190); break;
            case "Laos": coordinates = setCoordinates(500,250); break;
            case "Lebanon": coordinates = setCoordinates(0,0); break;
            case "Saint_Lucia": coordinates = setCoordinates(0,0); break;
            case "Liechtenstein": coordinates = setCoordinates(0,0); break;
            case "Sri_Lanka": coordinates = setCoordinates(0,0); break;
            case "Liberia": coordinates = setCoordinates(300,275); break;
            case "Lesotho": coordinates = setCoordinates(370,340); break;
            case "Lithuania": coordinates = setCoordinates(360,190); break;
            case "Luxembourg": coordinates = setCoordinates(0,0); break;
            case "Latvia": coordinates = setCoordinates(360,190); break;
            case "Libyan": coordinates = setCoordinates(340,240); break;
            case "Libanon": coordinates = setCoordinates(380,230); break;
            case "Morocco": coordinates = setCoordinates(300,230); break;
            case "Monaco": coordinates = setCoordinates(0,0); break;
            case "Moldova": coordinates = setCoordinates(360,160); break;
            case "Madagascar": coordinates = setCoordinates(400,320); break;
            case "Montenegro": coordinates = setCoordinates(0,0); break;
            case "Saint_Martin": coordinates = setCoordinates(0,0); break;
            case "Marshall_Islands": coordinates = setCoordinates(0,0); break;
            case "Macedonia": coordinates = setCoordinates(350,210); break;
            case "Mali": coordinates = setCoordinates(320,250); break;
            case "Macau": coordinates = setCoordinates(0,0); break;
            case "Myanmar": coordinates = setCoordinates(0,0); break;
            case "Mongolia": coordinates = setCoordinates(0,0); break;
            case "Northern_Mariana_Islands": coordinates = setCoordinates(0,0); break;
            case "Martinique": coordinates = setCoordinates(0,0); break;
            case "Mauritania": coordinates = setCoordinates(300,250); break;
            case "Montserrat": coordinates = setCoordinates(0,0); break;
            case "Malta": coordinates = setCoordinates(0,0); break;
            case "Mauritius": coordinates = setCoordinates(0,0); break;
            case "Maldives": coordinates = setCoordinates(0,0); break;
            case "Malawi": coordinates = setCoordinates(0,0); break;
            case "Mexico": coordinates = setCoordinates(0,0); break;
            case "Malaysia": s = "Malaysia"; break;
            case "Mozambique": coordinates = setCoordinates(380,320); break;
            case "Namibia": coordinates = setCoordinates(340,330); break;
            case "New_Caledonia": s = "New_Caledonia"; break;
            case "Niger": coordinates = setCoordinates(330,260); break;
            case "Norfolk_Island": coordinates = setCoordinates(0,0); break;
            case "Nigeria": coordinates = setCoordinates(330,275); break;
            case "Nicaragua": coordinates = setCoordinates(0,0); break;
            case "Netherlands": coordinates = setCoordinates(330,180); break;
            case "Norway": coordinates = setCoordinates(330,150); break;
            case "Nepal": coordinates = setCoordinates(0,0); break;
            case "Nauru": coordinates = setCoordinates(0,0); break;
            case "Niue": coordinates = setCoordinates(0,0); break;
            case "New_Zealand": coordinates = setCoordinates(0,0); break;
            case "Oman": coordinates = setCoordinates(0,0); break;
            case "Panama": coordinates = setCoordinates(0,0); break;
            case "Peru": coordinates = setCoordinates(180,320); break;
            case "French_Polynesia": coordinates = setCoordinates(0,0); break;
            case "Papua_New_Guinea": coordinates = setCoordinates(0,0); break;
            case "Philippines": coordinates = setCoordinates(0,0); break;
            case "Pakistan": coordinates = setCoordinates(0,0); break;
            case "Poland": coordinates = setCoordinates(350,180); break;
            case "St_Pierre_And_Miquelon": coordinates = setCoordinates(0,0); break;
            case "Pitcairn": coordinates = setCoordinates(0,0); break;
            case "Puerto_Rico": coordinates = setCoordinates(0,0); break;
            case "Palestine": coordinates = setCoordinates(380,230); break;
            case "Portugal": coordinates = setCoordinates(300,210); break;
            case "Palau": coordinates = setCoordinates(0,0); break;
            case "Paraguay": coordinates = setCoordinates(210,320); break;
            case "Qatar": coordinates = setCoordinates(0,0); break;
            case "Reunion": coordinates = setCoordinates(0,0); break;
            case "Romania": coordinates = setCoordinates(360,160); break;
            case "Serbia": coordinates = setCoordinates(355,200); break;
            case "Russia": coordinates = setCoordinates(400,180); break;
            case "Rwanda": coordinates = setCoordinates(370,300); break;
            case "Saudi_Arabia": coordinates = setCoordinates(400,250); break;
            case "Solomon_Islands": coordinates = setCoordinates(0,0); break;
            case "Seychelles": coordinates = setCoordinates(0,0); break;
            case "Sudan": coordinates = setCoordinates(370,260); break;
            case "Sweden": coordinates = setCoordinates(340,150); break;
            case "Singapore": coordinates = setCoordinates(0,0); break;
            case "St_Helena": coordinates = setCoordinates(0,0); break;
            case "Slovenia": coordinates = setCoordinates(0,0); break;
            case "Svalbard": coordinates = setCoordinates(340,80); break;
            case "Slovakia": coordinates = setCoordinates(350,200); break;
            case "Sierra_Leone": coordinates = setCoordinates(300,275); break;
            case "San_Marino": coordinates = setCoordinates(0,0); break;
            case "Senegal": coordinates = setCoordinates(290,260); break;
            case "Somalia": coordinates = setCoordinates(395,280); break;
            case "Suriname": coordinates = setCoordinates(215,280); break;
            case "South_Sudan": coordinates = setCoordinates(365,280); break;
            case "Sao_Tome_And_Principe": coordinates = setCoordinates(0,0); break;
            case "El_Salvador": coordinates = setCoordinates(0,0); break;
            case "Sint Maarten": coordinates = setCoordinates(0,0); break;
            case "Syria": coordinates = setCoordinates(0,0); break;
            case "Swaziland": coordinates = setCoordinates(0,0); break;
            case "Turks_And_Caicos_Islands": coordinates = setCoordinates(0,0); break;
            case "Chard": coordinates = setCoordinates(0,0); break;
            case "French_Southern_and_Antarctic_Lands": coordinates = setCoordinates(0,0); break;
            case "Togo": coordinates = setCoordinates(320,275); break;
            case "Thailand": coordinates = setCoordinates(0,0); break;
            case "Tajikistan": coordinates = setCoordinates(0,0); break;
            case "Tokelau": coordinates = setCoordinates(0,0); break;
            case "Timor_Leste": coordinates = setCoordinates(0,0); break;
            case "Turkmenistan": coordinates = setCoordinates(0,0); break;
            case "Tunisia": coordinates = setCoordinates(0,0); break;
            case "Tonga": coordinates = setCoordinates(0,0); break;
            case "Turkey": coordinates = setCoordinates(370,220); break;
            case "Trinidad_And_Tobago": coordinates = setCoordinates(0,0); break;
            case "Tuvalu": coordinates = setCoordinates(0,0); break;
            case "Taiwan": coordinates = setCoordinates(0,0); break;
            case "Tanzania": coordinates = setCoordinates(380,300); break;
            case "Uganda": coordinates = setCoordinates(370,280); break;
            case "Ukraine": coordinates = setCoordinates(370,205); break;
            case "Jarvis_Island": coordinates = setCoordinates(0,0); break;
            case "Baker_Island": coordinates = setCoordinates(0,0); break;
            case "Howland_Island": coordinates = setCoordinates(0,0); break;
            case "Johnston_Atoll": coordinates = setCoordinates(0,0); break;
            case "Midway_Islands": coordinates = setCoordinates(0,0); break;
            case "United_States_Virgin_Islands": coordinates = setCoordinates(0,0); break;
            case "United_States_of_America": coordinates = setCoordinates(130,200); break;
            case "Uruguay": coordinates = setCoordinates(215,350); break;
            case "Uzbekistan": coordinates = setCoordinates(0,0); break;
            case "Vatikan_City": coordinates = setCoordinates(0,0); break;
            case "Saint_Vincent": coordinates = setCoordinates(0,0); break;
            case "Venezuela": coordinates = setCoordinates(200,280); break;
            case "VirginIslands_BR": coordinates = setCoordinates(0,0); break;
            case "Virgin Islands_US": coordinates = setCoordinates(0,0); break;
            case "Vietnam": coordinates = setCoordinates(0,0); break;
            case "Vanuatu": coordinates = setCoordinates(0,0); break;
            case "Wallis_And_Futuna_Islands": coordinates = setCoordinates(0,0); break;
            case "Samoa": coordinates = setCoordinates(0,0); break;
            case "Yemen": coordinates = setCoordinates(395,260); break;
            case "Mayotte": coordinates = setCoordinates(0,0); break;
            case "South_Africa": coordinates = setCoordinates(360,340); break;
            case "Zambia": coordinates = setCoordinates(360,320); break;
            case "ZE": coordinates = setCoordinates(0,0); break;
        }
        return coordinates;
    }

    private double[] setCoordinates(double i, double i1) {
        double coordinates[] = new double[2];
        if (i == 0 || i1 == 0){
            coordinates = null;
        } else {
            coordinates[0] = i;
            coordinates[1] = i1;
        }
        return coordinates;
    }
}