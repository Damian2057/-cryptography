package crypto.project.GUI.MainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class Menu implements Initializable {
    public TextArea normalText;
    public TextArea codedText;
    public TextArea keyText;
    public Button onGenerate;
    public ComboBox comboBox = new ComboBox();

    public void onCode(ActionEvent actionEvent) {
    }

    public void show() throws IOException {
        Stage menuStage = new Stage();
        menuStage.setResizable(false);
        menuStage.setTitle("Crypto");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getResource("MainMenu.fxml")));
        Scene scene = new Scene(root);
        menuStage.setResizable(false);
        menuStage.setScene(scene);
        menuStage.show();
    }

    public void onDeCode(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onGenerate(ActionEvent actionEvent) {
        byte[] array = new byte[8]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        keyText.setText(generatedString);
    }
}
