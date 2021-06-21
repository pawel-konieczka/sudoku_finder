package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldRowId;
import pl.konieczki.sudokufinder.model.SudokuHelper;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import java.util.Arrays;

import static pl.konieczki.sudokufinder.model.SudokuHelper.COL_MAX_ID;
import static pl.konieczki.sudokufinder.model.SudokuHelper.COL_MIN_ID;

/**
 * Strategia w ramach wiersza szuka wystąpień pojedynczych możliwości i jeśli są pojedyncze,
 * to ustawia ją jako wartość.
 */
public class FindOnlyOneOccurrenceOfValueInRowStrategy extends AbstractRowDeterministicStrategy {

    public FindOnlyOneOccurrenceOfValueInRowStrategy(@NonNull SudokuFieldRowId rowId) {
        super(rowId);
    }

    @Override
    protected boolean doApply(@NonNull SudokuPossibilitiesHolder holder) {
        final var count = new int[SudokuHelper.ELEMENT_COUNT];
        Arrays.fill(count, 0);
        // counting occurrences of all possibles
        for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
            final byte[] possibles = holder.getPossibles(rowId.getRowId(), j);
            for (byte p : possibles) {
                count[p - 1]++;
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
        for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
            if (holder.hasPossible(rowId.getRowId(), j, value)) {
                holder.setValue(rowId.getRowId(), j, value);
                return true;
            }
        }
        return false;
    }
}
