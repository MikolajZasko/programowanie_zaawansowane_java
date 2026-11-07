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

            if (args.get(i).evaluate() == 1) {
                continue;
            }

            if (i != 0 && addPlus) {
                b.append("*");
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
        for(int i=0;i<args.size();i++){
            Prod m= new Prod();
            for(int j=0;j<args.size();j++){
                Node f = args.get(j);
                if(j==i)m.mul(f.diff(var));
                else m.mul(f);
            }
            r.add(m);
        }
        return r;
    }

    @Override
    boolean isZero() {
        for (int i = 0; i < args.size(); i++) {
            if (args.get(i).isZero()) {
                return true;
            }
        }

        return false;
    }

}

