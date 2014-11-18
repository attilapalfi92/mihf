package agent;

import java.util.ArrayList;

/**
 * Created by Attila on 2014.11.18..
 */
public class AgentManager {
    private ArrayList<Agent> agents;

    public AgentManager (int K)
    {
        agents = new ArrayList<Agent>(K);

    }
}
