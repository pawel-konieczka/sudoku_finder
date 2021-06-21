package pl.konieczki.sudokufinder.games;

import org.junit.Test;

public class GameSet1500No2Test extends AbstractGameTest {

    @Test
    public void game() {
        findSudoku(
                new byte[]{
                        0, 9, 0, 0, 0, 0, 0, 3, 0,
                        6, 0, 0, 0, 3, 0, 0, 0, 9,
                        0, 0, 2, 0, 1, 0, 8, 0, 0,
                        0, 0, 0, 9, 0, 4, 0, 0, 0,
                        0, 7, 9, 0, 2, 0, 1, 8, 0,
                        0, 0, 0, 1, 0, 8, 0, 0, 0,
                        0, 0, 7, 0, 8, 0, 5, 0, 0,
                        2, 0, 0, 0, 5, 0, 0, 0, 4,
                        0, 3, 0, 0, 0, 0, 0, 2, 0
                },
                "798542631"
        );
    }
}