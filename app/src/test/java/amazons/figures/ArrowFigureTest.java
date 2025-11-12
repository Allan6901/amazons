package amazons.figures;
import static org.assertj.core.api.Assertions.assertThat;

import amazons.board.MatrixBoard;
import amazons.board.Position;
import amazons.player.PlayerID;
import org.junit.jupiter.api.Test;
public class ArrowFigureTest
{

    @Test
    void testToString()
    {

        assertThat(ArrowFigure.ARROW_FIGURE.toString()).isEqualTo("XX");

    }

    @Test
    void testCanMoveTo()
    {
        assertThat(ArrowFigure.ARROW_FIGURE.canMoveTo(new Position(1,2),new MatrixBoard(4,3))).isFalse();
    }
    @Test
    void testGetPlayerID()
    {

        assertThat(ArrowFigure.ARROW_FIGURE.getPlayerID()).isSameAs(PlayerID.NONE);

    }
}
