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

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
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

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setPos (int x, int y)
    {
        X = x;
        Y = y;
    }

    public boolean Equal(Field f){
        if(f.getX()==getX() && f.getY()==getY())
            return true;
        return false;
    }

    public void setValue (double value)
    {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new String("x = " + X + "\t" + "y = " + Y + "\t" + "value = " + value);
    }
}
