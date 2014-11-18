package agent;

import Events.RoundFinishedHandler;
import field.Field;
import main.Application;

import java.util.ArrayList;

/**
 * Created by Adam on 2014.11.18..
 */
public class Agent extends Thread {
    private Field field;
    private RoundFinishedHandler roundHandler;
    private boolean readyToRun;

    public Field getField() {
        return field;
    }

    public void setReadyToRun(boolean readyToRun) {
        this.readyToRun = readyToRun;
    }

    public void setRoundHandler(RoundFinishedHandler roundHandler) {
        this.roundHandler = roundHandler;
    }

    public Agent()
    {

    }

    public void setField (Field field) {
        this.field = field;
    }

    @Override
    public void run() {


        int numberOfStays = 0;
        while (numberOfStays < 4) {

            // ha ez készenáll a futásra, akkor semmiképp se alszik.
            while (!readyToRun) {
                try {
                    sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

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
                }
                else if (values[i] > maxValue) {
                    maxIndexes.clear();
                    maxIndexes.add(i);
                    maxValue = values[i];
                }
            }

            int maxIndex = maxIndexes.get((int)(Math.random()*maxIndexes.size()));

            switch (maxIndex) {
                case 0:
                    field.setY(field.getY() - 1);
                    numberOfStays = 0;
                    break;
                case 1:
                    field.setX(field.getX() - 1);
                    numberOfStays = 0;
                    break;
                case 2:
                    numberOfStays++;
                    break;
                case 3:
                    field.setX(field.getX() + 1);
                    numberOfStays = 0;
                    break;
                case 4:
                    field.setY(field.getY() + 1);
                    numberOfStays = 0;
                    break;
                default:
                    break;
            }

            readyToRun = false;
            roundHandler.onAgentRoundFinished(this);
        }

    }


}
