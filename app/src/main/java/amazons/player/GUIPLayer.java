package amazons.player;

import amazons.board.Position;

import java.util.List;

public class GUIPLayer implements Player {

    private PlayerID playerID;
    @Override
    public Move play(Move opponentMove) {
        return null;
    }

    @Override
    public void initialize(int boardWidth, int boardHeight, PlayerID playerID, List<Position>[] initialPositions) {
        this.playerID = playerID;
    }

    @Override
    public boolean isGUIControlled() {
        return true;
    }

    @Override
    public PlayerID getPlayerID() {
        return this.playerID;
    }
}
