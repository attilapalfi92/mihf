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

    // each time a beam finds a global optimum, it's value must be added to this list
    private ArrayList<Double> foundGlobalOptimumValues = new ArrayList<Double>();

    private ArrayList<Double> realGlobalOptimumValues = new ArrayList<Double>();

    private ArrayList<Long> runTimeValues = new ArrayList<Long>();

    public Statistics(int runNumber, int beamNumber) {
        this.runNumber = runNumber;
        this.beamNumber = beamNumber;
    }

    public void createStatistics() {
        float globalOptimumFindRate = ((float)foundGlobalOptimumSteps.size()) / runNumber;
        System.out.println("Number of beams: " + beamNumber);
        System.out.println("Number of searches: " + runNumber);
        System.out.println("Rate of finding global optimums: " + globalOptimumFindRate);

        int allStepsToGlobalOpt = 0;
        for (int i = 0; i < foundGlobalOptimumSteps.size(); i++) {
            allStepsToGlobalOpt += foundGlobalOptimumSteps.get(i);
        }
        double averageStepsToGlobalOpt = 0;
        if(foundGlobalOptimumSteps.size() > 0) {
            averageStepsToGlobalOpt = ((double)allStepsToGlobalOpt) / foundGlobalOptimumSteps.size();
            System.out.println("Average steps to find global optimums: " + averageStepsToGlobalOpt);
        }
        else
            System.out.println("Average steps to find global optimums: " + "No global optimums found.");

        int allStepsToOpt = 0;
        for (int i = 0; i < foundOptimumSteps.size(); i++) {
            allStepsToOpt += foundOptimumSteps.get(i);
        }
        double averageStepsToOpt = ((double)allStepsToOpt) / foundOptimumSteps.size();
        System.out.println("Average steps to find optimums: " + averageStepsToOpt);

        double allValuesOfGlobalOpt = 0;
        for (int i = 0; i < foundGlobalOptimumValues.size(); i++) {
            allValuesOfGlobalOpt += foundGlobalOptimumValues.get(i);
        }
        if (foundGlobalOptimumValues.size() > 0) {
            double averageGlobalOptimumValues = allValuesOfGlobalOpt / foundGlobalOptimumValues.size();
            System.out.println("Average values of found global optimums: " + averageGlobalOptimumValues);
        }
        else
            System.out.println("Average values of found global optimums: " + "No global optimums found.");

        double allValuesOfOpt = 0;
        for (int i = 0; i < foundOptimumValues.size(); i++) {
            allValuesOfOpt += foundOptimumValues.get(i);
        }
        double averageOptimumValues = allValuesOfOpt / foundOptimumValues.size();
        System.out.println("Average values of found optimums: " + averageOptimumValues);


        double allValuesOfRealGlobalOpt = 0;
        for (int i = 0; i < realGlobalOptimumValues.size(); i++) {
            allValuesOfRealGlobalOpt += realGlobalOptimumValues.get(i);
        }
        double averageOptimumRealGlobalOptimumValues = allValuesOfRealGlobalOpt / realGlobalOptimumValues.size();
        System.out.println("Average values of real global optimums: " + averageOptimumRealGlobalOptimumValues);

        long allRunTimeValues = 0;
        for (int i = 0; i < runTimeValues.size(); i++)
            allRunTimeValues += runTimeValues.get(i);

        double averageRunTimeValues = (double)allRunTimeValues / runTimeValues.size();
        System.out.println("Average values of run times: " + averageRunTimeValues);
    }

    public ArrayList<Double> getFoundGlobalOptimumValues() {
        return foundGlobalOptimumValues;
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

    public ArrayList<Double> getRealGlobalOptimumValues() {
        return realGlobalOptimumValues;
    }

    public ArrayList<Long> getRunTimeValues() {
        return runTimeValues;
    }
}
