package agent;

import field.Field;
import main.ClimberMain;

/**
 * Created by Adam on 2014.11.18..
 */
public class Agent extends Thread {

    private int x;
    private int y;
    private Field currentField;

    public Agent()
    {

    }

    public void setField (Field field) {
        this.currentField = field;
    }

    @Override
    public void run() {
        int numberOfStays = 0;
        while (numberOfStays < 4) {

            double values[] = new double[]{
                    ClimberMain.fieldManager.getField(currentField.getX(), currentField.getY() - 1).getValue(),
                    ClimberMain.fieldManager.getField(currentField.getX() - 1, currentField.getY()).getValue(),
                    ClimberMain.fieldManager.getField(currentField.getX(), currentField.getY()).getValue(),
                    ClimberMain.fieldManager.getField(currentField.getX() + 1, currentField.getY()).getValue(),
                    ClimberMain.fieldManager.getField(currentField.getX(), currentField.getY() + 1).getValue()
            };

            double maxValue = 0;
            int maxIndex = -1;
            for (int i = 0; i < 5; i++) {
                if (values[i] > maxValue) {
                    maxValue = values[i];
                    maxIndex = i;
                }
            }

            switch (maxIndex) {
                case 0:
                    y -= 1;
                    numberOfStays = 0;
                    break;
                case 1:
                    x -= 1;
                    numberOfStays = 0;
                    break;
                case 2:
                    numberOfStays++;
                    break;
                case 3:
                    x += 1;
                    numberOfStays = 0;
                    break;
                case 4:
                    y += 1;
                    numberOfStays = 0;
                    break;
                default:
                    break;
            }
        }
    }
}
