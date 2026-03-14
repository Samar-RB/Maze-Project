package algorithms.search;
import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;
public interface ISearchable {

    AState getstartstate();

    AState getgoalstate();

    ArrayList<AState> getAllPossibleStates(AState s);

    public Maze getMaze();

    public boolean legalposition(int numofrow, int numofcol);





}
