package lab2;

// pierwiastek sumy kwadratów - Frobenius

import java.math.MathContext;
import java.util.Random;

public class Matrix {
    // variables
    double[]data;
    int rows;
    int cols;

    // constructors
    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }
    
    Matrix(double[][] d){
        // find the longest row
        int max_row = -1;
        int max_row_index = -1;

        for (int i = 0; i < d.length; i++) {
            int current_length = d[i].length;

            if (max_row < current_length) {
                max_row = current_length;
                max_row_index = i;
            }
        }

        if (max_row == -1 || max_row_index == -1) {
            throw new RuntimeException(String.format("Error: when creating a matrix probably empty list was provided"));
        }

        this.cols = max_row;
        this.rows = d.length;

        int maxv = Math.max(this.cols, this.rows);

        this.data = new double[maxv * maxv];

        // populate this.data
        for (int i = 0; i < this.rows; i++) {
            int current_length = d[i].length;

            for (int j = 0; j < this.cols; j++) {

                if (current_length > j){
                    this.data[(this.cols * i) + j] = d[i][j];
                }
                else {
                    this.data[(this.cols * i) + j] = 0;
                }
            }
        }
    }

    

    // getters
    double[][] asArray() {

        double [][] tmp = new double[this.rows][this.cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tmp[i][j] = get(i, j);
            }
        }

        return tmp;
    }

    double get(int r,int c) {
        if (this.in_bounds(r, c)){
            return data[(this.cols * r) + c];
        }
        else {
            throw new RuntimeException(String.format("Error: index out of bounds"));
        }
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[\n");
        for(int i = 0;i < this.rows;i++){
            buf.append("\t[ ");

            for(int j = 0;j < this.cols;j++){
                buf.append(data[(this.cols * i) + j]);

                if (! (j == this.cols - 1)) {
                    buf.append(", ");
                }
                else {
                    buf.append(" ");

                }
            }

            buf.append("]\n");
        }
        buf.append("]\n");
        return buf.toString();
    }

    public double frobenius() {

        double sum = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                double tmp = get(r,c);
                sum += tmp * tmp;
            }
        }

        return Math.sqrt(sum);
    }

    public int[] shape() {
        return new int[] { rows, cols };
    }

    // same shape should throw error so the return null is just for the ide to stop crying
    // in all funct: add, sub, mul, div
    public Matrix add(Matrix m) {
        if (same_shape(this, m)) {
            Matrix new_m = new Matrix(rows,cols);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                new_m.set(r,c, get(r, c) + m.get(r, c) );
            }
        }

        return new_m;
        }

        return null;
    }

    Matrix sub(Matrix m) {
        if (same_shape(this, m)) {
            Matrix new_m = new Matrix(rows,cols);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                new_m.set(r,c, get(r, c) - m.get(r, c) );
            }
        }

        return new_m;
        }

        return null;
    }

    Matrix mul(Matrix m){
        // i assume the mul / div are like sub / add - wired probably wrong
        if (same_shape(this, m)) {
            Matrix new_m = new Matrix(rows,cols);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                new_m.set(r,c, get(r, c) * m.get(r, c) );
            }
        }

        return new_m;
        }

        return null;
    }

    Matrix div(Matrix m){
        // i assume the mul / div are like sub / add - wired probably wrong
        if (same_shape(this, m)) {
            Matrix new_m = new Matrix(rows,cols);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                new_m.set(r,c, get(r, c) / m.get(r, c) );
            }
        }

        return new_m;
        }

        return null;
    }

    Matrix dot(Matrix m) {
        // check if possible
        if (cols != m.rows) {
            throw new RuntimeException(String.format("Error: can't 'dot' 2 matrixes like this \n m1: %s\nm2: %s", 
            toString(), 
            m.toString()));
        }

        Matrix new_m = new Matrix(rows,m.cols);
        
        for (int r = 0; r < new_m.rows; r++) {
            for (int c = 0; c < new_m.cols; c++) {
                // calculate the element

                double sum = 0;

                for (int n = 0; n < cols; n++) {
                    sum += get(r, n) * m.get(n, c);
                }

                new_m.set(r, c, sum);
            }
        }

        return new_m;
    }

    double det() {
        // using Gaussian_elimination
        // https://en.wikipedia.org/wiki/Gaussian_elimination
        //
        // dumb approach - 0 has to appear on a particular spot

        if ( cols != rows ){
            throw new RuntimeException(String.format("Error: det can be only calculated for square matrix, this is not a square matrix: \n%s",
                    toString()
            ));
        }

        // copy matrix
        Matrix m = new Matrix(this.asArray());

        // perform the Gaussian_elimination operation
        for ( int n = 0; n < m.cols; n++) {
            // n - current position of 0
            for ( int r = n + 1; r < m.rows; r++) {
                // r - row where 0 is expected to appear
                //
                // find what fraction needs to be before the prev column
                double frac = m.get(r,n) / m.get(n,n);

                // perform the operation for the whole row
                for ( int c = 0; c < m.cols; c++) {
                    double newVal = m.get(r, c) - (m.get(n, c) * frac);
                    m.set(r, c, newVal);
                }
            }
        }

        // calculate the det
        double det = 1;

        for ( int n = 0; n < m.cols; n++) {
            double current_element = m.get(n,n);

            if ( current_element == 0 ) {
                det = 0;
                break;
            }

            det *= current_element;
        }

        return det;
    }

    // inverts the matrix
    Matrix inv() {
        // check if matrix is n x n and if det != 0
        if ( det() == 0 ){
            throw new RuntimeException(String.format("Error: can't inv a matrix that has det == 0 \n%s",
                    toString()
            ));
        }

        Matrix e = Matrix.eye(cols);

        // rows == cols
        for (int n = 0; n < rows; n++ ) {
            double current_element = get(n,n);

            // if 0 find a row to swap with (value not 0)

            if (current_element == 0) {

                boolean swapped = false;
                for (int i = n + 1; i < rows; i++) {
                    if (get(i, n) != 0) {

                        swapRows(n, i);
                        e.swapRows(n, i);

                        swapped = true;
                        break;
                    }
                }

                // if element at the diagonal is still 0 here, the matrix is singular or cannot be inverted
                if (!swapped) {
                    throw new RuntimeException("Error: Matrix is singular or cannot be inverted.");
                }

                // update current_element - it probably changed due to row swapping
                current_element = get(n, n);
            }

            // Normalize the pivot row (make the diagonal element 1)
            divideRow(n, current_element);
            e.divideRow(n, current_element);

            // now the element at the main diagonal is 1,
            // using this 1 we 0 other elements in this column
            //
            // eliminate other rows in this column
            for (int r = 0; r < rows; r++) {
                if (r == n) continue;

                double factor = get(r, n);
                subtractRow(r, n, factor);    // row_r = row_r - (factor * row_n)
                e.subtractRow(r, n, factor);  // same for identity matrix
            }
        }

        return e;
    }

    // setters
    void set (int r,int c, double value) {
        if (! this.in_bounds(r, c)) {
            throw new RuntimeException(String.format("Error: index out of bounds"));
        }

        data[(this.cols * r) + c] = value;
    }

    void reshape(int newRows,int newCols){
        if(rows * cols != newRows * newCols) {
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        }
        else {
            this.cols = newCols;
            this.rows = newRows;
        }
    }

    // dodaje wartość w do każdego elementu
    Matrix add(double w){
        // Matrix new_m = new Matrix(rows,cols);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                set(r,c, get(r, c) + w);
            }
        }

        // do we create a new matrix ???
        return this;
    } 

    // odejmuje wartośc w od kazdego elementu
    Matrix sub(double w){
        // Matrix new_m = new Matrix(rows,cols);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                set(r,c, get(r, c) - w);
            }
        }

        // do we create a new matrix ???
        return this;
    } 

    // mnoży każdy element przez skalar w
    Matrix mul(double w){
        // Matrix new_m = new Matrix(rows,cols);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                set(r,c, get(r, c) * w);
            }
        }

        // do we create a new matrix ???
        return this;
    } 

    // dzieli każdy element przez skalar w
    Matrix div(double w){
        // Matrix new_m = new Matrix(rows,cols);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                set(r,c, get(r, c) / w);
            }
        }

        // do we create a new matrix ???
        return this;
    }

    // solves Ax = b
    // modifies the base matrix (this)
    double[] solve(double[] x) {
        if (x.length != rows) {
            throw new RuntimeException(String.format("Error: vector b has different amount of elements than matrix rows"));
        }

        if (det() == 0) {
            throw new RuntimeException(String.format("Error: don't know how to solve a matrix when det == 0"));
        }

        double[] b = x.clone();

        // rows == cols
        for (int n = 0; n < rows; n++ ) {
            double current_element = get(n, n);

            // if 0 find a row to swap with (value not 0)
            if (current_element == 0) {

                boolean swapped = false;
                for (int i = n + 1; i < rows; i++) {
                    if (get(i, n) != 0) {

                        // base matrix
                        swapRows(n, i);

                        // b vecor
                        swapVals(n,i,b);

                        swapped = true;
                        break;
                    }
                }

                // if element at the diagonal is still 0 here, the matrix is singular or cannot be ?solved? - it probably can i'll leave this for now
                if (!swapped) {
                    throw new RuntimeException("Error: Matrix is singular or cannot be inverted.");
                }

                // update current_element - it probably changed due to row swapping
                current_element = get(n, n);
            }

            // Normalize the pivot row (make the diagonal element 1)
            divideRow(n, current_element);
            divideVals(n,current_element,b);

            // now the element at the main diagonal is 1,
            // using this 1 we 0 other elements in this column
            //
            // eliminate other rows in this column
            for (int r = 0; r < rows; r++) {
                if (r == n) continue;

                double factor = get(r, n);
                subtractRow(r, n, factor);    // row_r = row_r - (factor * row_n)
                subtractVals(r,n,factor,b);
            }
        }

        return b;
    }

    // helper functions
    boolean in_bounds (int r, int c) {
        // check if in bounds
        if (r >= rows || c >= cols || r < 0 || c < 0){
            return false;
        }

        return true;
    }

    boolean same_shape(Matrix m1, Matrix m2) {
        // check if same shape
        if (m1.rows != m2.rows || m1.cols != m2.cols) {
            throw new RuntimeException(String.format("Error: can't add 2 matrixes of different shape \n m1: %s\nm2: %s",
            m1.toString(), 
            m2.toString()));
        }

        return true;
    }

    // Swaps two entire rows
    public void swapRows(int r1, int r2) {
        for (int c = 0; c < cols; c++) {
            double temp = get(r1, c);
            set(r1, c, get(r2, c));
            set(r2, c, temp);
        }
    }

    // swaps vals at indexes i1 and i2 in vector b
    public void swapVals(int i1, int i2, double[] b) {
        double temp = b[i1];
        b[i1] = b[i2];
        b[i2] = temp;
    }

    // Divides an entire row by a scalar (Normalization)
    public void divideRow(int r, double scalar) {
        for (int c = 0; c < cols; c++) {
            set(r, c, get(r, c) / scalar);
        }
    }

    // Divides an entire row by a scalar (Normalization)
    public void divideVals(int r, double scalar, double[] b) {
        b[r] = b[r] / scalar;
    }

    // Subtracts a scalar multiple of sourceRow from targetRow
    public void subtractRow(int targetRow, int sourceRow, double scalar) {
        for (int c = 0; c < cols; c++) {
            double newVal = get(targetRow, c) - (scalar * get(sourceRow, c));
            set(targetRow, c, newVal);
        }
    }

    // Subtracts a scalar multiple of sourceIndex from targetIndex
    // in vector b
    public void subtractVals (int targetIndex, int sourceIndex, double scalar, double[] b) {
        double newVal = b[targetIndex] - (scalar * b[sourceIndex]);
        b[targetIndex] = newVal;
    }

    // static functions
    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random random_gen = new Random();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                m.set(r,c,random_gen.nextDouble());
            }
        }

        return m;
    }

    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {

                if (r == c) {
                    m.set(r,c,1);
                }
                else {
                    m.set(r,c,0);
                }
            }
        }

        return m;
    }
}