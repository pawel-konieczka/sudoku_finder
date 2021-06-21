package pl.konieczki.sudokufinder.utils;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import pl.konieczki.sudokufinder.model.SudokuField;
import pl.konieczki.sudokufinder.model.SudokuPossibilitiesHolder;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
public class SudokuHistoryArchiver {

    private static final File HISTORY_FILE = new File("sudoku.history.txt");

    public void addToHistory(@NonNull SudokuPossibilitiesHolder holder, SudokuField field) {
        final List<String> lines = getLinesFromHistory();
        final String holderAndFieldToLine = holderAndFieldToLine(holder, field);
        if (!lines.contains(holderAndFieldToLine)) {
            lines.add(holderAndFieldToLine);
            setLinesToHistory(lines);
        }
    }

    public List<SudokuPossibilitiesHolder> getAvailableHolders() {
        return getLinesFromHistory().stream()
                .map(this::fromLineToHolderString)
                .filter(Objects::nonNull)
                .distinct()
                .map(this::stringToHolder)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public SudokuField getResultFromHistory(@NonNull SudokuPossibilitiesHolder holder) {
        final List<String> lines = getLinesFromHistory();
        final var line = getLineFromHistoryForGivenHolder(lines, holder);
        final var resultString = fromLineToResultString(line);
        return stringToField(resultString);
    }

    // -----------------------------------------------------------------------

    private String holderAndFieldToLine(@NonNull SudokuPossibilitiesHolder holder, SudokuField field) {
        final var holderToSave = holderToString(holder);
        final String fieldToSave;
        if (field != null) {
            fieldToSave = fieldToString(field);
        } else {
            fieldToSave = TextUtils.EMPTY;
        }
        return holderToSave + "$$" + fieldToSave;
    }

    private String holderToString(@NonNull SudokuPossibilitiesHolder holder) {
        return holder.toString().replaceAll("[\\n\\r]+", "||");
    }

    private SudokuPossibilitiesHolder stringToHolder(@NonNull String string) {
        final var input = string.replaceAll("\\|\\|", "\n");
        return SudokuPossibilitiesHolder.parse(input);
    }

    private String fieldToString(@NonNull SudokuField field) {
        return field.toString().replaceAll("[\\n\\r]", "||");
    }

    private SudokuField stringToField(@NonNull String string) {
        final var input = string.replaceAll("\\|\\|", "\n");
        return SudokuField.parse(input);
    }

    private List<String> getLinesFromHistory() {
        final List<String> lines = new ArrayList<>();
        try {
            if (HISTORY_FILE.exists())
                lines.addAll(Files.readAllLines(HISTORY_FILE.toPath()));
        } catch (Exception ex) {
            System.err.println("Exception when processing history: " + ex);
        }
        return lines;
    }

    private void setLinesToHistory(@NonNull List<String> lines) {
        final String output = lines.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining("\n"));
        try {
            Files.writeString(HISTORY_FILE.toPath(), output);
        } catch (Exception ex) {
            System.err.println("Exception when processing history: " + ex);
        }
    }

    private String fromLineToHolderString(@NonNull String line) {
        if (!line.isEmpty()) {
            final String[] strings = line.split("\\$\\$");
            if (strings.length > 0 && !strings[0].isEmpty())
                return strings[0];
        }
        return null;
    }

    private String fromLineToResultString(String line) {
        if (line != null && !line.isEmpty()) {
            final String[] strings = line.split("\\$\\$");
            if (strings.length > 1 && !strings[1].isEmpty())
                return strings[1];
        }
        return TextUtils.EMPTY;
    }

    private String getLineFromHistoryForGivenHolder(
            @NonNull List<String> lines, @NonNull SudokuPossibilitiesHolder holder
    ) {
        final var holderString = holderToString(holder);
        for (String line : lines) {
            if (line.startsWith(holderString))
                return line;
        }
        return null;
    }
}