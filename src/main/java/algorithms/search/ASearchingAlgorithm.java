package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{ //to implement all same implements in each ISearchingAlgorithm

    private int visitnodes;//nodes evaluated

    public ASearchingAlgorithm() {

        visitnodes = 0;

    }


    @Override
    public AState search(ISearchable s) {
        return null;
    }

    @Override
    public Solution solve(ISearchable domain) {
        ArrayList<AState> arraysolution = new ArrayList<AState>();
        AState statesolution = search(domain);
        if(statesolution==null){
            System.out.println("fgggffd");
        }
        if(statesolution!=null){
            arraysolution.add(statesolution);
            if(statesolution.getCamefrom()!=null){
                AState camefromsolution = statesolution.getCamefrom();
                while (camefromsolution!=null){
                    arraysolution.add(camefromsolution);// adding all the parents until we reached the start state then we stop and then we have all the path
                    camefromsolution = camefromsolution.getCamefrom();
                }
            }

        }
        ArrayList<AState> arraysolution2 = new ArrayList<AState>();// new array to get the right path
        for (int i = arraysolution.size()-1; i >= 0; i--) {
            arraysolution2.add(arraysolution.get(i));

        }


        Solution sol = new Solution(arraysolution2);
        return sol;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return 0;
    }



}
