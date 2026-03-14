package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;

public class Maze implements Serializable {

    public int[][] m_maze;
    private Position m_start;
    private Position m_goal;
    private int indexByteArray = 0;

    public Maze(int[][] pMaze, Position pStart, Position pGoal) {
        this.m_maze = pMaze;
        this.m_start = pStart;
        this.m_goal = pGoal;
    }

    public Position getStartPosition() {
        return m_start;
    }

    public Position getGoalPosition() {
        return m_goal;
    }

    public void print() {
        if (m_maze == null) return;

        for (int i = 0; i < m_maze.length; i++) {
            for (int j = 0; j < m_maze[i].length; j++) {
                if (i == m_start.getRowIndex() && j == m_start.getColumnIndex())
                    System.out.print("S");
                else if (m_goal != null && i == m_goal.getRowIndex() && j == m_goal.getColumnIndex())
                    System.out.print("E");
                else if (m_maze[i][j] == 1)
                    System.out.print("1");
                else if (m_maze[i][j] == 0)
                    System.out.print("0");
            }
            System.out.println();
        }
    }

    public void setvalue(int row, int column, int value) {
        m_maze[row][column] = value;
    }

    public int getvalue(int row, int column) {
        return m_maze[row][column];
    }

    public int getnumofcolumns() {
        return m_maze[0].length;
    }

    public int getnumofrows() {
        return m_maze.length;
    }

    public byte[] toByteArray() {
        ArrayList<Byte> byteArray = new ArrayList<>();
        insertUnsigned(m_maze.length, byteArray);
        insertUnsigned(m_maze[0].length, byteArray);
        insertUnsigned(m_start.getRowIndex(), byteArray);
        insertUnsigned(m_start.getColumnIndex(), byteArray);
        insertUnsigned(m_goal.getRowIndex(), byteArray);
        insertUnsigned(m_goal.getColumnIndex(), byteArray);

        for (int[] row : m_maze) {
            for (int cell : row) {
                byteArray.add((byte) cell);
            }
        }

        byte[] maze = new byte[byteArray.size()];
        for (int i = 0; i < maze.length; i++) {
            maze[i] = byteArray.get(i);
        }

        return maze;
    }

    public Maze(byte[] bytes) {
        int row = readUnsigned(bytes);
        int col = readUnsigned(bytes);
        Position start = new Position(readUnsigned(bytes), readUnsigned(bytes));
        Position end = new Position(readUnsigned(bytes), readUnsigned(bytes));
        this.m_start = start;
        this.m_goal = end;

        int[][] mazeArray = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (indexByteArray >= bytes.length) {
                    throw new ArrayIndexOutOfBoundsException("Index " + indexByteArray + " out of bounds for length " + bytes.length);
                }
                mazeArray[i][j] = Byte.toUnsignedInt(bytes[indexByteArray++]);
            }
        }
        this.m_maze = mazeArray;
    }

    private int readUnsigned(byte[] bytes) {
        int sum = 0;
        while (indexByteArray < bytes.length && bytes[indexByteArray] != (byte) 127) {
            sum += Byte.toUnsignedInt(bytes[indexByteArray]);
            indexByteArray++;
        }
        indexByteArray++;
        return sum;
    }

    private void insertUnsigned(int num, ArrayList<Byte> arr) {
        while (num >= 254) {
            arr.add((byte) 254);
            num -= 254;
        }
        arr.add((byte) (num));
        arr.add((byte) 127);
    }
}
