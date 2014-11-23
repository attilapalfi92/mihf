package agent;

import field.Field;

/**
 * Created by Attila on 2014.11.21..
 */
public class ReturnStructure {
    private Field field;
    private Integer stepCount;
    private long runTime;

    public ReturnStructure(Field field, Integer stepCount, long runTime) {
        this.field = field;
        this.stepCount = stepCount;
        this.runTime = runTime;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Integer getStepCount() {
        return stepCount;
    }

    public void setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }
}
