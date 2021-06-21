package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldSquareId;
import pl.konieczki.sudokufinder.model.SudokuHelper;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

public abstract class AbstractSquareDeterministicStrategy extends AbstractDeterministicStrategy {

    protected final SudokuFieldSquareId squareId;

    protected AbstractSquareDeterministicStrategy(@NonNull SudokuFieldSquareId squareId) {
        this.squareId = squareId;
    }

    protected abstract boolean doApply(@NonNull SudokuPossibilitiesHolder holder);

    protected void checkInAnyFieldHasPossibles(@NonNull SudokuPossibilitiesHolder holder) {
        if (this.nothingToWork)
            return;
        for (int i = squareId.getMinRowId(); i <= squareId.getMaxRowId(); i++) {
            for (int j = squareId.getMinColId(); j <= squareId.getMaxColId(); j++) {
                if (SudokuHelper.UNDEFINED_VALUE == holder.getValue(i, j))
                    return;
            }
        }
        // all fields has value, we can skip in the future any check
        this.nothingToWork = true;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " [" + squareId.name() + "]";
    }

    @Override
    public boolean apply(@NonNull SudokuPossibilitiesHolder holder) {
        checkInAnyFieldHasPossibles(holder);
        if (this.nothingToWork)
            return false;
        return doApply(holder);
    }
}
