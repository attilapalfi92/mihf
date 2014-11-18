package Gui;

import main.Application;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Attila on 2014.11.18..
 */
public class Window extends JWindow{
    private Panel panel;
    private int windowSize;

    public Window () {
        windowSize = 600;
        panel = new Panel(Application.fieldManager.getFieldSize(), windowSize);
        this.add(panel);
        this.setSize(windowSize, windowSize);
        this.setMinimumSize(new Dimension(windowSize, windowSize));
        this.setMaximumSize(new Dimension(windowSize, windowSize));
    }

    public Panel getPanel (){
        return panel;
    }
}
