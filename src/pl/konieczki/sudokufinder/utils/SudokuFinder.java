package pl.konieczki.sudokufinder.utils;

import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuFieldColId;
import pl.konieczki.sudokufinder.model.SudokuFieldRowId;
import pl.konieczki.sudokufinder.model.SudokuFieldSquareId;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;
import pl.konieczki.sudokufinder.strategies.deterministic.*;
import pl.konieczki.sudokufinder.strategies.heuristic.AbstractHeuristicStrategy;
import pl.konieczki.sudokufinder.strategies.heuristic.SelectOneOfTwoPossibilitiesHeuristicStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa szuka dla podanego układu częściowego (holdera) rozwiązania sudoku.
 */
public class SudokuFinder {

    private final SudokuHistoryArchiver archiver;
    private final AbstractHeuristicStrategy heuristicStrategy;

    public SudokuFinder(SudokuHistoryArchiver archiver) {
        this.archiver = archiver;
        final List<AbstractDeterministicStrategy> deterministicStrategies = new ArrayList<>();
        createBasicStrategies(deterministicStrategies);
        createAdvancedStrategies(deterministicStrategies);
        this.heuristicStrategy = new SelectOneOfTwoPossibilitiesHeuristicStrategy(deterministicStrategies);
    }

    public SudokuPossibilitiesHolder findSudoku(@NonNull SudokuPossibilitiesHolder holder) {
        final SudokuPossibilitiesHolder archHolder = holder.duplicate();
        final SudokuPossibilitiesHolder result = this.heuristicStrategy.process(holder);
        if (this.archiver != null) {
            this.archiver.addToHistory(archHolder, result.isCompleted() ? result.deconstruct() : null);
        }
        return result;
    }

    private void createAdvancedStrategies(@NonNull List<AbstractDeterministicStrategy> deterministicStrategies) {
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_I, SudokuFieldRowId.ROW_I));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_I, SudokuFieldRowId.ROW_II));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_I, SudokuFieldRowId.ROW_III));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_II, SudokuFieldRowId.ROW_I));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_II, SudokuFieldRowId.ROW_II));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_II, SudokuFieldRowId.ROW_III));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_III, SudokuFieldRowId.ROW_I));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_III, SudokuFieldRowId.ROW_II));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_III, SudokuFieldRowId.ROW_III));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_IV, SudokuFieldRowId.ROW_IV));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_IV, SudokuFieldRowId.ROW_V));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_IV, SudokuFieldRowId.ROW_VI));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_V, SudokuFieldRowId.ROW_IV));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_V, SudokuFieldRowId.ROW_V));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_V, SudokuFieldRowId.ROW_VI));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_VI, SudokuFieldRowId.ROW_IV));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_VI, SudokuFieldRowId.ROW_V));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_VI, SudokuFieldRowId.ROW_VI));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_VII, SudokuFieldRowId.ROW_VII));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_VII, SudokuFieldRowId.ROW_VIII));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_VII, SudokuFieldRowId.ROW_IX));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_VIII, SudokuFieldRowId.ROW_VII));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_VIII, SudokuFieldRowId.ROW_VIII));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_VIII, SudokuFieldRowId.ROW_IX));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_IX, SudokuFieldRowId.ROW_VII));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_IX, SudokuFieldRowId.ROW_VIII));
        deterministicStrategies.add(new RemoveFromRowWhenValueExistsOnlyInThisRowStrategy(SudokuFieldSquareId.SQUARE_IX, SudokuFieldRowId.ROW_IX));

        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_I, SudokuFieldColId.COL_I));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_I, SudokuFieldColId.COL_II));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_I, SudokuFieldColId.COL_III));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_II, SudokuFieldColId.COL_IV));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_II, SudokuFieldColId.COL_V));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_II, SudokuFieldColId.COL_VI));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_III, SudokuFieldColId.COL_VII));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_III, SudokuFieldColId.COL_VIII));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_III, SudokuFieldColId.COL_IX));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_IV, SudokuFieldColId.COL_I));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_IV, SudokuFieldColId.COL_II));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_IV, SudokuFieldColId.COL_III));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_V, SudokuFieldColId.COL_IV));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_V, SudokuFieldColId.COL_V));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_V, SudokuFieldColId.COL_VI));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_VI, SudokuFieldColId.COL_VII));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_VI, SudokuFieldColId.COL_VIII));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_VI, SudokuFieldColId.COL_IX));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_VII, SudokuFieldColId.COL_I));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_VII, SudokuFieldColId.COL_II));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_VII, SudokuFieldColId.COL_III));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_VIII, SudokuFieldColId.COL_IV));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_VIII, SudokuFieldColId.COL_V));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_VIII, SudokuFieldColId.COL_VI));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_IX, SudokuFieldColId.COL_VII));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_IX, SudokuFieldColId.COL_VIII));
        deterministicStrategies.add(new RemoveFromColumnWhenValueExistsOnlyInThisColumnStrategy(SudokuFieldSquareId.SQUARE_IX, SudokuFieldColId.COL_IX));
    }

    private void createBasicStrategies(@NonNull List<AbstractDeterministicStrategy> deterministicStrategies) {
        for (SudokuFieldSquareId squareId : SudokuFieldSquareId.values()) {
            deterministicStrategies.add(new RemoveKnownValueInSquareStrategy(squareId));
            deterministicStrategies.add(new FindOnlyOneOccurrenceOfValueInSquareStrategy(squareId));
            deterministicStrategies.add(new RemovePairFromOtherInSquareStrategy(squareId));
        }
        for (SudokuFieldRowId rowId : SudokuFieldRowId.values()) {
            deterministicStrategies.add(new RemoveKnownValueInRowStrategy(rowId));
            deterministicStrategies.add(new FindOnlyOneOccurrenceOfValueInRowStrategy(rowId));
            deterministicStrategies.add(new RemovePairFromOtherInRowStrategy(rowId));
        }
        for (SudokuFieldColId colId : SudokuFieldColId.values()) {
            deterministicStrategies.add(new RemoveKnownValueInColumnStrategy(colId));
            deterministicStrategies.add(new FindOnlyOneOccurrenceOfValueInColumnStrategy(colId));
            deterministicStrategies.add(new RemovePairFromOtherInColumnStrategy(colId));
        }
    }

}
