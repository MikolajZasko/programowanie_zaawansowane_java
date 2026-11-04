package lab5;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Constant extends Node {
    double value;
    Constant(double value){
        this.sign = value<0?-1:1;
        this.value = value<0?-value:value;
    }

    @Override
    double evaluate() {
        return sign*value;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("0.#####",new DecimalFormatSymbols(Locale.US));

        if (sign < 0) {
            return "(-" + format.format(value) + ")";
        }

        return format.format(value);
    }
}