package agent;

import field.Field;
import main.ClimberMain;

/**
 * Created by Adam on 2014.11.18..
 */
public class Agent extends Thread {

    private int x;
    private int y;
    private Field field;

    public Agent()
    {

    }

    public void setField (Field field) {
        this.field = field;
    }

    @Override
    public void run() {

        /*while (lastPos != currectPost)
        {

        }*/

        double values[] = new double [] {
                ClimberMain.fieldManager.getField(x, y - 1).getValue(),
                ClimberMain.fieldManager.getField(x - 1, y).getValue(),
                ClimberMain.fieldManager.getField(x, y).getValue(),
                ClimberMain.fieldManager.getField(x + 1, y).getValue(),
                ClimberMain.fieldManager.getField(x, y + 1).getValue()
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
                break;
            case 1:
                x -= 1;
                break;
            case 2:
                break;
            case 3:
                x += 1;
                break;
            case 4:
                y+=1;
                break;
            default:
                break;
        }
    }
}
