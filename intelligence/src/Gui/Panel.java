package Gui;

import main.Application;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Attila on 2014.11.18..
 */
public class Panel extends JPanel {
    private int pixelCount;

    public Panel(int pixelCount) {
        this.pixelCount = pixelCount;
    }

    @Override
    public void paint(Graphics g)
    {
        for(int x = 0; x < pixelCount; x++) {
            for (int y = 0; y < pixelCount; y++) {
                double value = Application.fieldManager.getField(x, y).getValue();

                // convert the value between 0 and 100

                Color pixelColor;
                if (value < 25)
                {
                    pixelColor = new Color(0, (int)(value/25) * 255, 255);
                }
                else if (value < 50)
                {
                    pixelColor = new Color(0, 255, (int) (255 - ((value-25)/25) * 255));
                }
                else if (value < 75)
                {
                    pixelColor = new Color((int)((value-50)/25) * 255, 255, 0);
                }
                else
                {
                    pixelColor = new Color(255, (int) (255 - ((value-75)/25) * 255), 0);
                }
            }
        }
    }
}
