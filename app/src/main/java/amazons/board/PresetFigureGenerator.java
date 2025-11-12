package amazons.board;

import amazons.figures.EmptyFigure;
import amazons.figures.Figure;
import amazons.figures.MovableFigure;

import java.util.Map;

public class PresetFigureGenerator implements FigureGenerator {
    private final Map<MovableFigure, Position> figuresMap;

    public PresetFigureGenerator(Map<MovableFigure, Position> figuresMap) {
        this.figuresMap = figuresMap;
    }

    /**
     * Returns the next {@link Figure} associated with the specified position in the grid.
     * If no figure is found at the given position, returns {@link EmptyFigure#EMPTY_FIGURE}.
     *
     * @param position The position in the two-dimensional grid.
     * @return The next {@link Figure} at the specified position or {@link EmptyFigure#EMPTY_FIGURE} if no figure is found.
     */
    @Override
    public Figure nextFigure(Position position) {
        if(figuresMap.containsValue(position)) {
            for (MovableFigure figure: figuresMap.keySet()) {
                if( figuresMap.get(figure).equals(position) ) {
                    return (Figure) figure;
                }
            }
        }
        return EmptyFigure.EMPTY_FIGURE;
    }
}
