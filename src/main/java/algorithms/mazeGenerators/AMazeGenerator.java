package algorithms.mazeGenerators;







public abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public abstract Maze generate(int row, int column);

    @Override
    public long measureAlgorithmTimeMillis(int row, int column) {
        long before = System.currentTimeMillis();
        generate(row, column);
        long after = System.currentTimeMillis();
        long returnval = after - before;
        return returnval;
    }
}
