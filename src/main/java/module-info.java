module Cryptography {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    //requires org.apache.logging.log4j;
    //requires org.slf4j;

    opens crypto.project.GUI;
    exports crypto.project.GUI;

    opens crypto.project.GUI.MainMenu;
    exports crypto.project.GUI.MainMenu;
    exports crypto.project.Model.crypto;
    opens crypto.project.Model.crypto;

    opens crypto.project.Model.casttypes;
    exports crypto.project.Model.casttypes;

}