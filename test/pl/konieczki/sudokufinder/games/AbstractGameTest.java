package pl.konieczki.sudokufinder.games;

import lombok.NonNull;
import org.junit.Assert;
import org.junit.Test;
import pl.konieczki.sudokufinder.model.SudokuField;
import pl.konieczki.sudokufinder.model.SudokuHelper;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;
import pl.konieczki.sudokufinder.utils.SudokuFinder;
import pl.konieczki.sudokufinder.utils.SudokuValidator;

abstract class AbstractGameTest {

    @Test
    public void runTest() {
        final SudokuField input = new SudokuField(prepareFields());
        final SudokuPossibilitiesHolder sph = SudokuPossibilitiesHolder.construct(input);
        final SudokuPossibilitiesHolder result = new SudokuFinder(null)
                .findSudoku(sph);
        final SudokuField sudokuField = result.deconstruct();
        Assert.assertTrue(SudokuValidator.validate(sudokuField));
        Assert.assertEquals(prepareFirstRow(), genFirstRowAsString(sudokuField));
    }

    protected abstract byte[] prepareFields();

    protected abstract String prepareFirstRow();

    private String genFirstRowAsString(@NonNull SudokuField sudokuField) {
        final var sb = new StringBuilder();
        for (var c = SudokuHelper.COL_MIN_ID; c <= SudokuHelper.COL_MAX_ID; c++)
            sb.append(sudokuField.get(SudokuHelper.ROW_MIN_ID, c));
        return sb.toString();
    }
}