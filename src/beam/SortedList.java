package beam;

import field.Field;
import field.FieldComparator;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Attila on 2014.12.12..
 */
public class SortedList implements ISortedList {
    private FieldComparator comparator;
    private TreeSet<Field> treeSet;
    private int limit;
    int elementNum;
    boolean full;

    public SortedList() {
        FieldComparator comparator = new FieldComparator();
        TreeSet<Field> treeSet = new TreeSet<Field>(comparator);
        elementNum = 0;
        full = false;
    }

    @Override
    public void setLimit(int K) {
        limit = K;
    }

    @Override
    public void addField(Field F) {
        if(full) {
            Field first = treeSet.first();
            int result = comparator.compare(F, first);
            // if F is smaller, result is -1
            // if F is larger, result is 1
            if (result > 0) {
                treeSet.remove(first);
            }
            treeSet.add(F);
        }
        else {
            treeSet.add(F);
            elementNum++;
        }
    }

    @Override
    public Field getField() {
        return treeSet.last();
    }

    @Override
    public Iterator<Field> iterator() {
        return treeSet.iterator();
    }
}
