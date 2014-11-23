package Log;

import field.Field;
import java.util.AbstractMap.SimpleEntry;

import java.util.ArrayList;

/**
 * Created by Attila on 2014.11.19..
 */
public class Logger {
    // done
    private static Field globalOptimum;
    //done
    //private static HashMap<Field, Integer> foundOptimums = new HashMap<Field, Integer>();
    private static ArrayList<SimpleEntry<Field, Integer>> foundOptimums2 = new ArrayList<SimpleEntry<Field, Integer>>();
    // done
    private static int numberOfBeams;
    // done
    //private static long searchTimeNano;
    private static ArrayList<Long> searchTimesNano = new ArrayList<Long>();
    // done
    private static int runCounter;

    public static void reInitialize() {
        globalOptimum = null;
        foundOptimums2 = new ArrayList<SimpleEntry<Field, Integer>>();
        numberOfBeams = 0;
        searchTimesNano = new ArrayList<Long>();

        //foundOptimums = new HashMap<Field, Integer>();
        //searchTimeNano = 0;
        runCounter++;

        /*
        File numberOfRunsFile = new File("numberofruns.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(numberOfRunsFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(runCounter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    public static ArrayList<Long> getSearchTimesNano() {
        return searchTimesNano;
    }

    public static int getRunCounter() {
        return runCounter;
    }

    public static void setRunCounter(int runCounter) {
        Logger.runCounter = runCounter;
    }

    public static void saveStatistics() {

    }

    public static Field getGlobalOptimum() {
        return globalOptimum;
    }

    public static void setGlobalOptimum(Field globalOptimum) {
        Logger.globalOptimum = globalOptimum;
    }

    /*public static HashMap<Field, Integer> getFoundOptimums() {
        return foundOptimums;
    }*/

    /*public static void setFoundOptimums(HashMap<Field, Integer> foundOptimums) {
        Logger.foundOptimums = foundOptimums;
    }*/

    public static int getNumberOfBeams() {
        return numberOfBeams;
    }

    public static void setNumberOfBeams(int numberOfBeams) {
        Logger.numberOfBeams = numberOfBeams;
    }

    /*public static long getSearchTimeNano() {
        return searchTimeNano;
    }

    public static void setSearchTimeNano(long searchTimeNano) {
        Logger.searchTimeNano = searchTimeNano;
    }*/

    public static ArrayList<SimpleEntry<Field, Integer>> getFoundOptimums2() {
        return foundOptimums2;
    }

    public static void setFoundOptimums2(ArrayList<SimpleEntry<Field, Integer>> foundOptimums2) {
        Logger.foundOptimums2 = foundOptimums2;
    }

    public static void finalizeLogging(Statistics statistics) {

        //statistics.getRunTimeValues().add(searchTimeNano);
        long maxTime = 0;
        for (int i = 0; i < searchTimesNano.size(); i++) {
            if (searchTimesNano.get(i) > maxTime)
                maxTime = searchTimesNano.get(i);
        }
        statistics.getRunTimeValues().add(maxTime);

        boolean globalFound2 = false;
        int globalOptimumStepCount2 = 0;
        int minimumOptimumStep2 = 10000;
        for (int i = 0; i < foundOptimums2.size(); i++) {
            if (foundOptimums2.get(i).getKey().getValue() == globalOptimum.getValue()) {
                globalFound2 = true;
                SimpleEntry<Field, Integer> pair = foundOptimums2.get(i);
                globalOptimumStepCount2 = pair.getValue();
                statistics.getFoundGlobalOptimumSteps().add(globalOptimumStepCount2);
                statistics.getFoundGlobalOptimumValues().add(pair.getKey().getValue());
                if(globalOptimumStepCount2 < minimumOptimumStep2){
                    minimumOptimumStep2 = globalOptimumStepCount2;
                }
            }
        }
        if (minimumOptimumStep2 < 10000)
            statistics.getFoundGlobalOptimumMinimumSteps().add(minimumOptimumStep2);

        if (globalFound2)
            statistics.setGlobalOptimumFoundCounter(statistics.getGlobalOptimumFoundCounter() + 1);


        /*boolean globalFound = false;
        Iterator it = foundOptimums.entrySet().iterator();
        int globalOptimumStepCount = 0;
        int minimumOptimumStep = 10000;
        while(it.hasNext()) {
            Map.Entry<Field, Integer> pairs = (Map.Entry<Field, Integer>)it.next();
            if (pairs.getKey().getValue() == globalOptimum.getValue()) {
                globalFound = true;
                globalOptimumStepCount = pairs.getValue();
                statistics.getFoundGlobalOptimumSteps().add(globalOptimumStepCount);
                statistics.getFoundGlobalOptimumValues().add(pairs.getKey().getValue());
                if(globalOptimumStepCount < minimumOptimumStep){
                    minimumOptimumStep = globalOptimumStepCount;
                }
            }
        }
        if(minimumOptimumStep < 10000)
            statistics.getFoundGlobalOptimumMinimumSteps().add(minimumOptimumStep);*/


        for (int i = 0; i < foundOptimums2.size(); i++) {
            SimpleEntry<Field, Integer> pair = foundOptimums2.get(i);
            statistics.getFoundOptimumSteps().add(pair.getValue());
            statistics.getFoundOptimumValues().add(pair.getKey().getValue());
        }


        //printWriter.println("All found optimums and step numbers:");
        /*Iterator it = foundOptimums.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Field, Integer> pairs = (Map.Entry<Field, Integer>)it.next();
            //printWriter.println(pairs.getKey() + ", step count: " + pairs.getValue());
            statistics.getFoundOptimumSteps().add(pairs.getValue());
            statistics.getFoundOptimumValues().add(pairs.getKey().getValue());
        }*/

    }
}
