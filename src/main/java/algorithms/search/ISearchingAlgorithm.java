package algorithms.search;

public interface ISearchingAlgorithm {

    AState search(ISearchable s);

    public Solution solve(ISearchable domain);

    public String getName();

    public int getNumberOfNodesEvaluated();

}
