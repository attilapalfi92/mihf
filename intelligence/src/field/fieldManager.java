package field;

/**
 * Created by Attila on 2014.11.18..
 */
public class fieldManager {
    private double[][] field;
    private int width;
    private int height;

    public fieldManager(int X, int Y) {
        width=X;
        height=Y;
        this.field = new double[Y][X];
    }

    public void generateField()
    {

    }

}
