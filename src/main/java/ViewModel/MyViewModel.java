package ViewModel;

import Model.IModel;
import algorithms.mazeGenerators.Maze;
import algorithms.search.AState;
import algorithms.search.Solution;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MyViewModel extends Observable implements Observer {
    private IModel model;
    private Maze maze;
    private int rowchar;
    private int colchar;
    private int numofrows;
    private int numofcolumns;
    private String str2;
    private ArrayList<AState> solutionfrommodel;
    private Solution solution;

    public int getNumofrows() {
        return numofrows;
    }

    public void stop2(){
        model.stop();
    }
    public void setNumofrows(int numofrows) {
        this.numofrows = numofrows;
    }

    public int getNumofcolumns() {
        return numofcolumns;
    }

    public void setNumofcolumns(int numofcolumns) {
        this.numofcolumns = numofcolumns;
    }

    public ArrayList<AState> getSolutionfrommodel() {
        return solutionfrommodel;
    }

    public void setSolutionfrommodel(ArrayList<AState> solutionfrommodel) {
        this.solutionfrommodel = solutionfrommodel;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public Maze getMaze() {
        return maze;
    }

    public int getRowchar() {
        return rowchar;
    }

    public int getColchar() {
        return colchar;
    }

    public MyViewModel(IModel model) {
        this.model = model;
        this.model.assighnObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {


        if(o instanceof IModel){
            if(model.getStrstr().equals("generatemaze")){//generate maze
                str2 = "generatingmaze";
                this.maze = model.getMaze();
                rowchar = this.maze.getStartPosition().getRowIndex();
                rowchar = this.maze.getStartPosition().getColumnIndex();
                setChanged();
                notifyObservers();
                return;
            }
            if(model.getStrstr().equals("solvemaze")){
                str2 = "solvingmaze";
                setSolutionfrommodel(model.getSolution());
                setChanged();
                notifyObservers();
                return;
            }
            if(model.getStrstr().equals("movingcharecter")){
                str2="movingcharecter";
                this.rowchar=model.getRowcharecter();
                this.colchar =model.getColcharecter();
                setChanged();
                notifyObservers();
                return;

            }
        }


    }

    public void generatemaze(int row,int col){
        this.model.generateRandomMaze(row,col);
    }

    public void movecharecter(KeyEvent keyEvent){
        model.updatecharecterlocation(keyEvent);

    }

    public void solvemaze(Maze maze){
        this.model.solvemaze(maze);
    }

    public ArrayList<AState> getsolution(){
        return this.solutionfrommodel;
    }

    public void Savemaze() throws IOException {
        model.Savingthemaze();
    }
}
