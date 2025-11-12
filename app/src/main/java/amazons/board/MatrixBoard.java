package amazons.board;

import amazons.figures.Figure;

import java.util.*;

public class MatrixBoard extends AbstractBoard implements Board {

    private final Figure[][] board;

    /**
     *Instantiates a matrix, then fills it with EmptyFigure
     */
    public MatrixBoard(int numberOfColumns, int numberOfRows) {
        super(numberOfColumns,numberOfRows);
        this.board = new Figure[numberOfColumns][numberOfRows];
        fill(new EmptyFigureGenerator());
    }

    /**
     * @param position: where the figure has to be positioned
     * @param figure: the figure to be placed at position {@code position}
     */
    @Override
    public void setFigure(Position position, Figure figure) {
        this.board[position.getX()][position.getY()] = figure;
    }

    /**
     * @param position: position of the figure to return
     * @return Figure at position in the board
     */
    @Override
    public Figure getFigure(Position position) {
        return this.board[position.getX()][position.getY()];
    }

    /**
     * Makes the matrix iterable
     * @return Iterator<Figure>
     */
    @Override
    public Iterator<Figure> iterator()
    {
        return new MatrixIterator<>(getNumberOfColumns(), getNumberOfRows(), this.board);
    }


    /**
     *Makes the matrix position iterable
     * @return Iterator<Position>
     */
    @Override
    public Iterator<Position> positionIterator() {
        return new PositionIterator(this.board,getNumberOfRows(),getNumberOfColumns());
    }

    /**
     * Initializes the matrix by filling it with EmptyFigure
     * @param generator FigureGenerator instance generator
     */
    @Override
    public void fill(FigureGenerator generator) {
        for(int column = 0; column < numberOfColumns; column++) {
            for(int row = 0; row < numberOfRows; row++ ) {
                board[column][row] = generator.nextFigure(new Position(column, row));
            }
        }
    }
}
