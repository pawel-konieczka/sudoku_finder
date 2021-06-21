package pl.konieczki.sudokufinder.model;

import org.junit.Assert;
import org.junit.Test;

public class SudokuHelperTest {

    @Test(expected = IllegalArgumentException.class)
    public void when_checkRange_is_zero_then_throws_exception() {
        SudokuHelper.checkRowRange(0);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_checkRange_is_more_than_nine_then_throws_exception() {
        SudokuHelper.checkRowRange(10);
        Assert.fail();
    }

    @Test
    public void when_checkRange_is_one_then_no_exception_is_thrown() {
        SudokuHelper.checkRowRange(1);
        Assert.assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_checkValue_is_less_than_zero_then_throws_exception() {
        SudokuHelper.checkRowRange(-1);
        Assert.fail();
    }

    @Test
    public void when_checkValue_is_Undefine_then_no_exception_is_thrown() {
        SudokuHelper.checkValue(SudokuHelper.UNDEFINED_VALUE);
        Assert.assertTrue(true);
    }

    @Test
    public void when_checkValue_is_between_one_and_nine_then_no_exception_is_thrown() {
        SudokuHelper.checkValue(6);
        Assert.assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_checkValue_is_more_then_nine_then_throws_exception() {
        SudokuHelper.checkValue(10);
        Assert.fail();
    }

    @Test
    public void when_row_id_is_3_and_col_id_is_5_then_idx_is_22() {
        final int idx = SudokuHelper.calcIdx(3, 5);
        Assert.assertEquals(22, idx);
    }

    @Test
    public void when_row_id_is_5_and_col_id_is_3_then_idx_is_38() {
        final int idx = SudokuHelper.calcIdx(5, 3);
        Assert.assertEquals(38, idx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_row_id_is_invalid_then_throws_exception() {
        SudokuHelper.calcIdx(10, 3);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_col_id_is_invalid_then_throws_exception() {
        SudokuHelper.calcIdx(3, 10);
        Assert.fail();
    }
}
