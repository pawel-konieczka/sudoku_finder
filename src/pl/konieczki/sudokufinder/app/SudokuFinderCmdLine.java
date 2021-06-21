package pl.konieczki.sudokufinder.app;

import pl.konieczki.sudokufinder.model.SudokuField;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;
import pl.konieczki.sudokufinder.utils.*;

public class SudokuFinderCmdLine {

    @SuppressWarnings("java:S106") // this is command line application
    public static void main(String[] args) throws Exception {
        final SudokuReader reader = new SudokuCmdLineReader();
        final var holder = reader.readSudokuPossibilitiesHolder();
        System.out.println("Input:");
        System.out.println("====================================================================");
        System.out.print(holder);
        System.out.println("Output:");
        System.out.println("====================================================================");
        final var finder = new SudokuFinder(new SudokuHistoryArchiver());
        final SudokuPossibilitiesHolder sudoku = finder.findSudoku(holder);
        if (sudoku.isCompleted()) {
            final SudokuField field = sudoku.deconstruct();
            if (SudokuValidator.validate(field)) {
                System.out.print(field);
            } else {
                System.out.println("Result sudoku is invalid:");
                System.out.print(field);
            }
        } else {
            System.out.println("Unable to find sudoku!");
        }
    }
}