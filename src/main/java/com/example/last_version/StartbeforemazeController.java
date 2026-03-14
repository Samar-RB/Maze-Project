package com.example.last_version;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartbeforemazeController {
    public void StartPlaying(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("numofrowsandcols.fxml"));
        Parent root = fxmloader.load();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Welcome To Our World!");
        stage.setScene(scene);
        stage.show();
    }
}
