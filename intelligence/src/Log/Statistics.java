package Log;

import java.util.ArrayList;

/**
 * Created by Attila on 2014.11.19..
 */
public class Statistics {
    private int runNumber = 0;
    private int beamNumber = 0;

    // each time a beam finds an optimum, it's step number must be added to this list
    private ArrayList<Integer> foundOptimumSteps = new ArrayList<Integer>();

    // each time a beam finds the global optimum, it's step number must be added to this list
    private ArrayList<Integer> foundGlobalOptimumSteps = new ArrayList<Integer>();

    // each time a beam finds an optimum, it's value must be added to this list
    private ArrayList<Double> foundOptimumValues = new ArrayList<Double>();

    private double globalOptimumValue;

    public Statistics(int runNumber, int beamNumber) {
        this.runNumber = runNumber;
        this.beamNumber = beamNumber;
        globalOptimumValue = 0;
    }

    public int getRunNumber() {
        return runNumber;
    }

    public void setRunNumber(int runNumber) {
        this.runNumber = runNumber;
    }

    public int getBeamNumber() {
        return beamNumber;
    }

    public void setBeamNumber(int beamNumber) {
        this.beamNumber = beamNumber;
    }

    public ArrayList<Integer> getFoundOptimumSteps() {
        return foundOptimumSteps;
    }

    public ArrayList<Integer> getFoundGlobalOptimumSteps() {
        return foundGlobalOptimumSteps;
    }

    public ArrayList<Double> getFoundOptimumValues() {
        return foundOptimumValues;
    }

    public double getGlobalOptimumValue() {
        return globalOptimumValue;
    }

    public void setGlobalOptimumValue(double globalOptimumValue) {
        this.globalOptimumValue = globalOptimumValue;
    }
}
