package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldSquareId;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

/**
 * Strategia szuka w kwadracie dwóch pól z dwiema takimi samymi możliwościami; gdy znajdzie,
 * usuwa te dwie możliwości z pozostałych pól.
 */
public class RemovePairFromOtherInSquareStrategy extends AbstractSquareDeterministicStrategy {

    public RemovePairFromOtherInSquareStrategy(@NonNull SudokuFieldSquareId squareId) {
        super(squareId);
    }

    @Override
    protected boolean doApply(@NonNull SudokuPossibilitiesHolder holder) {
        var result = false;
        for (int i = squareId.getMinRowId(); i <= squareId.getMaxRowId(); i++) {
            for (int j = squareId.getMinColId(); j <= squareId.getMaxColId(); j++) {
                final byte[] possibles = holder.getPossibles(i, j);
                if (possibles.length == 2) {
                    // order of "or" statement is important
                    result = searchAndRemove(i, j, possibles, holder) || result;
                }
            }
        }
        return result;
    }

    private boolean searchAndRemove(int rowId, int colId, byte[] possibles, @NonNull SudokuPossibilitiesHolder holder) {
        for (int i = squareId.getMinRowId(); i <= squareId.getMaxRowId(); i++) {
            for (int j = squareId.getMinColId(); j <= squareId.getMaxColId(); j++) {
                if ((i != rowId) || (j != colId)) {
                    final byte[] possibles2 = holder.getPossibles(i, j);
                    if (possibles2.length == 2 && areEquals(possibles, possibles2)) {
                        return removeFromSquareExcept(rowId, colId, i, j, possibles, holder);
                    }
                }
            }
        }
        return false;
    }

    private boolean removeFromSquareExcept(
            int rowId1, int colId1, int rowId2, int colId2, byte[] possibles,
            @NonNull SudokuPossibilitiesHolder holder
    ) {
        var result = false;
        for (int i = squareId.getMinRowId(); i <= squareId.getMaxRowId(); i++) {
            for (int j = squareId.getMinColId(); j <= squareId.getMaxColId(); j++) {
                if ((i != rowId1 || j != colId1) && (i != rowId2 || j != colId2)) {
                    for (byte p : possibles) {
                        result = holder.removePossible(i, j, p) || result;
                    }
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
