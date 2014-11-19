package Log;

import java.util.ArrayList;

/**
 * Created by Attila on 2014.11.19..
 */
public class Statistics {
    private static int runNumber = 0;
    private static int beamNumber = 0;

    // each time a beam finds an optimum, it's step number must be added to this list
    private static ArrayList<Integer> foundOptimumSteps = new ArrayList<Integer>();

    // each time a beam finds the global optimum, it's step number must be added to this list
    private static ArrayList<Integer> foundGlobalOptimumSteps = new ArrayList<Integer>();

    // each time a beam finds an optimum, it's value must be added to this list
    private static ArrayList<Double> foundOptimumValues = new ArrayList<Double>();

    public static int getRunNumber() {
        return runNumber;
    }

    public static void setRunNumber(int runNumber) {
        Statistics.runNumber = runNumber;
    }

    public static int getBeamNumber() {
        return beamNumber;
    }

    public static void setBeamNumber(int beamNumber) {
        Statistics.beamNumber = beamNumber;
    }

    public static ArrayList<Integer> getFoundOptimumSteps() {
        return foundOptimumSteps;
    }

    public static ArrayList<Integer> getFoundGlobalOptimumSteps() {
        return foundGlobalOptimumSteps;
    }

    public static ArrayList<Double> getFoundOptimumValues() {
        return foundOptimumValues;
    }
}
