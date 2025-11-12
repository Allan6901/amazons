package amazons.figures;
import static org.assertj.core.api.Assertions.assertThat;

import amazons.board.MatrixBoard;
import amazons.board.Position;
import amazons.player.PlayerID;
import org.junit.jupiter.api.Test;

public class EmptyFigureTest
{
    @Test
    void testToString()
    {

        assertThat(EmptyFigure.EMPTY_FIGURE.toString()).isEqualTo("  ");

    }

    @Test
    void testCanMoveTo()
    {
        assertThat(EmptyFigure.EMPTY_FIGURE.canMoveTo(new Position(1,2),new MatrixBoard(4,3))).isFalse();
    }
    @Test
    void testGetPlayerID()
    {

        assertThat(EmptyFigure.EMPTY_FIGURE.getPlayerID()).isSameAs(PlayerID.NONE);

    }
}
