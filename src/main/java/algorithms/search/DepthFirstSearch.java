package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{

    private String name;
    private Stack<AState> stack;
    private int numofnodes;


    public DepthFirstSearch() {
        super();
        this.name = "DepthFirstSearch";
        this.stack = null;
        numofnodes = 0;

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
                mazetosearch.getMaze().getvalue(finishroww,finishcoll) != 0){// check if the start state or the goal state are illigal
            return null;

        }
        boolean [][]visited = new boolean[mazetosearch.getMaze().getnumofrows()][mazetosearch.getMaze().getnumofcolumns()];// to not visit the same state more than once
        visited[startrow][startcol] = true;
        stack = new Stack<AState>(); // we used a stack to implement this algorithm
        stack.push(mazetosearch.getstartstate()); // Put the start node in the stack
        numofnodes++;
        if (mazetosearch.getstartstate().getNumofcolumn() == finishcoll && mazetosearch.getstartstate().getNumofrow() == finishroww) {// check if this state is the goal state
            return mazetosearch.getstartstate();
        }
        while (!stack.empty())
        {
            AState curstate = stack.pop();// we take and removed the state in the top of the stack

            if (curstate.getNumofcolumn() == finishcoll && curstate.getNumofrow() == finishroww){// check if we reached the goal state
                return curstate;
            }

            int length = mazetosearch.getAllPossibleStates(curstate).size();
            ArrayList<AState> newarraymaze = mazetosearch.getAllPossibleStates(curstate); // take the naiphors of this state
            for (int i = 0; i < length; i++)
            {
                int row = newarraymaze.get(i).getNumofrow();
                int col = newarraymaze.get(i).getNumofcolumn();
                newarraymaze.get(i).setCamefrom(curstate);

                if (mazetosearch.legalposition(row,col) &&
                        mazetosearch.getMaze().getvalue(row,col) == 0 &&
                        !visited[row][col])
                {
                    visited[row][col] = true;
                    numofnodes++;
                    stack.push(newarraymaze.get(i));

                }


            }


        }

        return null;
    }



    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numofnodes;
    }


}
