package main;

import Gui.Window;
import Log.Logger;
import Log.Statistics;
import agent.AgentManager;
import field.FieldManager;

import java.util.Scanner;

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
            AgentManager agentManager = new AgentManager(numberOfAgents, null, stats, false);
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
            AgentManager agentManager = new AgentManager(numberOfAgents, null, stats, false);
            agentManager.startAgentSimulation();
        }
        stats.createStatistics();
    }

    public static void graphicalRun(int numberOfAgents) {
        Statistics stats = new Statistics(1, numberOfAgents);
        stats.getRealGlobalOptimumValues().add(fieldManager.getGlobalOptimum().getValue());
        Window window = new Window();
        Logger.setNumberOfBeams(numberOfAgents);
        Logger.setGlobalOptimum(fieldManager.getGlobalOptimum());
        AgentManager agentManager = new AgentManager(numberOfAgents, window.getPanel(), stats, true);
        agentManager.startAgentSimulation();
    }


    public static void main(String[] args) {
        System.out.println("Please add the required difficulty of the map (search space).");
        System.out.println("This value should be an integer from 1 to 10000");
        Scanner scanner = new Scanner(System.in);
        int difficulty = scanner.nextInt();
        if (difficulty > 0)
            difficulty = 0 - difficulty;


        System.out.println("Next you will have to add the required number of searches. " +
                "After that, the program will run some searches based on your given numbers " +
                "and on the predefined logic.");
        System.out.println("After that, a search with 100 beams will be showed you on the generated search space.");
        System.out.println("Generating the search space...");

        fieldManager.generateField(difficulty);

        System.out.println("Please add the required number of searches.");
        System.out.println("The program is going to perform this number of searches with the following number of beams: ");
        System.out.println("1,   5,   10,   25,   50,   100");
        int numberOfSearches = scanner.nextInt();
        if (numberOfSearches < 1) {
            System.out.println(numberOfSearches + " is an invalid value. This number must be bigger than 0. The program is terminating");
            System.exit(1);
        }


        runOnSame(numberOfSearches, 1);
        Logger.reInitialize();
        runOnSame(numberOfSearches, 5);
        Logger.reInitialize();
        runOnSame(numberOfSearches, 10);
        Logger.reInitialize();
        runOnSame(numberOfSearches, 25);
        Logger.reInitialize();
        runOnSame(numberOfSearches, 50);
        Logger.reInitialize();
        runOnSame(numberOfSearches, 100);
        Logger.reInitialize();
        graphicalRun(100);
    }

}