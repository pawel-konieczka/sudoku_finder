package pl.konieczki.sudokufinder.model;

import org.junit.Assert;
import org.junit.Test;

public class SudokuFieldTest {

    @Test
    public void when_new_field_then_whole_is_zeros() {
        final SudokuField sf = new SudokuField();
        final String toString = "0 0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0 0\n";
        Assert.assertEquals(toString, sf.toString());
    }

    @Test
    public void when_buffer_is_given_then_object_is_created() {
        final byte[] buffer = new byte[]{
                1, 4, 7, 2, 5, 3, 6, 8, 9,
                2, 5, 6, 8, 9, 4, 1, 3, 7,
                3, 8, 9, 1, 7, 6, 2, 4, 5,
                4, 1, 2, 3, 6, 7, 5, 9, 8,
                5, 7, 3, 9, 8, 2, 4, 1, 6,
                9, 6, 8, 5, 4, 1, 7, 2, 3,
                6, 3, 4, 7, 1, 8, 9, 5, 2,
                7, 2, 5, 4, 3, 9, 8, 6, 1,
                8, 9, 1, 6, 2, 5, 3, 7, 4
        };
        final SudokuField sf = new SudokuField(buffer);
        final String toString = "1 4 7 2 5 3 6 8 9\n" +
                "2 5 6 8 9 4 1 3 7\n" +
                "3 8 9 1 7 6 2 4 5\n" +
                "4 1 2 3 6 7 5 9 8\n" +
                "5 7 3 9 8 2 4 1 6\n" +
                "9 6 8 5 4 1 7 2 3\n" +
                "6 3 4 7 1 8 9 5 2\n" +
                "7 2 5 4 3 9 8 6 1\n" +
                "8 9 1 6 2 5 3 7 4\n";
        Assert.assertEquals(toString, sf.toString());
    }

    @Test
    public void when_row_id_5_and_col_id_7_and_value_is_4_then_4_is_set() {
        final SudokuField sf = new SudokuField();
        sf.set(5, 7, (byte) 4);
        final String toString = "0 0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n0 0 0 0 0 0 4 0 0\n0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0 0\n";
        Assert.assertEquals(toString, sf.toString());
    }

    @Test
    public void parse() {
        final byte[] fields = new byte[]{
                4, 1, 7, 3, 9, 8, 6, 2, 5,
                2, 3, 8, 6, 4, 5, 9, 7, 1,
                6, 5, 9, 1, 7, 2, 3, 8, 4,
                9, 7, 4, 2, 5, 6, 1, 3, 8,
                3, 2, 6, 8, 1, 4, 5, 9, 7,
                1, 8, 5, 7, 3, 9, 4, 6, 2,
                7, 6, 3, 5, 2, 1, 8, 4, 9,
                5, 4, 2, 9, 8, 3, 7, 1, 6,
                8, 9, 1, 4, 6, 7, 2, 5, 3
        };
        final SudokuField expectedValue = new SudokuField(fields);
        final String input =
                "4 1 7 3 9 8 6 2 5\n" +
                        "2 3 8 6 4 5 9 7 1\n" +
                        "6 5 9 1 7 2 3 8 4\n" +
                        "9 7 4 2 5 6 1 3 8\n" +
                        "3 2 6 8 1 4 5 9 7\n" +
                        "1 8 5 7 3 9 4 6 2\n" +
                        "7 6 3 5 2 1 8 4 9\n" +
                        "5 4 2 9 8 3 7 1 6\n" +
                        "8 9 1 4 6 7 2 5 3\n";

        final SudokuField testedValue = SudokuField.parse(input);

        Assert.assertEquals(expectedValue.toString(), testedValue.toString());
    }
}