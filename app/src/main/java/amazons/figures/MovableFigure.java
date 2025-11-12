package amazons.figures;

import amazons.board.Board;
import amazons.board.Position;

import java.util.List;

public abstract  class MovableFigure{
    protected Position position;

    abstract  public List<Position> getAccessiblePositions(Board board);

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
