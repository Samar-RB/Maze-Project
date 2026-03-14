package com.example.last_version;

import View.MazeDisplayer;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.Maze;
import algorithms.search.AState;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MyViewController implements Initializable, Observer {
    private MediaPlayer mediaplayerBackground;
    private boolean flagToMusicBackground = false;
    public javafx.scene.control.Button music;
    @FXML
    private Label welcomeText;
    private MyViewModel viewmodel;
    private String str=null;
    @FXML
    public MazeDisplayer mazeDisplayer;
    @FXML
    public Label lbl_player_row;
    @FXML
    public Label lbl_player_column;
    private Maze maze;
    private ArrayList<AState> solutionfromviewmodel;
    public ArrayList<AState> getSolutionfromviewmodel() {
        return solutionfromviewmodel;
    }

    public void setSolutionfromviewmodel(ArrayList<AState> solutionfromviewmodel) {
        this.solutionfromviewmodel = solutionfromviewmodel;
    }

    StringProperty update_player_position_row = new SimpleStringProperty();
    StringProperty update_player_position_col = new SimpleStringProperty();

    public String get_update_player_position_row() {
        return update_player_position_row.get();
    }

    public void set_update_player_position_row(String update_player_position_row) {
        this.update_player_position_row.set(update_player_position_row);
    }

    public String get_update_player_position_col() {
        return update_player_position_col.get();
    }

    public void set_update_player_position_col(String update_player_position_col) {
        this.update_player_position_col.set(update_player_position_col);
    }

    public void StopMusicBackground() {
        if(flagToMusicBackground == false){
            mediaplayerBackground.play();
            flagToMusicBackground = true;
        } else {
            flagToMusicBackground =  false;
            mediaplayerBackground.stop();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_player_row.textProperty().bind(update_player_position_row);
        lbl_player_column.textProperty().bind(update_player_position_col);

        // בקשה לקבלת focus כדי שהקלט מהמקשים יעבוד
        Platform.runLater(() -> mazeDisplayer.requestFocus());
    }

    public void NewClick(ActionEvent actionEvent) {
        int rows = numofrowsandcolsController.numofrows;
        int cols = numofrowsandcolsController.numofcols;
        viewmodel.generatemaze(rows,cols);
    }

    public void SaveeClick(ActionEvent actionEvent) throws IOException {
        viewmodel.Savemaze();
    }

    public void LoadClick(ActionEvent actionEvent) {
    }

    public void HelpClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader FXMLLoader  = new FXMLLoader(getClass().getResource("Help.fxml"));
        Parent root = (Parent)FXMLLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Help...");
        Scene s = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(s);
        stage.show();
    }

    public void OptionsClick(ActionEvent actionEvent) {
    }

    public void ExitClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Your about to logout");
        alert.setContentText("Are you sure to exit?");
        if (alert.showAndWait().get() == ButtonType.OK){
            if(viewmodel!=null){
                viewmodel.stop2(); //stop servers
            }
            Platform.exit();
        }
    }

    public void AboutClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader FXMLLoader  = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent root = (Parent)FXMLLoader.load();
        Stage stage = new Stage();
        stage.setTitle("About...");
        Scene s = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(s);
        stage.show();
    }

    public void solvemaze(ActionEvent actionEvent) {
        viewmodel.solvemaze(this.maze);
    }

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }

    public void showingmaze(ActionEvent actionEvent) {
        int rows = numofrowsandcolsController.numofrows;
        int cols = numofrowsandcolsController.numofcols;
        viewmodel.generatemaze(rows,cols);
    }

    public void setViewmodel(MyViewModel viewmodel) {
        this.viewmodel = viewmodel;
    }

    public void musiconof(ActionEvent actionEvent) {
    }

    public void PropertiesClick(@NotNull ActionEvent actionEvent) throws IOException {
        FXMLLoader FXMLLoader  = new FXMLLoader(getClass().getResource("Proprrties.fxml"));
        Parent root = (Parent)FXMLLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Properties...");
        Scene s = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(s);
        stage.show();
    }

    public void keyPressed(KeyEvent keyEvent) {
        viewmodel.movecharecter(keyEvent);
        keyEvent.consume();
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        mazeDisplayer.requestFocus();
    }

    public void update(Observable o, Object arg) {
        if(o instanceof MyViewModel){
            if(viewmodel.getStr2().equals("generatingmaze")){
                this.maze = viewmodel.getMaze();
                set_update_player_position_row(this.maze.getStartPosition().getRowIndex()+"");
                set_update_player_position_col(this.maze.getStartPosition().getColumnIndex()+"");
                this.mazeDisplayer.setDrawedsolution(null);
                drawMaze();
                return;
            }
            if(viewmodel.getStr2().equals("solvingmaze")){
                ArrayList<AState> arraysol = viewmodel.getsolution();
                this.mazeDisplayer.setSolutiondis(arraysol);
                this.mazeDisplayer.setDrawedsolution("yes");
                this.mazeDisplayer.draw();
                return;
            }
            if(viewmodel.getStr2().equals("movingcharecter")){
                this.mazeDisplayer.setStrst("movingcharecteraftersolving");
                int rowfromvewmodel = this.viewmodel.getRowchar();
                int colfromvewmodel = this.viewmodel.getColchar();
                set_update_player_position_row(rowfromvewmodel + "");
                set_update_player_position_col(colfromvewmodel + "");
                this.maze.getStartPosition().setRow(rowfromvewmodel);
                this.maze.getStartPosition().setColumn(colfromvewmodel);
                this.mazeDisplayer.set_player_position(rowfromvewmodel,colfromvewmodel);
                this.mazeDisplayer.draw();
                return;
            }
        }
    }

    public void drawMaze(){
        mazeDisplayer.drawMaze(maze);
    }

    public void generateMaze(ActionEvent actionEvent) {
    }

    public void solveMaze(ActionEvent actionEvent) {
    }
}
