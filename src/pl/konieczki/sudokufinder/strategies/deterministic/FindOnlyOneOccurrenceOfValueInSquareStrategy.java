package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldSquareId;
import pl.konieczki.sudokufinder.model.SudokuHelper;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import java.util.Arrays;

/**
 * Strategia w ramach kwadratu szuka wystąpień pojedynczych możliwości i jeśli są pojedyncze,
 * to ustawia ją jako wartość.
 */
public class FindOnlyOneOccurrenceOfValueInSquareStrategy extends AbstractSquareDeterministicStrategy {

    public FindOnlyOneOccurrenceOfValueInSquareStrategy(@NonNull SudokuFieldSquareId squareId) {
        super(squareId);
    }

    @Override
    protected boolean doApply(@NonNull SudokuPossibilitiesHolder holder) {
        final var count = new int[SudokuHelper.ELEMENT_COUNT];
        Arrays.fill(count, 0);
        // counting occurrences of all possibles
        for (int i = squareId.getMinRowId(); i <= squareId.getMaxRowId(); i++) {
            for (int j = squareId.getMinColId(); j <= squareId.getMaxColId(); j++) {
                final byte[] possibles = holder.getPossibles(i, j);
                for (byte p : possibles) {
                    count[p - 1]++;
                }
            }
        }
        var result = false;
        // searching which occurs once
        for (var k = 0; k < count.length; k++) {
            if (count[k] == 1) {
                // order of "or" statement is important
                result = findAndSetProperValue(holder, (byte) (k + 1)) || result;
            }
        }
        return result;
    }

    private boolean findAndSetProperValue(@NonNull SudokuPossibilitiesHolder holder, byte value) {
        for (int i = squareId.getMinRowId(); i <= squareId.getMaxRowId(); i++) {
            for (int j = squareId.getMinColId(); j <= squareId.getMaxColId(); j++) {
                if (holder.hasPossible(i, j, value)) {
                    holder.setValue(i, j, value);
                    return true;
                }
            }
        }
        return false;
    }
}
