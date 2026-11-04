package lab5;

import org.junit.jupiter.api.Test;

import java.util.Locale;

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

    @Test
    public void buildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);

        // looks for y = 0
        double minVal = -5.0;
        double maxVal = 5.0;
        double currentVal = 0;
        double result = 0;
        double tolerance = 1e-7;

        while (true) {
            // guess in the middle
            currentVal = minVal + (maxVal - minVal) / 2;
            x.setValue(currentVal);
            result = exp.evaluate();

            // debug
            System.out.printf("guessing: f(%f)=%f\n", currentVal, result);

            // check if succeeded
            if (Math.abs(result) < tolerance){
                break;
            }

            // if not, check if val is more than 0 or less
            if (result > 0) {
                maxVal = currentVal;
                continue;
            }

            if (result < 0) {
                minVal = currentVal;
                continue;
            }
        }

        // debug
        System.out.printf(Locale.US, "found it!: f(%f)=%f\n", currentVal, result);

        // prints vals
//        for (double v = -5; v < 5; v += 0.1) {
//            x.setValue(v);
//            System.out.printf(Locale.US, "f(%f)=%f\n", v, exp.evaluate());
//        }
    }

    @Test
    void defineCircle(){
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.println(circle.toString());

        double xv = 100*(Math.random()-.5);
        double yv = 100*(Math.random()-.5);
        x.setValue(xv);
        y.setValue(yv);
        double fv = circle.evaluate();
        System.out.print(String.format("Punkt (%f,%f) leży %s koła %s\n",xv,yv,(fv<0?"wewnątrz":"na zewnątrz"),circle.toString()));
    }
}
