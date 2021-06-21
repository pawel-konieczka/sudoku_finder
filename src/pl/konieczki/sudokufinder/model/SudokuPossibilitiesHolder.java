package pl.konieczki.sudokufinder.model;

import lombok.NonNull;

import java.util.Arrays;

import static pl.konieczki.sudokufinder.model.SudokuHelper.*;

public class SudokuPossibilitiesHolder {

    private final byte[][] possibles;

    public SudokuPossibilitiesHolder() {
        this.possibles = new byte[ROW_COUNT * COL_COUNT][];
        resetAll();
    }

    public static SudokuPossibilitiesHolder construct(@NonNull SudokuField sf) {
        final var result = new SudokuPossibilitiesHolder();
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++)
            for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
                byte value = sf.get(i, j);
                if (value != SudokuHelper.UNDEFINED_VALUE) {
                    result.setValue(i, j, value);
                }
            }
        return result;
    }

    public SudokuField deconstruct() {
        final var result = new SudokuField();
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
                final int idx = calcIdx(i, j);
                if (this.possibles[idx].length != 1)
                    throw new IllegalStateException("Unable to construct SudokuField:\n" + this);
                result.set(i, j, this.possibles[idx][0]);
            }
        }
        return result;
    }

    public void setValue(int rowId, int colId, byte value) {
        checkValue(value);
        this.possibles[calcIdx(rowId, colId)] = new byte[]{value};
    }

    public byte getValue(int rowId, int colId) {
        final int idx = calcIdx(rowId, colId);
        if (this.possibles[idx].length > 1)
            return SudokuHelper.UNDEFINED_VALUE;
        return this.possibles[idx][0];
    }

    public void resetAll() {
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
                resetPossibles(i, j);
            }
        }
    }

    public void resetPossibles(int rowId, int colId) {
        final int idx = calcIdx(rowId, colId);
        this.possibles[idx] = SudokuHelper.getAllValues();
    }

    public void removePossibles(int rowId, int colId, @NonNull byte[] possibles) {
        for (byte p : possibles)
            removePossible(rowId, colId, p);
    }

    public boolean removePossible(int rowId, int colId, byte possible) {
        checkValue(possible);
        final int idx = calcIdx(rowId, colId);
        if (this.possibles[idx].length == 1 && this.possibles[idx][0] == possible)
            throw new IllegalStateException(
                    "Trying to remove last possible (rowId: " + rowId + ", colId: " + colId + ", possible: " + possible + ")"
            );
        final var tmp = new byte[ELEMENT_COUNT];
        var tmpCnt = 0;
        var result = false;
        for (var i = 0; i < this.possibles[idx].length; i++) {
            if (this.possibles[idx][i] != possible) {
                tmp[tmpCnt] = this.possibles[idx][i];
                tmpCnt++;
            } else {
                result = true; // if possible found
            }
        }
        this.possibles[idx] = Arrays.copyOf(tmp, tmpCnt);
        return result;
    }

    public byte[] getPossibles(int rowId, int colId) {
        final int idx = calcIdx(rowId, colId);
        // @TODO: think if you have a value (single) what should be returned?
        return Arrays.copyOf(this.possibles[idx], this.possibles[idx].length);
    }

    public boolean hasPossible(int rowId, int colId, byte possible) {
        checkValue(possible);
        final int idx = calcIdx(rowId, colId);
        if (this.possibles[idx].length == 1)
            return false; // possible is not equal to value
        for (var i = 0; i < this.possibles[idx].length; i++)
            if (this.possibles[idx][i] == possible)
                return true;
        return false;
    }

    public String toString() {
        final var sb = new StringBuilder();
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
                sb.append('[').append(i).append(',').append(j).append(']').append(" = {");
                final int idx = calcIdx(i, j);
                for (var k = 0; k < this.possibles[idx].length; k++) {
                    sb.append(this.possibles[idx][k]).append(',');
                }
                sb.deleteCharAt(sb.length() - 1).append('}').append("\n");
            }
        }
        return sb.toString();
    }

    public SudokuPossibilitiesHolder duplicate() {
        final var result = new SudokuPossibilitiesHolder();
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
                final int idx = calcIdx(i, j);
                result.possibles[idx] = Arrays.copyOf(this.possibles[idx], possibles[idx].length);
            }
        }
        return result;
    }

    public boolean isCompleted() {
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
                final int idx = calcIdx(i, j);
                if (this.possibles[idx].length != 1)
                    return false;
            }
        }
        return true;
    }

    public static SudokuPossibilitiesHolder parse(String string) {
        // [1,1] = {4}||[1,2] = {1}||[1,3] = {7}||
        if (string == null || string.isEmpty())
            return null;
        final String[] cells = string.split("[\n\r]");
        final int total = ROW_COUNT * COL_COUNT;
        if (cells.length != total)
            throw new IllegalStateException("Invalid element count: expected " + total + " but get " + cells.length);
        final var result = new SudokuPossibilitiesHolder();
        for (var i = 0; i < total; i++) {
            final int rowId = 1 + cells[i].charAt(1) - (int) '1';
            final int colId = 1 + cells[i].charAt(3) - (int) '1';
            final var arr = cells[i].substring(9, cells[i].length() - 1);
            final String[] arr2 = arr.split(",");
            final var possibles = new byte[ELEMENT_COUNT];
            Arrays.fill(possibles, (byte) 0);
            for (String s : arr2) {
                byte p = (byte) (s.charAt(0) - '1');
                possibles[p]++;
            }
            final var possiblesToRemove = new byte[possibles.length - arr2.length];
            var cnt = 0;
            for (byte p = 0; p < possibles.length; p++) {
                if (possibles[p] == 0) {
                    possiblesToRemove[cnt] = (byte) (p + 1);
                    cnt++;
                }
            }
            if (cnt > 0) {
                result.removePossibles(rowId, colId, Arrays.copyOf(possiblesToRemove, cnt));
            }
        }
        return result;
    }
}