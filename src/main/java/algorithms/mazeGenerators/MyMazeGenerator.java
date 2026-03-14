package algorithms.mazeGenerators;
import algorithms.search.MazeState;
import java.util.ArrayList;

public class MyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int column) {
        if (row < 2 || column < 2) return null;

        int[][] maze_array = new int[row][column];


        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++) {
                maze_array[i][j] = 1;
            }
        return prim(maze_array,row,column);
    }
    private Maze prim(int[][] maze_array,int row, int col) {
        MazeState start = new MazeState((int) (Math.random() * row), 0);
        start.setCamefrom(null);
        Position goal = null;
        ArrayList<MazeState> frontier = new ArrayList<>();
        iterate(maze_array,start,frontier);

        MazeState last = null;
        try {
            while (!frontier.isEmpty()) {
                MazeState current = frontier.remove((int) (Math.random() * frontier.size()));
                MazeState temp = neigbor(current);

                try {
                    if (maze_array[current.getNumofrow()][current.getNumofcolumn()] == 1) {
                        assert temp != null;
                        if (maze_array[temp.getNumofrow()][temp.getNumofcolumn()] == 1) {

                            maze_array[current.getNumofrow()][current.getNumofcolumn()] = 0;
                            maze_array[temp.getNumofrow()][temp.getNumofcolumn()] = 0;
                            last = temp;
                            //System.out.println(last.getNumofrow());
                            iterate(maze_array, temp, frontier);
                        }
                    }

                }

                catch(Exception ignored){
                } //
                if (frontier.isEmpty()) {
                    goal = new Position(last.getNumofrow(), last.getNumofcolumn());
                }}

            Position st = new Position(start.getNumofrow(), start.getNumofcolumn());
            maze_array[st.getRowIndex()][st.getColumnIndex()]=0;
            return new Maze(maze_array, st, goal);
        }catch (Exception E){
            return null;
        }
    }
    /**
     *  iterate through direct neighbors of node
     * @param maze_array
     * @param position
     * @param frontier
     */

    /**
     *
     * @param current
     * @return
     */
    private MazeState neigbor (MazeState current) {
        try {

            Position position = new Position(current.getNumofrow(),current.getNumofcolumn());
            MazeState parent = (MazeState) current.getCamefrom();
            Position position_parent = new Position(parent.getNumofrow(),parent.getNumofcolumn());
            int x=Integer.compare(position.getRowIndex(), position_parent.getRowIndex());
            int y=Integer.compare(position.getColumnIndex()+position.getColumnIndex(),position_parent.getColumnIndex());
            if (current.getCamefrom() != null) {
                if (position.getRowIndex() != position_parent.getRowIndex()) {
                    MazeState r = new MazeState(position.getRowIndex() + x, position.getColumnIndex());
                    r.setCamefrom(current);
                    r.setCost(current.getCost());
                    return r;
                }
                if (position.getColumnIndex() != position_parent.getColumnIndex()){
                    MazeState r2=new MazeState(position.getRowIndex(),position.getColumnIndex()+y);
                    r2.setCamefrom(current);
                    r2.setCost(current.getCost());
                    return r2;}
            }
        } catch(Exception e) {
        }
        return null;
    }


    private void iterate(int[][] maze_array, MazeState position, ArrayList<MazeState> frontier) {
        try {
            for(int x = -1; x <= 1; x++)
                for(int y = -1; y <= 1; y++) {
                    if (x == 0 && y == 0 || x != 0 && y != 0) continue;
                    try {
                        if (maze_array[position.getNumofrow()+x][position.getNumofcolumn()+y] == 0)
                            continue;
                    } catch(Exception e) {
                        continue;
                    } //
                    MazeState p=new MazeState(position.getNumofrow()+x, position.getNumofcolumn()+y);
                    p.setCost(position.getCost());
                    p.setCamefrom(position);
                    frontier.add( p);
                }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
