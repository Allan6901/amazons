package amazons.player;

import amazons.board.Position;
import java.util.List;
import java.util.Scanner;


    /**
     * Implementation of the Player interface for a Command Line (CL) player in the Amazon game.
     * This player interacts with the game through the command line interface.
     */
public class CLPlayer implements Player {
    private PlayerID playerID;
    private final static Scanner inputScanner = new Scanner(System.in);


    /**
     * Indicates whether the player is GUI-controlled.
     *
     * @return false, as this player is controlled via the command line interface.
     */
    @Override
    public boolean isGUIControlled() {
        return false;
    }

    /**
     * Implements the play method for a player in the Amazon game.
     * The player is prompted to select the starting position for their Amazon, the destination for the Amazon,
     * and the position to shoot the arrow.
     *
     * @param opponentMove The opponent's move. Not used in this implementation, but required by the interface.
     * @return A Move object representing the player's move, including the starting position for the Amazon,
     * the destination for the Amazon, and the position to shoot the arrow.
     */
    @Override
    public Move play(Move opponentMove) {
        System.out.println(playerID + " select amazon? (enter X Y coordinate)");
        System.out.print("Y : ");
        int yCoordinate = inputScanner.nextInt();
        System.out.print("X :");
        int xCoordinate = inputScanner.nextInt();

        Position amazonStartPosition = new Position(yCoordinate, xCoordinate);
        System.out.println(this.playerID + " select destination? (enter X Y coordinate)");
        System.out.print("Y : ");
        yCoordinate = inputScanner.nextInt();
        System.out.print("X : ");
        xCoordinate = inputScanner.nextInt();

        Position amazonDstPosition = new Position(yCoordinate, xCoordinate);
        System.out.println(playerID + " where to shoot arrow? (enter X Y coordinate)");
        System.out.print("Y : ");
        yCoordinate = inputScanner.nextInt();
        System.out.print("X : ");
        xCoordinate = inputScanner.nextInt();

        Position arrowDstPosition = new Position(yCoordinate, xCoordinate);

        return new Move(amazonStartPosition, amazonDstPosition, arrowDstPosition);
    }

    /**
     *Initializes the player with the board dimensions, player ID, and initial positions.
     *
     * @param boardWidth:       the width of the board   (number of columns)
     * @param boardHeight:      the height of the board (number of rows)
     * @param playerID:     the Id of this player. The first player index is  0
     * @param initialPositions: the initials positions of the figures of each player
     */
    @Override
    public void initialize(int boardWidth, int boardHeight,  PlayerID playerID, List<Position>[] initialPositions) {
        this.playerID = playerID;
    }

    /**
     * Retrieves the player's ID.
     *
     * @return The player's ID.
     */
    @Override
    public PlayerID getPlayerID() {
        return playerID;
    }
}
