package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldRowId;
import pl.konieczki.sudokufinder.model.SudokuFieldSquareId;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import java.util.Arrays;

import static pl.konieczki.sudokufinder.model.SudokuHelper.COL_MAX_ID;
import static pl.konieczki.sudokufinder.model.SudokuHelper.COL_MIN_ID;

/**
 * Strategia w danym wierszu usuwa z pozostałych kwadratów te wartości, które w bieżącym kwadracie występują
 * wyłącznie w danym wierszu
 */
public class RemoveFromRowWhenValueExistsOnlyInThisRowStrategy extends AbstractRemoveFromXWhenValueExistsOnlyInThisXStrategy {

    private final SudokuFieldRowId rowId;

    public RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(@NonNull SudokuFieldSquareId squareId, @NonNull SudokuFieldRowId rowId) {
        super(squareId);
        this.rowId = rowId;
        if (this.squareId.getMinRowId() > rowId.getRowId() || this.squareId.getMaxRowId() < rowId.getRowId())
            throw new IllegalArgumentException("RowId and SquareId doesn't match.");
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " [" + squareId.name() + ", " + rowId.name() + "]";
    }

    @Override
    public boolean apply(@NonNull SudokuPossibilitiesHolder holder) {
        if (this.nothingToWork)
            return false;
        final int col_id = this.squareId.getMinColId() + 1;
        final int row_id = this.rowId.getRowId();
        final byte[] valueBefore = holder.getPossibles(row_id, col_id - 1);
        final byte[] value = holder.getPossibles(row_id, col_id);
        final byte[] valueAfter = holder.getPossibles(row_id, col_id + 1);
        if (impossibleToHaveToCommons(valueBefore, value, valueAfter)) {
            this.nothingToWork = true;
            return false;
        }
        final byte[] all = mergeUnique(valueBefore, value, valueAfter);
        // we have to check if any value exists in at least two filed in row and not in other place
        final byte[] candidates = findCandidates(all, holder);
        // we remove them from possibles in other squares
        var result = false;
        for (byte p : candidates) {
            for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
                if (j < squareId.getMinColId() || j > squareId.getMaxColId()) {
                    // order of "or" statement is important
                    result = holder.removePossible(row_id, j, p) || result;
                }
            }
        }
        return result;
    }

    @SuppressWarnings("java:S3776")
    private byte[] findCandidates(@NonNull byte[] candidates, @NonNull SudokuPossibilitiesHolder holder) {
        final var result = new byte[candidates.length];
        var cnt = 0;
        for (byte c : candidates) {
            var countInRow = 0;
            var countInNoRow = 0;
            for (int i = squareId.getMinRowId(); i <= squareId.getMaxRowId(); i++) {
                for (int j = squareId.getMinColId(); j <= squareId.getMaxColId(); j++) {
                    if (hasValueOrPossible(holder, c, i, j)) {
                        if (i == rowId.getRowId())
                            countInRow++;
                        else
                            countInNoRow++;
                    }
                }
            }
            // at least two and only in processing row
            if (countInRow > 1 && countInNoRow == 0) {
                result[cnt] = c; // is good candidate
                cnt++;
            }
        }
        return Arrays.copyOf(result, cnt);
    }
}