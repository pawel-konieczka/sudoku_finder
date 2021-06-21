package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldSquareId;
import pl.konieczki.sudokufinder.model.SudokuHelper;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import java.util.Arrays;

public abstract class AbstractRemoveFromXWhenValueExistsOnlyInThisXStrategy extends AbstractDeterministicStrategy {

    protected final SudokuFieldSquareId squareId;

    protected AbstractRemoveFromXWhenValueExistsOnlyInThisXStrategy(@NonNull SudokuFieldSquareId squareId) {
        this.squareId = squareId;
    }

    protected boolean impossibleToHaveToCommons(byte[] valueBefore, byte[] value, byte[] valueAfter) {
        // if at least two fields have only valid value, we can stop searching
        var stop = 0;
        if (valueBefore.length == 1)
            stop++;
        if (value.length == 1)
            stop++;
        if (valueAfter.length == 1)
            stop++;
        return stop > 1;
    }

    protected byte[] mergeUnique(@NonNull byte[] valueBefore, @NonNull byte[] value, @NonNull byte[] valueAfter) {
        final var myMap = new byte[SudokuHelper.ELEMENT_COUNT];
        Arrays.fill(myMap, (byte) 0);
        for (byte e : valueBefore) {
            myMap[e - 1] = 1;
        }
        for (byte e : value) {
            myMap[e - 1] = 1;
        }
        for (byte e : valueAfter) {
            myMap[e - 1] = 1;
        }
        final var result = new byte[SudokuHelper.ELEMENT_COUNT];
        var cnt = 0;
        for (byte i = 0; i < myMap.length; i++) {
            if (myMap[i] == 1) {
                result[cnt] = (byte) (i + 1);
                cnt++;
            }
        }
        return Arrays.copyOf(result, cnt);
    }

    protected boolean hasValueOrPossible(@NonNull SudokuPossibilitiesHolder holder, byte c, int i, int j) {
        return (c == holder.getValue(i, j)) || holder.hasPossible(i, j, c);
    }
}