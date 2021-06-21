package pl.konieczki.sudokufinder.games;

import org.junit.Test;

public class GameSet1500No1Test extends AbstractGameTest {

    @Test
    public void game() {
        findSudoku(
                new byte[]{
                        0, 0, 0, 0, 9, 0, 0, 2, 0,
                        0, 0, 8, 0, 0, 0, 0, 0, 1,
                        0, 5, 0, 1, 7, 0, 0, 0, 0,
                        0, 0, 4, 2, 0, 0, 0, 0, 0,
                        3, 0, 6, 0, 0, 0, 5, 0, 7,
                        0, 0, 0, 0, 0, 9, 4, 0, 0,
                        0, 0, 0, 0, 2, 1, 0, 4, 0,
                        5, 0, 0, 0, 0, 0, 7, 0, 0,
                        0, 9, 0, 0, 6, 0, 0, 0, 0
                },
                "417398625"
        );
    }
}
