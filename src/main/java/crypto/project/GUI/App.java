package crypto.project.GUI;

import crypto.project.GUI.MainMenu.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;


public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Locale.setDefault(new Locale("eng","ENG"));
        Menu menu = new Menu();
        menu.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
