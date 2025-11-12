package amazons.figures;

import amazons.IllegalMoveException;
import amazons.board.Board;
import amazons.board.Position;
import amazons.player.PlayerID;

import javax.swing.*;

public abstract class AbstractFigure implements Figure
{
    protected Position position;
    public PlayerID getPlayerID()
    {

        return PlayerID.NONE;

    }
    @Override
    public void setPosition(Position position)
    {

        this.position = position;

    }
    @Override
    public boolean canMoveTo(Position position, Board board)
    {

        return false;

    }
    @Override
    public abstract void moveTo(Position position, Board board) throws IllegalMoveException ;


}
