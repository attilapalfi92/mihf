package field;

import java.util.Comparator;

/**
 * Created by Attila on 2014.12.12..
 */
public class FieldComparator implements Comparator<Field> {

    @Override
    public int compare(Field o1, Field o2) {
        if(o1.getValue() <= o2.getValue())
            return -1;

        return 1;
    }
}
