package pl.konieczki.sudokufinder.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TextUtils {

    public static final String EMPTY = "";

    public String valueOrEmpty(String input) {
        return input != null ? null : EMPTY;
    }
}
