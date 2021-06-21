package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldSquareId;
import pl.konieczki.sudokufinder.model.SudokuHelper;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

/**
 * Strategia w danym kwadracie usuwa z możliwych wartości te, które są znane
 */
public class RemoveKnownValueInSquareStrategy extends AbstractSquareDeterministicStrategy {

    public RemoveKnownValueInSquareStrategy(@NonNull SudokuFieldSquareId squareId) {
        super(squareId);
    }

    @Override
    protected boolean doApply(@NonNull SudokuPossibilitiesHolder holder) {
        prepareCollect();
        collectKnownValues(holder);
        return removeKnownValuesFromUnknownFields(holder);
    }

    private boolean removeKnownValuesFromUnknownFields(SudokuPossibilitiesHolder holder) {
        byte[] possibles = getKnownValues();
        if (possibles.length == SudokuHelper.ELEMENT_COUNT) {
            // all values in square are know - break strategy
            this.nothingToWork = true;
            return false;
        }
        var result = false;
        for (int i = squareId.getMinRowId(); i <= squareId.getMaxRowId(); i++) {
            for (int j = squareId.getMinColId(); j <= squareId.getMaxColId(); j++) {
                byte value = holder.getValue(i, j);
                if (value == SudokuHelper.UNDEFINED_VALUE) {
                    for (byte possible : possibles) {
                        result = holder.removePossible(i, j, possible) || result;
                    }
                }
            }
        }
        return result;
    }

    private void collectKnownValues(SudokuPossibilitiesHolder holder) {
        for (int i = squareId.getMinRowId(); i <= squareId.getMaxRowId(); i++) {
            for (int j = squareId.getMinColId(); j <= squareId.getMaxColId(); j++) {
                byte value = holder.getValue(i, j);
                if (value != SudokuHelper.UNDEFINED_VALUE)
                    addKnownValue(value);
            }
        }
    }
}
