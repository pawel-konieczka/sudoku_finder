package pl.konieczki.sudokufinder.strategies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.konieczki.sudokufinder.model.SudokuFieldSquareId;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;
import pl.konieczki.sudokufinder.strategies.deterministic.AbstractDeterministicStrategy;
import pl.konieczki.sudokufinder.strategies.deterministic.FindOnlyOneOccurrenceOfValueInSquareStrategy;

public class FindOnlyOneOccurrenceOfValueInSquareStrategyTest {

    private final AbstractDeterministicStrategy strategy
            = new FindOnlyOneOccurrenceOfValueInSquareStrategy(SudokuFieldSquareId.SQUARE_I);

    @Before
    public void setup() {
        strategy.resetNothingToWorkFlag();
    }

    @Test
    public void when_no_singles_exist_then_nothing_changed() {
        final SudokuPossibilitiesHolder testedValue = new SudokuPossibilitiesHolder();
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();

        final boolean result = strategy.apply(testedValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }

    @Test
    public void when_value_3_exists_then_nothing_changed() {
        final SudokuPossibilitiesHolder testedValue = new SudokuPossibilitiesHolder();
        testedValue.setValue(2, 2, (byte) 3);
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();

        final boolean result = strategy.apply(testedValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }

    @Test
    public void when_possible_3_exists_in_one_possibles_then_3_is_set_as_value() {
        final SudokuPossibilitiesHolder testedValue = new SudokuPossibilitiesHolder();
        final byte TREE = (byte) 3;
        testedValue.removePossible(1, 1, TREE);
        testedValue.removePossible(1, 2, TREE);
        testedValue.removePossible(1, 3, TREE);
        testedValue.removePossible(2, 2, TREE);
        testedValue.removePossible(2, 3, TREE);
        testedValue.removePossible(3, 1, TREE);
        testedValue.removePossible(3, 2, TREE);
        testedValue.removePossible(3, 3, TREE);
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();
        expectedValue.setValue(2, 1, TREE);

        final boolean result = strategy.apply(testedValue);

        Assert.assertTrue(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }
}
