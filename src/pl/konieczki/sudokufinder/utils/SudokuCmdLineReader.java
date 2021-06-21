package pl.konieczki.sudokufinder.utils;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuField;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static pl.konieczki.sudokufinder.model.SudokuHelper.*;

/**
 * Class for reading sudoku input from console.
 */
public class SudokuCmdLineReader implements SudokuReader {

    @Override
    public SudokuField readSudokuField() throws IOException {
        final SudokuPossibilitiesHolder holder = readHolder();
        if (holder.isCompleted())
            return holder.deconstruct();
        throw new IllegalStateException("Unable to construct sudoku");
    }

    @Override
    public SudokuPossibilitiesHolder readSudokuPossibilitiesHolder() throws IOException {
        return readHolder();
    }

    private SudokuPossibilitiesHolder readHolder() throws IOException {
        final var result = new SudokuPossibilitiesHolder();
        final var br = new BufferedReader(new InputStreamReader(System.in));
        println(" |123456789");
        println("-+---------");
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            readHolderForRow(result, br, i);
        }
        return result;
    }

    private void readHolderForRow(SudokuPossibilitiesHolder result, BufferedReader br, int i) throws IOException {
        final int c1 = '1';
        final int c9 = '9';
        final int c0 = '0';
        var invalidValue = true;
        while (invalidValue) {
            print(i + "|");
            final String line = TextUtils.valueOrEmpty(br.readLine());
            final int len = line.length();
            if (len != COL_COUNT) {
                println("Invalid line length");
                continue;
            }
            for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
                int c = line.charAt(j - COL_MIN_ID);
                if (c >= c1 && c <= c9) {
                    result.setValue(i, j, (byte) (MIN_VALUE + c - c1));
                    invalidValue = false;
                } else if (c == c0)
                    invalidValue = false;
                else {
                    println("Invalid value at: " + j);
                    invalidValue = true;
                    break;
                }
            }
        } // while
    }

    public static void println(@NonNull String message) {
        print(message + System.lineSeparator());
    }

    @SuppressWarnings("java:S106") // this is cmd line class
    public static void print(@NonNull String message) {
        System.out.print(message);
    }
}