package Events;

import agent.Agent;
import field.Field;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Attila on 2014.11.18..
 */
public interface GraphicHandler {
    public void onRedraw(ArrayList<Agent> agents, ArrayList<Field> foundFields);
    public void onRedrawBeams(ArrayList<Field> foundFields_, ArrayList<Field> temp_);
    public void onRedrawBeams(Iterator<Field> foundIterator_, ArrayList<Field> temp_);
}
