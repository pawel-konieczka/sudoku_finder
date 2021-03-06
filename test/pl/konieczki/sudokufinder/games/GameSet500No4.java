package pl.konieczki.sudokufinder.games;

public class GameSet500No4 extends AbstractGameTest {

    @Override
    protected byte[] prepareFields() {
        return new byte[]{
                0, 0, 3, 0, 0, 0, 5, 0, 0,
                0, 0, 0, 2, 0, 0, 0, 0, 0,
                5, 0, 7, 0, 8, 0, 9, 0, 3,
                0, 0, 0, 9, 0, 4, 0, 2, 0,
                0, 0, 6, 0, 0, 0, 4, 0, 0,
                0, 3, 0, 5, 0, 6, 0, 0, 0,
                8, 0, 1, 0, 4, 0, 7, 0, 5,
                0, 0, 0, 0, 0, 9, 0, 0, 0,
                0, 0, 4, 0, 0, 0, 1, 0, 0
        };
    }

    @Override
    protected String prepareFirstRow() {
        return "683497512";
    }
}