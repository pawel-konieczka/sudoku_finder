package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldColId;
import pl.konieczki.sudokufinder.model.SudokuHelper;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import static pl.konieczki.sudokufinder.model.SudokuHelper.ROW_MAX_ID;
import static pl.konieczki.sudokufinder.model.SudokuHelper.ROW_MIN_ID;

/**
 * Strategia w danej kolumnie usuwa z możliwych wartości te, które są znane
 */
public class RemoveKnownValueInColumnStrategy extends AbstractColumnDeterministicStrategy {

    public RemoveKnownValueInColumnStrategy(@NonNull SudokuFieldColId colId) {
        super(colId);
    }

    @Override
    protected boolean doApply(@NonNull SudokuPossibilitiesHolder holder) {
        prepareCollect();
        // 1. collect known values from column
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            byte value = holder.getValue(i, this.colId.getColId());
            if (value != SudokuHelper.UNDEFINED_VALUE)
                addKnownValue(value);
        }
        // 2. remove known values from unknown fields
        byte[] possibles = getKnownValues();
        if (possibles.length == SudokuHelper.ELEMENT_COUNT) {
            // all values in column are know - break strategy
            this.nothingToWork = true;
            return false;
        }
        var result = false;
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            byte value = holder.getValue(i, this.colId.getColId());
            if (value == SudokuHelper.UNDEFINED_VALUE) {
                for (byte possible : possibles) {
                    // order of "or" statement is important
                    result = holder.removePossible(i, this.colId.getColId(), possible) || result;
                }
            }
        }
        return result;
    }
}
