package pl.konieczki.sudokufinder.strategies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.konieczki.sudokufinder.model.SudokuFieldColId;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;
import pl.konieczki.sudokufinder.strategies.deterministic.AbstractDeterministicStrategy;
import pl.konieczki.sudokufinder.strategies.deterministic.RemoveKnownValueInColumnStrategy;

public class RemoveKnownValueInColumnStrategyTest {

    private final AbstractDeterministicStrategy strategy
            = new RemoveKnownValueInColumnStrategy(SudokuFieldColId.COL_III);

    @Before
    public void setup() {
        strategy.resetNothingToWorkFlag();
    }

    @Test
    public void when_column_not_contains_know_value_then_nothing_changed() {
        final SudokuPossibilitiesHolder testValue = new SudokuPossibilitiesHolder();
        final SudokuPossibilitiesHolder expectedValue = testValue.duplicate();

        final boolean result = strategy.apply(testValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testValue.toString());
    }

    @Test
    public void when_column_has_6_value_then_no_6_is_in_possibles() {
        final SudokuPossibilitiesHolder testValue = new SudokuPossibilitiesHolder();
        final byte SIX = (byte) 6;
        testValue.setValue(2, 3, SIX);
        final SudokuPossibilitiesHolder expectedValue = testValue.duplicate();
        expectedValue.removePossible(1, 3, SIX);
        expectedValue.removePossible(3, 3, SIX);
        expectedValue.removePossible(4, 3, SIX);
        expectedValue.removePossible(5, 3, SIX);
        expectedValue.removePossible(6, 3, SIX);
        expectedValue.removePossible(7, 3, SIX);
        expectedValue.removePossible(8, 3, SIX);
        expectedValue.removePossible(9, 3, SIX);

        final boolean result = strategy.apply(testValue);

        Assert.assertTrue(result);
        Assert.assertEquals(expectedValue.toString(), testValue.toString());
    }

    @Test
    public void when_column_has_all_values_then_nothing_changed() {
        final SudokuPossibilitiesHolder testValue = new SudokuPossibilitiesHolder();
        testValue.setValue(1, 3, (byte) 1);
        testValue.setValue(2, 3, (byte) 2);
        testValue.setValue(3, 3, (byte) 3);
        testValue.setValue(4, 3, (byte) 4);
        testValue.setValue(5, 3, (byte) 5);
        testValue.setValue(6, 3, (byte) 6);
        testValue.setValue(7, 3, (byte) 7);
        testValue.setValue(8, 3, (byte) 8);
        testValue.setValue(9, 3, (byte) 9);
        final SudokuPossibilitiesHolder expectedValue = testValue.duplicate();

        final boolean result = strategy.apply(testValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testValue.toString());
    }
}
