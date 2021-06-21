package pl.konieczki.sudokufinder.games;

import org.junit.Test;

public class GameSet500No3Test extends AbstractGameTest {

    @Test
    public void game() {
        findSudoku(
                new byte[]{
                        0, 0, 0, 7, 0, 0, 3, 0, 0,
                        0, 0, 6, 8, 0, 0, 2, 0, 0,
                        1, 7, 0, 3, 0, 0, 0, 5, 0,
                        0, 0, 0, 0, 3, 0, 4, 8, 1,
                        0, 0, 0, 0, 0, 0, 0, 0, 0,
                        6, 2, 1, 0, 4, 0, 0, 0, 0,
                        0, 3, 0, 0, 0, 6, 0, 4, 7,
                        0, 0, 2, 0, 0, 9, 6, 0, 0,
                        0, 0, 8, 0, 0, 3, 0, 0, 0
                },
                "284765319"
        );
    }
}
