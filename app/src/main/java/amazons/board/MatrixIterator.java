package amazons.board;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator<T> implements Iterator<T> {

    private final int numberOfRows;
    private final int numberOfColumns;
    private final T[][] matrix;
    private int currentRow;
    private int currentColumn;

    public MatrixIterator(int numberOfColumns, int numberOfRows, T[][] matrix) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.matrix = matrix;
        this.currentRow = 0;
        this.currentColumn = 0;
    }

    /**
     * checks if currentPosition is not out of bounds
     * @return true if currentPosition is oud=t of bounds , false else
     */
    @Override
    public boolean hasNext() {
        return currentRow < numberOfRows && currentColumn < numberOfColumns;
    }

    /**
     * @return returns the next iteration
     */
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the matrix");
        }

        T element = matrix[currentColumn][currentRow];

        currentColumn++;

        if (currentColumn == numberOfColumns) {
            currentColumn = 0;
            currentRow++;
        }

        return element;
    }


}