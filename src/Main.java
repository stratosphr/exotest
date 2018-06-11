import langs.bevent.Event;
import langs.bevent.Machine;
import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.arith.Fun;
import langs.bevent.exprs.arith.Int;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.bool.*;
import langs.bevent.exprs.defs.ConstDef;
import langs.bevent.exprs.defs.FunDef;
import langs.bevent.exprs.defs.SetDef;
import langs.bevent.exprs.defs.VarDef;
import langs.bevent.exprs.sets.NamedSet;
import langs.bevent.exprs.sets.Range;
import langs.bevent.exprs.sets.Set;
import langs.bevent.substitutions.*;
import visitors.primer.Primer;
import z3.Z3;
import z3.Z3Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<ConstDef> constsDefs = Arrays.asList(
                new ConstDef(new Const("n"), new Int(3))
        );
        List<SetDef> setsDefs = Arrays.asList(
                new SetDef("Bats", new Range(new Int(1), new Const("n")))
        );
        List<VarDef> varsDefs = Arrays.asList(
                new VarDef(new Var("h"), new Set(new Int(0), new Int(1))),
                new VarDef(new Var("sw"), new NamedSet("Bats"))
        );
        List<FunDef> funsDefs = Arrays.asList(
                new FunDef("bat", new NamedSet("Bats"), new Set(new Int(0), new Int(1)))
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
        MultipleAssignment multipleAssignment = new MultipleAssignment(
                new FunAssignment(new Fun("bat", new Int(1)), new Int(0)),
                new FunAssignment(new Fun("bat", new Int(3)), new Int(0))
        );
        Invariant invariant_ = machine.getInvariant().accept(new Primer(machine.getAssignables()));
        ABoolExpr prd = multipleAssignment.getPrd(new LinkedHashSet<>(machine.getAssignables()));
        System.out.println(prd);
        Z3Result z3Result = Z3.checkSAT(new And(invariant, invariant_, prd));
        System.out.println(z3Result.getModel(machine.getAssignables()));
        System.out.println(z3Result.getModel(machine.getAssignables().stream().map(assignable -> assignable.accept(new Primer(assignable))).collect(Collectors.toList())));
        System.out.println(new Any(
                new True(),
                new Skip(),
                new VarIn(new Var("i1!"), new Set(new Int(0))),
                new VarIn(new Var("i2!"), new Range(new Int(0), new Int(1)))
        ));
        System.out.println(new Choice(new Skip(), new Select(new True(), new Skip()), new Select(new True(), multipleAssignment)).getPrd(new LinkedHashSet<>(machine.getAssignables())));
        System.out.println(new Choice(new Skip(), new Select(new True(), new Skip())));
    }

}
