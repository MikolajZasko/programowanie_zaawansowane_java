package lab5;

import java.lang.Math;

public class Sin extends Node {
    Double sinVal;
    Double value;

    Sin (double value) {
        this.sinVal = Math.sin(value);
        this.value = value;
    }

    void setValue (double value) {
        this.sinVal = Math.sin(value);
        this.value = value;
    }

    @Override
    double evaluate() {
        return sinVal;
    }

    @Override
    public String toString() {
        if (sinVal < 0) {
            return "-(sin(" + value + "))";
        }

        return "sin(" + value + ")";
    }

    @Override
    Node diff(Variable var) {
        return new Cos(var.value);
    }

    @Override
    boolean isZero(){
        if (sinVal == 0) {
            return true;
        }

        return false;
    }
}
