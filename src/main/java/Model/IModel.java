package Model;

import algorithms.mazeGenerators.Maze;
import algorithms.search.AState;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;

public interface IModel {

    public void generateRandomMaze(int row, int col);

    public Maze getMaze();
    public void updatecharecterlocation(KeyEvent keyEvent);
    public int getColcharecter();
    public int getRowcharecter();
    public void assighnObserver(Observer o);
    public void solvemaze(Maze maze);
    public String getStrstr();
    public void setStrstr(String strstr);
    public void solsol(Maze maze);
    //public ArrayList<AState> solveProblem(ISearchable domain, ISearchingAlgorithm searcher);
    public ArrayList<AState> getSolution();
    public void setSolution(ArrayList<AState> solution);
    public  void strat();
    public  void stop();
    public void Savingthemaze() throws IOException;
}
