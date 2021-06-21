package pl.konieczki.sudokufinder.strategies.deterministic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuHelper;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import java.util.Arrays;

public abstract class AbstractDeterministicStrategy {

    protected boolean nothingToWork = false;
    private final byte[] elements = new byte[SudokuHelper.ELEMENT_COUNT];
    private int elementCount = 0;

    public abstract String getName();

    /**
     * Returns true if anything was changed
     *
     * @param holder possibilities holder
     * @return true if strategy makes any changes
     */
    public abstract boolean apply(@NonNull SudokuPossibilitiesHolder holder);

    public void resetNothingToWorkFlag() {
        this.nothingToWork = false;
    }

    protected void prepareCollect() {
        this.elementCount = 0;
    }

    protected void addKnownValue(byte value) {
        this.elements[elementCount++] = value;
    }

    protected byte[] getKnownValues() {
        return Arrays.copyOf(this.elements, this.elementCount);
    }
}
