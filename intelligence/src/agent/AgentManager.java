package agent;

import field.Field;
import main.ClimberMain;

import java.util.ArrayList;

/**
 * Created by Attila on 2014.11.18..
 */
public class AgentManager {
    private ArrayList<Agent> agents;

    public AgentManager (int K)
    {
        agents = new ArrayList<Agent>(K);
        int fieldSize = ClimberMain.fieldManager.getFieldSize();

        for(int i = 0; i < K; i++)
        {
            int startposX = (int)Math.random() * fieldSize;
            int startposY = (int)Math.random() * fieldSize;
            agents.get(i).setField(new Field(startposX, startposY));

        }
    }
}
