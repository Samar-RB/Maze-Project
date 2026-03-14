package com.example.last_version;

import Model.IModel;
import Model.MyModel;
import ViewModel.MyViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class numofrowsandcolsController {

    @FXML
    public TextField textField_mazeRows;
    @FXML
    public TextField textField_mazeColumns;
    public MyViewModel viewmodel;

    public static int numofrows;
    public static int numofcols;

    public void generatefirstmaze(ActionEvent actionEvent) throws IOException {
        numofrows = Integer.parseInt(textField_mazeRows.getText());
        numofcols = Integer.parseInt(textField_mazeColumns.getText());

        if (numofrows <= 2 || numofcols <= 2) {
            showAlert("The numbers are incorrect, please enter different numbers");
            return;
        }

        // צור את המודל וה-ViewModel
        IModel model = new MyModel();
        MyViewModel viewmodel = new MyViewModel(model);
        this.viewmodel = viewmodel; // נשמור כדי לסגור שרתים ב-ExitClick

        // טען את המסך של MyView.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyView.fxml"));
        Parent root = fxmlLoader.load();

        // חבר את ה-ViewModel לקונטרולר של המסך החדש
        MyViewController view = fxmlLoader.getController();
        view.setViewmodel(viewmodel);
        viewmodel.addObserver(view);

        // פתח את החלון
        Stage stage = new Stage();
        stage.setTitle("MyView!");
        Scene s = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(s);
        stage.show();

        // הגדרת סגירה בטוחה
        stage.setOnCloseRequest(event -> {
            event.consume();
            ExitClick(stage);
        });
    }

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }

    public void ExitClick(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit");
        alert.setContentText("Are you sure you want to exit?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            if (viewmodel != null) {
                viewmodel.stop2(); // Stop servers
            }
            stage.close();
            Platform.exit();
        }
    }
}
