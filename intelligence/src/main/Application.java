package main;

import Gui.Window;
import agent.AgentManager;
import field.FieldManager;

public class Application {

    // 6000x6000 field size
    public static FieldManager fieldManager = new FieldManager(600);

    public static void main(String[] args ){
        fieldManager.generateField();
        Window window = new Window();
        AgentManager agentManager = new AgentManager(1, window.getPanel());
        //System.gc();
    }

}