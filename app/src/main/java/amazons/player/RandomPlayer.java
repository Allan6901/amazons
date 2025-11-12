package amazons.player;

import amazons.board.Board;
import amazons.board.CardinalDirection;
import amazons.board.Position;
import amazons.figures.Amazon;
import amazons.util.RandomUtil;

import java.util.List;
import java.util.Random;

public class RandomPlayer extends AbstractPlayer
{
    /**
     * This class creates an AI that moves its amazons at random.
     */


    /**
     * @param startPosition Starting position
     * @param playerID ID of player
     * @param board Matrix
     * @param player type of IA
     * @return a less basic position at random
     */
    @Override
    public Position getRandomPosition(Position startPosition, PlayerID playerID, Board board, Player player) {
        Amazon amazon = new Amazon(startPosition, playerID.ordinal());
        List<Position> accessiblePositions = amazon.getAccessiblePositions(board);
        return RandomUtil.getRandomElement(new Random(), accessiblePositions);
    }
}
