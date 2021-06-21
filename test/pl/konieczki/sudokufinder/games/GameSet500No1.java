package pl.konieczki.sudokufinder.games;

public class GameSet500No1 extends AbstractGameTest {

    @Override
    protected byte[] prepareFields() {
        return new byte[]{
                0, 4, 8, 3, 0, 0, 0, 0, 0,
                0, 0, 3, 5, 7, 0, 0, 0, 9,
                0, 0, 0, 0, 2, 4, 0, 8, 7,
                0, 0, 6, 0, 1, 0, 0, 3, 2,
                0, 5, 9, 0, 0, 0, 8, 4, 0,
                1, 2, 0, 0, 3, 0, 9, 0, 0,
                9, 8, 0, 6, 4, 0, 0, 0, 0,
                5, 0, 0, 0, 8, 3, 1, 0, 0,
                0, 0, 0, 0, 0, 1, 6, 2, 0
        };
    }

    @Override
    protected String prepareFirstRow() {
        return "748396215";
    }
}