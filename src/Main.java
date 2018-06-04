import langs.bevent.exprs.arith.Fun;
import langs.bevent.exprs.arith.Int;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.bool.*;
import langs.bevent.exprs.defs.VarDef;
import langs.bevent.exprs.sets.Range;
import langs.bevent.exprs.sets.Set;
import langs.bevent.exprs.sets.Z;
import z3.Z3;
import z3.Z3Result;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ABoolExpr invariant = new And(
                new ForAll(
                        new Equiv(
                                new In<>(new Fun("bat", new Var("i")), new Set(new Int(0), new Int(1))),
                                new In<>(new Var("i"), new Range(new Int(1), new Int(3)))
                        ),
                        new VarDef(new Var("i"), new Z())
                )
        );
        Exists exists = new Exists(
                new And(
                        new NEq(new Var("i"), new Var("j")),
                        new Equals(new Fun("bat", new Var("i")), new Int(1)),
                        new Equals(new Fun("bat", new Var("j")), new Int(1))
                ),
                new VarDef(new Var("i"), new Range(new Int(1), new Int(3))),
                new VarDef(new Var("j"), new Range(new Int(1), new Int(3)))
        );
        ABoolExpr constraint = new And(
                new Equals(new Fun("bat", new Int(2)), new Int(0))
        );
        System.out.println(new And(invariant, exists, constraint));
        Z3Result r1 = Z3.checkSAT(new And(invariant, exists, constraint));
        System.out.println(r1.getModel(Arrays.asList(new Fun("bat", new Int(1)), new Fun("bat", new Int(2)), new Fun("bat", new Int(3)))));
    }

}
