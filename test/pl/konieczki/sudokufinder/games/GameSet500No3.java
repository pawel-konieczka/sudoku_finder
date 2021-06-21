package pl.konieczki.sudokufinder.games;

public class GameSet500No3 extends AbstractGameTest {

    @Override
    protected byte[] prepareFields() {
        return new byte[]{
                0, 0, 0, 7, 0, 0, 3, 0, 0,
                0, 0, 6, 8, 0, 0, 2, 0, 0,
                1, 7, 0, 3, 0, 0, 0, 5, 0,
                0, 0, 0, 0, 3, 0, 4, 8, 1,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                6, 2, 1, 0, 4, 0, 0, 0, 0,
                0, 3, 0, 0, 0, 6, 0, 4, 7,
                0, 0, 2, 0, 0, 9, 6, 0, 0,
                0, 0, 8, 0, 0, 3, 0, 0, 0
        };
    }

    @Override
    protected String prepareFirstRow() {
        return "284765319";
    }
}