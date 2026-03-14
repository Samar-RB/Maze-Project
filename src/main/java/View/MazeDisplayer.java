package View;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MazeDisplayer extends Canvas {

    public MazeDisplayer() {
        setFocusTraversable(true);
        requestFocus();
    }

    private String strst;
    private String drawedsolution;
    private ArrayList<AState> solutiondis;
    private Maze maze;
    private int row_player;
    private int col_player;
    private double canvasHeight = getHeight();
    private double canvasWidth = getWidth();
    private GraphicsContext graphicsContext = getGraphicsContext2D();
    StringProperty imageFileNameWall = new SimpleStringProperty();
    StringProperty imageFileNamePlayer = new SimpleStringProperty();
    StringProperty imageFileNameFlag = new SimpleStringProperty();
    StringProperty imageFileNameTree = new SimpleStringProperty();
    StringProperty imageFileNameWinner = new SimpleStringProperty();

    public String getDrawedsolution() {
        return drawedsolution;
    }

    public void setDrawedsolution(String drawedsolution) {
        this.drawedsolution = drawedsolution;
    }

    public ArrayList<AState> getSolutiondis() {
        return solutiondis;
    }

    public void setSolutiondis(ArrayList<AState> solutiondis) {
        this.solutiondis = solutiondis;
    }

    public String getStrst() {
        return strst;
    }

    public void setStrst(String strst) {
        this.strst = strst;
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public double getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(double canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public double getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(double canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public String getImageFileNameFlag() {
        return imageFileNameFlag.get();
    }

    public void setImageFileNameFlag(String imageFileNameFlag) {
        this.imageFileNameFlag.set(imageFileNameFlag);
    }

    public String getImageFileNameWall() {
        return imageFileNameWall.get();
    }

    public void setImageFileNameWall(String imageFileNameWall) {
        this.imageFileNameWall.set(imageFileNameWall);
    }

    public String getImageFileNamePlayer() {
        return imageFileNamePlayer.get();
    }

    public void setImageFileNamePlayer(String imageFileNamePlayer) {
        this.imageFileNamePlayer.set(imageFileNamePlayer);
    }

    public String getImageFileNameWinner() {
        return imageFileNameWinner.get();
    }

    public StringProperty imageFileNameWinnerProperty() {
        return imageFileNameWinner;
    }

    public void setImageFileNameWinner(String imageFileNameWinner) {
        this.imageFileNameWinner.set(imageFileNameWinner);
    }

    public String getImageFileNameTree() {
        return imageFileNameTree.get();
    }

    public StringProperty imageFileNameTreeProperty() {
        return imageFileNameTree;
    }

    public void setImageFileNameTree(String imageFileNameTree) {
        this.imageFileNameTree.set(imageFileNameTree);
    }

    public Maze getMaze() {
        return this.maze;
    }

    public int getRow_player() {
        return row_player;
    }

    public int getCol_player() {
        return col_player;
    }

    public void set_player_position(int row, int col) {
        if (row == this.maze.getnumofrows() || row == -1 || col == this.maze.getnumofcolumns() || col == -1) {
            draw();
            return;
        }
        this.row_player = row;
        this.col_player = col;
        this.maze.getStartPosition().setRow(row_player);
        this.maze.getStartPosition().setColumn(col_player);
        draw();
    }

    public void drawMaze(Maze maze) {
        this.maze = maze;
        row_player = maze.getStartPosition().getRowIndex();
        col_player = maze.getStartPosition().getColumnIndex();
        draw();
    }

    public void draw() {
        if (maze != null) {
            canvasHeight = getHeight();
            canvasWidth = getWidth();
            int row = maze.m_maze.length;
            int col = maze.m_maze[0].length;
            double cellHeight = canvasHeight / row;
            double cellWidth = canvasWidth / col;
            graphicsContext = getGraphicsContext2D();
            graphicsContext.clearRect(0, 0, canvasWidth, canvasHeight);
            graphicsContext.setFill(Color.RED);
            double w, h;
            Image wallImage = null;
            try {
                wallImage = new Image(new FileInputStream(getImageFileNameWall()));
            } catch (FileNotFoundException e) {
                System.out.println("There is no file....");
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (maze.m_maze[i][j] == 1) {
                        h = i * cellHeight;
                        w = j * cellWidth;
                        if (wallImage == null) {
                            graphicsContext.fillRect(w, h, cellWidth, cellHeight);
                        } else {
                            graphicsContext.drawImage(wallImage, w, h, cellWidth, cellHeight);
                        }
                    }
                }
            }

            if (getDrawedsolution() != null) {
                Image treeImage = null;
                try {
                    treeImage = new Image(new FileInputStream(getImageFileNameTree()));
                } catch (FileNotFoundException e) {
                    System.out.println("There is no tree image....");
                }
                for (AState aState : solutiondis) {
                    double h_player2 = aState.getNumofrow() * cellHeight;
                    double w_player2 = aState.getNumofcolumn() * cellWidth;
                    if (!(aState.getNumofrow() == this.maze.getStartPosition().getRowIndex()
                            && aState.getNumofcolumn() == this.maze.getStartPosition().getColumnIndex())
                            && !(aState.getNumofrow() == this.maze.getGoalPosition().getRowIndex()
                            && aState.getNumofcolumn() == this.maze.getGoalPosition().getColumnIndex())) {
                        graphicsContext.drawImage(treeImage, w_player2, h_player2, cellWidth, cellHeight);
                    }
                }
            }

            double h_player = this.maze.getStartPosition().getRowIndex() * cellHeight;
            double w_player = this.maze.getStartPosition().getColumnIndex() * cellWidth;
            Image playerImage = null;
            Image flag_end = null;
            Image winner_image = null;
            try {
                playerImage = new Image(new FileInputStream(getImageFileNamePlayer()));
            } catch (FileNotFoundException e) {
                System.out.println("There is no Image player....");
            }
            graphicsContext.drawImage(playerImage, w_player, h_player, cellWidth, cellHeight);

            double h_endflagrow = this.maze.getGoalPosition().getRowIndex() * cellHeight;
            double w_endflagcol = this.maze.getGoalPosition().getColumnIndex() * cellWidth;

            try {
                flag_end = new Image(new FileInputStream(getImageFileNameFlag()));
            } catch (FileNotFoundException e) {
                System.out.println("There is no end flag....");
            }

            graphicsContext.drawImage(flag_end, w_endflagcol, h_endflagrow, cellWidth, cellHeight);

            if (this.maze.getStartPosition().getRowIndex() == this.maze.getGoalPosition().getRowIndex()
                    && this.maze.getStartPosition().getColumnIndex() == this.maze.getGoalPosition().getColumnIndex()) {
                try {
                    winner_image = new Image(new FileInputStream(getImageFileNameWinner()));
                } catch (FileNotFoundException e) {
                    System.out.println("There is no end flag....");
                }

                graphicsContext.drawImage(winner_image, w_endflagcol, h_endflagrow, cellWidth, cellHeight);
            }
        }
    }
}
