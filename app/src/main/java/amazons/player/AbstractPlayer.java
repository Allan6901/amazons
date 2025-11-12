package amazons.player;

import amazons.IllegalMoveException;
import amazons.board.*;
import amazons.figures.Amazon;
import amazons.figures.Figure;
import amazons.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class  AbstractPlayer implements Player
{
    protected Random random = new Random();
    protected PlayerID playerID;
    protected MatrixBoard board;

    /**
     * Returns the list of amazons of a playerID
     * @param playerID ID Of player
     * @param board matrix
     * @return List<Amazon>
     */
    public static List<Amazon> getAmazonByID(PlayerID playerID, Board board) {
        List<Amazon> amazons = new ArrayList<>();
        for(int i = 0; i < board.getNumberOfColumns(); i++){
            for(int j = 0; j < board.getNumberOfRows(); j++){
                Figure figure = board.getFigure(new Position(i, j));
                if(figure instanceof Amazon && figure.getPlayerID().equals(playerID))
                    amazons.add((Amazon) figure);
            }
        }
        return amazons;
    }

    /**
     *Gives next computer movements while respecting movement rules
     * @param opponentMove: the last move of the opponent
     * @return Move : next computer movements
     */
    @Override
    public Move play(Move opponentMove) {
        if(opponentMove.getAmazonStartPosition() != null) {
            try {
                board.moveFigure(opponentMove.getAmazonStartPosition(), opponentMove.getAmazonDstPosition());
                board.shootArrow(opponentMove.getAmazonDstPosition(), opponentMove.getArrowDestPosition());
            } catch (IllegalMoveException ignored) {
            }
        }
        Position startPosition = getRandomAmazon(playerID, board).getPosition();
        Position dstPosition = getRandomPosition(startPosition, playerID, board, this);
        try {
            board.moveFigure(startPosition, dstPosition);
        } catch (IllegalMoveException ignored) {
        }
        Position arrowPosition = getRandomPosition(dstPosition, playerID, board, this);
        try {
            board.shootArrow(dstPosition, arrowPosition);
        } catch (IllegalMoveException ignored) {
        }
        return new Move(startPosition, dstPosition, arrowPosition);
    }

    /**
     * Returns a random amazon from the list of playerID amazons
     * @param playerID ID Of player
     * @param board Matrix
     * @return Amazon type
     */
    public static Amazon getRandomAmazon(PlayerID playerID, Board board) {
        List<Amazon> playerMovableAmazons = getAmazonByID(playerID, board).stream().filter(amazon -> amazon.stuck(board)).toList();
        return RandomUtil.getRandomElement( new Random(), playerMovableAmazons);
    }

    /**
     *Gives a random position from an amazon's list of accessible positions
     * @param startPosition Starting position
     * @param playerID ID of player
     * @param board Matrix
     * @param player type of IA
     * @return Position type
     */
    public abstract Position getRandomPosition(Position startPosition, PlayerID playerID, Board board, Player player) ;

    /**
     * Initializes the matrix
     * @param boardHeight:      the height of the board (number of rows)
     * @param boardWidth:       the width of the board   (number of columns)
     * @param playerID:     the Id of this player. The first player index is  0
     * @param initialPositions: the initials positions of the figures of each player
     */
    @Override
    public void initialize(int boardHeight, int boardWidth, PlayerID playerID, List<Position>[] initialPositions) {
        this.playerID = playerID;
        this.board = new MatrixBoard(boardWidth, boardHeight);
        for(int i = 0; i < initialPositions.length; i++){
            for(Position position : initialPositions[i]){
                board.setFigure(position, new Amazon( position, i ));
            }
        }
    }

    /**
     * Returns whether it's a human or an ia
     * @return true: human \ false: IA
     */
    @Override
    public boolean isGUIControlled() {
        return false;
    }

    @Override
    public PlayerID getPlayerID() {
        return this.playerID;
    }
}
