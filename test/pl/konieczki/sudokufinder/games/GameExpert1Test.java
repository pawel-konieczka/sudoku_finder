package pl.konieczki.sudokufinder.games;

import org.junit.Test;

public class GameExpert1Test extends AbstractGameTest {

    @Test
    public void game() {
        findSudoku(
                new byte[]{
                        0, 7, 0, 0, 0, 8, 5, 0, 9,
                        3, 4, 0, 0, 0, 0, 0, 2, 8,
                        8, 0, 0, 0, 6, 0, 0, 0, 0,
                        0, 1, 3, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 9, 2, 6, 0, 0, 3,
                        0, 0, 0, 0, 0, 0, 2, 0, 0,
                        0, 0, 0, 0, 3, 0, 0, 7, 0,
                        0, 3, 0, 0, 5, 0, 8, 0, 0,
                        7, 0, 5, 8, 0, 0, 0, 4, 0
                },
                "671248539"
        );
    }
}
