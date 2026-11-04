package lab5;

import java.util.ArrayList;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node n1){
        args.add(n1);
    }
    Prod(double c){
        // wywołaj konstruktor jednoargumentowy przekazując new Constant(c)

        // this(...)
        // instead of
        // new Prod(...)
        this(new Constant(c));
    }

    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }

    // sth wrong here
    Prod(double c, Node n){
        // wywołaj konstruktor dwuargumentowy
        this(new Constant(c), n);
    }

    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        Constant new_c = new Constant(c);
        args.add(new_c);
        return this;
    }

    @Override
    double evaluate() {
        double result = 1.0;
        int size = args.size(); // Cache size to avoid calling size() on every iteration

        // oblicz iloczyn czynników wołąjąc ich metodę evaluate
        for (int i = 0; i < size; i++) {
            double node_val = args.get(i).evaluate();

            if (node_val == 0.0) {
                return 0.0;
            }

            result *= node_val;
        }
        return sign*result;
    }

    int getArgumentsCount(){return args.size();}


    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-(");

        int size = args.size();
        for (int i = 0; i < size; i++) {
            b.append(args.get(i).toString());
            // Append " + " only if this is NOT the last node
            if (i < size - 1) {
                b.append("*");
            }
        }

        if(sign<0)b.append(")");

        return b.toString();
    }


}

