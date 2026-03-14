package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{

    private Maze maze;
    private MazeState mazestart;
    private MazeState mazefinish;

    public SearchableMaze(Maze maze) {
        if(maze==null){
            return;
        }
        this.maze = maze;
        this.mazestart = new MazeState (maze.getStartPosition().getRowIndex(),maze.getStartPosition().getColumnIndex());
        this.mazefinish = new MazeState (maze.getGoalPosition().getRowIndex(),maze.getGoalPosition().getColumnIndex());
    }

    @Override
    public AState getstartstate() {
        return mazestart;
    }

    @Override
    public AState getgoalstate() {
        return mazefinish;
    }

    public Maze getMaze() {
        return maze;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {// added diagonals and not diagnols
        if(s==null){
            return null;
        }
        ArrayList<AState> possiblestates = new ArrayList<AState>(); //array to save the states
        MazeState news = (MazeState) s;
        int numofcolumn = ((MazeState) s).getNumofcolumn();
        int numofrow = ((MazeState) s).getNumofrow();
        if (legalposition(numofrow - 1, numofcolumn)) {
            if (maze.getvalue(numofrow - 1, numofcolumn) == 0) {
                MazeState newmazestate = new MazeState(numofrow - 1, numofcolumn);
                if(newmazestate.getCost() ==0 ){
                    newmazestate.setCost(10);
                }
                possiblestates.add(newmazestate);
            }
        }

        if(legalposition(numofrow-1, numofcolumn+1)){
            if(maze.getvalue(numofrow-1,numofcolumn+1)==0){
                if((maze.getvalue(numofrow-1 , numofcolumn) == 0 ) || maze.getvalue(numofrow , numofcolumn+1) == 0) {// check if there is a normal path
                    MazeState newmazestate = new MazeState(numofrow-1,numofcolumn+1);
                    if(newmazestate.getCost() ==0 ){
                        newmazestate.setCost(15);
                    }
                    possiblestates.add(newmazestate);

                }

            }
        }

        if (legalposition(numofrow, numofcolumn + 1)) {
            if (maze.getvalue(numofrow, numofcolumn + 1) == 0) {
                MazeState newmazestate = new MazeState(numofrow, numofcolumn + 1);
                if(newmazestate.getCost() ==0 ){
                    newmazestate.setCost(10);
                }
                possiblestates.add(newmazestate);
            }
        }

        if(legalposition(numofrow+1, numofcolumn+1)){
            if(maze.getvalue(numofrow+1,numofcolumn+1)==0){
                if((maze.getvalue(numofrow , numofcolumn+1) == 0 ) || maze.getvalue(numofrow + 1, numofcolumn) == 0) {// check if there is a normal path
                    MazeState newmazestate = new MazeState(numofrow+1,numofcolumn+1 );
                    if(newmazestate.getCost() ==0 ){
                        newmazestate.setCost(15);
                    }
                    possiblestates.add(newmazestate);


                }

            }
        }

        if (legalposition(numofrow + 1, numofcolumn)) {
            if (maze.getvalue(numofrow + 1, numofcolumn) == 0) {
                MazeState newmazestate = new MazeState(numofrow + 1, numofcolumn);
                if(newmazestate.getCost() ==0 ){
                    newmazestate.setCost(10);
                }
                possiblestates.add(newmazestate);
            }
        }

        if(legalposition(numofrow+1, numofcolumn-1)){
            if(maze.getvalue(numofrow+1,numofcolumn-1)==0){
                if((maze.getvalue(numofrow , numofcolumn-1) == 0 ) || maze.getvalue(numofrow + 1, numofcolumn) == 0) {// check if there is a normal path
                    MazeState newmazestate = new MazeState(numofrow+1,numofcolumn-1);
                    if(newmazestate.getCost() ==0 ){
                        newmazestate.setCost(15);
                    }
                    possiblestates.add(newmazestate);

                }

            }
        }


        if (legalposition(numofrow, numofcolumn - 1)) {
            if (maze.getvalue(numofrow, numofcolumn - 1) == 0) {
                MazeState newmazestate = new MazeState(numofrow, numofcolumn - 1);
                if(newmazestate.getCost() ==0 ){
                    newmazestate.setCost(10);
                }
                possiblestates.add(newmazestate);
            }
        }

        if(legalposition(numofrow-1, numofcolumn-1)){
            if(maze.getvalue(numofrow-1,numofcolumn-1)==0){
                if((maze.getvalue(numofrow-1 , numofcolumn) == 0 ) || maze.getvalue(numofrow , numofcolumn-1) == 0) {// check if there is a normal path
                    MazeState newmazestate = new MazeState(numofrow-1,numofcolumn-1);
                    if(newmazestate.getCost() ==0 ){
                        newmazestate.setCost(15);
                    }
                    possiblestates.add(newmazestate);

                }

            }
        }

        return possiblestates;
    }

    public boolean legalposition(int numofrow, int numofcol){
        if(numofrow>= maze.getnumofrows() || numofrow<0 || numofcol >= maze.getnumofcolumns() || numofcol <0 ){
            return false;
        }
        else{
            return true;
        }
    }


}
