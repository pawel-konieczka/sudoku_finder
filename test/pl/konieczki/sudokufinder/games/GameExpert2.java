package pl.konieczki.sudokufinder.games;

public class GameExpert2 extends AbstractGameTest {

    @Override
    protected byte[] prepareFields() {
        return new byte[]{
                6, 0, 0, 2, 0, 7, 0, 0, 9,
                0, 5, 0, 1, 0, 9, 0, 0, 0,
                0, 0, 8, 0, 0, 0, 0, 0, 5,
                1, 0, 0, 5, 0, 3, 0, 0, 0,
                5, 0, 0, 0, 6, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 3, 0, 4,
                2, 0, 0, 6, 0, 0, 9, 0, 0,
                0, 0, 0, 8, 0, 0, 0, 4, 0,
                4, 0, 0, 9, 7, 1, 0, 6, 2
        };
    }

    @Override
    protected String prepareFirstRow() {
        return "641257839";
    }
}