package amazons.board;

import amazons.figures.EmptyFigure;
import amazons.figures.Figure;
import amazons.figures.MovableFigure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomFigureGenerator implements FigureGenerator {
    private Random random;
    private List<MovableFigure> figures;
    private Iterator<Position> positionIterator;
    private List<Figure> listFiguresWithEmptyFigure = new ArrayList<>();
    private int numberOfPositions;

    /**
     * This generator is responsible for randomly assigning figures to positions in a grid.
     *
     * @param random           The {@link Random} object to generate random values.
     * @param figures          The list of {@link MovableFigure} objects to be assigned to positions.
     * @param positionIterator The iterator providing positions in the two-dimensional grid.
     */
    public RandomFigureGenerator(Random random, List<MovableFigure> figures, Iterator<Position> positionIterator) {
        this.random = random;
        this.figures = figures;
        this.positionIterator = positionIterator;
        fill_listFigures();

        while (positionIterator.hasNext())
            getNumberOfPositions();
        fillListFiguresWithEmptyFigure();

    }

    public void getNumberOfPositions()
    {

            positionIterator.next();
            numberOfPositions++;

    }
    /**
     * Fills the list of figures with movable figures provided in the constructor.
     * Each movable figure is cast to a {@link Figure} and added to the list.
     */
    private void fill_listFigures()
    {
        for(MovableFigure moveFigures : figures)
        {
            listFiguresWithEmptyFigure.add((Figure) moveFigures);
        }
    }

    /**
     * Fills the remaining positions in the list of figures with {@link EmptyFigure#EMPTY_FIGURE}.
     * This ensures that the list has enough elements for the total number of positions.
     */
    private void fillListFiguresWithEmptyFigure()
    {
        for( int i =figures.size() ; i<numberOfPositions;i++)
        {
            listFiguresWithEmptyFigure.add(EmptyFigure.EMPTY_FIGURE);

        }
    }

    /**
     * Returns the next {@link Figure} to be placed at the specified position.
     * If there are no more positions available, returns {@link EmptyFigure#EMPTY_FIGURE}.
     *
     * @param position The position in the two-dimensional grid where the figure will be placed.
     * @return The next {@link Figure} to be placed at the specified position or {@link EmptyFigure#EMPTY_FIGURE} if no positions are available.
     */
    @Override
    public Figure nextFigure(Position position)
    {
        if(numberOfPositions <= 0)
            return EmptyFigure.EMPTY_FIGURE;
        int indexRandom = random.nextInt(numberOfPositions);
        numberOfPositions -- ;
        Figure FigureToPlace = listFiguresWithEmptyFigure.get(indexRandom);
        FigureToPlace.setPosition(position);
        listFiguresWithEmptyFigure.remove(FigureToPlace);
        return FigureToPlace;

    }


}

