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

                // 3 
                // 0 1 2 
                if (current_length > j){
                    System.out.println("current_length: " + current_length);
                    System.out.println("j: " + j);
                    this.data[(this.cols * i) + j] = d[i][j];
                }
                else {
                    this.data[(this.cols * i) + j] = 0;
                }
            }
        }

        System.out.println("max_row: " + max_row);
        System.out.println("max_row_index: " + max_row_index);
    }

    

    // getters
    double[][] asArray() {

        double [][] tmp = new double[this.cols][this.cols];

        int current_row = 0;
        int current_col = 0;

        for (double item : this.data) {
            tmp[current_row][current_col] = item;

            current_col++;

            if (current_col >= this.cols) {
                current_col = 0;
                current_row++;
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
        for(int i = 0;i < this.cols;i++){
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
                new_m.set(r,c, get(r, c) + m.get(r, c) );
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

    // helper functions
    boolean in_bounds (int r, int c) {
        // check if in bounds
        if (r >= rows || c >= cols){
            return false;
        }

        return true;
    }

    boolean same_shape(Matrix m1, Matrix m2) {
        // check if same shape
        if (m1.shape() != m2.shape()) {
            throw new RuntimeException(String.format("Error: can't add 2 matrixes of different shape \n m1: %s\nm2: %s", 
            m1.toString(), 
            m2.toString()));
        }

        return true;
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