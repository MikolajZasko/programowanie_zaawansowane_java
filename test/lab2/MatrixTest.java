package lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void testDimensionsConstructor() {

        int cols = 3;
        int rows = 3;

        Matrix m = new Matrix(rows,cols);

        assertEquals(rows,m.rows);
        assertEquals(cols,m.cols);
    }

    @Test
    void testArrayConstructor() {

        double [][] source = {
                {1,2,3},
                {4,5,6}
        };

        Matrix m = new Matrix(source);

        double [][] result = m.asArray();

        // check row amount
        assertEquals(source.length,result.length);

        for (int r = 0; r < m.rows; r++) {

            // check row size
            assertEquals(source[r].length,result[r].length);

            // check values
            for (int c = 0; c < m.cols; c++) {
                assertEquals(source[r][c],result[r][c]);
            }
        }
    }

    @Test
    void asArray() {
    }

    @Test
    void get() {
    }

    @Test
    void testToString() {
    }

    @Test
    void frobenius() {
    }

    @Test
    void shape() {
    }

    @Test
    void add() {
    }

    @Test
    void sub() {
    }

    @Test
    void mul() {
    }

    @Test
    void div() {
    }

    @Test
    void dot() {
    }

    @Test
    void det() {
    }

    @Test
    void inv() {
    }

    @Test
    void set() {
    }

    @Test
    void reshape() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void testSub() {
    }

    @Test
    void testMul() {
    }

    @Test
    void testDiv() {
    }

    @Test
    void solve() {
    }

    @Test
    void in_bounds() {
    }

    @Test
    void same_shape() {
    }

    @Test
    void swapRows() {
    }

    @Test
    void swapVals() {
    }

    @Test
    void divideRow() {
    }

    @Test
    void divideVals() {
    }

    @Test
    void subtractRow() {
    }

    @Test
    void subtractVals() {
    }

    @Test
    void random() {
    }

    @Test
    void eye() {
    }
}