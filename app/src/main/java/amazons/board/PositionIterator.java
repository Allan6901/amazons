package amazons.board;

import amazons.figures.Figure;
import javafx.geometry.Pos;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PositionIterator implements  Iterator<Position>
{
    private final Figure[][] matrix;
    private final int numberOfRows;
    private final int numberOfColumns;
    private int currentRow =0;
    private int currentColumn=0;

    public PositionIterator(Figure[][] matrix, int numberOfRows, int numberOfColumns) {
        this.matrix = matrix;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;

    }

    /**
     * Checks if there are more positions to iterate in the two-dimensional grid.
     *
     * @return {@code true} if there are more positions to iterate, {@code false} otherwise.
     */
    @Override
    public boolean hasNext() {
        return currentRow < numberOfRows && currentColumn < numberOfColumns;
    }

    /**
    *
    *Returns the next position in the two-dimensional grid.
    * @return The next {@link Position} in the grid.
    * @throws NoSuchElementException if there are no more elements to iterate.
    */
    @Override
    public Position next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements");
        }


        Position position = new Position(currentColumn, currentRow );

        if(currentColumn < numberOfColumns - 1)
            currentColumn ++;
        else{
            currentColumn = 0;
            currentRow ++;
        }

        return position;
    }

}


