package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldRowId;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import static pl.konieczki.sudokufinder.model.SudokuHelper.*;

public abstract class AbstractRowDeterministicStrategy extends AbstractDeterministicStrategy {

    protected final SudokuFieldRowId rowId;

    protected AbstractRowDeterministicStrategy(@NonNull SudokuFieldRowId rowId) {
        this.rowId = rowId;
    }

    protected abstract boolean doApply(@NonNull SudokuPossibilitiesHolder holder);

    protected void checkInAnyFieldHasPossibles(@NonNull SudokuPossibilitiesHolder holder) {
        if (this.nothingToWork)
            return;
        for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
            if (UNDEFINED_VALUE == holder.getValue(rowId.getRowId(), j))
                return;
        }
        // all fields has value, we can skip in the future any check
        this.nothingToWork = true;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " [" + rowId.name() + "]";
    }

    @Override
    public boolean apply(@NonNull SudokuPossibilitiesHolder holder) {
        checkInAnyFieldHasPossibles(holder);
        if (this.nothingToWork)
            return false;
        return doApply(holder);
    }
}