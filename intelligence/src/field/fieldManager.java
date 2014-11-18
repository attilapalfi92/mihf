package field;

/**
 * Created by Attila on 2014.11.18..
 */
public class fieldManager {
    private double[][] field;
    private int X;
    private int Y;

    public fieldManager(int X, int Y) {
        this.X=X;
        this.Y=Y;
        this.field = new double[X][Y];
    }

    public void generateField()
    {
        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++)
            {
                // TODO: epic equation
                field[x][y] = 0;
            }
        }
    }

}
