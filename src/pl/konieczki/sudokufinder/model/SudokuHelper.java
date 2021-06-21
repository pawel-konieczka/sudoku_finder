package pl.konieczki.sudokufinder.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SudokuHelper {

    public static final int ELEMENT_COUNT = 9;
    public static final int ROW_COUNT = ELEMENT_COUNT;
    public static final int COL_COUNT = ELEMENT_COUNT;
    public static final byte UNDEFINED_VALUE = 0;
    public static final byte MIN_VALUE = 1;
    public static final byte MAX_VALUE = 9;
    public static final byte ROW_MIN_ID = 1;
    public static final byte ROW_MAX_ID = 9;
    public static final byte COL_MIN_ID = 1;
    public static final byte COL_MAX_ID = 9;

    public static void checkValue(int value) {
        if (value != UNDEFINED_VALUE && (value < MIN_VALUE || value > MAX_VALUE))
            throw new IllegalArgumentException("Value must be between 1 and 9 or 0.");
    }

    public static void checkRange(int rowId, int colId) {
        checkRowRange(rowId);
        checkColRange(colId);
    }

    public static void checkRowRange(int rowId) {
        checkRange(rowId, ROW_MIN_ID, ROW_MAX_ID, "Row ID must be between 1 and 9.");
    }

    public static void checkColRange(int colId) {
        checkRange(colId, COL_MIN_ID, COL_MAX_ID, "Col ID must be between 1 and 9.");
    }

    private static void checkRange(int id, int minId, int maxId, String message) {
        if (id < minId || id > maxId)
            throw new IllegalArgumentException(message);
    }

    public static int calcIdx(int rowId, int colId) {
        checkRange(rowId, colId);
        return ROW_COUNT * (rowId - 1) + (colId - 1);
    }

    public byte[] getAllValues() {
        final var result = new byte[ELEMENT_COUNT];
        for (byte i = MIN_VALUE; i <= MAX_VALUE; i++) {
            result[i - MIN_VALUE] = i;
        }
        return result;
    }
}