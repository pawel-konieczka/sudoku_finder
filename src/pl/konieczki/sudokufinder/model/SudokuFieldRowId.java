package pl.konieczki.sudokufinder.model;

public enum SudokuFieldRowId {
    ROW_I(1),
    ROW_II(2),
    ROW_III(3),
    ROW_IV(4),
    ROW_V(5),
    ROW_VI(6),
    ROW_VII(7),
    ROW_VIII(8),
    ROW_IX(9);

    private final int rowId;

    SudokuFieldRowId(int rowId) {
        this.rowId = rowId;
    }

    public int getRowId() {
        return rowId;
    }
}