import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(new Int(42));
        System.out.println(new Plus(new Int(42), new Minus(new Int(4), new Int(0), new Int(0)), new Int(54), new Minus(new Int(5))));
        System.out.println(new Div(new Int(42), new Int(3)));
        System.out.println(new And());
        System.out.println(new And(new False()));
        System.out.println(new And(
                new False(),
                new Or(new True(), new False(), new Not(new Or())),
                new Equiv(new True(), new False()),
                new Implies(new Equiv(new False(), new Or()), new Implies(new True(), new False()))
        ));
        System.out.println(new Plus(new Var("test"), new Var("toto"), new Const("someConst")));
    }

}
