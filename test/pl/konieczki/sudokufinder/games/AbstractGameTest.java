package pl.konieczki.sudokufinder.games;

import lombok.NonNull;
import org.junit.Assert;
import pl.konieczki.sudokufinder.model.SudokuField;
import pl.konieczki.sudokufinder.model.SudokuHelper;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;
import pl.konieczki.sudokufinder.utils.SudokuFinder;
import pl.konieczki.sudokufinder.utils.SudokuValidator;

abstract class AbstractGameTest {

    protected void findSudoku(@NonNull byte[] fields, @NonNull String firstRow) {
        final SudokuField input = new SudokuField(fields);
        final SudokuPossibilitiesHolder sph = SudokuPossibilitiesHolder.construct(input);
        final SudokuPossibilitiesHolder result = new SudokuFinder(null)
                .findSudoku(sph);
        final SudokuField sudokuField = result.deconstruct();
        Assert.assertTrue(SudokuValidator.validate(sudokuField));
        Assert.assertEquals(firstRow, genFirstRowAsString(sudokuField));
    }

    private String genFirstRowAsString(@NonNull SudokuField sudokuField) {
        final var sb = new StringBuilder();
        for (var c = SudokuHelper.COL_MIN_ID; c <= SudokuHelper.COL_MAX_ID; c++)
            sb.append(sudokuField.get(SudokuHelper.ROW_MIN_ID, c));
        return sb.toString();
    }
}
