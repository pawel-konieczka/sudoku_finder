package pl.konieczki.sudokufinder.games;

import org.junit.Test;

public class GameMedium2Test extends AbstractGameTest {

    @Test
    public void game() {
        findSudoku(
                new byte[]{
                        1, 2, 0, 0, 6, 8, 4, 0, 0,
                        0, 0, 0, 0, 0, 9, 0, 0, 0,
                        3, 9, 4, 0, 2, 0, 0, 8, 0,
                        4, 6, 0, 2, 0, 5, 0, 0, 0,
                        0, 3, 0, 0, 9, 0, 0, 5, 0,
                        0, 0, 0, 4, 0, 3, 0, 7, 2,
                        0, 5, 0, 0, 7, 0, 2, 4, 9,
                        0, 0, 0, 9, 0, 0, 0, 0, 0,
                        0, 0, 6, 8, 5, 0, 0, 3, 1
                },
                "125368497"
        );
    }
}
