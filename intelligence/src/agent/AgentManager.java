package agent;

import Events.AgentFinishedRunning;
import Events.GraphicHandler;
import Events.RoundFinishedHandler;
import Log.Logger;
import field.Field;
import main.Application;

import java.util.ArrayList;

/**
 * Created by Attila on 2014.11.18..
 */
public class AgentManager implements RoundFinishedHandler, AgentFinishedRunning{
    private ArrayList<Agent> agents;
    private ArrayList<Field> foundValues;
    private GraphicHandler handler;
    private int agentNumber;
    private int agentRoundsFinished;
    private static Object syncObject = new Object();
    private long startTimeNano;

    public AgentManager (int K, GraphicHandler handler_)
    {
        handler = handler_;
        foundValues=new ArrayList<Field>();
        agents = new ArrayList<Agent>(K);
        agentNumber = K;
        agentRoundsFinished = 0;
        int fieldSize = Application.fieldManager.getFieldSize();

        startTimeNano = System.nanoTime();

        for(int i = 0; i < agentNumber; i++)
        {
            Agent temp = new Agent();
            int startposX = (int)(Math.random() * fieldSize);
            int startposY = (int)(Math.random() * fieldSize);
            temp.setField(new Field(startposX, startposY));
            temp.setRoundHandler(this);
            temp.setFinishedHandler(this);
            temp.setReadyToRun(true);
            agents.add(temp);
            temp.start();
        }
    }

    // szerintem már jó, mutasd meg dorkának, ő majd code reviewolja
    @Override
    public void onAgentRoundFinished(Agent agent) {
        synchronized (syncObject)
        {
            // ha minden ágens befejezte a kört
            if (++agentRoundsFinished >= agentNumber) {

                // egyesével újraengedélyezzük őket
                for(int i = 0; i < agentNumber; i++) {
                    agents.get(i).setReadyToRun(true);
                }

                // majd kérünk egy kirajzolást
                handler.onRedraw(agents, foundValues);

                agentRoundsFinished = 0;
            }
        }
    }

    @Override
    public void onAgentFinishedRunning(Agent agent, double foundValue) {
        synchronized (syncObject) {
            agents.remove(agent);
            agentNumber--;
            Field found = new Field(agent.getField().getX(), agent.getField().getY(), foundValue);
            foundValues.add(found);
            Logger.getFoundOptimums().add(found);

            if (agentNumber == 0) {
                long totalRunTime = System.nanoTime() - startTimeNano;
                Logger.setSearchTimeNano(totalRunTime);
            }
        }
    }
}
