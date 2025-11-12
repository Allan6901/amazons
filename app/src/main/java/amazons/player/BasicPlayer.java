package amazons.player;

import amazons.board.*;
import amazons.figures.Amazon;
import amazons.util.RandomUtil;

import java.util.List;
import java.util.Random;

public class BasicPlayer extends AbstractPlayer
{
    /*
     * this class creates an AI that makes basic movements
     */

    /**
     * @param startPosition Starting position
     * @param playerID ID of player
     * @param board Matrix
     * @param player type of IA
     * @return  a random position in a basic way
     */
    @Override
    public Position getRandomPosition(Position startPosition, PlayerID playerID, Board board, Player player) {
        Amazon amazon = new Amazon(startPosition, playerID.ordinal());
        List<Position> accessiblePositions = amazon.getAccessiblePositions(board);
        Position randomPosition = RandomUtil.getRandomElement(new Random(), accessiblePositions);
        CardinalDirection direction = startPosition.getDirection(randomPosition);
        return startPosition.next(direction);
    }

}
