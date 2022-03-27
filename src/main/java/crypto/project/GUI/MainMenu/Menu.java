package crypto.project.GUI.MainMenu;

import crypto.project.Model.castTypes.TypeConverter;
import crypto.project.Model.castTypes.XorFunction;
import crypto.project.Model.crypto.DesX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class Menu implements Initializable {

    @FXML
    public TextArea normalText = new TextArea();
    public TextArea codedText = new TextArea();
    public Button onGenerate;
    public TextField normalfileField = new TextField();
    public Button normalFileButton = new Button();
    public RadioButton fileicon = new RadioButton();
    public RadioButton texticon = new RadioButton();
    public TextArea keyText2;
    public TextArea keyText3;
    public TextArea keyText1;
    public Button SaveButton1 = new Button();
    public Text saveText = new Text();
    public Text chooseText = new Text();
    public TextField fileExtenson = new TextField();

    private File normalFile;
    private Stage normalFileStage;

    private Stage saveFileStage;

    private byte[] textAreaInByteForm;
    private byte[] fileInByteForm;

    public void onCode(ActionEvent actionEvent) {
        DesX desX = new DesX();

        textAreaInByteForm = TypeConverter.stringToByteTab(normalText.getText());

        byte[] firstXorKey = TypeConverter.stringToByteTab(keyText1.getText());
        byte[] desKey = TypeConverter.stringToByteTab(keyText2.getText());
        byte[] secondXorKey = TypeConverter.stringToByteTab(keyText3.getText());
        byte[] a = desX.codeText(textAreaInByteForm,firstXorKey,desKey,secondXorKey);

        byte[] xd = desX.codeText(a,firstXorKey,desKey,secondXorKey);
        codedText.setText(TypeConverter.byteTabToString(xd));
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

        normalText.setWrapText(true);
        codedText.setWrapText(true);

        texticon.setSelected(true);

        normalfileField.setVisible(false);
        normalFileButton.setVisible(false);
        SaveButton1.setVisible(false);
        saveText.setVisible(false);
        chooseText.setVisible(false);
        fileExtenson.setVisible(false);
    }

    private String generateKey(int length) throws UnsupportedEncodingException {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private void genFirstKey() throws UnsupportedEncodingException {
        keyText1.setText(generateKey(8));
    }

    private void genSecondKey() throws UnsupportedEncodingException {
        keyText2.setText(generateKey(7));
    }

    private void genThirdKey() throws UnsupportedEncodingException {
        keyText3.setText(generateKey(8));
    }

    public void onGenerate(ActionEvent actionEvent) throws UnsupportedEncodingException {
        genFirstKey();
        genSecondKey();
        genThirdKey();
    }

    public void onFIleChooose(ActionEvent actionEvent) {
        fileicon.setSelected(true);
        texticon.setSelected(false);

        normalfileField.setVisible(true);
        normalFileButton.setVisible(true);
        SaveButton1.setVisible(true);
        saveText.setVisible(true);
        chooseText.setVisible(true);
        fileExtenson.setVisible(true);
    }

    public void onTextChoose(ActionEvent actionEvent) {
        texticon.setSelected(true);
        fileicon.setSelected(false);

        normalfileField.setVisible(false);
        normalFileButton.setVisible(false);
        SaveButton1.setVisible(false);
        saveText.setVisible(false);
        chooseText.setVisible(false);
        fileExtenson.setVisible(false);
    }

    public void onNormalFileChoose(ActionEvent actionEvent) {
        try {
            normalFileStage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open normal File");
            normalFile = fileChooser.showOpenDialog(normalFileStage);
            normalfileField.setText(normalFile.getAbsolutePath());
            fileInByteForm = Files.readAllBytes(Path.of(normalFile.getAbsolutePath()));
            String s = new String(fileInByteForm, System.getProperty("file.encoding"));
            normalText.setText(s);
        } catch (Exception ignored) {
        }
    }

    public void onSaveFileChoose(ActionEvent actionEvent) throws IOException {
        try {
            saveFileStage = new Stage();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Save file");
            String directory = "";
            directory = directoryChooser.showDialog(saveFileStage).toString();
            try (FileOutputStream fos = new FileOutputStream(directory+"\\"+fileExtenson.getText())) {
                fos.write(fileInByteForm);
                fos.close();
                fileExtenson.clear();
            } catch (Exception ignored) {

            }
        } catch (Exception ignored) {

        }


    }
}
