package lab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node... n){
        Collections.addAll(args, n);
    }


    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    // sth wrong here
    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        // oblicz sumę wartości zwróconych przez wywołanie evaluate skłądników sumy
        double result = args.stream().mapToDouble(Node::evaluate).sum();
        return sign*result;
    }

    int getArgumentsCount(){return args.size();}

    public String toString(){

        if (isZero()) {
            return "";
        }

        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-(");

        int size = args.size();
        boolean addPlus = false;

        for (int i = 0; i < size; i++) {

            if (args.get(i).isZero()) {
                continue;
            }

            if (i != 0 && addPlus) {
                b.append(" + ");
            }
            else {
                addPlus = true;
            }

            b.append(args.get(i).toString());
        }

        if(sign<0)b.append(")");
        return b.toString();
    }

    @Override
    Node diff(Variable var) {
        Sum r = new Sum();
        for(Node n:args){
            r.add(n.diff(var));
        }
        return r;
    }

    @Override
    boolean isZero() {
        if (Math.abs(this.evaluate()) < tolerance) {
            return true;
        }

        return false;
    }


}
