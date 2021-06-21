package pl.konieczki.sudokufinder.utils;

import org.junit.Assert;
import org.junit.Test;
import pl.konieczki.sudokufinder.model.SudokuField;

public class SudokuValidatorTest {

    @Test
    public void when_valid_sudoku_then_true() {
        final byte[] example = new byte[]{
                1, 4, 7, 2, 5, 3, 6, 8, 9,
                2, 5, 6, 8, 9, 4, 1, 3, 7,
                3, 8, 9, 1, 7, 6, 2, 4, 5,
                4, 1, 2, 3, 6, 7, 5, 9, 8,
                5, 7, 3, 9, 8, 2, 4, 1, 6,
                9, 6, 8, 5, 4, 1, 7, 2, 3,
                6, 3, 4, 7, 1, 8, 9, 5, 2,
                7, 2, 5, 4, 3, 9, 8, 6, 1,
                8, 9, 1, 6, 2, 5, 3, 7, 4
        };
        final SudokuField sudokuField = new SudokuField(example);
        final boolean testedValue = SudokuValidator.validate(sudokuField);
        Assert.assertTrue(testedValue);
    }

    @Test
    public void when_invalid_sudoku_then_false() {
        final byte[] example = new byte[]{
                1, 1, 7, 2, 5, 3, 6, 8, 9,
                2, 5, 6, 8, 9, 4, 1, 3, 7,
                3, 8, 9, 1, 7, 6, 2, 4, 5,
                4, 1, 2, 3, 6, 7, 5, 9, 8,
                5, 7, 3, 9, 8, 2, 4, 1, 6,
                9, 6, 8, 5, 4, 1, 7, 2, 3,
                6, 3, 4, 7, 1, 8, 9, 5, 2,
                7, 2, 5, 4, 3, 9, 8, 6, 1,
                8, 9, 1, 6, 2, 5, 3, 7, 4
        };
        final SudokuField sudokuField = new SudokuField(example);
        final boolean testedValue = SudokuValidator.validate(sudokuField);
        Assert.assertFalse(testedValue);
    }
}
