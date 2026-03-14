package algorithms.search;

import java.util.*;

public class BestFirstSearch extends ASearchingAlgorithm {

    private String name;
    private PriorityQueue<AState> Pqueue;// we use PriorityQueue to priority cost
    private int numofnodes;

    public BestFirstSearch() {
        super();
        this.name = "BestFirstSearch";
        this.Pqueue = null;
        numofnodes = 0;
    }



    public int equals(AState o1, AState o2) {
        if(o1.getCost() > o2.getCost()){//we prefer the diagnol first
            return 1;
        }
        if(o1.getCost() < o2.getCost()){
            return -1;
        }
        if(o1.getCost() == o2.getCost()){
            return 0;
        }
        return 0;
    }

    @Override
    public AState search(ISearchable s) {
        if(s==null){
            return null;
        }
        ISearchable mazetosearch = (ISearchable) s;
        int numofcoll = mazetosearch.getMaze().getnumofcolumns();
        int numofroww = mazetosearch.getMaze().getnumofrows();
        int startcol = (mazetosearch.getMaze().getStartPosition().getColumnIndex());
        int startrow = (mazetosearch.getMaze().getStartPosition().getRowIndex());
        int finishcoll = (mazetosearch.getMaze().getGoalPosition().getColumnIndex());
        int finishroww = (mazetosearch.getMaze().getGoalPosition().getRowIndex());
        if (mazetosearch.getMaze().getvalue(startrow, startcol) != 0 ||
                mazetosearch.getMaze().getvalue(finishroww, finishcoll) != 0) {// check if the start state or the goal state are illigal
            return null;

        }

        boolean[][] visited = new boolean[mazetosearch.getMaze().getnumofrows()][mazetosearch.getMaze().getnumofcolumns()];// to not visit the same state more than once
        visited[startrow][startcol] = true;
        Pqueue = new PriorityQueue<AState>(this::equals);
        Pqueue.add(mazetosearch.getstartstate());
        numofnodes++;
        if (mazetosearch.getstartstate().getNumofcolumn() == finishcoll && mazetosearch.getstartstate().getNumofrow() == finishroww){// check if we reached the goal state
            return mazetosearch.getstartstate();
        }

        while (!Pqueue.isEmpty()) {

            AState pt = Pqueue.poll();// we take and removed the state
            if (pt.getNumofcolumn() == finishcoll && pt.getNumofrow() == finishroww){// check if we reached the goal state
                return pt;

            }

            int length = mazetosearch.getAllPossibleStates(pt).size();
            ArrayList<AState> newarraymaze = mazetosearch.getAllPossibleStates(pt);
            for (int i = 0; i < length; i++) {
                int row = newarraymaze.get(i).getNumofrow();
                int col = newarraymaze.get(i).getNumofcolumn();
                newarraymaze.get(i).setCamefrom(pt);

                if (mazetosearch.legalposition(row, col) &&
                        mazetosearch.getMaze().getvalue(row, col) == 0 &&
                        !visited[row][col]) {
                    visited[row][col] = true;
                    numofnodes++;// all visited states
                    Pqueue.add(newarraymaze.get(i));

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
        return numofnodes;
    }
}



