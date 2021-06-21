package pl.konieczki.sudokufinder.utils;

import lombok.Getter;

import java.util.Arrays;

public enum SudokuGeneratorDifficulty {
    BEGINNER(3),
    EASY(4),
    MEDIUM(5),
    HARD(6),
    EXPERT(7);

    @Getter
    private final int count;

    SudokuGeneratorDifficulty(int count) {
        this.count = count;
    }

    public static SudokuGeneratorDifficulty findByCount(int count) {
        return Arrays.stream(SudokuGeneratorDifficulty.values())
                .filter(v -> v.count == count)
                .findFirst()
                .orElse(null);
    }
}