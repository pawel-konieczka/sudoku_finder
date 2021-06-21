package pl.konieczki.sudokufinder.utils;

import pl.konieczki.sudokufinder.model.SudokuField;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import java.io.IOException;

public interface SudokuReader {

    SudokuField readSudokuField() throws IOException;

    SudokuPossibilitiesHolder readSudokuPossibilitiesHolder() throws IOException;
}
