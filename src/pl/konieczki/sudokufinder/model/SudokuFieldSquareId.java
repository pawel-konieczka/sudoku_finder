package pl.konieczki.sudokufinder.model;

public enum SudokuFieldSquareId {
    SQUARE_I(1, 3, 1, 3),
    SQUARE_II(1, 3, 4, 6),
    SQUARE_III(1, 3, 7, 9),
    SQUARE_IV(4, 6, 1, 3),
    SQUARE_V(4, 6, 4, 6),
    SQUARE_VI(4, 6, 7, 9),
    SQUARE_VII(7, 9, 1, 3),
    SQUARE_VIII(7, 9, 4, 6),
    SQUARE_IX(7, 9, 7, 9);

    private final int minRowId;
    private final int maxRowId;
    private final int minColId;
    private final int maxColId;

    public int getMinRowId() {
        return minRowId;
    }

    public int getMaxRowId() {
        return maxRowId;
    }

    public int getMinColId() {
        return minColId;
    }

    public int getMaxColId() {
        return maxColId;
    }

    SudokuFieldSquareId(int minRowId, int maxRowId, int minColId, int maxColId) {
        this.minRowId = minRowId;
        this.maxRowId = maxRowId;
        this.minColId = minColId;
        this.maxColId = maxColId;
    }
}