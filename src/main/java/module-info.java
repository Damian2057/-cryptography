module Cryptography {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens crypto.project.GUI;
    exports crypto.project.GUI;

    opens crypto.project.GUI.MainMenu;
    exports crypto.project.GUI.MainMenu;
    exports crypto.project.Model.crypto;
    opens crypto.project.Model.crypto;

    opens crypto.project.Model.castTypes;
    exports crypto.project.Model.castTypes;
}