package algorithms.search;

public class MazeState extends AState{

    private int numofrow;
    private int numofcolumn;

    public MazeState(int numofrow, int numofcolumn) {
        this.numofrow = numofrow;
        this.numofcolumn = numofcolumn;
    }

    @Override
    public int getNumofcolumn() {
        return numofcolumn;
    }

    @Override
    public int getNumofrow() {
        return numofrow;
    }


    @Override
    public void printmazestate() {
        System.out.println(String.format("{%s, %s}", numofrow, numofcolumn));
    }

    @Override
    public String toString() { // to print the state as we want
        String newstr = "{" + this.numofrow + "," + this.numofcolumn + "}";
        return newstr;

    }
}
