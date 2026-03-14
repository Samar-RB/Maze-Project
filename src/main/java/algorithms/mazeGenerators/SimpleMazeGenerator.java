package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     *
     * @param row
     * @param column
     * @return Simple maze
     */

    @Override


    public Maze generate(int row, int column) {
        if (row < 2 || column < 2) return null;

        int[][] maze_array = new int[row][column];


        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++) {
                maze_array[i][j] = (int) (Math.random() * 2);

            }
        Random rg = new Random();
        int xrand = rg.nextInt(row);
        if ( xrand > row / 2 ) {
            xrand -= 2;
        } else {
            xrand += 2;
        }

        Position start = new Position(xrand, 0);
        maze_array[start.getRowIndex()][start.getColumnIndex()]=0;
        Position goal ;
        int crow = start.getRowIndex();
        int col = 1;
        int maxRow = maze_array.length - 2;
        int maxCol = maze_array[ 0 ].length - 2;


        int direction = 1;
        if ( crow > ( maxRow / 2 ) )
            direction = -1;

        while (col <= maxCol && crow <= maxRow && crow >= 2) {
            maze_array[ crow ][ col ] = 0;
            crow = crow + direction;
            maze_array[ crow ][ col ] = 0;
            col++;
        }
        goal = new Position(crow, col);

        try {
            return new Maze(maze_array, start, goal);
        } catch (Exception ignored) {
        }
        return null;
    }

}
