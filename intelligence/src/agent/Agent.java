package agent;

import field.Field;

/**
 * Created by Adam on 2014.11.18..
 */
public class Agent extends Thread {

    private int x;
    private int y;
    private Field field;

    public Agent(Field f)
    {
        field = f;
    }

    @Override
    public void run() {

        double values[] = new double[] {
            field.getFieldValue(x, y - 1),
            field.getFieldValue(x - 1, y),
            field.getFieldValue(x, y),
            field.getFieldValue(x + 1, y),
            field.getFieldValue(x, y + 1) };

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
