package amazons.board;

public class AbstractIterator<T>
{
    private final T[][] matrix;
    private final int numberOfRows;
    private final int numberOfColumns;
    private int currentRow;
    private int currentColumn;


    public AbstractIterator(T[][] matrix, int numberOfRows, int numberOfColumns, int currentRow, int currentColumn)
    {
        this.matrix = matrix;

        this.numberOfRows = numberOfRows;

        this.numberOfColumns = numberOfColumns;

        this.currentRow = currentRow;

        this.currentColumn = currentColumn;

    }

    public T[][] getMatrix()
    {
        return matrix;
    }

    public int getNumberOfRows()
    {

        return numberOfRows;

    }

    public int getNumberOfColumns()
    {

        return numberOfColumns;

    }

    public int getCurrentRow()
    {

        return currentRow;

    }

    public int getCurrentColumn()
    {

        return currentColumn;

    }
}
