package Gui;

import Events.GraphicHandler;
import Log.Logger;
import agent.Agent;
import field.Field;
import main.Application;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Attila on 2014.11.18..
 */
public class Panel extends JPanel implements GraphicHandler {
    private int resolution;
    private int pixelCount;
    private Color[][] fieldColors;
    private ArrayList<Agent> agents;
    private ArrayList<Field> foundFields;

    public Panel(int resolution_, int pixelCount_) {
        resolution = resolution_;
        pixelCount = pixelCount_;
        fieldColors = new Color[pixelCount][pixelCount];
        agents = new ArrayList<Agent>();
        foundFields = new ArrayList<Field>();
        recalculateFieldColors();
    }

    public void recalculateFieldColors(){
        double absoluteMaxVal = 0;
        Field absoluteMaxField = null;
        for (int i = 0; i < resolution; i++) {
            for (int j = 0; j < resolution; j++) {
                double val = Application.fieldManager.getField(i, j).getValue();
                if (val > absoluteMaxVal) {
                    absoluteMaxVal = val;
                    absoluteMaxField = new Field(i, j, absoluteMaxVal);
                }
            }
        }

        Logger.setGlobalOptimum(absoluteMaxField);

        int pointsPerPixel = resolution / pixelCount;
        for(int x = 0; x < pixelCount; x++) {
            for (int y = 0; y < pixelCount; y++) {

                // searching the max value
                double maxVal = 0;
                double value;
                for (int i = 0; i < pointsPerPixel; i ++) {
                    for (int j = 0; j < pointsPerPixel; j++) {
                        value = Application.fieldManager.getField(x * pointsPerPixel + i, y * pointsPerPixel + j).getValue();
                        if (value > maxVal)
                            maxVal = value;
                    }
                }

                value = maxVal;
                // convert the value between 0 and 100
                value = (value / absoluteMaxVal) * 100;

                Color pixelColor;
                if (value < 25) {
                    pixelColor = new Color(0, (int)(value/25) * 255, 255);
                }
                else if (value < 50) {
                    pixelColor = new Color(0, 255, (int) (255 - ((value-25)/25) * 255));
                }
                else if (value < 75) {
                    pixelColor = new Color((int)((value-50)/25) * 255, 255, 0);
                }
                else {
                    pixelColor = new Color(255, (int) (255 - ((value-75)/25) * 255), 0);
                }

                fieldColors[x][y] = pixelColor;
            }
        }
    }

    @Override
    public void paint(Graphics g)
    {
        for(int x = 0; x < pixelCount; x++) {
            for (int y = 0; y < pixelCount; y++) {
                g.setColor(fieldColors[x][y]);
                g.fillRect(x, y, 1, 1);
            }
        }

        g.setColor(new Color(0, 0, 0));
        for(int i = 0; i < agents.size(); i++) {
            g.fillRect(agents.get(i).getField().getX() - 1, agents.get(i).getField().getY() - 1, 2, 2);
        }

        for(int i = 0; i < foundFields.size(); i++) {
            g.fillRect(foundFields.get(i).getX() - 1, foundFields.get(i).getY() - 1, 2, 2);
        }
    }

    @Override
    public void onRedraw(ArrayList<Agent> agents_, ArrayList<Field> foundFields_) {
        if (agents_ != agents)
            agents = agents_;
        if (foundFields != foundFields_)
            foundFields = foundFields_;
        repaint();
    }
}
