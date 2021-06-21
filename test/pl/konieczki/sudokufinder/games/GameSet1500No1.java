package pl.konieczki.sudokufinder.games;

public class GameSet1500No1 extends AbstractGameTest {

    @Override
    protected byte[] prepareFields() {
        return new byte[]{
                0, 0, 0, 0, 9, 0, 0, 2, 0,
                0, 0, 8, 0, 0, 0, 0, 0, 1,
                0, 5, 0, 1, 7, 0, 0, 0, 0,
                0, 0, 4, 2, 0, 0, 0, 0, 0,
                3, 0, 6, 0, 0, 0, 5, 0, 7,
                0, 0, 0, 0, 0, 9, 4, 0, 0,
                0, 0, 0, 0, 2, 1, 0, 4, 0,
                5, 0, 0, 0, 0, 0, 7, 0, 0,
                0, 9, 0, 0, 6, 0, 0, 0, 0
        };
    }

    @Override
    protected String prepareFirstRow() {
        return "417398625";
    }
}