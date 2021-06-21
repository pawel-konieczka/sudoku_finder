package pl.konieczki.sudokufinder.strategies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.konieczki.sudokufinder.model.SudokuFieldSquareId;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;
import pl.konieczki.sudokufinder.strategies.deterministic.AbstractSquareDeterministicStrategy;
import pl.konieczki.sudokufinder.strategies.deterministic.RemovePairFromOtherInSquareStrategy;

public class RemovePairFromOtherInSquareStrategyTest {

    private final AbstractSquareDeterministicStrategy strategy
            = new RemovePairFromOtherInSquareStrategy(SudokuFieldSquareId.SQUARE_I);

    @Before
    public void setup() {
        strategy.resetNothingToWorkFlag();
    }

    @Test
    public void when_no_pairs_in_square_then_nothing_changed() {
        final SudokuPossibilitiesHolder testedValue = new SudokuPossibilitiesHolder();
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();

        final boolean result = strategy.apply(testedValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }

    @Test
    public void when_single_pair_in_square_then_nothing_changed() {
        final SudokuPossibilitiesHolder testedValue = new SudokuPossibilitiesHolder();
        testedValue.removePossibles(1, 1, new byte[]{1, 3, 5, 6, 7, 8, 9});
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();

        final boolean result = strategy.apply(testedValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }

    @Test
    public void when_two_total_different_pairs_in_square_then_nothing_changed() {
        final SudokuPossibilitiesHolder testedValue = new SudokuPossibilitiesHolder();
        testedValue.removePossibles(1, 1, new byte[]{1, 3, 5, 6, 7, 8, 9});
        testedValue.removePossibles(3, 3, new byte[]{2, 4, 5, 6, 7, 8, 9});
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();

        final boolean result = strategy.apply(testedValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }

    @Test
    public void when_two_partial_different_pairs_in_square_then_nothing_changed() {
        final SudokuPossibilitiesHolder testedValue = new SudokuPossibilitiesHolder();
        testedValue.removePossibles(1, 1, new byte[]{1, 3, 5, 6, 7, 8, 9});
        testedValue.removePossibles(3, 3, new byte[]{2, 3, 5, 6, 7, 8, 9});
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();

        final boolean result = strategy.apply(testedValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }

    @Test
    public void when_two_pairs_in_square_then_possibles_removed_from_other() {
        final SudokuPossibilitiesHolder testedValue = new SudokuPossibilitiesHolder();
        testedValue.removePossibles(1, 1, new byte[]{1, 3, 5, 6, 7, 8, 9});
        testedValue.removePossibles(3, 3, new byte[]{1, 3, 5, 6, 7, 8, 9});
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();
        final byte[] toRemove = new byte[] {2, 4};
        expectedValue.removePossibles(1, 2, toRemove);
        expectedValue.removePossibles(1, 3, toRemove);
        expectedValue.removePossibles(2, 1, toRemove);
        expectedValue.removePossibles(2, 2, toRemove);
        expectedValue.removePossibles(2, 3, toRemove);
        expectedValue.removePossibles(3, 1, toRemove);
        expectedValue.removePossibles(3, 2, toRemove);

        final boolean result = strategy.apply(testedValue);

        Assert.assertTrue(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }
}
