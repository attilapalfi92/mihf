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
        double delta = ((double)100)/(fieldSize*fieldSize);
        double current = 0;
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++)
            {
                //field
                double mi = Math.random();
                // TODO: epic equation
                // ideiglenesen feltöltöm 0-100 közötti értékekkel

                field[x][y] = new Field(x, y, current);
                current += delta;
            }
        }
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Field getField(int x, int y)
    {
        return field[x][y];
    }
}