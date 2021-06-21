package pl.konieczki.sudokufinder.model;

public enum SudokuFieldColId {
    COL_I(1),
    COL_II(2),
    COL_III(3),
    COL_IV(4),
    COL_V(5),
    COL_VI(6),
    COL_VII(7),
    COL_VIII(8),
    COL_IX(9);

    private final int colId;

    SudokuFieldColId(int colId) {
        this.colId = colId;
    }

    public int getColId() {
        return colId;
    }
}