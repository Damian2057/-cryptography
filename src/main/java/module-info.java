module Cryptography {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens crypto.project.GUI;
    exports crypto.project.GUI;

    opens crypto.project.GUI.MainMenu;
    exports crypto.project.GUI.MainMenu;

    opens crypto.project.Model.castTypes;
    exports crypto.project.Model.castTypes;
    exports crypto.project.Model.crypto.algorithm;
    opens crypto.project.Model.crypto.algorithm;
    exports crypto.project.Model.functional;
    opens crypto.project.Model.functional;
    exports crypto.project.Model.crypto.component;
    opens crypto.project.Model.crypto.component;
}