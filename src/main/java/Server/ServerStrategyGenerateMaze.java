package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient)  {


        try {
            ObjectInputStream fromclient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toclientob = new ObjectOutputStream(outToClient);
            ByteArrayOutputStream outbyte = new ByteArrayOutputStream();
            MyCompressorOutputStream toClient = new MyCompressorOutputStream(outbyte);
            int[] arrmaze;
            Maze newmaze;

            arrmaze = (int[]) fromclient.readObject();
            IMazeGenerator mazeGenerator = Configurations.getmazeGeneratingAlgorithm();
            newmaze = mazeGenerator.generate(arrmaze[0], arrmaze[1]);
            byte[] bytearrayofmaze = newmaze.toByteArray();
            toClient.write(bytearrayofmaze);
            toClient.flush();
            toClient.close();
            outbyte.write(bytearrayofmaze);
            toclientob.writeObject(outbyte.toByteArray());
            toclientob.flush();
            outbyte.close();
            toclientob.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

