package pl.konieczki.sudokufinder.games;

import org.junit.Test;

public class GameEasy1Test extends AbstractGameTest {

    @Test
    public void game() {
        findSudoku(
                new byte[]{
                        1, 0, 7, 0, 0, 3, 0, 8, 0,
                        0, 5, 6, 0, 9, 0, 0, 0, 7,
                        3, 8, 9, 0, 7, 0, 2, 0, 5,
                        0, 0, 2, 3, 6, 7, 5, 9, 0,
                        0, 7, 0, 9, 8, 2, 4, 0, 6,
                        9, 0, 8, 0, 0, 1, 7, 2, 3,
                        6, 0, 4, 7, 1, 0, 0, 0, 2,
                        7, 2, 5, 4, 3, 9, 8, 6, 1,
                        0, 0, 0, 6, 0, 0, 3, 7, 4
                },
                "147253689"
        );
    }
}
