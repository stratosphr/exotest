import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.arith.Int;
import langs.bevent.exprs.arith.Minus;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.bool.*;
import langs.bevent.exprs.sets.Range;
import langs.bevent.exprs.sets.Set;
import z3.Z3;

public class Main {

    public static void main(String[] args) {
        ABoolExpr aTrue = new True();
        ABoolExpr aFalse = new False();
        Equals equals = new Equals(new Int(5), new Const("const"), new Var("myVar"), new Minus(new Int(42), new Int(40), new Int(-3)));
        In<Var> in = new In<>(new Var("myVar"), new Set(new Int(42), new Int(2), new Int(4)));
        In<Var> in2 = new In<>(new Var("myVar"), new Range(new Int(24), new Int(42)));
        Z3.checkSAT(aTrue);
        Z3.checkSAT(aFalse);
        Z3.checkSAT(equals);
        Z3.checkSAT(new And(in, new LT(new Var("myVar"), new Int(42)), new LT(new Var("myVar"), new Int(4))));
        Z3.checkSAT(in2);
        Z3.checkSAT(new And(in2, new Not(new In<>(new Var("myVar"), new Range(new Int(24), new Int(41))))));
        System.out.println(new And(aTrue, aTrue, aTrue, aFalse));
        System.out.println(new Or(aTrue, aTrue, aTrue, aFalse));
    }

}
