package amazons.util;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static amazons.util.RandomUtil.getRandomElement;
import static amazons.util.RandomUtil.getRandomElements;
import static org.assertj.core.api.Assertions.assertThat;

class RandomUtilTest {

    private final Integer[] intArray = new Integer[]{1,2,3,4,5,6,7,8,9,10};
    private final List<Integer> intList = List.of(1,2,3,4,5,6,7,8,9,10);
    private final Random random = new Random();



    @Test
    void testGetRandomElement() {
        assertThat(getRandomElement(random,intList)).isGreaterThanOrEqualTo(1)
                .isLessThanOrEqualTo(10);
        assertThat(getRandomElement(random,intArray)).isGreaterThanOrEqualTo(1)
                .isLessThanOrEqualTo(10);
    }



    @Test
    void testGetRandomElements() {
        assertThat(getRandomElements(random,intList,10)).hasSameElementsAs(intList);
        assertThat(getRandomElements(random,intArray,10)).hasSameElementsAs(intList);
        assertThat(getRandomElements(random,intList,4))
                .hasSize(4)
                .isSubsetOf(intList)
                .doesNotHaveDuplicates();

        assertThat(getRandomElements(random,intArray,4))
                .hasSize(4)
                .doesNotHaveDuplicates()
                .isSubsetOf(intList);
    }
}