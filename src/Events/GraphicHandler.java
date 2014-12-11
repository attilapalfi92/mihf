package Events;

import agent.Agent;
import field.Field;

import java.util.ArrayList;

/**
 * Created by Attila on 2014.11.18..
 */
public interface GraphicHandler {
    public void onRedraw(ArrayList<Agent> agents, ArrayList<Field> foundFields);
    public void onRedrawBeams(ArrayList<Field> activeFields_, ArrayList<Field> foundFields_);
}
