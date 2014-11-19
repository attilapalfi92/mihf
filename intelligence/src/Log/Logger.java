package Log;

import field.Field;

import java.util.ArrayList;

/**
 * Created by Attila on 2014.11.19..
 */
public class Logger {
    // done
    private static Field globalOptimum;
    //done
    private static ArrayList<Field> foundOptimums = new ArrayList<Field>();
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

    public static ArrayList<Field> getLocalOptimums() {
        return localOptimums;
    }

    public static void setLocalOptimums(ArrayList<Field> localOptimums) {
        Logger.localOptimums = localOptimums;
    }

    public static ArrayList<Field> getFoundOptimums() {
        return foundOptimums;
    }

    public static void setFoundOptimums(ArrayList<Field> foundOptimums) {
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
}
