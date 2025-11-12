package amazons.board;
import amazons.figures.EmptyFigure;
import amazons.figures.Figure;
import java.util.*;


public class MapBoard extends AbstractBoard implements Board
{

    private Map<Position, Figure> board  ;

    public MapBoard(int numberOfColumns, int numberOfRows) {
        super(numberOfColumns, numberOfRows);
        this.board =new HashMap<>();
        fill(new EmptyFigureGenerator());
    }

    /**
     *
     * @param position: where the figure has to be positioned
     * @param figure: the figure to be placed at position {@code position}
     */
    @Override
    public void setFigure(Position position, Figure figure)
    {
        board.put(position,figure);
    }

    /**
     *
     * @param position: position of the figure to return
     * @return a Figure at position
     */
    @Override
    public Figure getFigure(Position position)
    {
        return board.get(position);
    }

    /**
     *
     * @param position: position to test
     * @return
     */
    @Override
    public boolean isEmpty(Position position) {
        return board.get(position).equals(EmptyFigure.EMPTY_FIGURE);
    }

    @Override
    public MatrixIterator<Figure> iterator()
    {
        return (MatrixIterator<Figure>) board.values().iterator();
    }

    @Override
    public Iterator<Position> positionIterator() {
        return board.keySet().iterator();
    }

    @Override
    public void fill(FigureGenerator generator) {
        for(int column = 0; column < numberOfColumns; column++) {
            for(int row = 0; row < numberOfRows; row++ ) {
                Position position = new Position(column, row);
                board.put(position, generator.nextFigure(position));
            }
        }
    }
}
