package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {

    private ArrayList<AState> solution;

    public Solution(ArrayList<AState> solution) {
        this.solution = solution;
    }

    public ArrayList<AState> getSolutionPath(){
        return solution;
    }

    public void setSolution(ArrayList<AState> solution) {
        this.solution = solution;
    }
}
