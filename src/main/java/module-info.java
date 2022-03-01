module Cryptography {
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    //requires org.apache.logging.log4j;
    //requires org.slf4j;

    opens crypto.project.GUI;
    exports crypto.project.GUI;

    opens crypto.project.Model;
    exports crypto.project.Model;
}