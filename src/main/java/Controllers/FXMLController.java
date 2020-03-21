package Controllers;

import App.FileLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

import java.io.FileWriter;
import java.io.IOException;

public class FXMLController {
    private String savename = "";
    private FileLoader fileLoader;

    @FXML
    Button btnSaveFile = new Button(), btnOpenFile = new Button();
    @FXML
    HTMLEditor HTMLEditor = new HTMLEditor();
    @FXML
    TextField filename = new TextField(), version = new TextField();
    @FXML
    Label labInfo = new Label();

    public void initialize(){
        //endrer filnavn etterhvert som det blir endret
        filename.textProperty().addListener(observable -> {
            savename = filename.getText() + "-v" + version.getText() + ".html";
        });
        version.textProperty().addListener(observable -> {
            savename = filename.getText() + "-v" + version.getText() + ".html";
        });

        //bare tall i versjon
        version.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if(!newValue.matches("\\d|\\d{2}|\\d{3}|\\d{4}|^$")) version.setText(oldValue);
        });
    }

    public void saveFile() throws IOException {
        if(filename.getText().isEmpty() || version.getText().isEmpty())
            labInfo.setText("Fyll inn filnavn og versjon!");
        else {
            try {
                FileWriter file = new FileWriter("htmlFiles/" + savename);
                file.append(HTMLEditor.getHtmlText());
                file.close();
                labInfo.setText("Filen " + savename + " ble lagret.");
                System.out.println("Filen " + savename + " ble lagret.");
            } catch (Exception e) {
                labInfo.setText("Feil!");
                System.out.println("Feil!");
                e.printStackTrace();
            }
        }
    }

    public void enableGUI(boolean bool){
        if (bool){
            btnOpenFile.setDisable(true);
            btnSaveFile.setDisable(true);
            filename.setDisable(true);
            version.setDisable(true);
            HTMLEditor.setDisable(true);
        }else {
            btnOpenFile.setDisable(false);
            btnSaveFile.setDisable(false);
            filename.setDisable(false);
            version.setDisable(false);
            HTMLEditor.setDisable(false);
        }
    }

    public void openFile(){
        if(filename.getText().isEmpty() || version.getText().isEmpty())
            labInfo.setText("Fyll inn filnavn og versjon!");
        else{
            fileLoader = new FileLoader("htmlFiles/" + savename);
            //hvis filen blir funnet
            fileLoader.setOnSucceeded(event -> {
                HTMLEditor.setHtmlText(fileLoader.getValue());
                enableGUI(false);
                labInfo.setText("Filen " + savename + " er lastet inn.");
                System.out.println("Filen " + savename + " er lastet inn.");
            });
            //om filen ikke blir funnet
            fileLoader.setOnFailed(event -> {
                try {
                    throw event.getSource().getException();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                btnOpenFile.setDisable(false);
                btnSaveFile.setDisable(false);
                labInfo.setText("Fant ikke angitt fil.");
                System.out.println("Fant ikke angitt fil.");
            });

            Thread thread = new Thread(fileLoader);
            thread.setDaemon(true);
            enableGUI(true);
            thread.start();
        }
    }
}
