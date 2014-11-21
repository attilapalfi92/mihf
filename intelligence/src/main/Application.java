package main;

import Gui.Window;
import Log.Logger;
import Log.Statistics;
import agent.AgentManager;
import field.FieldManager;

import java.io.*;

public class Application {

    // 600x600 field size
    public static FieldManager fieldManager = new FieldManager(600);

    public static void cleanRun(int numberOfRuns, int numberOfAgents) {
        Statistics stats = new Statistics(numberOfRuns, numberOfAgents);
        for (int i = 0; i < numberOfRuns; i++) {
            Logger.reInitialize();
            fieldManager.generateField(-800);
            stats.getRealGlobalOptimumValues().add(fieldManager.getGlobalOptimum().getValue());
            Logger.setNumberOfBeams(numberOfAgents);
            AgentManager agentManager = new AgentManager(numberOfAgents, null, stats);
            agentManager.startAgentSimulation();
        }
        stats.createStatistics();
    }

    public static void runOnSame(int numberOfRuns, int numberOfAgents) {
        Statistics stats = new Statistics(numberOfRuns, numberOfAgents);
        stats.getRealGlobalOptimumValues().add(fieldManager.getGlobalOptimum().getValue());
        for (int i = 0; i < numberOfRuns; i++) {
            Logger.reInitialize();
            Logger.setGlobalOptimum(fieldManager.getGlobalOptimum());
            Logger.setNumberOfBeams(numberOfAgents);
            AgentManager agentManager = new AgentManager(numberOfAgents, null, stats);
            agentManager.startAgentSimulation();
        }
        stats.createStatistics();
    }

    public static void graphicalRun(int numberOfAgents) {
        Statistics stats = new Statistics(1, numberOfAgents);
        stats.getRealGlobalOptimumValues().add(fieldManager.getGlobalOptimum().getValue());
        Window window = new Window();
        Logger.setNumberOfBeams(numberOfAgents);
        AgentManager agentManager = new AgentManager(numberOfAgents, window.getPanel(), stats);
        agentManager.startAgentSimulation();
        stats.createStatistics();
    }


    public static void main(String[] args) {
        File numberOfRunsFile = new File("numberofruns.txt");
        BufferedReader reader = null;

        /*try {
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
        }*/
        //graphicalRun(10);
        fieldManager.generateField(-1200);
        runOnSame(1000, 1);
        runOnSame(1000, 5);
        runOnSame(1000, 10);
        runOnSame(1000, 25);
        runOnSame(1000, 50);
        runOnSame(1000, 100);
        graphicalRun(100);
        /*
        Window window = new Window();
        int numberOfBeams = 50;
        Logger.setNumberOfBeams(numberOfBeams);
        AgentManager agentManager = new AgentManager(numberOfBeams, window.getPanel());*/
    }

}