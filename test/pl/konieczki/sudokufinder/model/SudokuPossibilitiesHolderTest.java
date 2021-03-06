package pl.konieczki.sudokufinder.model;

import org.junit.Assert;
import org.junit.Test;

public class SudokuPossibilitiesHolderTest {

    @Test
    public void parse() {
        final byte[] fields = new byte[]{
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
        final SudokuPossibilitiesHolder expected = SudokuPossibilitiesHolder.construct(new SudokuField(fields));
        final String input = "[1,1] = {1,2,3,4,5,6,7,8,9}||[1,2] = {1,2,3,4,5,6,7,8,9}||[1,3] = {1,2,3,4,5,6,7,8,9}||[1,4] = {1,2,3,4,5,6,7,8,9}||[1,5] = {9}||[1,6] = {1,2,3,4,5,6,7,8,9}||[1,7] = {1,2,3,4,5,6,7,8,9}||[1,8] = {2}||[1,9] = {1,2,3,4,5,6,7,8,9}||[2,1] = {1,2,3,4,5,6,7,8,9}||[2,2] = {1,2,3,4,5,6,7,8,9}||[2,3] = {8}||[2,4] = {1,2,3,4,5,6,7,8,9}||[2,5] = {1,2,3,4,5,6,7,8,9}||[2,6] = {1,2,3,4,5,6,7,8,9}||[2,7] = {1,2,3,4,5,6,7,8,9}||[2,8] = {1,2,3,4,5,6,7,8,9}||[2,9] = {1}||[3,1] = {1,2,3,4,5,6,7,8,9}||[3,2] = {5}||[3,3] = {1,2,3,4,5,6,7,8,9}||[3,4] = {1}||[3,5] = {7}||[3,6] = {1,2,3,4,5,6,7,8,9}||[3,7] = {1,2,3,4,5,6,7,8,9}||[3,8] = {1,2,3,4,5,6,7,8,9}||[3,9] = {1,2,3,4,5,6,7,8,9}||[4,1] = {1,2,3,4,5,6,7,8,9}||[4,2] = {1,2,3,4,5,6,7,8,9}||[4,3] = {4}||[4,4] = {2}||[4,5] = {1,2,3,4,5,6,7,8,9}||[4,6] = {1,2,3,4,5,6,7,8,9}||[4,7] = {1,2,3,4,5,6,7,8,9}||[4,8] = {1,2,3,4,5,6,7,8,9}||[4,9] = {1,2,3,4,5,6,7,8,9}||[5,1] = {3}||[5,2] = {1,2,3,4,5,6,7,8,9}||[5,3] = {6}||[5,4] = {1,2,3,4,5,6,7,8,9}||[5,5] = {1,2,3,4,5,6,7,8,9}||[5,6] = {1,2,3,4,5,6,7,8,9}||[5,7] = {5}||[5,8] = {1,2,3,4,5,6,7,8,9}||[5,9] = {7}||[6,1] = {1,2,3,4,5,6,7,8,9}||[6,2] = {1,2,3,4,5,6,7,8,9}||[6,3] = {1,2,3,4,5,6,7,8,9}||[6,4] = {1,2,3,4,5,6,7,8,9}||[6,5] = {1,2,3,4,5,6,7,8,9}||[6,6] = {9}||[6,7] = {4}||[6,8] = {1,2,3,4,5,6,7,8,9}||[6,9] = {1,2,3,4,5,6,7,8,9}||[7,1] = {1,2,3,4,5,6,7,8,9}||[7,2] = {1,2,3,4,5,6,7,8,9}||[7,3] = {1,2,3,4,5,6,7,8,9}||[7,4] = {1,2,3,4,5,6,7,8,9}||[7,5] = {2}||[7,6] = {1}||[7,7] = {1,2,3,4,5,6,7,8,9}||[7,8] = {4}||[7,9] = {1,2,3,4,5,6,7,8,9}||[8,1] = {5}||[8,2] = {1,2,3,4,5,6,7,8,9}||[8,3] = {1,2,3,4,5,6,7,8,9}||[8,4] = {1,2,3,4,5,6,7,8,9}||[8,5] = {1,2,3,4,5,6,7,8,9}||[8,6] = {1,2,3,4,5,6,7,8,9}||[8,7] = {7}||[8,8] = {1,2,3,4,5,6,7,8,9}||[8,9] = {1,2,3,4,5,6,7,8,9}||[9,1] = {1,2,3,4,5,6,7,8,9}||[9,2] = {9}||[9,3] = {1,2,3,4,5,6,7,8,9}||[9,4] = {1,2,3,4,5,6,7,8,9}||[9,5] = {6}||[9,6] = {1,2,3,4,5,6,7,8,9}||[9,7] = {1,2,3,4,5,6,7,8,9}||[9,8] = {1,2,3,4,5,6,7,8,9}||[9,9] = {1,2,3,4,5,6,7,8,9}||";

        final SudokuPossibilitiesHolder testedValue = SudokuPossibilitiesHolder.parse(input.replaceAll("\\|\\|", "\n"));

        Assert.assertEquals(expected.toString(), testedValue.toString());
    }
}
