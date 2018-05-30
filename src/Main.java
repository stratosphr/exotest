import langs.bevent.exprs.AExpr;
import langs.bevent.exprs.arith.Int;
import langs.bevent.exprs.arith.Var;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        AExpr[] array = new AExpr[]{
                new Int(1),
                new Var("test1"),
                new Var("test2"),
                new Int(10),
                new Var("test"),
                new Int(2),
                new Var("test10"),
                new Int(3)
        };
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
    }

}
