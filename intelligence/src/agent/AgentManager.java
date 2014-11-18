package agent;

import Events.AgentFinishedRunning;
import Events.GraphicHandler;
import Events.RoundFinishedHandler;
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

    public AgentManager (int K, GraphicHandler handler_)
    {
        handler = handler_;
        foundValues=new ArrayList<Field>();
        agents = new ArrayList<Agent>(K);
        agentNumber = K;
        agentRoundsFinished = 0;
        int fieldSize = Application.fieldManager.getFieldSize();

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

            // ha még nem fejezte be minden ágnes
            else {
                // akkor nincs lófasz se.
            }
        }
    }

    @Override
    public void onAgentFinishedRunning(Agent agent, double foundValue) {
        synchronized (syncObject) {
            this.agents.remove(agent);
            this.agentNumber--;
            foundValues.add(new Field(agent.getField().getX(), agent.getField().getY(), foundValue));
        }
    }
}
