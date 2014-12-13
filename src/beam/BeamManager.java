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
    //private ArrayList<Field> temp;
    //private ArrayList<Field> sortedList;
    private ArrayList<Field> temp;
    private SortedList sortedList;
    private GraphicHandler handler;
    private Statistics statistics;
    private int sizeOfBeam;
    private int timesOfNoNewOpt;
    private int maxTimesOfNoNewOpt;

    public BeamManager(int K, GraphicHandler h, Statistics s, boolean gui, int t){
        handler = h;
        statistics = s;
        timesOfNoNewOpt = 0;
        maxTimesOfNoNewOpt = t;
        sizeOfBeam = K;
        temp = new ArrayList<Field>();
        //sortedList = new SortedList(K);
        sortedList = new SortedList(600*600);
        sizeOfBeam = 600 * 600;
    }

    public void doTheSearch(){
        int stepCount = 0;
        long searchTime=System.nanoTime();
        int fieldSize= Application.fieldManager.getFieldSize();
        double lastMaximumValue = 0;
        for(int i = 0; i < sizeOfBeam; i++){
            int startposX = (int) (Math.random() * fieldSize);
            int startposY = (int) (Math.random() * fieldSize);
            sortedList.addField(Application.fieldManager.getField(startposX, startposY));
        }

        while(timesOfNoNewOpt < maxTimesOfNoNewOpt){
            long checktime = System.currentTimeMillis();
            temp.clear();
            for (Iterator<Field> i = sortedList.iterator(); i.hasNext();){
                temp.add(i.next());
            }

            if (handler != null){
                handler.onRedrawBeams(temp, null);
                long sleeptime = checktime + 100 - System.currentTimeMillis();
                if(sleeptime > 0)
                    try {
                        Thread.sleep(sleeptime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }

            for (int i = 0; i < temp.size(); i++) {
                Field currField = temp.get(i);
                int x=currField.getX();
                int y=currField.getY();
                sortedList.addField(Application.fieldManager.getField(x + 1, y));
                sortedList.addField(Application.fieldManager.getField(x, y + 1));
                sortedList.addField(Application.fieldManager.getField(x, y - 1));
                sortedList.addField(Application.fieldManager.getField(x - 1, y));
            }

            checktime = System.currentTimeMillis();
            if(handler != null){
                handler.onRedrawBeams(sortedList.iterator(), temp);
                long sleeptime = checktime + 500 - System.currentTimeMillis();
                if(sleeptime > 0)
                    try {
                        Thread.sleep(sleeptime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }

            if(lastMaximumValue == sortedList.getField().getValue()){
                timesOfNoNewOpt++;
            }else{
                timesOfNoNewOpt = 0;
                lastMaximumValue = sortedList.getField().getValue();
            }
            stepCount++;
        }
        //TODO: rewrite this
        if(handler == null) {
            Logger.getFoundOptimums2().add(new AbstractMap.SimpleEntry<Field, Integer>(sortedList.getField(), stepCount));
            Logger.getSearchTimesNano().add(System.nanoTime() - searchTime);
            Logger.finalizeLogging(statistics);
        }
        //System.out.println("The searh has ended"+String.valueOf(temp.get(0).getValue()));
    }

}
