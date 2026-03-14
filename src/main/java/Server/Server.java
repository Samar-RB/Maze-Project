package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static Server.Configurations.Createconffile;
import static Server.Configurations.getthreadPoolSize;

public class Server { //מימשנו בעזרת המימוש של המעבדה
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stopp;
    private ExecutorService threadPool; // Thread pool


    public Server(int portt, int listeningIntervalMS, IServerStrategy strategy) {
        Createconffile();//בכל פעם שמגדירים שרת, נוודא שנמצא קובץ קונפיגרציה
        this.port = portt;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(getthreadPoolSize());
    }

    public void start() {
        {
            new Thread(() -> {serverStrategy();}).start();
        }
    }

    private void serverStrategy(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);

            while (!stopp) {
                try {
                    Socket clientSocket = serverSocket.accept();

                    // Insert the new task into the thread pool:
                    threadPool.submit(() -> {
                        handleClient(clientSocket);
                    });

                } catch (SocketTimeoutException e){
                    e.getStackTrace();

                }
            }
            serverSocket.close();
            threadPool.shutdownNow();

            //threadPool.shutdown(); // do not allow any new tasks into the thread pool (not doing anything to the current tasks and running threads)

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void stop(){
        stopp = true;
    }
}
