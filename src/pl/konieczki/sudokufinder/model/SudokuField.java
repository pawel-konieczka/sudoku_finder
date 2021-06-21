package pl.konieczki.sudokufinder.model;

import java.util.Arrays;

import static pl.konieczki.sudokufinder.model.SudokuHelper.*;

public class SudokuField {

    private final byte[] numbers;

    public SudokuField() {
        this.numbers = new byte[ROW_COUNT * COL_COUNT];
        Arrays.fill(this.numbers, UNDEFINED_VALUE);
    }

    public SudokuField(byte[] buffer) {
        if (buffer == null || buffer.length != ROW_COUNT * COL_COUNT)
            throw new IllegalArgumentException("Bad buffer size!");
        this.numbers = Arrays.copyOf(buffer, buffer.length);
    }

    public byte get(int rowId, int colId) {
        return this.numbers[SudokuHelper.calcIdx(rowId, colId)];
    }

    public void set(int rowId, int colId, byte value) {
        checkValue(value);
        this.numbers[SudokuHelper.calcIdx(rowId, colId)] = value;
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder();
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
                if (j > COL_MIN_ID)
                    sb.append(' ');
                sb.append(this.numbers[SudokuHelper.calcIdx(i, j)]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static SudokuField parse(String input) {
        //4 1 7 3 9 8 6 2 5
        //2 3 8 6 4 5 9 7 1
        //6 5 9 1 7 2 3 8 4
        //9 7 4 2 5 6 1 3 8
        //3 2 6 8 1 4 5 9 7
        //1 8 5 7 3 9 4 6 2
        //7 6 3 5 2 1 8 4 9
        //5 4 2 9 8 3 7 1 6
        //8 9 1 4 6 7 2 5 3
        if (input == null || input.isEmpty())
            return null;
        input = input.replaceAll("[\n\r]+", " ");
        final String[] arr = input.split(" ");
        final int total = ROW_COUNT * COL_COUNT;
        if (arr.length != total)
            throw new IllegalStateException("Invalid element count: expected " + total + " but get " + arr.length);
        final var fields = new byte[total];
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
                final int idx = calcIdx(i, j);
                fields[idx] = (byte) (1 + arr[idx].charAt(0) - '1');
            }
        }
        return new SudokuField(fields);
    }
}