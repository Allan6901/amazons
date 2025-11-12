package amazons.board;

import org.junit.jupiter.api.Test;


import static amazons.board.CardinalDirection.getDirection;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardinalDirectionTest {

    @Test
    void testGetDirection(){
        int sourceX = 0;
        int sourceY = 0;
        assertThat(getDirection(sourceX,sourceY,2,2)).isEqualTo(CardinalDirection.SOUTH_EAST);
        assertThat(getDirection(sourceX,sourceY,0,2)).isEqualTo(CardinalDirection.SOUTH);
        assertThat(getDirection(sourceX,sourceY,-2,2)).isEqualTo(CardinalDirection.SOUTH_WEST);
        assertThat(getDirection(sourceX,sourceY,-2,0)).isEqualTo(CardinalDirection.WEST);
        assertThat(getDirection(sourceX,sourceY,-2,-2)).isEqualTo(CardinalDirection.NORTH_WEST);
        assertThat(getDirection(sourceX,sourceY,0,-2)).isEqualTo(CardinalDirection.NORTH);
        assertThat(getDirection(sourceX,sourceY,2,-2)).isEqualTo(CardinalDirection.NORTH_EAST);
        assertThat(getDirection(sourceX,sourceY,2,0)).isEqualTo(CardinalDirection.EAST);
    }



}