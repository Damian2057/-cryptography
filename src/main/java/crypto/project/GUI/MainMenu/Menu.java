package crypto.project.GUI.MainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class Menu implements Initializable {

    @FXML
    public TextArea normalText = new TextArea();
    public TextArea codedText = new TextArea();
    public Button onGenerate;
    public TextField normalfileField = new TextField();
    public TextField codedfileField = new TextField();
    public Button normalFileButton = new Button();
    public Button codedFileButton = new Button();
    public RadioButton fileicon = new RadioButton();
    public RadioButton texticon = new RadioButton();
    public TextArea keyText2;
    public TextArea keyText3;
    public TextArea keyText1;
    public TextField normalfieldSave = new TextField();
    public TextField codedfieldSave = new TextField();
    public Button SaveButton1 = new Button();
    public Button SaveButton2 = new Button();

    private File normalFile;
    private File codedFile;

    private Stage normalFileStage;
    private Stage codedFileStage;

    public void onCode(ActionEvent actionEvent) {
    }

    public void onDeCode(ActionEvent actionEvent) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        texticon.setSelected(true);
        normalfileField.setEditable(false);
        codedfileField.setEditable(false);

        normalFileButton.setDisable(true);
        codedFileButton.setDisable(true);

        SaveButton1.setDisable(true);
        SaveButton2.setDisable(true);
    }

    private void genFirstKey() {
        byte[] array = new byte[64]; // 32 key
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        keyText1.setText(generatedString);
    }

    private void genSecondKey() {
        byte[] array = new byte[56]; // 32 key
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        keyText2.setText(generatedString);
    }

    private void genThirdKey() {
        byte[] array = new byte[64]; // 32 key
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        keyText3.setText(generatedString);
    }

    public void onGenerate(ActionEvent actionEvent) {
        genFirstKey();
        genSecondKey();
        genThirdKey();
    }

    public void onFIleChooose(ActionEvent actionEvent) {
        fileicon.setSelected(true);
        texticon.setSelected(false);

        normalFileButton.setDisable(false);
        codedFileButton.setDisable(false);

        SaveButton1.setDisable(false);
        SaveButton2.setDisable(false);
    }

    public void onTextChoose(ActionEvent actionEvent) {
        texticon.setSelected(true);
        fileicon.setSelected(false);

        normalFileButton.setDisable(true);
        codedFileButton.setDisable(true);

        SaveButton1.setDisable(true);
        SaveButton2.setDisable(true);
    }

    public void onNormalFileChoose(ActionEvent actionEvent) {
        try {
            normalFileStage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open normal File");
            normalFile = fileChooser.showOpenDialog(normalFileStage);
            normalfileField.setText(normalFile.getAbsolutePath());
            //to normalText.setText(bit array)
        } catch (Exception ignored) {
        }
    }

    public void onNormalCodedChoose(ActionEvent actionEvent) {
        try {
            codedFileStage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open coded File");
            codedFile = fileChooser.showOpenDialog(codedFileStage);
            codedfileField.setText(codedFile.getAbsolutePath());
            //to codedText.setText(bit array)
        } catch (Exception ignored) {
        }
    }

    private DirectoryChooser directoryChooser = new DirectoryChooser();

    public void onSaveFileChoose(ActionEvent actionEvent) throws IOException {
    }

    public void onSaveCodedChoose(ActionEvent actionEvent) throws IOException {
    }
}
