package amazons.board;

import javafx.scene.input.DataFormat;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class which describe a location.
 * {@param} int x and int y , two attributes for initialize the location of a amazon.
 *
 */
public class Position implements Serializable {
    public static final DataFormat POSITION_FORMAT = new DataFormat("amazons.position");

   private int x;
   private int  y;


    /**
     *Initialize the coordinate
     * @param x , ordinate
     * @param y , easting
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the ordinate
     */
    public int getX()
    {
        return this.x;
    }

    /**
     *
     * @return the easting
     */
    public int getY()
    {return this.y;
    }


    /**
     * method which could return if the location is out of bounds
     * @param numberOfColumns , number of columns
     * @param numberOfRows , nimber of rows
     * @return boolean ( true if it's out of bound and false otherwise
     */
    public boolean isOutOfBounds(int numberOfColumns, int numberOfRows)
    {

          if(this.getY() < 0 || getY() > numberOfRows || this.getX() < 0|| this.getX() > numberOfColumns){
              return true;
          }
        return false;
    }

    /**
     *
     * @param direction
     * @return the New position
     */
    public Position next(CardinalDirection direction)
    {

        return new Position(getX()+direction.deltaColumn,getY()+direction.deltaRow);

    }

    /**
     *
     * @param destPosition
     * @return The cardinal Direction
     */
    public CardinalDirection getDirection(Position destPosition){
        return CardinalDirection.getDirection(x,y, destPosition.x, destPosition.y);
    }

    /**
     *
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (getX() != position.getX()) return false;
        return getY() == position.getY();
    }

    /**
     *
     * @return Objects.hash(getX(), getY());
     */
    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    public String toString()
    {

        return "("+getX()+","+getY()+")";

    }

}
