package amazons.board;

import amazons.figures.EmptyFigure;
import amazons.figures.Figure;

public class EmptyFigureGenerator implements FigureGenerator {



    /**
     *
     * @param position where the figure has to be positioned
     * @return Empty figure
     */
    @Override
    public Figure nextFigure(Position position)
    {
        return EmptyFigure.EMPTY_FIGURE;
    }

}
