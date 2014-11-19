package Log;

import field.Field;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Attila on 2014.11.19..
 */
public class Logger {
    // done
    private static Field globalOptimum;
    //done
    private static HashMap<Field, Integer> foundOptimums = new HashMap<Field, Integer>();
    // done
    private static int numberOfBeams;
    // done
    private static long searchTimeNano;
    // done
    private static int runCounter;

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

    public static HashMap<Field, Integer> getFoundOptimums() {
        return foundOptimums;
    }

    public static void setFoundOptimums(HashMap<Field, Integer> foundOptimums) {
        Logger.foundOptimums = foundOptimums;
    }

    public static int getNumberOfBeams() {
        return numberOfBeams;
    }

    public static void setNumberOfBeams(int numberOfBeams) {
        Logger.numberOfBeams = numberOfBeams;
    }

    public static long getSearchTimeNano() {
        return searchTimeNano;
    }

    public static void setSearchTimeNano(long searchTimeNano) {
        Logger.searchTimeNano = searchTimeNano;
    }

    public static void writeFile() {
        String filename = new String("run_" + numberOfBeams + "_beams_and_number_" + runCounter + ".txt");
        try {
            FileWriter writer = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println(numberOfBeams + " beams.");
            printWriter.println("Run counter: " + runCounter);
            printWriter.println("Global optimum: " + globalOptimum);

            boolean globalFound = false;
            Iterator it = foundOptimums.entrySet().iterator();
            int globalOptimumStepCount = 0;
            while(it.hasNext()) {
                //Map.Entry pairs = (Map.Entry)it.next();
                Map.Entry<Field, Integer> pairs = (Map.Entry<Field, Integer>)it.next();
                if (pairs.getKey() == globalOptimum) {
                    globalFound = true;
                    globalOptimumStepCount = pairs.getValue();
                }
            }
            printWriter.println("Global optimum found: " + globalFound);
            if (globalFound) {
                printWriter.println("Global optimum step count: " + globalOptimumStepCount);
            }

            printWriter.println("All found optimums and step numbers:");
            it = foundOptimums.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry<Field, Integer> pairs = (Map.Entry<Field, Integer>)it.next();
                printWriter.println(pairs.getKey() + ", step count: " + pairs.getValue());
            }


            printWriter.println("Total search time in nanos: " + searchTimeNano);

            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
