package lab2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    // variables
    double [][] source_rect = {
            {1,2,3,7},
            {4,5,6,8}
    };

    double [][] source_square = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
    };

    double [][][] tested_matrixes = {
            source_rect,
            source_square
    };

    @Test
    void testDimensionsConstructor() {

        for (double[][] source_matrix : tested_matrixes) {

            System.out.printf("Testing matrix: %s\n", Arrays.deepToString(source_matrix));

            int cols = source_matrix[0].length;
            int rows = source_matrix.length;

            Matrix m = new Matrix(rows, cols);

            assertEquals(rows, m.rows);
            assertEquals(cols, m.cols);
        }
    }

    @Test
    void testArrayConstructor() {

        for (double[][] source_matrix : tested_matrixes) {

            System.out.printf("Testing matrix: %s\n", Arrays.deepToString(source_matrix));

            Matrix m = new Matrix(source_matrix);

            double [][] result = m.asArray();

            // check row amount
            assertEquals(source_matrix.length,result.length);

            for (int r = 0; r < m.rows; r++) {

                // check row size
                assertEquals(source_matrix[r].length,result[r].length);

                // check values
                for (int c = 0; c < m.cols; c++) {
                    assertEquals(source_matrix[r][c],result[r][c]);
                }
            }
        }
    }

    @Test
    void asArray() {
        // test quite literally equal to testArrayConstructor
        for (double[][] source_matrix : tested_matrixes) {

            System.out.printf("Testing matrix: %s\n", Arrays.deepToString(source_matrix));

            Matrix m = new Matrix(source_matrix);

            double[][] result = m.asArray();

            // check row amount
            assertEquals(source_matrix.length, result.length);

            for (int r = 0; r < m.rows; r++) {

                // check row size
                assertEquals(source_matrix[r].length, result[r].length);

                // check values
                for (int c = 0; c < m.cols; c++) {
                    assertEquals(source_matrix[r][c], result[r][c]);
                }
            }
        }
    }

    @Test
    void get() {
        for (double[][] source_matrix : tested_matrixes) {

            System.out.printf("Testing matrix: %s\n", Arrays.deepToString(source_matrix));

            Matrix m = new Matrix(source_matrix);

            // check all the values
            for (int r = 0; r < m.rows; r++) {
                for (int c = 0; c < m.rows; c++) {

                    assertEquals(source_matrix[r][c], m.get(r,c));

                }
            }
        }
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