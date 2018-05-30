import langs.bevent.exprs.arith.Int;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.bool.ABoolExpr;
import langs.bevent.exprs.bool.And;
import langs.bevent.exprs.bool.In;
import langs.bevent.exprs.sets.Range;
import z3.Model;
import z3.Z3;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Var bat1 = new Var("bat1");
        Var bat2 = new Var("bat2");
        Var bat3 = new Var("bat3");
        ABoolExpr expr = new And(
                new In<>(bat1, new Range(new Int(0), new Int(1))),
                new In<>(bat2, new Range(new Int(1), new Int(3))),
                new In<>(bat3, new Range(new Int(42), new Int(64)))
        );
        Model model = Z3.checkSAT(expr).getModel(Arrays.asList(bat2, bat3, bat1));
        System.out.println(model);
    }

}
