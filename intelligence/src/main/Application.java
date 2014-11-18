package main;

import Gui.Window;
import agent.AgentManager;
import field.FieldManager;

import java.lang.String;

public class Application {

    // 6000x6000 field size
    public static FieldManager fieldManager = new FieldManager(6000);

    public static void main(String[] args ){
        Window window = new Window();

        AgentManager agentManager = new AgentManager(10, window.getPanel());

    }

}