package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;

import static Server.Configurations.getmazeGeneratingAlgorithm;
import static Server.Configurations.getmazeSearchingAlgorithm;

public class ServerStrategySolveSearchProblem implements IServerStrategy{



    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            Solution solution;
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze mazefromclient;
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            mazefromclient = (Maze) fromClient.readObject();
            String nameofmazefile = mazefromclient.toString();
            File filemaze = new File(tempDirectoryPath, nameofmazefile);
            if(!(filemaze.exists())){//if the file is not exist then must to create a file with the name of the maze and to put the solution into the file
                FileOutputStream fileOut = new FileOutputStream(filemaze);
                ObjectOutputStream objectReturn = new ObjectOutputStream(fileOut);
                SearchableMaze searchableMaze = new SearchableMaze(mazefromclient);
                solution = (getmazeSearchingAlgorithm()).solve(searchableMaze);
                objectReturn.writeObject(solution);
                objectReturn.flush();

            }
            else{// if the file is exist then we took the solution from the file
                FileInputStream fileinput = new FileInputStream(filemaze);
                ObjectInputStream returnfile = new ObjectInputStream(fileinput);
                solution = (Solution) returnfile.readObject();
                returnfile.close();

            }
            toClient.writeObject(solution);//return the solution to the client
            toClient.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
