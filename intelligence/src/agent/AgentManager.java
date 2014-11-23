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
    private ArrayList<Thread> threads;
    private ArrayList<Field> foundValues;
    private GraphicHandler handler;
    private int agentNumber;
    private Statistics statistics;
    private Object syncObject;
    private boolean GUI;
    private int agentRoundsFinished;

    public AgentManager(int K, GraphicHandler handler_, Statistics statistics, boolean gui) {
        handler = handler_;
        foundValues = new ArrayList<Field>();
        agentNumber = K;
        this.statistics = statistics;
        int fieldSize = Application.fieldManager.getFieldSize();
        agents = new ArrayList<Agent>();
        threads = new ArrayList<Thread>();
        syncObject = new Object();
        GUI = gui;
        agentRoundsFinished = 0;

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
        if (!GUI) {
            for (int i = 0; i < agentNumber; i++) {
                Agent temp = new Agent(GUI);
                int startposX = (int) (Math.random() * fieldSize);
                int startposY = (int) (Math.random() * fieldSize);
                temp.setField(new Field(startposX, startposY));
                temp.setRoundHandler(this);
                agents.add(temp);
                ReturnStructure foundOptimum = temp.runSingeThreaded();
                foundValues.add(foundOptimum.getField());
                Logger.getFoundOptimums2().add(new Pair<Field, Integer>(foundOptimum.getField(), foundOptimum.getStepCount()));

                Logger.getSearchTimesNano().add(foundOptimum.getRunTime());
            }
            Logger.finalizeLogging(statistics);
        }
        else
        {
            for (int i = 0; i < agentNumber; i++)
            {
                Agent temp = new Agent(GUI);
                int startposX = (int) (Math.random() * fieldSize);
                int startposY = (int) (Math.random() * fieldSize);
                temp.setField(new Field(startposX, startposY));
                temp.setRoundHandler(this);
                temp.setFinishedHandler(this);
                agents.add(temp);
                Thread t = new Thread(temp);
                threads.add(t);
                t.start();
            }

        }

    }

    // szerintem már jó, mutasd meg dorkának, ő majd code reviewolja
    @Override
    public void onAgentRoundFinished(Agent agent) {
        // majd kérünk egy kirajzolást
        if (GUI) {
            synchronized (syncObject)
            {
                // ha minden ágens befejezte a kört
                if (++agentRoundsFinished >= agentNumber) {

                    // egyesével újraengedélyezzük őket
                    for(int i = 0; i < agentNumber; i++) {
                        agents.get(i).setReadyToRun(true);
                    }

                    // majd kérünk egy kirajzolást
                    if(handler != null)
                        handler.onRedraw(agents, foundValues);
                    else
                        System.out.println(foundValues);

                    agentRoundsFinished = 0;
                }
            }
        }
    }

    @Override
    public void onAgentFinishedRunning(Agent agent, double foundValue, int stepCount) {
        if(GUI) {
            synchronized (syncObject) {
                agents.remove(agent);
                agentNumber--;
                Field found = new Field(agent.getField().getX(), agent.getField().getY(), foundValue);
                foundValues.add(found);
                //Logger.getFoundOptimums().put(found, stepCount);

                if (agentNumber == 0) {
                    //long totalRunTime = System.nanoTime() - startTimeNano;
                    //Logger.setSearchTimeNano(totalRunTime);
                    //Logger.finalizeLogging(statistics);
                    System.out.println("Search finished!");
                    //System.exit(0);
                }
            }
        }

    }
}
