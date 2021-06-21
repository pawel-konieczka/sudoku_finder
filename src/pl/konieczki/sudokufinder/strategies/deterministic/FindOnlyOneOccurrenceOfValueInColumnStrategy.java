package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldColId;
import pl.konieczki.sudokufinder.model.SudokuHelper;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import java.util.Arrays;

import static pl.konieczki.sudokufinder.model.SudokuHelper.ROW_MAX_ID;
import static pl.konieczki.sudokufinder.model.SudokuHelper.ROW_MIN_ID;

public class FindOnlyOneOccurrenceOfValueInColumnStrategy extends AbstractColumnDeterministicStrategy {

    public FindOnlyOneOccurrenceOfValueInColumnStrategy(@NonNull SudokuFieldColId colId) {
        super(colId);
    }

    @Override
    protected boolean doApply(@NonNull SudokuPossibilitiesHolder holder) {
        final var count = new int[SudokuHelper.ELEMENT_COUNT];
        Arrays.fill(count, 0);
        // counting occurrences of all possibles
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            final byte[] possibles = holder.getPossibles(i, colId.getColId());
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
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            if (holder.hasPossible(i, colId.getColId(), value)) {
                holder.setValue(i, colId.getColId(), value);
                return true;
            }
        }
        return false;
    }
}