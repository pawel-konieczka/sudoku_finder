package pl.konieczki.sudokufinder.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import pl.konieczki.sudokufinder.model.*;

import java.util.Arrays;

@UtilityClass
public class SudokuValidator {

    public boolean validate(@NonNull SudokuField sudokuField) {
        for (SudokuFieldRowId rowId : SudokuFieldRowId.values())
            if (!validateRow(sudokuField, rowId))
                return false;
        for (SudokuFieldColId colId : SudokuFieldColId.values())
            if (!validateCol(sudokuField, colId))
                return false;
        for (SudokuFieldSquareId squareId : SudokuFieldSquareId.values())
            if (!validateSquare(sudokuField, squareId))
                return false;
        return true;
    }

    private boolean validateRow(@NonNull SudokuField sudokuField, @NonNull SudokuFieldRowId rowId) {
        final var arr = new byte[SudokuHelper.COL_COUNT];
        Arrays.fill(arr, (byte) 0);
        for (int j = SudokuHelper.COL_MIN_ID; j <= SudokuHelper.COL_MAX_ID; j++) {
            arr[sudokuField.get(rowId.getRowId(), j) - 1] = 1;
        }
        var sum = 0;
        for (byte a : arr)
            sum = sum + a;
        return SudokuHelper.COL_COUNT == sum;
    }

    private boolean validateCol(@NonNull SudokuField sudokuField, @NonNull SudokuFieldColId colId) {
        final var arr = new byte[SudokuHelper.ROW_COUNT];
        Arrays.fill(arr, (byte) 0);
        for (int i = SudokuHelper.ROW_MIN_ID; i <= SudokuHelper.ROW_MAX_ID; i++) {
            arr[sudokuField.get(i, colId.getColId()) - 1] = 1;
        }
        var sum = 0;
        for (byte a : arr)
            sum = sum + a;
        return SudokuHelper.ROW_COUNT == sum;
    }

    private boolean validateSquare(@NonNull SudokuField sudokuField, @NonNull SudokuFieldSquareId squareId) {
        final var arr = new byte[SudokuHelper.ROW_COUNT];
        Arrays.fill(arr, (byte) 0);
        for (int i = squareId.getMinRowId(); i <= squareId.getMaxRowId(); i++) {
            for (int j = squareId.getMinColId(); j <= squareId.getMaxColId(); j++) {
                arr[sudokuField.get(i, j) - 1] = 1;
            }
        }
        var sum = 0;
        for (byte a : arr)
            sum = sum + a;
        return SudokuHelper.ROW_COUNT == sum;
    }
}