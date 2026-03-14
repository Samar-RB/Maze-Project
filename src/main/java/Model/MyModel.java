package Model;

import Client.Client;
import Client.*;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import Server.Server;
import Server.*;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyModel extends Observable implements IModel {
    private static boolean serverStart = false;
    private Server mazeGeneratingServer;
    private Server solveSearchProblemServer;
    private Maze maze;
    private int numofrows;
    private int numofcols;
    private int rowcharecter;
    private int colcharecter;
    private String strstr=null;
    private ArrayList<AState> solution;
    private Solution sol;
    private ExecutorService threadPool;
    private boolean disabled =true;

    public int getNumofrows() {
        return numofrows;
    }

    public void setNumofrows(int numofrows) {
        this.numofrows = numofrows;
    }

    public int getNumofcols() {
        return numofcols;
    }

    public void setNumofcols(int numofcols) {
        this.numofcols = numofcols;
    }

    public Solution getSol() {
        return sol;
    }

    public void setSol(Solution solu) {
        this.sol = solu;
    }

    public ArrayList<AState> getSolution() {
        return solution;
    }

    public void setSolution(ArrayList<AState> solution) {
        this.solution = solution;
    }

    public String getStrstr() {
        return strstr;
    }

    public void setStrstr(String strstr) {
        this.strstr = strstr;
    }

    public int getRowcharecter() {
        return rowcharecter;
    }

    @Override
    public void assighnObserver(Observer o) {
        this.addObserver(o);
    }

    @Override
    public void solvemaze(Maze maze) {
        if(!disabled){//if equal to true, then the maze equal null and we dont have a maze to solve
            strstr="solvemaze";
            solsol(maze);
        }

    }

    @Override


    public int getColcharecter() {
        return colcharecter;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public MyModel() {
        solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        maze=null;
        this.solveSearchProblemServer.start();
        this.mazeGeneratingServer.start();
        this.threadPool = Executors.newFixedThreadPool(Configurations.getthreadPoolSize());
        strstr=null;
    }

    public void updatecharecterlocation(KeyEvent keyEvent){
        /*
        durection 8 -> UP
        durection 2 -> DOWN
        durection 6 -> RIGHT
        durection 4 -> LEFT
        durection 9 -> UPRIGHT
        durection 7 -> UPLEFT
        durection 1 -> DOWNLEFT
        durection 3 -> DOWNRIGHT
         */
        if(keyEvent.getCode() == KeyCode.DIGIT8 || keyEvent.getCode() == KeyCode.NUMPAD8) {
            if (rowcharecter != 0 && this.maze.m_maze[rowcharecter - 1][colcharecter] == 0) {
                rowcharecter--;
                strstr="movingcharecter";
                this.maze.getStartPosition().setRow(rowcharecter);
                setChanged();
                notifyObservers();
                return;
            }
        }
        if(keyEvent.getCode() == KeyCode.DIGIT2 || keyEvent.getCode() == KeyCode.NUMPAD2){
            if(rowcharecter!=(this.maze.m_maze.length-1) && this.maze.m_maze[rowcharecter+1][colcharecter]==0){
                rowcharecter++;
                strstr="movingcharecter";
                this.maze.getStartPosition().setRow(rowcharecter);
                setChanged();
                notifyObservers();
                return;

            }
        }



        if(keyEvent.getCode() == KeyCode.DIGIT6 || keyEvent.getCode() == KeyCode.NUMPAD6) {
            if (colcharecter != ((this.maze.m_maze[0].length) - 1) && this.maze.m_maze[rowcharecter][colcharecter + 1] == 0) {
                colcharecter++;
                strstr="movingcharecter";
                this.maze.getStartPosition().setColumn(colcharecter);
                setChanged();
                notifyObservers();
                return;

            }
        }
        if(keyEvent.getCode() == KeyCode.DIGIT4 || keyEvent.getCode() == KeyCode.NUMPAD4) {
            if (colcharecter != 0 && this.maze.m_maze[rowcharecter][colcharecter - 1] == 0) {
                colcharecter--;
                strstr="movingcharecter";
                this.maze.getStartPosition().setColumn(colcharecter);
                setChanged();
                notifyObservers();
                return;

            }
        }
        if(keyEvent.getCode() == KeyCode.DIGIT9 || keyEvent.getCode() == KeyCode.NUMPAD9) {
            if (rowcharecter != 0 && colcharecter != (this.maze.m_maze.length - 1) && this.maze.m_maze[rowcharecter - 1][colcharecter + 1] == 0) {
                rowcharecter--;
                colcharecter++;
                strstr="movingcharecter";
                this.maze.getStartPosition().setRow(rowcharecter);
                this.maze.getStartPosition().setColumn(colcharecter);
                setChanged();
                notifyObservers();
                return;

            }
        }
        if(keyEvent.getCode() == KeyCode.DIGIT7 || keyEvent.getCode() == KeyCode.NUMPAD7) {
            if (rowcharecter != 0 && colcharecter != 0 && this.maze.m_maze[rowcharecter - 1][colcharecter - 1] == 0) {
                rowcharecter--;
                colcharecter--;
                strstr="movingcharecter";
                this.maze.getStartPosition().setRow(rowcharecter);
                this.maze.getStartPosition().setColumn(colcharecter);
                setChanged();
                notifyObservers();
                return;

            }
        }
        if(keyEvent.getCode() == KeyCode.DIGIT1 || keyEvent.getCode() == KeyCode.NUMPAD1) {
            if (rowcharecter != (this.maze.m_maze.length - 1) && colcharecter != 0 && this.maze.m_maze[rowcharecter + 1][colcharecter - 1] == 0) {
                rowcharecter++;
                colcharecter--;
                strstr="movingcharecter";
                this.maze.getStartPosition().setRow(rowcharecter);
                this.maze.getStartPosition().setColumn(colcharecter);
                setChanged();
                notifyObservers();
                return;

            }
        }
        if(keyEvent.getCode() == KeyCode.DIGIT3 || keyEvent.getCode() == KeyCode.NUMPAD3) {
            if (rowcharecter != (this.maze.m_maze.length - 1) && colcharecter != (this.maze.m_maze.length - 1) && this.maze.m_maze[rowcharecter + 1][colcharecter + 1] == 0) {
                rowcharecter++;
                colcharecter++;
                strstr="movingcharecter";
                this.maze.getStartPosition().setRow(rowcharecter);
                this.maze.getStartPosition().setColumn(colcharecter);
                setChanged();
                notifyObservers();
                return;

            }
        }


    }

    private void CommunicateWithServer_MazeGenerating(final int x, final int y) {
        try {//from the main from part B
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        toServer.flush();
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        int[] mazeDimensions = new int[]{x, y};
                        toServer.writeObject(mazeDimensions);
                        toServer.flush();
                        byte[] compressedMaze = (byte[])((byte[])fromServer.readObject());
                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        byte[] decompressedMaze = new byte[x * y + 30];
                        is.read(decompressedMaze);
                        Maze maze = new Maze(decompressedMaze);
                        setMaze(maze);
                        maze.print();
                        // System.out.println("yesyesyesyesyesyes");
                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }

                }
            });
            client.communicateWithServer();
        } catch (UnknownHostException var3) {
            var3.printStackTrace();
        }

    }
    public void generateRandomMaze(int row, int col)
    {
        CommunicateWithServer_MazeGenerating(row, col);
        rowcharecter=this.maze.getStartPosition().getRowIndex();
        colcharecter = this.maze.getStartPosition().getColumnIndex();
        strstr="generatemaze";
        this.disabled = false;
        setChanged();
        notifyObservers();
    }

    public Maze getMaze() {
        strstr="generatemaze";
        return this.maze;
    }

    public void strat() {
        if(serverStart == false){
            this.solveSearchProblemServer.start();
            this.mazeGeneratingServer.start();
            serverStart = true;
        }

    }

    public void stop() {
        this.solveSearchProblemServer.stop();
        this.mazeGeneratingServer.stop();
        threadPool.shutdown();
    }


    public void solsol(Maze mazetosolve) {
        try {//from the main from part B
            Client client = new Client(InetAddress.getLocalHost(), 5401, new IClientStrategy() {
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        mazetosolve.print();
                        toServer.writeObject(mazetosolve);
                        toServer.flush();
                        Solution mazeSolution = (Solution)fromServer.readObject();
                        setSol(mazeSolution);
                        System.out.println(String.format("Solution steps: %s", mazeSolution));
                        ArrayList<AState> mazeSolutionSteps = mazeSolution.getSolutionPath();
                        setSolution(mazeSolutionSteps);

                        for(int i = 0; i < mazeSolutionSteps.size(); ++i) {
                            System.out.println(String.format("%s. %s", i, ((AState)mazeSolutionSteps.get(i)).toString()));
                        }
                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }

                }
            });
            client.communicateWithServer();
            setChanged();
            notifyObservers();
        } catch (UnknownHostException var1) {
            var1.printStackTrace();
        }

    }


    public void Savingthemaze() throws IOException {
        String tempDirectoryPath = System.getProperty("java.io.tmpdir");

        String nameofmazefile = this.maze.toString();
        File filemaze = new File(tempDirectoryPath, nameofmazefile);



    }
}
