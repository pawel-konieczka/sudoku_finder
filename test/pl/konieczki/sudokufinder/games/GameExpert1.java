package pl.konieczki.sudokufinder.games;

public class GameExpert1 extends AbstractGameTest {

    @Override
    protected byte[] prepareFields() {
        return new byte[]{
                0, 7, 0, 0, 0, 8, 5, 0, 9,
                3, 4, 0, 0, 0, 0, 0, 2, 8,
                8, 0, 0, 0, 6, 0, 0, 0, 0,
                0, 1, 3, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 9, 2, 6, 0, 0, 3,
                0, 0, 0, 0, 0, 0, 2, 0, 0,
                0, 0, 0, 0, 3, 0, 0, 7, 0,
                0, 3, 0, 0, 5, 0, 8, 0, 0,
                7, 0, 5, 8, 0, 0, 0, 4, 0
        };
    }

    @Override
    protected String prepareFirstRow() {
        return "671248539";
    }
}