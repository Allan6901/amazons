package amazons.player;

import amazons.IllegalMoveException;
import amazons.board.Board;
import amazons.board.CardinalDirection;
import amazons.board.Position;
import amazons.figures.Amazon;
import amazons.util.RandomUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GreedyPlayer extends AbstractPlayer{
    /*
     * In this class, a score is defined as the
     * number of shots an Amazon can block
     * from her opponent's position.
     */

    /**
     *Gives next computer movements while respecting movement rules
     * @param opponentMove: the last move of the opponent
     * @return Move : next computer movements
     */
    @Override
    public Move play(Move opponentMove) {

        if(opponentMove.getAmazonStartPosition() != null) {

            try {

                this.board.moveFigure(opponentMove.getAmazonStartPosition(), opponentMove.getAmazonDstPosition());

                this.board.shootArrow(opponentMove.getAmazonDstPosition(), opponentMove.getArrowDestPosition());

            } catch (IllegalMoveException ignored) {}
        }
        Position startPosition = getRandomAmazonGreedy(playerID, board).getPosition();

        Position dstPosition = getPositionNoRandom(startPosition, playerID, board, this);

        try {this.board.moveFigure(startPosition, dstPosition);}

        catch (IllegalMoveException ignored) {}

        Position arrowPosition = shootArrow(dstPosition, playerID, board, this);
        try
        {this.board.shootArrow(dstPosition, arrowPosition);}
        catch (IllegalMoveException ignored)
        {}

        return new Move(startPosition, dstPosition, arrowPosition);
    }

    /**
     * @param startPosition: starting position
     * @param playerID: ID of player
     * @param board: matrix
     * @param player: type of IA
     * @return  Returns a non-random position if possible
     */
    private Position getPositionNoRandom(Position startPosition, PlayerID playerID, Board board, Player player)
    {

        Amazon amazon = new Amazon(startPosition, playerID.ordinal());

        List<Amazon> oppoAmazons = getAmazonByID(playerID.opponent(),board);

        List<Position> accessiblePositions = amazon.getAccessiblePositions(board);

        Position positionFinal =null;

        for(Position position : accessiblePositions)
        {
            for (Amazon opponent : oppoAmazons)
            {
                for(Position oppoPosition : opponent.getAccessiblePositions(board))
                {
                    if(position.equals(oppoPosition))

                        positionFinal = position ;
                }
            }

        }
        if(positionFinal == null)

            return getRandomPosition(startPosition,playerID,board,player);

        return  positionFinal ;

    }

    /**
     *
     * @param startPosition Starting position
     * @param playerID ID of player
     * @param board Matrix
     * @param player type of IA
     * @return Returns a random position
     */
    @Override
    public Position getRandomPosition(Position startPosition, PlayerID playerID, Board board, Player player) {
        Amazon amazon = new Amazon(startPosition, playerID.ordinal());

        List<Position> accessiblePositions = amazon.getAccessiblePositions(board);

        return RandomUtil.getRandomElement(new Random(), accessiblePositions);

    }

    /**
     * @param startPosition: starting position
     * @param playerID: ID of player
     * @param board: matrix
     * @param player: type of IA
     * @return Returns a position corresponding to the arrow target
     */
    private Position shootArrow(Position startPosition,PlayerID playerID,Board board , Player player)
    {

        List<Amazon> oppoAmazons = getAmazonByID(playerID.opponent(),board);

        Position positionFinal = null;

        Amazon amazonAtStartPosition = (Amazon) board.getFigure(startPosition);

        for(Position position : amazonAtStartPosition.getAccessiblePositions(board))
        {
            for(Amazon oppoAmazon : oppoAmazons)
            {
                Position nextPosition = position.next(CardinalDirection.getDirection(position.getX(),position.getY(),oppoAmazon.getPosition().getX(),oppoAmazon.getPosition().getY()));

                if(board.getFigure(nextPosition).equals(oppoAmazon))

                {positionFinal = position  ;}
            }
        }
        if( positionFinal == null)

            return getRandomPosition(startPosition, playerID, board, this);

        return positionFinal ;
    }

    /**
     *Choose the best amazon to move which will be determined by the score
     * @param playerID: ID of player
     * @param board: Matrix
     * @return the best amazon to move which will be determined by the score
     */
    public Amazon getRandomAmazonGreedy(PlayerID playerID, Board board)
    {
        List<Amazon> oppoAmazons = getAmazonByID(playerID.opponent(),board); // opponent's amazon list

        List<Amazon> amazonsID = getAmazonByID(playerID,board).stream().filter(amazon -> amazon.stuck(board)).toList(); // list of ia amazons

        return betterMove(board,amazonsID,oppoAmazons);

    }

    /**
     * Choose the best amazon to move which will be determined by the score
     * @param board: Matrix
     * @param amazons:List of ia amazons
     * @param opponentAmazon: Opponent's amazon list
     * @return the best amazon to move which will be determined by the score
     */
    private Amazon betterMove( Board board ,List<Amazon> amazons,List<Amazon> opponentAmazon )
    {
        Amazon amazonstart = new Amazon(null,0);

        Map<Amazon,Integer> betterAmazonToMove = new HashMap<>() ;

        for(Amazon amazon : amazons)
        {
            if(max(score(board,amazon,opponentAmazon),betterAmazonToMove))

                betterAmazonToMove =score(board,amazon,opponentAmazon);

        }
    for(Amazon amazon : betterAmazonToMove.keySet())

        amazonstart = new Amazon(amazon.getPosition(),amazon.getPlayerID().index);

    return amazonstart ;

    }

    /**
     * the Amazon score pair with the best score
     * @param amazon: Couple amazon score 1
     * @param otherAmazon: Couple amazon score 2
     * @return true: if amazon has a better score than otherAmazon . false otherwise
     */
    private boolean max(Map<Amazon,Integer> amazon , Map<Amazon,Integer> otherAmazon)
    {
        int scoreLeft = 0;

        int scoreRight=0;

        for(Integer scoreLeft2 : amazon.values())

                scoreLeft = scoreLeft2;

        for(Integer scoreRight2 : otherAmazon.values())

            scoreRight = scoreRight2;

        return scoreLeft>=scoreRight ;

    }

    /**
     *
     * @param boardScore: Matrix
     * @param amazon: amazon
     * @param amazonsOpponent: Opponent's amazon list
     * @return an amazon score
     * Explanation of the score -> please , up to the javadoc class
     */
    private Map<Amazon,Integer> score(Board boardScore , Amazon amazon ,List<Amazon> amazonsOpponent)
    {
        int scoreBlock = 0;

        Map<Amazon,Integer> betterMove = new HashMap<>();

        for(Amazon oppoAmazon : amazonsOpponent)
        {

            if( countMatchingElements(amazon.getAccessiblePositions(boardScore),oppoAmazon.getAccessiblePositions(boardScore)) > scoreBlock)

                scoreBlock = countMatchingElements(amazon.getAccessiblePositions(boardScore),oppoAmazon.getAccessiblePositions(boardScore));

        }
        betterMove.put(amazon,scoreBlock);

        return betterMove ;

    }

    /**
     * counts the number of identical positions
     * @param list1: first list
     * @param list2: second list
     * @return number of identical positions in the list2
     */
    private int countMatchingElements(List<Position> list1,List<Position> list2)
    {
        int counter =0;

            for(Position pos1 : list1)
            {
                for(Position pos2 : list2)
                {
                    if(pos2.equals(pos1))
                    {
                        counter++;
                    }
                }
            }

        return  counter;
    }

}
