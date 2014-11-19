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
        File numberOfRuns = new File("numberofruns.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(numberOfRuns));
            String numOfRuns = null;

            numOfRuns = reader.readLine();
            int num = Integer.parseInt(numOfRuns);

            Logger.setRunCounter(num);

        } catch (FileNotFoundException e) {
            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("numberofruns.txt")));
                writer.write(1);
                Logger.setRunCounter(1);

            } catch (FileNotFoundException e1) {
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
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