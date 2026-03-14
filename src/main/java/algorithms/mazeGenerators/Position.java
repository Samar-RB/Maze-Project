package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;

public class Position implements Serializable {

    private int row;
    private int column;
    private Position parent;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        parent = null;
    }

    public Position(int row, int column, Position p) {
        this.row = row;
        this.column = column;
        parent = p;
    }

    public int getRowIndex() {
        return row;
    }

    public int getColumnIndex() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "{" + row + "," + column + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row &&
                column == position.column;
    }

    public Position opposite() {
        Integer r = row;
        Integer c = column;
        try {
            if (parent != null) {
                if (r.compareTo(parent.row) != 0)
                    return new Position(r + r.compareTo(parent.row), c, this);
                if (c.compareTo(parent.column) != 0)
                    return new Position(r, c + c.compareTo(parent.column), this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
