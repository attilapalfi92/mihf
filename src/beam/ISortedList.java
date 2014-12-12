package beam;

import field.Field;

import java.util.Iterator;

/**
 * Created by Attila on 2014.12.12..
 */
public interface ISortedList {
    public void setLimit(int K);
    public void addField(Field F);
    public Field getField();
    public Iterator<Field> iterator();
}
