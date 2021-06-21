package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldRowId;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import static pl.konieczki.sudokufinder.model.SudokuHelper.COL_MAX_ID;
import static pl.konieczki.sudokufinder.model.SudokuHelper.COL_MIN_ID;

/**
 * Strategia szuka w wierszu dwóch pól z dwiema takimi samymi możliwościami; gdy znajdzie,
 * usuwa te dwie możliwości z pozostałych pól.
 */
public class RemovePairFromOtherInRowStrategy extends AbstractRowDeterministicStrategy {

    public RemovePairFromOtherInRowStrategy(@NonNull SudokuFieldRowId rowId) {
        super(rowId);
    }

    @Override
    protected boolean doApply(@NonNull SudokuPossibilitiesHolder holder) {
        var result = false;
        for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
            final byte[] possibles = holder.getPossibles(rowId.getRowId(), j);
            if (possibles.length == 2) {
                // order of "or" statement is important
                result = searchAndRemove(j, possibles, holder) || result;
            }
        }
        return result;
    }

    private boolean searchAndRemove(int colId, byte[] possibles, @NonNull SudokuPossibilitiesHolder holder) {
        for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
            if (j != colId) {
                final byte[] possibles2 = holder.getPossibles(rowId.getRowId(), j);
                if (possibles2.length == 2 && areEquals(possibles, possibles2)) {
                    return removeFromRowExcept(colId, j, possibles, holder);
                }
            }
        }
        return false;
    }

    private boolean removeFromRowExcept(
            int colId1, int colId2, byte[] possibles,
            @NonNull SudokuPossibilitiesHolder holder
    ) {
        var result = false;
        for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
            if (j != colId1 && j != colId2) {
                for (byte p : possibles) {
                    result = holder.removePossible(rowId.getRowId(), j, p) || result;
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
