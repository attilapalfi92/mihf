package beam;

import Events.GraphicHandler;
import Log.Logger;
import Log.Statistics;
import agent.ReturnStructure;
import field.Field;
import main.Application;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Adam on 2014.12.11..
 */
public class BeamManager {
    private ArrayList<Field> activeFields;
    private ArrayList<Field> foundFields;
    private GraphicHandler handler;
    private Statistics statistics;
    private boolean GUI;
    private int sizeOfBeam;
    private int timesOfNoNewOpt;
    private int maxTimesOfNoNewOpt;

    public BeamManager(int K,GraphicHandler h,Statistics s,boolean gui,int t){
        handler=h;
        statistics=s;
        GUI=gui;
        timesOfNoNewOpt=0;
        maxTimesOfNoNewOpt=t;
        sizeOfBeam=K;
        activeFields =new ArrayList<Field>();
        foundFields =new ArrayList<Field>();
    }

    public void doTheSearch(){
        int stepCount=0;
        long searchTime=System.nanoTime();
        int fieldSize= Application.fieldManager.getFieldSize();
        double lastMaximumValue=0;
        for(int i=0;i<sizeOfBeam;i++){
            int startposX = (int) (Math.random() * fieldSize);
            int startposY = (int) (Math.random() * fieldSize);
            activeFields.add(Application.fieldManager.getField(startposX,startposY));
        }

        while(timesOfNoNewOpt<maxTimesOfNoNewOpt){
            long checktime=System.currentTimeMillis();
            if(handler!= null){
                handler.onRedrawBeams(activeFields,null);
                long sleeptime=checktime+100-System.currentTimeMillis();
                if(sleeptime>0)
                    try {
                        Thread.sleep(sleeptime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }

            for(Iterator<Field> i=activeFields.iterator();i.hasNext();){
                Field currField=i.next();
                foundFields.add(currField);
                int x=currField.getX();
                int y=currField.getY();
                foundFields.add(Application.fieldManager.getField(x+1,y));
                foundFields.add(Application.fieldManager.getField(x,y+1));
                foundFields.add(Application.fieldManager.getField(x,y-1));
                foundFields.add(Application.fieldManager.getField(x - 1, y));
            }
            checktime=System.currentTimeMillis();
            if(handler!= null){
                handler.onRedrawBeams(activeFields,foundFields);
                long sleeptime=checktime+500-System.currentTimeMillis();
                if(sleeptime>0)
                    try {
                        Thread.sleep(sleeptime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            activeFields.clear();

            for(int i=0;i<sizeOfBeam;i++) {
                Field maximalField = foundFields.get(0);
                int itNumb=0;
                for (int it = 0; it<foundFields.size();it++) {
                    Field temp=foundFields.get(it);
                    if(temp.getValue() > maximalField.getValue()) {
                        maximalField = temp;
                        itNumb=it;
                    }
                }
                activeFields.add(maximalField);
                foundFields.remove(itNumb);
            }
            foundFields.clear();
            if(lastMaximumValue==activeFields.get(0).getValue()){
                timesOfNoNewOpt++;
            }else{
                timesOfNoNewOpt=0;
                lastMaximumValue=activeFields.get(0).getValue();
            }
            stepCount++;
        }
        if(handler == null) {
            Logger.getFoundOptimums2().add(new AbstractMap.SimpleEntry<Field, Integer>(activeFields.get(0), stepCount));
            Logger.getSearchTimesNano().add(System.nanoTime() - searchTime);
            Logger.finalizeLogging(statistics);
        }
        //System.out.println("The searh has ended"+String.valueOf(activeFields.get(0).getValue()));
    }

}
