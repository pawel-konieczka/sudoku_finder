package pl.konieczki.sudokufinder.games;

public class GameSet500No2 extends AbstractGameTest {

    @Override
    protected byte[] prepareFields() {
        return new byte[]{
                0, 0, 5, 2, 1, 0, 6, 3, 0,
                0, 0, 0, 9, 0, 6, 0, 0, 4,
                8, 0, 3, 0, 7, 0, 0, 0, 2,
                7, 4, 0, 0, 5, 0, 0, 9, 0,
                5, 0, 6, 0, 0, 0, 4, 0, 1,
                0, 8, 0, 0, 6, 0, 0, 7, 3,
                2, 0, 0, 0, 8, 0, 7, 0, 9,
                6, 0, 0, 1, 0, 9, 0, 0, 0,
                0, 5, 9, 0, 2, 7, 1, 0, 0
        };
    }

    @Override
    protected String prepareFirstRow() {
        return "495218637";
    }
}