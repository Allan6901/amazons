package amazons.player;

import amazons.board.Position;

import java.util.List;

public interface Player {
    /**
     * Play one turn of the game. Receives as input the move of the other player.
     * Return the move of  this player
     *
     * @param opponentMove: the last move of the opponent
     * @return the new move
     */
    Move play(Move opponentMove);

    /**
     * Give the player the initial state of the game
     *
     * @param boardHeight:      the height of the board (number of rows)
     * @param boardWidth:       the width of the board   (number of columns)
     * @param playerID:     the Id of this player. The first player index is  0
     * @param initialPositions: the initials positions of the figures of each player
     */
    void initialize(int boardHeight, int boardWidth, PlayerID playerID,
                    List<Position>[] initialPositions);


    /**
     * Determines whether this {@code Player} is controlled by the Graphical User Interface.
     * GUIcontrolled players have their move input by the user via the interface
     * @return {@code true} is this {@code Player} is controlled by the GUI, {@code false}otherwise
     */
    boolean isGUIControlled();
    /**
     * Return the ID of this player
     * @return the ID of this player
     */
    PlayerID getPlayerID();
}