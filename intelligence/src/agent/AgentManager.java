package agent;

import Events.AgentFinishedRunning;
import Events.GraphicHandler;
import Events.RoundFinishedHandler;
import Log.Logger;
import Log.Statistics;
import field.Field;
import javafx.util.Pair;
import main.Application;

import java.util.ArrayList;

/**
 * Created by Attila on 2014.11.18..
 */
public class AgentManager implements RoundFinishedHandler, AgentFinishedRunning {
    private ArrayList<Agent> agents;
    private ArrayList<Field> foundValues;
    private GraphicHandler handler;
    private int agentNumber;
    private int agentRoundsFinished;
    private Object syncObject = new Object();
    private long startTimeNano;
    private Statistics statistics;

    public AgentManager(int K, GraphicHandler handler_, Statistics statistics) {
        handler = handler_;
        foundValues = new ArrayList<Field>();
        agentNumber = K;
        agentRoundsFinished = 0;
        this.statistics = statistics;
        int fieldSize = Application.fieldManager.getFieldSize();
        agents = new ArrayList<Agent>();
        startTimeNano = System.nanoTime();

        /*for(int i = 0; i < agentNumber; i++)
        {
            Agent temp = new Agent();
            int startposX = (int)(Math.random() * fieldSize);
            int startposY = (int)(Math.random() * fieldSize);
            temp.setField(new Field(startposX, startposY));
            temp.setRoundHandler(this);
            temp.setFinishedHandler(this);
            agents.add(temp);
        }*/
    }

    public void startAgentSimulation() {
        int fieldSize = Application.fieldManager.getFieldSize();
        for (int i = 0; i < agentNumber; i++) {
            Agent temp = new Agent();
            int startposX = (int) (Math.random() * fieldSize);
            int startposY = (int) (Math.random() * fieldSize);
            temp.setField(new Field(startposX, startposY));
            temp.setRoundHandler(this);
            agents.add(temp);
            Pair<Field, Integer> optimum = temp.run();
            foundValues.add(optimum.getKey());
            agents.remove(temp);
            Logger.getFoundOptimums().put(optimum.getKey(), optimum.getValue());
        }
        long totalRunTime = System.nanoTime() - startTimeNano;
        Logger.setSearchTimeNano(totalRunTime);
        Logger.finalizeLogging(statistics);

    }

    // szerintem már jó, mutasd meg dorkának, ő majd code reviewolja
    @Override
    public void onAgentRoundFinished(Agent agent) {
        // majd kérünk egy kirajzolást
        if (handler != null)
            handler.onRedraw(agents, foundValues);
        else
            System.out.println(foundValues);
    }

    @Override
    public void onAgentFinishedRunning(Agent agent, double foundValue, int stepCount) {
        /*synchronized (syncObject) {
            agents.remove(agent);
            agentNumber--;
            Field found = new Field(agent.getField().getX(), agent.getField().getY(), foundValue);
            foundValues.add(found);
            Logger.getFoundOptimums().put(found, stepCount);

            if (agentNumber == 0) {
                long totalRunTime = System.nanoTime() - startTimeNano;
                Logger.setSearchTimeNano(totalRunTime);
                Logger.finalizeLogging(statistics);
                System.out.println("Search finished!");
                //System.exit(0);
            }
        }*/
    }
}
