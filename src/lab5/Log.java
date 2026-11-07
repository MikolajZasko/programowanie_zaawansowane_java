package lab5;

import java.lang.Math;

public class Log extends Node{

    // baseVal can be only read
    double baseVal = Math.E;
    double value;
    static double h = 1e-6; // step size

    Log (double  newVal) {
        this.value = newVal;
    }

    void setValue (double newVal) {
        this.value = newVal;
    }

    @Override
    double evaluate() {
        return Math.log(value);
    }

    @Override
    public String toString() {
        if (evaluate() < 0) {
            return "-(log(" + value + "))";
        }

        return "log(" + value + ")";
    }

    @Override
    Node diff(Variable var) {
        return new Log((Math.log(var.value + h) - Math.log(var.value - h)) / (2 * h));
    }

    @Override
    boolean isZero(){
        if (evaluate() == 0) {
            return true;
        }

        return false;
    }
}
