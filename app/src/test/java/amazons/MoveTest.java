package amazons;

import amazons.board.Position;
import amazons.player.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class MoveTest {

    private Move testMove;
    private final Position amazonStartPosition = new Position(0,0);
    private final Position amazonDstPosition = new Position(1,1);
    private final Position arrowDstPosition  = new Position(1,2);
    @BeforeEach
    void setTestMove(){
        testMove = new Move(amazonStartPosition, amazonDstPosition, arrowDstPosition);
    }
    @Test
    void testGetters() {
        assertThat(testMove.getAmazonStartPosition()).isEqualTo(amazonStartPosition);
        assertThat(testMove.getAmazonDstPosition()).isEqualTo(amazonDstPosition);
        assertThat(testMove.getArrowDestPosition()).isEqualTo(arrowDstPosition);
    }

    @Test
    void testToString()
    {

        assertThat(testMove.toString()).isEqualTo("(0,0):(1,1)->(1,2)");

    }
    @Test
    void testEquals()
    {
        Move testMove2 = testMove;
        Move testMove3= new Move(new Position(1,2),new Position(2,2),arrowDstPosition);

        assertThat(testMove2.equals(testMove)).isTrue();
        assertThat(testMove3.equals(testMove)).isFalse();

    }
}