package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldRowId;
import pl.konieczki.sudokufinder.model.SudokuHelper;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import static pl.konieczki.sudokufinder.model.SudokuHelper.*;

/**
 * Strategia w danym wierszu usuwa z możliwych wartości te, które są znane
 */
public class RemoveKnownValueInRowStrategy extends AbstractRowDeterministicStrategy {

    public RemoveKnownValueInRowStrategy(@NonNull SudokuFieldRowId rowId) {
        super(rowId);
    }

    @Override
    protected boolean doApply(@NonNull SudokuPossibilitiesHolder holder) {
        prepareCollect();
        // 1. collect known values from row
        for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
            byte value = holder.getValue(this.rowId.getRowId(), j);
            if (value != UNDEFINED_VALUE)
                addKnownValue(value);
        }
        // 2. remove known values from unknown fields
        byte[] possibles = getKnownValues();
        if (possibles.length == SudokuHelper.ELEMENT_COUNT) {
            // all values in row are know - break strategy
            this.nothingToWork = true;
            return false;
        }
        var result = false;
        for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
            byte value = holder.getValue(this.rowId.getRowId(), j);
            if (value == UNDEFINED_VALUE) {
                for (byte possible : possibles) {
                    // order of "or" statement is important
                    result = holder.removePossible(this.rowId.getRowId(), j, possible) || result;
                }
            }
        }
        return result;
    }
}
