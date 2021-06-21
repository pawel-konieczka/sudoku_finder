package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldColId;
import pl.konieczki.sudokufinder.model.SudokuHelper;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import static pl.konieczki.sudokufinder.model.SudokuHelper.ROW_MAX_ID;
import static pl.konieczki.sudokufinder.model.SudokuHelper.ROW_MIN_ID;

public abstract class AbstractColumnDeterministicStrategy extends AbstractDeterministicStrategy {

    protected final SudokuFieldColId colId;

    protected AbstractColumnDeterministicStrategy(@NonNull SudokuFieldColId colId) {
        this.colId = colId;
    }

    protected abstract boolean doApply(@NonNull SudokuPossibilitiesHolder holder);

    protected void checkInAnyFieldHasPossibles(@NonNull SudokuPossibilitiesHolder holder) {
        if (this.nothingToWork)
            return;
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            if (SudokuHelper.UNDEFINED_VALUE == holder.getValue(i, colId.getColId()))
                return;
        }
        // all fields has value, we can skip in the future any check
        this.nothingToWork = true;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " [" + colId.name() + "]";
    }

    @Override
    public boolean apply(@NonNull SudokuPossibilitiesHolder holder) {
        checkInAnyFieldHasPossibles(holder);
        if (this.nothingToWork)
            return false;
        return doApply(holder);
    }
}
