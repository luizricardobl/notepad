package com.example.lab10;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class myNotePadController {

    private FileChooser fileChooser = new FileChooser();

    @FXML
    private TextArea textArea;

    @FXML
    //Method to close the app:
    private void exitApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    //Method to clear the text area:
    private void newFile(ActionEvent event) {
        textArea.clear();
        Stage stage = (Stage) textArea.getScene().getWindow();
        stage.setTitle("Untitled - My Notepad");
    }

    @FXML
    //Method to open a file selected by the user:
    private void openFile(ActionEvent event) {
        File file = fileChooser.showSaveDialog(null);
        if (file != null){
            textArea.clear();
            Stage stage = (Stage) textArea.getScene().getWindow();
            stage.setTitle(file.getName() + " - My Notepad");
            BufferedReader buff = null;
            try{
                String currentLine;
                buff = new BufferedReader(new FileReader(file));
                while((currentLine = buff.readLine()) != null){
                    textArea.appendText(currentLine + "\n");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void saveAs(ActionEvent event){
        File myFile = fileChooser.showSaveDialog(null);
        String taContent = textArea.getText();
        if(myFile != null){
            Stage stage = (Stage) textArea.getScene().getWindow();
            stage.setTitle(myFile.getName() + " - Notepad");
            try{
                if(!myFile.exists()){
                    myFile.createNewFile();
                }
                FileWriter file = new FileWriter(myFile.getAbsoluteFile() + ".txt");
                BufferedWriter buff = new BufferedWriter(file);
                buff.write(taContent);
                buff.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}