package amazons.board;

import amazons.figures.Figure;

public interface FigureGenerator {
    Figure nextFigure(Position position);
}