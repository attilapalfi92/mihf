package Gui;

import Events.GraphicHandler;
import agent.Agent;
import field.Field;
import main.Application;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Attila on 2014.11.18..
 */
public class Panel extends JPanel implements GraphicHandler {
    private int resolution;
    private int pixelCount;
    private Color[][] fieldColors;
    private ArrayList<Agent> agents;
    private ArrayList<Field> foundFields;
    private ArrayList<Field> temp;
    private Iterator<Field> foundIterator;
    private boolean beams;
    private boolean newGenerationDraw;

    public Panel(int resolution_, int pixelCount_) {
        resolution = resolution_;
        pixelCount = pixelCount_;
        fieldColors = new Color[pixelCount][pixelCount];
        agents = new ArrayList<Agent>();
        foundFields = new ArrayList<Field>();
        temp = new ArrayList<Field>();
        beams=false;
        recalculateFieldColors();
        newGenerationDraw = false;
    }

    public void setBeams(boolean b){
        beams=b;
    }

    public void recalculateFieldColors(){
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
                value = (value / Application.fieldManager.getGlobalOptimum().getValue()) * 100;

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
        if(beams){
            g.setColor(new Color(255, 255, 255));

            if(temp!=null){
                for(int i = 0; i < temp.size(); i++) {
                    g.fillRect(temp.get(i).getX() - 1, temp.get(i).getY() - 1, 2, 2);
                }
            }

            if(newGenerationDraw) {
                g.setColor(new Color(0, 0, 0));
                while(foundIterator.hasNext())
                {
                    Field temp = foundIterator.next();
                    g.fillRect(temp.getX() - 1, temp.getY() - 1, 2, 2);
                }
            }

            else
            {
                g.setColor(new Color(0, 0, 0));
                for(int i = 0; i < foundFields.size(); i++) {
                    g.fillRect(foundFields.get(i).getX() - 1, foundFields.get(i).getY() - 1, 2, 2);
                }
            }



        }else{
            g.setColor(new Color(0, 0, 0));
            for(int i = 0; i < agents.size(); i++) {
                g.fillRect(agents.get(i).getField().getX() - 1, agents.get(i).getField().getY() - 1, 2, 2);
            }

            for(int i = 0; i < foundFields.size(); i++) {
                g.fillRect(foundFields.get(i).getX() - 1, foundFields.get(i).getY() - 1, 2, 2);
            }

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

    public void onRedrawBeams(ArrayList<Field> foundFields_, ArrayList<Field> temp_) {
        newGenerationDraw = false;
        if (temp != temp_)
            temp = temp_;
        if (foundFields != foundFields_)
            foundFields = foundFields_;
        repaint();
    }

    public void onRedrawBeams(Iterator<Field> foundIterator_, ArrayList<Field> temp_) {
        newGenerationDraw = true;
        foundIterator = foundIterator_;
        if (temp != temp_)
            temp = temp_;
        repaint();
    }

    public void setNewGenerationDraw(boolean newGenerationDraw) {
        this.newGenerationDraw = newGenerationDraw;
    }
}
