package pl.konieczki.sudokufinder.games;

import org.junit.Test;

public class GameMedium1Test extends AbstractGameTest {

    @Test
    public void game() {
        findSudoku(
                new byte[]{
                        0, 1, 0, 0, 0, 0, 0, 0, 9,
                        5, 0, 0, 8, 0, 9, 4, 0, 3,
                        4, 0, 0, 0, 0, 3, 0, 0, 5,
                        1, 0, 4, 0, 0, 0, 3, 0, 7,
                        3, 7, 0, 0, 4, 0, 5, 2, 8,
                        9, 0, 8, 0, 0, 0, 1, 0, 6,
                        6, 0, 0, 2, 0, 0, 0, 0, 4,
                        0, 0, 5, 6, 0, 4, 0, 0, 2,
                        8, 0, 0, 0, 0, 0, 0, 7, 0
                },
                "213456789"
        );
    }
}
