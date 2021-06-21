package pl.konieczki.sudokufinder.strategies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.konieczki.sudokufinder.model.SudokuFieldSquareId;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;
import pl.konieczki.sudokufinder.strategies.deterministic.AbstractDeterministicStrategy;
import pl.konieczki.sudokufinder.strategies.deterministic.RemoveKnownValueInSquareStrategy;

public class RemoveKnownValueInSquareStrategyTest {

    private final AbstractDeterministicStrategy strategy
            = new RemoveKnownValueInSquareStrategy(SudokuFieldSquareId.SQUARE_I);

    @Before
    public void setup() {
        strategy.resetNothingToWorkFlag();
    }

    @Test
    public void when_square_not_contains_know_value_then_nothing_changed() {
        final SudokuPossibilitiesHolder testValue = new SudokuPossibilitiesHolder();
        final SudokuPossibilitiesHolder expectedValue = testValue.duplicate();

        final boolean result = strategy.apply(testValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testValue.toString());
    }

    @Test
    public void when_square_has_6_value_then_no_6_is_in_possibles() {
        final SudokuPossibilitiesHolder testValue = new SudokuPossibilitiesHolder();
        final byte SIX = (byte) 6;
        testValue.setValue(2, 3, SIX);
        final SudokuPossibilitiesHolder expectedValue = testValue.duplicate();
        expectedValue.removePossible(1, 1, SIX);
        expectedValue.removePossible(1, 2, SIX);
        expectedValue.removePossible(1, 3, SIX);
        expectedValue.removePossible(2, 1, SIX);
        expectedValue.removePossible(2, 2, SIX);
        expectedValue.removePossible(3, 1, SIX);
        expectedValue.removePossible(3, 2, SIX);
        expectedValue.removePossible(3, 3, SIX);

        final boolean result = strategy.apply(testValue);

        Assert.assertTrue(result);
        Assert.assertEquals(expectedValue.toString(), testValue.toString());
    }

    @Test
    public void when_square_has_all_values_then_nothing_changed() {
        final SudokuPossibilitiesHolder testValue = new SudokuPossibilitiesHolder();
        testValue.setValue(1, 1, (byte) 1);
        testValue.setValue(1, 2, (byte) 2);
        testValue.setValue(1, 3, (byte) 3);
        testValue.setValue(2, 1, (byte) 4);
        testValue.setValue(2, 2, (byte) 5);
        testValue.setValue(2, 3, (byte) 6);
        testValue.setValue(3, 1, (byte) 7);
        testValue.setValue(3, 2, (byte) 8);
        testValue.setValue(3, 3, (byte) 9);
        final SudokuPossibilitiesHolder expectedValue = testValue.duplicate();

        final boolean result = strategy.apply(testValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testValue.toString());
    }
}
