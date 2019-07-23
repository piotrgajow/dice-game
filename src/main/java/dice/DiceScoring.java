package dice;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DiceScoring {

    public int score(int[] dice, ScoringCategory category, boolean wasFirstThrow) {
        System.out.println(Arrays.toString(dice));
        int score = 0;
        int[] counts = countDice(dice);
        switch (category) {
            case CHANCE:
                score = IntStream.of(dice).sum();
                break;
            case PAIR:
                score = nOrMoreOfKind(counts, 2);
                break;
            case TWO_PAIRS:
                score = twoPairs(counts);
                break;
            case THREE_OF_A_KIND:
                score = nOrMoreOfKind(counts, 3);
                break;
            case FOUR_OF_A_KIND:
                score = nOrMoreOfKind(counts, 4);
                break;
            case FULL_HOUSE:
                score = fullHouse(counts);
                break;
            case SMALL_STRAIGHT:
                Arrays.sort(dice);
                score = Arrays.equals(dice, new int[]{1, 2, 3, 4, 5}) ? 15 : 0;
                break;
            case LARGE_STRAIGHT:
                Arrays.sort(dice);
                score = Arrays.equals(dice, new int[]{2, 3, 4, 5, 6}) ? 20 : 0;
                break;
            case YAHTZEE:
                score = nOfKind(counts, 5) > 0 ? 50 : 0;
                break;
        }

        return wasFirstThrow ? score * 2 : score;
    }

    private int[] countDice(int[] dice) {
        int[] counts = new int[6];
        for (int i = 0; i < 5; i++) {
            counts[dice[i] - 1]++;
        }
        return counts;
    }

    private int nOrMoreOfKind(int[] counts, int n) {
        for (int i = 5; i >= 0; i--) {
            if (counts[i] >= n) {
                return (i + 1) * n;
            }
        }
        return 0;
    }

    private int nOfKind(int[] counts, int n) {
        for (int i = 5; i >= 0; i--) {
            if (counts[i] == n) {
                return (i + 1) * n;
            }
        }
        return 0;
    }

    private int twoPairs(int[] counts) {
        boolean secondPair = false;
        int score = 0;
        for (int i = 5; i >= 0; i--) {
            if (counts[i] >= 4 && secondPair) {
                return score + (i + 1) * 2;
            } else if (counts[i] >= 4) {
                return (i + 1) * 4;
            } else if (counts[i] >= 2 && secondPair) {
                return score + (i + 1) * 2;
            } else if (counts[i] >= 2) {
                score = (i + 1) * 2;
                secondPair = true;
            }
        }
        return 0;
    }

    private int fullHouse(int[] counts) {
        int score = nOfKind(counts, 5);
        if (score != 0) {
            return score;
        }
        int scoreThree = nOfKind(counts, 3);
        int scoreTwo = nOfKind(counts, 2);
        if (scoreTwo == 0 || scoreThree == 0) {
            return 0;
        }
        return scoreThree + scoreTwo;
    }

}
