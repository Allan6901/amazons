package amazons.figures;

import amazons.IllegalMoveException;
import amazons.board.Board;
import amazons.board.CardinalDirection;
import amazons.board.Position;
import amazons.player.PlayerID;

import java.util.ArrayList;
import java.util.List;

public class Amazon extends MovableFigure implements Figure
{
    private final PlayerID player ;

    /**
     * Initialize amazon by using a Position and a number
     * @param position amazon position
     * @param number player
     */
    public Amazon(Position position, int number)
    {
        this.position = position;

        if( number == 0)

            this.player = PlayerID.PLAYER_ZERO;

        else if( number == 1)

            this.player = PlayerID.PLAYER_ONE;

        else

            this.player = PlayerID.NONE;

    }

    /**
     * define whether an amazon can move to Position in board
     * @param position: a valid position in {@code board}
     * @param board: a board on which this is placed
     * @return a boolean : true if amazon can move to position false else.
     */
    @Override
    public boolean canMoveTo(Position position, Board board) {

        if( !board.isEmpty(position) || !moveDiagonalOrNot(position) || board.isOutOfBoard(position) ||  this.position.equals(position)){

            return false;
        }

        return validMove(position, board);
    }

    /**
     * @param position : destination position
     * @return whether it's a diagonal movement or not
     */
    private boolean moveDiagonalOrNot(Position position)
    {
        if( position.getX() != this.position.getX() && position.getY() != this.position.getY() )
        {

            return Math.abs(this.position.getX() - position.getX()) == Math.abs(this.position.getY() - position.getY());

        }

        return true;

    }

    /**
     *
     * @param destinationPosition : destination position
     * @param board: a board on which this is placed
     * @return the correct path
     */
    private boolean validMove(Position destinationPosition, Board board)
    {
        CardinalDirection direction  = this.position.getDirection(destinationPosition);

        Position nextPosition = this.position.next(direction);

        while(!nextPosition.equals(destinationPosition))
        {
            if (!board.isEmpty(nextPosition))
            {

                return false;

            }

            nextPosition = nextPosition.next(direction);

        }

        return true;

    }

    /**
     * Change the position if it possible ( board not modified ).
     * @param position: the position to which this should be moved
     * @param board: a board on which this is placed
     */
    @Override
    public void moveTo(Position position, Board board) throws IllegalMoveException
    {

        if(canMoveTo(position, board))

            this.position = position;

        else

            throw new IllegalMoveException("Invalid move");

    }

    @Override
    public void setPosition(Position position)
    {
        super.setPosition(position);
    }

    @Override
    public PlayerID getPlayerID() {
        return this.player ;
    }

    /**
     * Figure out all position where an amazon can move .
     * @param board , board which could contains amazon and arrows
     * @return a list which contains all position where amazon can go.
     *
     */
    @Override
    public List<Position> getAccessiblePositions(Board board)
    {

        List<Position> accessiblePositions= new ArrayList<>();

        for(int column =0 ; column < board.getNumberOfColumns() ; column ++)
        {

            for (int row = 0; row < board.getNumberOfRows(); row++)
            {

                Position position = new Position(column, row);

                if (canMoveTo(position, board))

                    accessiblePositions.add(position);

            }

        }

        return accessiblePositions;

    }

    /**
     * returns a string describing the player's amazon
     * @return a string
     */
    @Override
    public String toString()
    {

        return "A"+getPlayerID().getIndex();

    }
    public boolean stuck(Board board)
    {
        return !getAccessiblePositions(board).isEmpty();

    }

}
