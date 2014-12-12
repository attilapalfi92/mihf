package beam;

import field.Field;

/**
 * Created by Attila on 2014.12.12..
 */
public interface ISortedList {
    public void setLimit(int K);
    public void addField(Field F);
    public Field getField();
}
