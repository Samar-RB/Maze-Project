package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int column) {
        if (row < 2 || column < 2) return null;
        int[][] emptyMaze = new int[ row ][ column ];
        Position p=new Position(0,0);
        Position end=new Position((int) (Math.random()*row), (int) (Math.random()*column));
        return new Maze(emptyMaze,p,end);
    }
}