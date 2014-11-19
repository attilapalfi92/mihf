package main;

import Gui.Window;
import Log.Logger;
import agent.AgentManager;
import field.FieldManager;

import java.io.*;

public class Application {

    // 600x600 field size
    public static FieldManager fieldManager = new FieldManager(600);

    public static void main(String[] args ){
        File numberOfRunsFile = new File("numberofruns.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(numberOfRunsFile));
            String numOfRuns = null;

            numOfRuns = reader.readLine();
            int num = Integer.parseInt(numOfRuns);
            Logger.setRunCounter(num);
            reader.close();

            FileWriter fileWriter = new FileWriter(numberOfRunsFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(++num);
            fileWriter.close();

        } catch (FileNotFoundException e) {
            try {
                FileWriter fileWriter = new FileWriter(numberOfRunsFile);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println("1");
                fileWriter.close();

                Logger.setRunCounter(1);
                fileWriter.close();

            } catch (FileNotFoundException e1) {
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("numberofruns.txt not found. Creating one.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        fieldManager.generateField();
        Window window = new Window();
        int numberOfBeams = 10;
        Logger.setNumberOfBeams(numberOfBeams);
        AgentManager agentManager = new AgentManager(numberOfBeams, window.getPanel());
    }

}