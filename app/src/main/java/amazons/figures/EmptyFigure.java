package amazons.figures;

import amazons.IllegalMoveException;
import amazons.board.Board;
import amazons.board.Position;

public class EmptyFigure extends AbstractFigure {

    public final static Figure EMPTY_FIGURE = new EmptyFigure();

    private EmptyFigure() {
    }

    @Override
    public String toString()
    {

        return "  ";

    }

    @Override
    public void moveTo(Position position, Board board) throws IllegalMoveException
    {

            throw new IllegalMoveException("Empty figure can't move");

    }

}


