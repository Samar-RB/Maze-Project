package Server;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;

import java.io.*;
import java.util.Properties;

public class Configurations {

    static Properties prop = new Properties();
    static String propFileName = "config.properties"; // קובץ צריך להיות ב־src/main/resources

    public static void Createconffile() {
        try {
            File file = new File(propFileName);

            if (!file.exists()) {
                // אם הקובץ לא קיים – ניצור אותו עם ערכים ברירת מחדל
                OutputStream outputStream = new FileOutputStream(propFileName);

                prop.setProperty("mazeSearchingAlgorithm", "BestFirstSearch");
                prop.setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
                prop.setProperty("threadPoolSize", "1");

                prop.store(outputStream, null);
                outputStream.close();
            } else {
                // קיים – נטען את הערכים ממנו
                InputStream inputStream = new FileInputStream(propFileName);
                prop.load(inputStream);
                inputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ISearchingAlgorithm getmazeSearchingAlgorithm() {
        String user = prop.getProperty("mazeSearchingAlgorithm");

        if ("BestFirstSearch".equals(user)) {
            return new BestFirstSearch();
        }
        if ("BreadthFirstSearch".equals(user)) {
            return new BreadthFirstSearch();
        }
        if ("DepthFirstSearch".equals(user)) {
            return new DepthFirstSearch();
        }

        return null;
    }

    public static IMazeGenerator getmazeGeneratingAlgorithm() {
        String user = prop.getProperty("mazeGeneratingAlgorithm");

        if ("EmptyMazeGenerator".equals(user)) {
            return new EmptyMazeGenerator();
        }
        if ("SimpleMazeGenerator".equals(user)) {
            return new SimpleMazeGenerator();
        }
        if ("MyMazeGenerator".equals(user)) {
            return new MyMazeGenerator();
        }

        return null;
    }

    public static int getthreadPoolSize() {
        String user = prop.getProperty("threadPoolSize");
        if (user == null || user.isEmpty()) {
            return 1; // ברירת מחדל אם חסר
        }

        try {
            return Integer.parseInt(user);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 1; // fallback default
        }
    }

    public void SetthreadPoolSize(String numofthreads) {
        prop.setProperty("threadPoolSize", numofthreads);
    }

    public void SetmazeGeneratingAlgorithm(String mazeGeneratingAlgorithmstring) {
        prop.setProperty("mazeGeneratingAlgorithm", mazeGeneratingAlgorithmstring);
    }

    public void SetmazeSearchingAlgorithm(String mazeSearchingAlgorithmstring) {
        prop.setProperty("mazeSearchingAlgorithm", mazeSearchingAlgorithmstring);
    }
}
