package agent;

import field.Field;
import main.ClimberMain;

import java.util.ArrayList;

/**
 * Created by Adam on 2014.11.18..
 */
public class Agent extends Thread {
    private Field field;

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

            double values[] = new double[]{
                    ClimberMain.fieldManager.getField(field.getX(), field.getY() - 1).getValue(),
                    ClimberMain.fieldManager.getField(field.getX() - 1, field.getY()).getValue(),
                    ClimberMain.fieldManager.getField(field.getX(), field.getY()).getValue(),
                    ClimberMain.fieldManager.getField(field.getX() + 1, field.getY()).getValue(),
                    ClimberMain.fieldManager.getField(field.getX(), field.getY() + 1).getValue()
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

            int maxIndex = maxIndexes.get((int)Math.random()*maxIndexes.size());

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
        }

    }
}
