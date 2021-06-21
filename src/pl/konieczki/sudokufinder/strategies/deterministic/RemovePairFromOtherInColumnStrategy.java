package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldColId;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import static pl.konieczki.sudokufinder.model.SudokuHelper.ROW_MAX_ID;
import static pl.konieczki.sudokufinder.model.SudokuHelper.ROW_MIN_ID;

/**
 * Strategia szuka w kolumnie dwóch pól z dwiema takimi samymi możliwościami; gdy znajdzie,
 * usuwa te dwie możliwości z pozostałych pól.
 */
public class RemovePairFromOtherInColumnStrategy extends AbstractColumnDeterministicStrategy {

    public RemovePairFromOtherInColumnStrategy(@NonNull SudokuFieldColId columnId) {
        super(columnId);
    }

    @Override
    protected boolean doApply(@NonNull SudokuPossibilitiesHolder holder) {
        var result = false;
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            final byte[] possibles = holder.getPossibles(i, colId.getColId());
            if (possibles.length == 2) {
                // order of "or" statement is important
                result = searchAndRemove(i, possibles, holder) || result;
            }
        }
        return result;
    }

    private boolean searchAndRemove(int rowId, byte[] possibles, @NonNull SudokuPossibilitiesHolder holder) {
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            if (i != rowId) {
                final byte[] possibles2 = holder.getPossibles(i, colId.getColId());
                if (possibles2.length == 2 && areEquals(possibles, possibles2)) {
                    return removeFromColumnExcept(rowId, i, possibles, holder);
                }
            }
        }
        return false;
    }

    private boolean removeFromColumnExcept(
            int rowId1, int rowId2, byte[] possibles,
            @NonNull SudokuPossibilitiesHolder holder
    ) {
        var result = false;
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            if (i != rowId1 && i != rowId2) {
                for (byte p : possibles) {
                    result = holder.removePossible(i, colId.getColId(), p) || result;
                }
            }
        }
        return result;
    }

    private boolean areEquals(byte[] possibles, byte[] possibles2) {
        return (possibles[0] == possibles2[0] && possibles[1] == possibles2[1])
                || (possibles[0] == possibles2[1] && possibles[1] == possibles2[0]);
    }
}