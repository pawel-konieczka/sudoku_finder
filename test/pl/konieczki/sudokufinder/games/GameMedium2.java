package pl.konieczki.sudokufinder.games;

public class GameMedium2 extends AbstractGameTest {

    @Override
    protected byte[] prepareFields() {
        return new byte[]{
                1, 2, 0, 0, 6, 8, 4, 0, 0,
                0, 0, 0, 0, 0, 9, 0, 0, 0,
                3, 9, 4, 0, 2, 0, 0, 8, 0,
                4, 6, 0, 2, 0, 5, 0, 0, 0,
                0, 3, 0, 0, 9, 0, 0, 5, 0,
                0, 0, 0, 4, 0, 3, 0, 7, 2,
                0, 5, 0, 0, 7, 0, 2, 4, 9,
                0, 0, 0, 9, 0, 0, 0, 0, 0,
                0, 0, 6, 8, 5, 0, 0, 3, 1
        };
    }

    @Override
    protected String prepareFirstRow() {
        return "125368497";
    }
}