package pl.konieczki.sudokufinder.strategies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.konieczki.sudokufinder.model.SudokuField;
import pl.konieczki.sudokufinder.model.SudokuFieldRowId;
import pl.konieczki.sudokufinder.model.SudokuFieldSquareId;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;
import pl.konieczki.sudokufinder.strategies.deterministic.AbstractDeterministicStrategy;
import pl.konieczki.sudokufinder.strategies.deterministic.RemoveFromRowWhenValueExistsOnlyInThisRowStrategy;

public class RemoveFromRowWhenValueExistsOnlyInThisRowStrategyTest {

    private final AbstractDeterministicStrategy strategy
            = new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(
            SudokuFieldSquareId.SQUARE_I, SudokuFieldRowId.ROW_II
    );

    @Before
    public void setup() {
        strategy.resetNothingToWorkFlag();
    }

    @Test
    public void when_no_possibles_then_nothing_changed() {
        final byte[] fields = new byte[]{
                1, 2, 3, 0, 0, 0, 0, 0, 0,
                4, 5, 6, 0, 0, 0, 0, 0, 0,
                7, 8, 9, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        final SudokuPossibilitiesHolder testedValue
                = SudokuPossibilitiesHolder.construct(
                new SudokuField(fields)
        );
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();

        final boolean result = strategy.apply(testedValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }

    @Test
    public void when_all_fields_in_row_are_defined_then_nothing_changed() {
        final byte[] fields = new byte[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                4, 5, 6, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        final SudokuPossibilitiesHolder testedValue
                = SudokuPossibilitiesHolder.construct(
                new SudokuField(fields)
        );
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();

        final boolean result = strategy.apply(testedValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }

    @Test
    public void when_two_fields_in_row_are_defined_then_nothing_changed() {
        final byte[] fields = new byte[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                4, 0, 6, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        final SudokuPossibilitiesHolder testedValue
                = SudokuPossibilitiesHolder.construct(
                new SudokuField(fields)
        );
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();

        final boolean result = strategy.apply(testedValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }

    @Test
    public void when_5_exists_twice_only_in_row_then_5_is_removed_from_other_squares() {
        final SudokuPossibilitiesHolder testedValue = new SudokuPossibilitiesHolder();
        final byte FIVE = (byte) 5;
        testedValue.removePossible(1, 1, FIVE);
        testedValue.removePossible(1, 2, FIVE);
        testedValue.removePossible(1, 3, FIVE);
        testedValue.removePossibles(2, 1, new byte[]{1, 2, 3, 6, 7, 8, 9});
        testedValue.removePossibles(2, 2, new byte[]{1, 2, 3, 5, 7, 8, 9});
        testedValue.removePossibles(2, 3, new byte[]{1, 2, 3, 4, 7, 8, 9});
        testedValue.removePossible(3, 1, FIVE);
        testedValue.removePossible(3, 2, FIVE);
        testedValue.removePossible(3, 3, FIVE);
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();
        expectedValue.removePossible(2, 4, FIVE);
        expectedValue.removePossible(2, 5, FIVE);
        expectedValue.removePossible(2, 6, FIVE);
        expectedValue.removePossible(2, 7, FIVE);
        expectedValue.removePossible(2, 8, FIVE);
        expectedValue.removePossible(2, 9, FIVE);

        final boolean result = strategy.apply(testedValue);

        Assert.assertTrue(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }

    @Test
    public void when_2_exists_not_only_in_row_then_2_is_not_removed() {
        final SudokuPossibilitiesHolder testedValue = new SudokuPossibilitiesHolder();
        final byte FIVE = (byte) 5;
        testedValue.removePossible(1, 1, FIVE);
        testedValue.removePossible(1, 2, FIVE);
        testedValue.removePossible(1, 3, FIVE);
        testedValue.removePossibles(2, 1, new byte[]{1, 2, 3, 6, 7, 8, 9});
        testedValue.removePossibles(2, 2, new byte[]{1, 2, 3, 5, 7, 8, 9});
        testedValue.removePossibles(2, 3, new byte[]{1, 2, 3, 4, 7, 8, 9});
        testedValue.removePossible(3, 1, FIVE);
        testedValue.removePossible(3, 2, FIVE);
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();

        final boolean result = strategy.apply(testedValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }
}
