package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    private String name;
    private Queue<AState> queue;
    private int numofnodesevaluated;

    public BreadthFirstSearch() {
        super();
        this.name = "BreadthFirstSearch";
        this.queue = null;
        numofnodesevaluated = 0;
    }



    @Override
    public AState search(ISearchable s) {
        if(s==null){
            return null;
        }
        ISearchable mazetosearch = (ISearchable) s;
        int numofcoll = mazetosearch.getMaze().getnumofcolumns();
        int numofroww = mazetosearch.getMaze().getnumofrows();
        int startcol = ( mazetosearch.getMaze().getStartPosition().getColumnIndex());
        int startrow = (mazetosearch.getMaze().getStartPosition().getRowIndex());
        int finishcoll = (mazetosearch.getMaze().getGoalPosition().getColumnIndex());
        int finishroww = (mazetosearch.getMaze().getGoalPosition().getRowIndex());

        if (mazetosearch.getMaze().getvalue(startrow,startcol) != 0 ||
                mazetosearch.getMaze().getvalue(finishroww,finishcoll) != 0){ // check if the start state or the goal state are illigal

            return null;

        }

        boolean [][]visited = new boolean[mazetosearch.getMaze().getnumofrows()][mazetosearch.getMaze().getnumofcolumns()];// to not visit the same state more than once
        visited[startrow][startcol] = true;
        queue = new LinkedList<AState>();
        queue.add(mazetosearch.getstartstate());
        numofnodesevaluated++;
        if (mazetosearch.getstartstate().getNumofcolumn() == finishcoll && mazetosearch.getstartstate().getNumofrow() == finishroww){// check if we reached the goal state
            return mazetosearch.getstartstate();
        }

        while (!queue.isEmpty())
        {
            AState pt = queue.poll(); // if we dont reached the goal state then we removed this state and adding the all possiblestates
            if (pt.getNumofcolumn() == finishcoll && pt.getNumofrow() == finishroww){// check if we reached the goal state
                return pt;

            }

            int length = mazetosearch.getAllPossibleStates(pt).size();
            ArrayList<AState> newarraymaze = mazetosearch.getAllPossibleStates(pt);
            for (int i = 0; i < length; i++)
            {
                int row = newarraymaze.get(i).getNumofrow();
                int col = newarraymaze.get(i).getNumofcolumn();
                newarraymaze.get(i).setCamefrom(pt);// save the parent of this state

                if (mazetosearch.legalposition(row,col) &&
                        mazetosearch.getMaze().getvalue(row,col) == 0 &&
                        !visited[row][col])
                {
                    visited[row][col] = true;
                    numofnodesevaluated++; // all visited states
                    queue.add(newarraymaze.get(i));

                }
            }
        }
        return null;


    }




    @Override
    public String getName() {
        return this.name;
    }



    @Override
    public int getNumberOfNodesEvaluated() {
        return numofnodesevaluated;
    }
}
