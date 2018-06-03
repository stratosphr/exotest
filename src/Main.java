import langs.bevent.exprs.arith.Fun;
import langs.bevent.exprs.arith.Int;
import langs.bevent.exprs.arith.Plus;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.bool.And;
import langs.bevent.exprs.bool.Equals;
import langs.bevent.exprs.bool.False;
import langs.bevent.substitutions.FunAssignment;
import langs.bevent.substitutions.MultipleAssignment;
import langs.bevent.substitutions.Select;
import langs.bevent.substitutions.VarAssignment;

import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        VarAssignment var = new VarAssignment(new Var("bat1"), new Plus(new Int(42), new Int(2)));
        FunAssignment fun = new FunAssignment(new Fun("bat", new Int(1)), new Plus(new Int(42), new Int(2)));
        VarAssignment var2 = new VarAssignment(new Var("v1"), new Plus(new Int(42), new Int(2)));
        Select select = new Select(new And(new False(), new Equals(new Var("v1"), new Int(42))), new MultipleAssignment(var, fun, var2));
        System.out.println(select.getPrd(new HashSet<>(Arrays.asList(new Var("v1"), new Fun("bat", new Int(1)), new Var("bat1")))));
    }

}
