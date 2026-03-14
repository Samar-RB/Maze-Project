package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {//כמו שממשנו במעבדה
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    public void communicateWithServer(){
        start();

    }
    private void start(){//כמו שממשנו במעבדה
        try{
            Socket serverSocket = new Socket(serverIP, serverPort);
            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
//            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
