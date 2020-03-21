package Controllers;

import App.FileLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
        filename.textProperty().addListener(observable -> {
            savename = filename.getText() + version.getText() + ".html";
        });
        version.textProperty().addListener(observable -> {
            savename = filename.getText() + version.getText() + ".html";
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

    public void openFile(){
        if(filename.getText().isEmpty() || version.getText().isEmpty())
            labInfo.setText("Fyll inn filnavn og versjon!");
        else{
            try {
                fileLoader = new FileLoader("htmlFiles/" + savename);
                fileLoader.setOnSucceeded(event -> {
                    HTMLEditor.setHtmlText(fileLoader.getValue());
                    btnOpenFile.setDisable(false);
                    btnSaveFile.setDisable(false);
                    labInfo.setText("Filen " + savename + " er lastet inn.");
                    System.out.println("Filen " + savename + " er lastet inn.");
                });
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
                btnOpenFile.setDisable(true);
                btnSaveFile.setDisable(true);
                thread.start();

            }catch (Exception e){
                labInfo.setText("Feil!");
                System.out.println("Feil!");
                e.printStackTrace();
            }
        }
    }
}
