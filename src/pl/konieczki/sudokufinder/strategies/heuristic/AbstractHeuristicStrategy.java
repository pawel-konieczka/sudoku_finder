package pl.konieczki.sudokufinder.strategies.heuristic;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;
import pl.konieczki.sudokufinder.strategies.deterministic.AbstractDeterministicStrategy;
import pl.konieczki.sudokufinder.utils.SudokuLogger;

import java.util.List;

public abstract class AbstractHeuristicStrategy {

    protected final List<AbstractDeterministicStrategy> deterministicStrategies;

    protected AbstractHeuristicStrategy(
            @NonNull List<AbstractDeterministicStrategy> deterministicStrategies
    ) {
        this.deterministicStrategies = deterministicStrategies;
    }

    protected void applyDeterministicStrategies(@NonNull SudokuPossibilitiesHolder holder) {
        SudokuLogger.log(this.getClass().getSimpleName() + ": running deterministic strategies");
        this.deterministicStrategies.forEach(AbstractDeterministicStrategy::resetNothingToWorkFlag);
        var step = 0;
        try {
            boolean shouldRunNextRound;
            do {
                shouldRunNextRound = false;
                step++;
                for (AbstractDeterministicStrategy strategy : this.deterministicStrategies) {
                    SudokuLogger.log(
                            this.getClass().getSimpleName()
                                    + ": running strategy: " + strategy.getName()
                                    + " (step: " + step + ")"
                    );
                    // order in "or" statement is important
                    shouldRunNextRound = strategy.apply(holder) || shouldRunNextRound;
                }
            } while (shouldRunNextRound);
        } catch (IllegalStateException isEx) {
            // trying to make invalid operation
            holder.resetAll();
        }
    }

    public abstract SudokuPossibilitiesHolder process(@NonNull SudokuPossibilitiesHolder holder);
}