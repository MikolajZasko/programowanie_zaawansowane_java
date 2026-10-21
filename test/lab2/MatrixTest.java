package lab2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MatrixTest {

    // variables
    static double [][] source_rect = {
            {1,2,3,7},
            {4,5,6,8}
    };

    static double [][] source_square = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
    };

    static double[][][] tested_matrices() {
        return new double[][][] {
                source_square,
                source_rect
        };
    }

    @ParameterizedTest
    @MethodSource("tested_matrices")
    void testDimensionsConstructor(double[][] source_matrix) {

        System.out.printf("Testing matrix: %s\n", Arrays.deepToString(source_matrix));

        int cols = source_matrix[0].length;
        int rows = source_matrix.length;

        Matrix m = new Matrix(rows, cols);

        assertEquals(rows, m.rows);
        assertEquals(cols, m.cols);

        System.out.print("Passed 🟢 \n\n");
    }

    @ParameterizedTest
    @MethodSource("tested_matrices")
    void testArrayConstructor(double[][] source_matrix) {

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

        System.out.print("Passed 🟢 \n\n");
    }

    @ParameterizedTest
    @MethodSource("tested_matrices")
    void asArray(double[][] source_matrix) {
        // test quite literally equal to testArrayConstructor
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

        System.out.print("Passed 🟢 \n\n");
    }

    @ParameterizedTest
    @MethodSource("tested_matrices")
    void get(double[][] source_matrix) {
        System.out.printf("Testing matrix: %s\n", Arrays.deepToString(source_matrix));

        Matrix m = new Matrix(source_matrix);

        // check all the values
        for (int r = 0; r < m.rows; r++) {
            for (int c = 0; c < m.rows; c++) {

                assertEquals(source_matrix[r][c], m.get(r,c));

            }
        }

        System.out.print("Passed 🟢 \n\n");
    }

    @ParameterizedTest
    @MethodSource("tested_matrices")
    void testToString(double[][] source_matrix) {
        System.out.printf("This is a manual test count the string contents.\nTesting matrix: %s\n", Arrays.deepToString(source_matrix));

        Matrix m = new Matrix(source_matrix);

        System.out.printf("Result: %s\n", m.toString());

        System.out.print("Manual 🔵 \n\n");
    }

    @ParameterizedTest
    @MethodSource("tested_matrices")
    void reshape(double[][] source_matrix) {

        // settings for the test
        int newRows = 4;
        int newCols = 2;

        System.out.printf("Testing matrix: %s\n", Arrays.deepToString(source_matrix));

        Matrix m = new Matrix(source_matrix);

        if(m.rows * m.cols != newRows * newCols) {
            assertThrows(RuntimeException.class, () -> {
                m.reshape(newRows, newCols);
            });

            System.out.print("Exception caught! 🔵 \n");
        }
        else  {
            m.reshape(newRows, newCols);

            System.out.print("Reshaping successful! 🔵 \n");
        }

        System.out.print("Passed 🟢 \n\n");
    }

    @ParameterizedTest
    @MethodSource("tested_matrices")
    void sub(double[][] source_matrix) {
        System.out.printf("Testing matrix: %s\n", Arrays.deepToString(source_matrix));

        Matrix m = new Matrix(source_matrix);

        Matrix result = m.sub(m);

        System.out.printf("After sub (new matrix): %s\n",result.toString());

        assertEquals(0,result.frobenius());
        System.out.print("Passed 🟢 \n\n");
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