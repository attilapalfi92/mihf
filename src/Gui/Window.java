package Gui;

import main.Application;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Attila on 2014.11.18..
 */
public class Window extends JFrame{
    private Panel panel;
    private int windowSize;

    public Window () {
        super();
        windowSize = 600;
        panel = new Panel(Application.fieldManager.getFieldSize(), windowSize);
        this.add(panel);
        this.setSize(windowSize, windowSize);
        this.setMinimumSize(new Dimension(windowSize, windowSize));
        this.setMaximumSize(new Dimension(windowSize, windowSize));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public Panel getPanel (){
        return panel;
    }
}
