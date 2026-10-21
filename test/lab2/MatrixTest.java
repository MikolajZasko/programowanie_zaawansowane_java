package lab2;

// java imports
import java.util.Arrays;

// test imports
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

// external lib for testing matrixes
import org.ejml.simple.SimpleMatrix;

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

    static int[] tested_vals() {
        return new int[] {1,5, 100};
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

            System.out.print("Exception caught: invalid reshape params 🔵 \n");
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

    @ParameterizedTest
    @MethodSource("tested_matrices")
    void mul(double[][] source_matrix) {
        System.out.printf("Testing matrix: %s\n", Arrays.deepToString(source_matrix));

        Matrix m = new Matrix(source_matrix);

        Matrix result1 = m.mul(-1);

        System.out.printf("After mul (new matrix): %s\n",result1.toString());

        Matrix result2 = result1.sub(m);

        System.out.printf("After sub (new matrix): %s\n",result2.toString());

        assertEquals(0,result2.frobenius());
        System.out.print("Passed 🟢 \n\n");
    }

    @ParameterizedTest
    @MethodSource("tested_matrices")
    void add(double[][] source_matrix) {
        // very similar to mul test
        System.out.printf("Testing matrix: %s\n", Arrays.deepToString(source_matrix));

        Matrix m = new Matrix(source_matrix);

        Matrix result1 = m.mul(-1);

        System.out.printf("After mul (new matrix): %s\n",result1.toString());

        Matrix result2 = result1.add(m);

        System.out.printf("After add (new matrix): %s\n",result2.toString());

        assertEquals(0,result2.frobenius());
        System.out.print("Passed 🟢 \n\n");
    }

    @ParameterizedTest
    @MethodSource("tested_matrices")
    void div(double[][] source_matrix) {
        System.out.printf("Testing matrix: %s\n", Arrays.deepToString(source_matrix));

        Matrix m = new Matrix(source_matrix);

        Matrix result1 = m.div(m);

        System.out.printf("After mul (new matrix): %s\n",result1.toString());
        System.out.printf("New matrix frobenius: %s\n",result1.frobenius());

        double result2 = Math.sqrt( result1.cols * result1.rows);

        assertEquals(result2, result1.frobenius());

        System.out.print("Passed 🟢 \n\n");
    }

    @ParameterizedTest
    @MethodSource("tested_matrices")
    void det(double[][] source_matrix) {
        System.out.printf("Testing matrix: %s\n", Arrays.deepToString(source_matrix));

        Matrix m = new Matrix(source_matrix);
        SimpleMatrix ejm_m = new SimpleMatrix(source_matrix);

        if (m.cols != m.rows) {
            assertThrows(RuntimeException.class, m::det);

            System.out.print("Exception caught: non square matrix 🔵 \n");
        }

        else  {
            double my_det = m.det();
            double ejm_det = ejm_m.determinant();

            assertEquals(my_det,ejm_det);
        }

        System.out.print("Passed 🟢 \n\n");
    }

    @ParameterizedTest
    @MethodSource("tested_vals")
    void eye(int source_val) {
        System.out.printf("Testing val: %d\n", source_val);

        Matrix m = Matrix.eye(source_val);

        System.out.printf("Eye matrix (new matrix): %s\n",m.toString());

        double frobenius_val = m.frobenius();

        System.out.printf("Frobenius val from eye matrix: %f\n",frobenius_val);

        assertEquals(Math.sqrt(source_val), frobenius_val);

        System.out.print("Passed 🟢 \n\n");
    }

    @Test
    void frobenius() {
    }

    @Test
    void shape() {
    }

    @Test
    void dot() {
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
}