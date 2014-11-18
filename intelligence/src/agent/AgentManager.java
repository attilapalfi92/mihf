package agent;

import Events.GraphicHandler;
import Events.RoundFinishedHandler;
import field.Field;
import main.Application;

import java.util.ArrayList;

/**
 * Created by Attila on 2014.11.18..
 */
public class AgentManager implements RoundFinishedHandler{
    private ArrayList<Agent> agents;
    private GraphicHandler handler;
    private int agentNumber;
    private int agentRoundsFinished;
    private boolean allAgentsReady;

    public AgentManager (int K, GraphicHandler handler_)
    {
        handler = handler_;
        agents = new ArrayList<Agent>(K);
        agentNumber = K;
        agentRoundsFinished = 0;
        int fieldSize = Application.fieldManager.getFieldSize();

        for(int i = 0; i < K; i++)
        {
            int startposX = (int)Math.random() * fieldSize;
            int startposY = (int)Math.random() * fieldSize;
            agents.get(i).setField(new Field(startposX, startposY));
            agents.get(i).setRoundHandler(this);
        }
    }

    // ez teljesen rossz ki kell találni valamit.
    @Override
    public void onAgentRoundFinished(Agent agent) {
        if (agentRoundsFinished++ == agentNumber)
        {
            for(int i = 0; i < agentNumber; i++) {
                agents.get(i).setAllReady(true);
            }

            agentRoundsFinished = 0;
        }
    }
}
