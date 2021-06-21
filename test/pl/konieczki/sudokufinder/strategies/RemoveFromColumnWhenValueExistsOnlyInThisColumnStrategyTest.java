package pl.konieczki.sudokufinder.strategies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.konieczki.sudokufinder.model.SudokuField;
import pl.konieczki.sudokufinder.model.SudokuFieldColId;
import pl.konieczki.sudokufinder.model.SudokuFieldSquareId;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;
import pl.konieczki.sudokufinder.strategies.deterministic.AbstractDeterministicStrategy;
import pl.konieczki.sudokufinder.strategies.deterministic.RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy;

public class RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategyTest {

    private final AbstractDeterministicStrategy strategy
            = new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(
            SudokuFieldSquareId.SQUARE_I, SudokuFieldColId.COL_II
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
    public void when_all_fields_in_column_are_defined_then_nothing_changed() {
        final byte[] fields = new byte[]{
                0, 2, 0, 0, 0, 0, 0, 0, 0,
                0, 5, 0, 0, 0, 0, 0, 0, 0,
                0, 8, 0, 0, 0, 0, 0, 0, 0,
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
    public void when_two_fields_in_column_are_defined_then_nothing_changed() {
        final byte[] fields = new byte[]{
                0, 2, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 8, 0, 0, 0, 0, 0, 0, 0,
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
    public void when_5_exists_twice_only_in_column_then_5_is_removed_from_other_squares() {
        final SudokuPossibilitiesHolder testedValue = new SudokuPossibilitiesHolder();
        final byte FIVE = (byte) 5;
        testedValue.removePossible(1, 1, FIVE);
        testedValue.removePossibles(1, 2, new byte[]{1, 2, 3, 6, 7, 8, 9});
        testedValue.removePossible(1, 3, FIVE);
        testedValue.removePossible(2, 1, FIVE);
        testedValue.removePossibles(2, 2, new byte[]{1, 2, 3, 5, 7, 8, 9});
        testedValue.removePossible(2, 3, FIVE);
        testedValue.removePossible(3, 1, FIVE);
        testedValue.removePossibles(3, 2, new byte[]{1, 2, 3, 4, 7, 8, 9});
        testedValue.removePossible(3, 3, FIVE);
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();
        expectedValue.removePossible(4, 2, FIVE);
        expectedValue.removePossible(5, 2, FIVE);
        expectedValue.removePossible(6, 2, FIVE);
        expectedValue.removePossible(7, 2, FIVE);
        expectedValue.removePossible(8, 2, FIVE);
        expectedValue.removePossible(9, 2, FIVE);

        final boolean result = strategy.apply(testedValue);

        Assert.assertTrue(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }

    @Test
    public void when_2_exists_not_only_in_column_then_2_is_not_removed() {
        final SudokuPossibilitiesHolder testedValue = new SudokuPossibilitiesHolder();
        final byte FIVE = (byte) 5;
        testedValue.removePossible(1, 1, FIVE);
        testedValue.removePossibles(1, 2, new byte[]{1, 2, 3, 6, 7, 8, 9});
        testedValue.removePossible(1, 3, FIVE);
        testedValue.removePossible(2, 1, FIVE);
        testedValue.removePossibles(2, 2, new byte[]{1, 2, 3, 5, 7, 8, 9});
        testedValue.removePossible(2, 3, FIVE);
        testedValue.removePossible(3, 1, FIVE);
        testedValue.removePossibles(3, 2, new byte[]{1, 2, 3, 4, 7, 8, 9});
        final SudokuPossibilitiesHolder expectedValue = testedValue.duplicate();

        final boolean result = strategy.apply(testedValue);

        Assert.assertFalse(result);
        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }
}
