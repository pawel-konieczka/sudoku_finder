package pl.konieczki.sudokufinder.utils;

import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SudokuLogger {

    @Setter
    private static boolean enabled = false;

    @SuppressWarnings("java:S106")
    public void log(@NonNull String message) {
        if (enabled)
            System.out.println(message);
    }
}
