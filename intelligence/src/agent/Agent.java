package agent;

import Events.AgentFinishedRunning;
import Events.RoundFinishedHandler;
import field.Field;
import main.Application;

import java.util.ArrayList;

/**
 * Created by Adam on 2014.11.18..
 */
public class Agent {
    private Field field;
    private RoundFinishedHandler roundHandler;
    private AgentFinishedRunning finishedHandler;
    private static int IDcounter = 0;
    private int stepCounter;
    private int ID;
    private long startTimeNano;

    public Field getField() {
        return field;
    }

    public void setRoundHandler(RoundFinishedHandler roundHandler) {
        this.roundHandler = roundHandler;
    }

    public void setFinishedHandler(AgentFinishedRunning finishedHandler) {
        this.finishedHandler = finishedHandler;
    }

    public Agent() {
        stepCounter = 0;
        ID = IDcounter++;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public ReturnStructure run() {
        startTimeNano = System.nanoTime();
        int numberOfStays = 0;
        while (numberOfStays < 4) {

            // sajnálom, ez még szar. ha az ágens a pálya szélére ér, akkor out of bound exception lesz lépéskor.
            double values[] = new double[]{
                    Application.fieldManager.getField(field.getX(), field.getY() - 1).getValue(),
                    Application.fieldManager.getField(field.getX() - 1, field.getY()).getValue(),
                    Application.fieldManager.getField(field.getX(), field.getY()).getValue(),
                    Application.fieldManager.getField(field.getX() + 1, field.getY()).getValue(),
                    Application.fieldManager.getField(field.getX(), field.getY() + 1).getValue()
            };

            double maxValue = 0;
            ArrayList<Integer> maxIndexes = new ArrayList<Integer>();
            int currentMaxIndex = -1;

            for (int i = 0; i < 5; i++) {
                if (values[i] == maxValue) {
                    maxIndexes.add(i);
                } else if (values[i] > maxValue) {
                    maxIndexes.clear();
                    maxIndexes.add(i);
                    maxValue = values[i];
                }
            }

            int maxIndex = maxIndexes.get((int) (Math.random() * maxIndexes.size()));

            switch (maxIndex) {
                case 0:
                    field.setY(field.getY() - 1);
                    numberOfStays = 0;
                    //System.out.println(ID + ". agent steps upwards");
                    break;
                case 1:
                    field.setX(field.getX() - 1);
                    numberOfStays = 0;
                    //System.out.println(ID + ". agent steps left");
                    break;
                case 2:
                    numberOfStays++;
                    //System.out.println(ID + ". agent staying on his ass");
                    break;
                case 3:
                    field.setX(field.getX() + 1);
                    numberOfStays = 0;
                    //System.out.println(ID + ". agent steps right");
                    break;
                case 4:
                    field.setY(field.getY() + 1);
                    numberOfStays = 0;
                    //System.out.println(ID + ". agent steps downwards");
                    break;
                default:
                    break;
            }

            stepCounter++;
            roundHandler.onAgentRoundFinished(this);

            /*
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */
        }
        //finishedHandler.onAgentFinishedRunning(this,Application.fieldManager.getField(field.getX(), field.getY()).getValue(), stepCounter);

        long totalRunTime = System.nanoTime() - startTimeNano;
        //Pair<Field, Integer> v = new Pair<Field, Integer>(Application.fieldManager.getField(field.getX(), field.getY()), stepCounter);
        ReturnStructure ret = new ReturnStructure(Application.fieldManager.getField(field.getX(), field.getY()), stepCounter, totalRunTime);
        return ret;
    }


}
