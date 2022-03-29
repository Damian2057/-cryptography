package crypto.project.GUI.MainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Error {

    public Button closeButton;

    public void show() throws IOException {
        Stage error = new Stage();
        error.setResizable(false);
        error.setTitle("Error");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getResource("Error.fxml")));
        Scene scene = new Scene(root);
        error.setResizable(false);
        error.setScene(scene);
        error.show();
    }


    public void onClose(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
