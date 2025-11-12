package amazons.player;

import amazons.board.Position;

import java.util.Objects;

public class Move {

    private Position amazonStartPosition;
    private Position amazonDstPosition;
    private Position arrowDstPosition;


    public Move(Position amazonStartPosition, Position amazonDstPosition, Position arrowDstPosition) {
        this.amazonStartPosition = amazonStartPosition;
        this.amazonDstPosition = amazonDstPosition;
        this.arrowDstPosition = arrowDstPosition;
    }
    private Move() {}

    public static final Move DUMMY_MOVE = new Move();

    public Position getAmazonStartPosition() {
        return this.amazonStartPosition;
    }

    public Position getAmazonDstPosition() {
        return this.amazonDstPosition;
    }

    public Position getArrowDestPosition() {
        return this.arrowDstPosition;
    }

    /**
     * Check whether an object is equal to the same movement
     * @param position
     * @return true: equals / false: otherwise
     */
    public boolean equals(Object position)
    {
        if (!(position instanceof Move))
            return false;
        return this.hashCode() == position.hashCode();
    }

    @Override
    public String toString() {
        return amazonStartPosition.toString() + ":" + amazonDstPosition.toString() + "->" + arrowDstPosition.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(amazonStartPosition, amazonDstPosition, arrowDstPosition);
    }




}
