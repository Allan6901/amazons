package amazons.figures;

import amazons.IllegalMoveException;
import amazons.board.Board;
import amazons.board.Position;

public class ArrowFigure extends AbstractFigure {
    public final static Figure ARROW_FIGURE = new ArrowFigure();

    private ArrowFigure() {
    }

    @Override
    public String toString()
    {
        return "XX";
    }


    @Override
    public void moveTo(Position position, Board board) throws IllegalMoveException
    {
            throw new IllegalMoveException("Arrow figure can't move");
    }
}
