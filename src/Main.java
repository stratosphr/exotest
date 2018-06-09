import langs.bevent.Event;
import langs.bevent.Machine;
import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.And;
import langs.bevent.exprs.bool.Equals;
import langs.bevent.exprs.bool.Invariant;
import langs.bevent.exprs.defs.ConstDef;
import langs.bevent.exprs.defs.FunDef;
import langs.bevent.exprs.defs.SetDef;
import langs.bevent.exprs.defs.VarDef;
import langs.bevent.exprs.sets.NamedSet;
import langs.bevent.exprs.sets.Range;
import langs.bevent.exprs.sets.Set;
import langs.bevent.substitutions.ASubstitution;
import langs.bevent.substitutions.Skip;
import z3.Z3;
import z3.Z3Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<ConstDef> constsDefs = Arrays.asList(
                new ConstDef(new Const("z"), new Plus(new Const("n"), new Int(3))),
                new ConstDef(new Const("n"), new Int(10))
        );
        List<SetDef> setsDefs = Arrays.asList(
                new SetDef("Bats", new Range(new Int(1), new Const("n"))),
                new SetDef("Bats2", new Set(new Int(1), new Const("n"), new Const("z")))
        );
        List<VarDef> varsDefs = Arrays.asList(
                new VarDef(new Var("h"), new Set(new Int(0), new Int(1))),
                new VarDef(new Var("sw"), new NamedSet("Bats"))
        );
        List<FunDef> funsDefs = Arrays.asList(
                new FunDef("bat", new NamedSet("Bats"), new Set(new Int(0), new Int(1))),
                new FunDef("bat2", new NamedSet("Bats2"), new Set(new Int(0), new Int(1)))
        );
        Invariant invariant = new Invariant(new And(
                new Equals(new Fun("bat", new Var("sw")), new Int(1))
        ));
        ASubstitution initialisation = new Skip();
        List<Event> events = new ArrayList<>();
        Machine machine = Machine.build(
                "EL",
                constsDefs,
                setsDefs,
                varsDefs,
                funsDefs,
                invariant,
                initialisation,
                events
        );
        Z3Result z3Result = Z3.checkSAT(machine.getInvariant());
        System.out.println(z3Result.getModel(machine.getAssignables()));
    }

}
