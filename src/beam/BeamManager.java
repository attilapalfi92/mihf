package beam;

import Events.GraphicHandler;
import Log.Logger;
import Log.Statistics;
import field.Field;
import main.Application;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Adam on 2014.12.11..
 */
public class BeamManager {
    //private ArrayList<Field> activeFields;
    //private ArrayList<Field> foundFields;
    private ArrayList<Field> activeFields;
    private SortedList foundFields;
    private GraphicHandler handler;
    private Statistics statistics;
    private boolean GUI;
    private int sizeOfBeam;
    private int timesOfNoNewOpt;
    private int maxTimesOfNoNewOpt;

    public BeamManager(int K, GraphicHandler h, Statistics s, boolean gui, int t){
        handler = h;
        statistics = s;
        GUI = gui;
        timesOfNoNewOpt = 0;
        maxTimesOfNoNewOpt = t;
        sizeOfBeam = K;
        activeFields = new ArrayList<Field>();
        foundFields = new SortedList();
    }

    public void doTheSearch(){
        int stepCount = 0;
        long searchTime=System.nanoTime();
        int fieldSize= Application.fieldManager.getFieldSize();
        double lastMaximumValue = 0;
        for(int i = 0; i < sizeOfBeam; i++){
            int startposX = (int) (Math.random() * fieldSize);
            int startposY = (int) (Math.random() * fieldSize);
            foundFields.addField(Application.fieldManager.getField(startposX, startposY));
        }

        while(timesOfNoNewOpt < maxTimesOfNoNewOpt){
            long checktime = System.currentTimeMillis();

            for(Iterator<Field> i=foundFields.iterator();i.hasNext();){
                activeFields.add(i.next());
            }

            if(handler != null){
                handler.onRedrawBeams(activeFields, null);
                long sleeptime = checktime + 100 - System.currentTimeMillis();
                if(sleeptime > 0)
                    try {
                        Thread.sleep(sleeptime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }

            for(int i = 0; i<activeFields.size();i++ ) {
                Field currField=activeFields.get(i);
                int x=currField.getX();
                int y=currField.getY();
                foundFields.addField(Application.fieldManager.getField(x + 1, y));
                foundFields.addField(Application.fieldManager.getField(x, y + 1));
                foundFields.addField(Application.fieldManager.getField(x, y - 1));
                foundFields.addField(Application.fieldManager.getField(x - 1, y));
            }

            checktime = System.currentTimeMillis();
            /*if(handler != null){
                handler.onRedrawBeams(activeFields, foundFields);
                long sleeptime = checktime + 500 - System.currentTimeMillis();
                if(sleeptime > 0)
                    try {
                        Thread.sleep(sleeptime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }*/

            if(lastMaximumValue==foundFields.getMaximumValue()){
                timesOfNoNewOpt++;
            }else{
                timesOfNoNewOpt=0;
                lastMaximumValue=foundFields.getMaximumValue();
            }
            stepCount++;
        }
        //TODO: rewrite this
        if(handler == null) {
            Logger.getFoundOptimums2().add(new AbstractMap.SimpleEntry<Field, Integer>(activeFields.get(0), stepCount));
            Logger.getSearchTimesNano().add(System.nanoTime() - searchTime);
            Logger.finalizeLogging(statistics);
        }
        //System.out.println("The searh has ended"+String.valueOf(activeFields.get(0).getValue()));
    }

}
