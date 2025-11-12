package amazons.player;
import amazons.board.Board;
import amazons.board.MatrixBoard;
import amazons.figures.Amazon;
import amazons.figures.Figure;
import amazons.game.Game;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.assertj.core.api.Assertions.*;

public class AbstractPlayerTest
{
    Game game = new Game();
    private BasicPlayer basic = new BasicPlayer();
    private Board board = new MatrixBoard(5,5);

    @Test
    public void testGetAmazonByIDAndInitialize()
    {
        game.initializeGame(new GUIPLayer(),new GUIPLayer());
        List<Amazon> amazons = new ArrayList<>();
        Iterator<Figure> boardIterator = game.getBoard().iterator();
        while(boardIterator.hasNext())
        {
            boardIterator.next();
            if(boardIterator.next() instanceof Amazon && boardIterator.next().getPlayerID().equals(PlayerID.PLAYER_ONE))
            {
                amazons.add((Amazon) boardIterator.next());
            }
        }
        assertThat(basic.getAmazonByID(PlayerID.PLAYER_ONE,new MatrixBoard(5,5))).containsExactlyElementsOf(amazons);

    }



}
