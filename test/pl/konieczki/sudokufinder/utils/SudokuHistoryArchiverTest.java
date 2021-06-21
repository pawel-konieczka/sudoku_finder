package pl.konieczki.sudokufinder.utils;

import org.junit.Assert;
import org.junit.Test;
import pl.konieczki.sudokufinder.model.SudokuField;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

public class SudokuHistoryArchiverTest {

    @Test
    public void test_history() {
        final SudokuHistoryArchiver archiver = new SudokuHistoryArchiver();
        final SudokuFinder sudokuFinder = new SudokuFinder(null);
        for (SudokuPossibilitiesHolder holder : archiver.getAvailableHolders()) {
            final SudokuField field = archiver.getResultFromHistory(holder);
            if (field != null) {
                final SudokuPossibilitiesHolder sudoku = sudokuFinder.findSudoku(holder);
                if (sudoku.isCompleted()) {
                    final SudokuField sudokuField = sudoku.deconstruct();
                    final String expected = sudokuField.toString();
                    if (!SudokuValidator.validate(sudokuField)) {
                        throw new RuntimeException("Invalid sudoku:\n" + expected);
                    }
                    final String tested = field.toString();
                    Assert.assertEquals(expected, tested);
                }
            }
        }
    }
}
