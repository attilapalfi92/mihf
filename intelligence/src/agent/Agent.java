package agent;

import field.Field;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by Adam on 2014.11.18..
 */
public class Agent {

    private int x;
    private int y;


    public double run(Field f) {

        double values[] = new double[] {
            f.getFieldValue(x,y - 1), f.getFieldValue(x - 1,y), f.getFieldValue(x,y), f.getFieldValue(x + 1,y), f.getFieldValue(x,y + 1)} ;
        double maxValue = 0;
        int maxI = -1;
        for (int i = 0; i < 5; i++) {
            if (values[i] > maxValue) {
                maxValue = values[i];
                maxI = i;
            }
        }
        switch (maxI) {
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
        return 0;
    }
}
