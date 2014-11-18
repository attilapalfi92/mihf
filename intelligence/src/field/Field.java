package field;

/**
 * Created by Attila on 2014.11.18..
 */
public class Field {
    int X, Y;
    double value;

    public Field (int x, int y, double value)
    {
        X = x;
        Y = y;
        this.value = value;
    }

    public Field (int x, int y)
    {
        X = x;
        Y = y;
        value = 0;
    }

    public Field ()
    {
        X = 0; Y = 0; value = 0;
    }

    public void setPos (int x, int y)
    {
        X = x;
        Y = y;
    }

    public void setValue (double value)
    {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
