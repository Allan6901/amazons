package amazons.board;

import amazons.IllegalMoveException;
import amazons.figures.Amazon;
import amazons.figures.ArrowFigure;
import amazons.figures.EmptyFigure;
import amazons.figures.Figure;

import java.util.Iterator;

public abstract class AbstractBoard implements Board
{
    protected final int numberOfColumns;

    protected final int numberOfRows;

    public AbstractBoard(int numberOfColumns, int numberOfRows)
    {
        this.numberOfColumns = numberOfColumns;

        this.numberOfRows = numberOfRows;

    }

    /**
     * Checks whether the box at position is empty or not
     * @param position: position to test
     * @return a boolean true : if is empty false else
     */
    @Override
    public boolean isEmpty(Position position)
    {

        if (!isOutOfBoard(position))
        {

            return getFigure(position).equals(EmptyFigure.EMPTY_FIGURE);

        }

        return false;

    }

    /**
     * Check if position is out of bound
     * @param position: position to test
     * @return a boolean : true if is out of bound false else
     */
    @Override
    public boolean isOutOfBoard(Position position)
    {

        return position.isOutOfBounds(numberOfColumns, numberOfRows);

    }

    /**
     *Moves a figure within the rules of the game
     * @param startPosition: the position of the figure to move
     * @param dstPosition: the destination position of the figure
     * @throws IllegalMoveException if movement is illegal
     */
    @Override
    public void moveFigure(Position startPosition, Position dstPosition) throws IllegalMoveException
    {

        Figure figure = getFigure(startPosition);

        figure.moveTo(dstPosition, this);

        setFigure(startPosition, EmptyFigure.EMPTY_FIGURE);

        setFigure(dstPosition, figure);

    }

    /**
     * Shoots an arrow in the table
     * @param startPosition: the origin of the arrow shot
     * @param arrowDstPosition: the destination of the arrow
     * @throws IllegalMoveException: if movement is illegal
     */
    @Override
    public void shootArrow(Position startPosition, Position arrowDstPosition) throws IllegalMoveException
    {

        Figure figure = new Amazon(startPosition, 0);

        if(!figure.canMoveTo(arrowDstPosition, this))

            throw new IllegalMoveException("Illegal move");

        shoot(arrowDstPosition);

    }

    /**
     * Put an arrow in the board
     * @param position: position
     */
    private void shoot(Position position)
    {

        setFigure(position, ArrowFigure.ARROW_FIGURE);

    }
    public int getNumberOfColumns()
    {

        return numberOfColumns;

    }

    public int getNumberOfRows()
    {

        return numberOfRows;

    }

    /**
     * returns a representation of the board
     * @return a string
     */
    public String toString()
    {

        String board ="";

        int row =0 ;

        while( row < getNumberOfRows() )
        {
            board = addString(board,"+----");

            board =body(board,row);

            row ++;

        }
        board = addString(board,"+----");

        board = bottom(board);

        return board ;

    }

    /**
     * Concatenates 2 strings
     * @param base leftString
     * @param add RightString
     * @return leftString + RightString
     */
    private String addString(String base, String add)
    {

        for (int column = 0; column < getNumberOfColumns(); column++)
            base += add;

        base += "+\n";

        return base;
    }

    /**
     * Returns the center of the matrix representation
     * @param base initial string
     * @param row matrix row
     * @return a string type
     */
    private String body(String base, int row)
    {

        for (int column = 0; column < getNumberOfColumns(); column++)
        {

            Figure figure = getFigure(new Position(column,row));

            base += "| "+figure.toString()+" ";

        }

        base += "| "+row+"\n";

        return base;

    }

    /**
     * returns the bottom of the matrix representation
     * @param base initial string
     * @return a string type
     */
    private String bottom(String base)
    {

        for (int column = 0; column < getNumberOfColumns(); column++)

            base += "  "+column+"  ";

        return base;

    }

}
