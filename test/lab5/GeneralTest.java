package lab5;

import org.junit.jupiter.api.Test;

public class GeneralTest {
    @Test
    public void buildAndPrint(){
        Variable x = new Variable("x");
//        x.setValue(-3);
        Node exp = new Sum()
                .add(2.1,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.println(exp.toString());
    }

    @Test
    public void testProdBuilding() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
//        y.setValue(-1);
        Prod p = new Prod(y,x);
        p.mul(-1);
        System.out.println(p.toString());
    }
}
