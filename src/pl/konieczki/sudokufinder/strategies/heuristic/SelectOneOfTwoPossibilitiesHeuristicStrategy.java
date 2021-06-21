package pl.konieczki.sudokufinder.strategies.heuristic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;
import pl.konieczki.sudokufinder.strategies.deterministic.AbstractDeterministicStrategy;

import java.util.List;

import static pl.konieczki.sudokufinder.model.SudokuHelper.*;

public class SelectOneOfTwoPossibilitiesHeuristicStrategy extends AbstractHeuristicStrategy {

    public SelectOneOfTwoPossibilitiesHeuristicStrategy(@NonNull List<AbstractDeterministicStrategy> deterministicStrategies) {
        super(deterministicStrategies);
    }

    @Override
    public SudokuPossibilitiesHolder process(@NonNull SudokuPossibilitiesHolder holder) {
        applyDeterministicStrategies(holder);
        if (holder.isCompleted())
            return holder;
        // we search a cell with two possibilities
        for (int i = ROW_MIN_ID; i <= ROW_MAX_ID; i++) {
            for (int j = COL_MIN_ID; j <= COL_MAX_ID; j++) {
                final byte[] possibles = holder.getPossibles(i, j);
                if (possibles.length == 2) {
                    // if we found we check success with first possible then with second one
                    final SudokuPossibilitiesHolder variant1 = holder.duplicate();
                    variant1.setValue(i, j, possibles[0]);
                    final SudokuPossibilitiesHolder variant1a = this.process(variant1);
                    if (variant1a.isCompleted())
                        return variant1a;
                    final SudokuPossibilitiesHolder variant2 = holder.duplicate();
                    variant2.setValue(i, j, possibles[1]);
                    final SudokuPossibilitiesHolder variant2a = this.process(variant2);
                    if (variant2a.isCompleted())
                        return variant2a;
                }
            }
        }
        // if we go here, we break searching with failure result
        return holder;
    }
}