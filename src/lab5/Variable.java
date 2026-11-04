package lab5;

public class Variable extends Node {
    String name;
    Double value;

    Variable(String name){
        this.name = name;
        this.value = 0.0;
    }

    void setValue(double d){

        value = d;
//        sign = d<0?-1:1;
//        value = d<0?-value:value;
    }

    @Override
    double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        //
        //
        // String sgn=sign<0?"-":"";

        if (sign < 0) {
            return "-(" + name + ")";
        }

        return name;
    }

}
