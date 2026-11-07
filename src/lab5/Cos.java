package lab5;

import java.lang.Math;

public class Cos extends Node {
    Double cosVal;
    Double value;

    Cos (double value) {
        this.cosVal = Math.cos(value);
        this.value = value;
    }

    void setValue (double value) {
        this.cosVal = Math.cos(value);
        this.value = value;
    }

    @Override
    double evaluate() {
        return cosVal;
    }

    @Override
    public String toString() {
        if (cosVal < 0) {
            return "-(cos(" + value + "))";
        }

        return "cos(" + value + ")";
    }

    @Override
    Node diff(Variable var) {
        return new Sin(var.value);
    }

    @Override
    boolean isZero(){
        if (cosVal == 0) {
            return true;
        }

        return false;
    }
}
