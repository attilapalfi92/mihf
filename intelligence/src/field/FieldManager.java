package field;


/**
 * Created by Attila on 2014.11.18..
 */
public class FieldManager {
    // change pls
    private Field[][] field;
    private int fieldSize;

    public FieldManager(int fieldSize) {
        this.fieldSize = fieldSize;
        field = new Field[fieldSize][fieldSize];
    }

    public void generateField()
    {
        int numberOfSamples = 50;
        double delta = ((double) 100) / (fieldSize * fieldSize);
        double current = 0;
        double m[] = new double[numberOfSamples];
        double hx[] = new double[numberOfSamples];
        double hy[] = new double[numberOfSamples];

        for (int i = 0; i < numberOfSamples; i++) {
            m[i] = Math.random();
            hx[i] = Math.random();
            hy[i] = Math.random();
        }
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {
               //field
                // TODO: check epic equation
                //megírtam a feltöltő függvényt. elvileg...
                double z = 0;
                for (int i = 0; i < numberOfSamples; i++) {
                    //TODO: check hy és hx, mert lehet nem ezeknek kéne lenniük
                    z = z + 10 * (1 + m[i]) * Math.exp(-800 * (Math.pow(x / fieldSize - hx[i], 2) + Math.pow(y / fieldSize - hy[i], 2)));
                }
                field[x][y] = new Field(x, y, z);
                //current += delta;
            }
        }
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Field getField(int x, int y)
    {
        if (x < fieldSize && y < fieldSize && x > 0 && y > 0)
            return field[x][y];
        else
            //bit of patchwork munka, ha tudunk ennél jobbat akkor mehet, exceptionre megállna a tömb feltöltése
            return new Field(0,0,0);
    }
}