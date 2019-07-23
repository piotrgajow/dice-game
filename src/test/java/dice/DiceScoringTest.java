package dice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DiceScoringTest {

    @ParameterizedTest
    @MethodSource({"diceTestData"})
    void shouldCalculateNumberOfPoints(int expected, int[] diceValues, ScoringCategory category, boolean wasFirstThrow) {
        DiceScoring dice = new DiceScoring();

        int result = dice.score(diceValues, category, wasFirstThrow);

        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> diceTestData() {
        return Stream.of(
                Arguments.arguments(15, new int[]{1, 2, 3, 4, 5}, ScoringCategory.CHANCE, false),
                Arguments.arguments(38, new int[]{6, 3, 4, 1, 5}, ScoringCategory.CHANCE, true),
                Arguments.arguments(60, new int[]{6, 6, 6, 6, 6}, ScoringCategory.CHANCE, true),
                Arguments.arguments(5, new int[]{1, 1, 1, 1, 1}, ScoringCategory.CHANCE, false),
                Arguments.arguments(0, new int[]{1, 2, 3, 4, 5}, ScoringCategory.PAIR, false),
                Arguments.arguments(10, new int[]{5, 5, 1, 2, 3}, ScoringCategory.PAIR, false),
                Arguments.arguments(12, new int[]{1, 1, 6, 6, 1}, ScoringCategory.PAIR, false),
                Arguments.arguments(24, new int[]{6, 3, 2, 1, 6}, ScoringCategory.PAIR, true),
                Arguments.arguments(12, new int[]{6, 6, 6, 6, 6}, ScoringCategory.PAIR, false),
                Arguments.arguments(0, new int[]{1, 2, 3, 4, 5}, ScoringCategory.TWO_PAIRS, false),
                Arguments.arguments(14, new int[]{1, 6, 1, 5, 6}, ScoringCategory.TWO_PAIRS, false),
                Arguments.arguments(20, new int[]{3, 2, 1, 2, 3}, ScoringCategory.TWO_PAIRS, true),
                Arguments.arguments(24, new int[]{6, 6, 6, 6, 6}, ScoringCategory.TWO_PAIRS, false),
                Arguments.arguments(0, new int[]{1, 1, 2, 2, 6}, ScoringCategory.THREE_OF_A_KIND, false),
                Arguments.arguments(3, new int[]{1, 6, 1, 6, 1}, ScoringCategory.THREE_OF_A_KIND, false),
                Arguments.arguments(18, new int[]{1, 3, 3, 3, 2}, ScoringCategory.THREE_OF_A_KIND, true),
                Arguments.arguments(18, new int[]{6, 6, 6, 6, 6}, ScoringCategory.THREE_OF_A_KIND, false),
                Arguments.arguments(0, new int[]{1, 2, 3, 4, 6}, ScoringCategory.FOUR_OF_A_KIND, false),
                Arguments.arguments(0, new int[]{6, 6, 6, 1, 1}, ScoringCategory.FOUR_OF_A_KIND, false),
                Arguments.arguments(12, new int[]{3, 3, 6, 3, 3}, ScoringCategory.FOUR_OF_A_KIND, false),
                Arguments.arguments(16, new int[]{2, 1, 2, 2, 2}, ScoringCategory.FOUR_OF_A_KIND, true),
                Arguments.arguments(24, new int[]{6, 6, 6, 6, 6}, ScoringCategory.FOUR_OF_A_KIND, false),
                Arguments.arguments(0, new int[]{1, 1, 5, 5, 6}, ScoringCategory.FULL_HOUSE, false),
                Arguments.arguments(0, new int[]{3, 3, 3, 3, 6}, ScoringCategory.FULL_HOUSE, false),
                Arguments.arguments(27, new int[]{5, 5, 5, 6, 6}, ScoringCategory.FULL_HOUSE, false),
                Arguments.arguments(42, new int[]{3, 3, 3, 6, 6}, ScoringCategory.FULL_HOUSE, true),
                Arguments.arguments(30, new int[]{6, 6, 6, 6, 6}, ScoringCategory.FULL_HOUSE, false),
                Arguments.arguments(0, new int[]{1, 2, 3, 4, 6}, ScoringCategory.SMALL_STRAIGHT, false),
                Arguments.arguments(15, new int[]{1, 5, 4, 2, 3}, ScoringCategory.SMALL_STRAIGHT, false),
                Arguments.arguments(30, new int[]{5, 3, 2, 4, 1}, ScoringCategory.SMALL_STRAIGHT, true),
                Arguments.arguments(0, new int[]{1, 3, 4, 5, 6}, ScoringCategory.LARGE_STRAIGHT, false),
                Arguments.arguments(20, new int[]{6, 5, 4, 2, 3}, ScoringCategory.LARGE_STRAIGHT, false),
                Arguments.arguments(40, new int[]{5, 3, 2, 4, 6}, ScoringCategory.LARGE_STRAIGHT, true),
                Arguments.arguments(0, new int[]{6, 6, 6, 6, 5}, ScoringCategory.YAHTZEE, false),
                Arguments.arguments(50, new int[]{6, 6, 6, 6, 6}, ScoringCategory.YAHTZEE, false),
                Arguments.arguments(50, new int[]{1, 1, 1, 1, 1}, ScoringCategory.YAHTZEE, false),
                Arguments.arguments(100, new int[]{1, 1, 1, 1, 1}, ScoringCategory.YAHTZEE, true)
        );
    }

}
