package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldColId;
import pl.konieczki.sudokufinder.model.SudokuFieldSquareId;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import java.util.Arrays;

import static pl.konieczki.sudokufinder.model.SudokuHelper.ROW_MAX_ID;
import static pl.konieczki.sudokufinder.model.SudokuHelper.ROW_MIN_ID;

/**
 * Strategia w danej kolumnie usuwa z pozostałych kwadratów te wartości, które w bieżącym kwadracie występują
 * wyłącznie w danej kolumnie
 */
public class RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy extends AbstractRemoveFromXWhenValueExistsOnlyInThisXStrategy {

    private final SudokuFieldColId colId;

    public RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(@NonNull SudokuFieldSquareId squareId, @NonNull SudokuFieldColId colId) {
        super(squareId);
        this.colId = colId;
        if (this.squareId.getMinColId() > colId.getColId() || this.squareId.getMaxColId() < colId.getColId())
            throw new IllegalArgumentException("ColId and SquareId doesn't match.");
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " [" + squareId.name() + ", " + colId.name() + "]";
    }

    @Override
    public boolean apply(@NonNull SudokuPossibilitiesHolder holder) {
        if (this.nothingToWork)
            return false;
        final var row_id = this.squareId.getMinRowId() + 1;
        final var col_id = this.colId.getColId();
        final byte[] valueBefore = holder.getPossibles(row_id - 1, col_id);
        final byte[] value = holder.getPossibles(row_id, col_id);
        final byte[] valueAfter = holder.getPossibles(row_id + 1, col_id);
        if (impossibleToHaveToCommons(valueBefore, value, valueAfter)) {
            this.nothingToWork = true;
            return false;
        }
        final byte[] all = mergeUnique(valueBefore, value, valueAfter);
        // we have to check if any value exists in at least two filed in col and not in other place
        final byte[] candidates = findCandidates(all, holder);
        // we remove them from possibles in other squares
        var result = false;
        for (byte p : candidates) {
            for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
                if (i < squareId.getMinRowId() || i > squareId.getMaxRowId()) {
                    // order of "or" statement is important
                    result = holder.removePossible(i, col_id, p) || result;
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
            var countInCol = 0;
            var countInNoCol = 0;
            for (int i = squareId.getMinRowId(); i <= squareId.getMaxRowId(); i++) {
                for (int j = squareId.getMinColId(); j <= squareId.getMaxColId(); j++) {
                    if (hasValueOrPossible(holder, c, i, j)) {
                        if (j == colId.getColId())
                            countInCol++;
                        else
                            countInNoCol++;
                    }
                }
            }
            // at least two and only in processing col
            if (countInCol > 1 && countInNoCol == 0) {
                result[cnt] = c; // is good candidate
                cnt++;
            }
        }
        return Arrays.copyOf(result, cnt);
    }
}