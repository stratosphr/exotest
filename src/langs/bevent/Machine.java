package langs.bevent;

import errors.UninitializedMachineError;
import langs.AObject;
import langs.bevent.exprs.arith.AAssignable;
import langs.bevent.exprs.arith.Fun;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.bool.*;
import langs.bevent.exprs.defs.*;
import langs.bevent.exprs.sets.Z;
import langs.bevent.substitutions.ASubstitution;
import visitors.computers.SetElementsComputer;
import visitors.formatters.object.IObjectFormatter;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by gvoiron on 08/06/18.
 * Time : 09:42
 */
public final class Machine extends AObject {

    private static Machine singleton;
    private final String name;
    private final LinkedHashSet<ConstDef> constsDefs;
    private final LinkedHashSet<SetDef> setsDefs;
    private final LinkedHashSet<VarDef> varsDefs;
    private final LinkedHashSet<FunDef> funsDefs;
    private final LinkedHashMap<String, ADef> defs;
    private final Invariant invariant;
    private final ASubstitution initialisation;
    private final LinkedHashSet<Event> events;
    private final LinkedHashSet<AAssignable> assignables;

    private Machine(String name, List<ConstDef> constsDefs, List<SetDef> setsDefs, List<VarDef> varsDefs, List<FunDef> funsDefs, Invariant invariant, ASubstitution initialisation, List<Event> events) {
        this.name = name;
        this.constsDefs = new LinkedHashSet<>(constsDefs);
        this.setsDefs = new LinkedHashSet<>(setsDefs);
        this.varsDefs = new LinkedHashSet<>(varsDefs);
        this.funsDefs = new LinkedHashSet<>(funsDefs);
        this.defs = new LinkedHashMap<>();
        Stream.of(getConstsDefs(), getSetsDefs(), getVarsDefs(), getFunsDefs()).flatMap(Collection::stream).forEach(def -> {
            if (defs.containsKey(def.getName())) {
                throw new Error("Symbol \"" + def.getName() + "\" was already defined in this scope.");
            } else {
                defs.put(def.getName(), def);
            }
        });
        this.invariant = new Invariant(new And(
                new And(getConstsDefs().stream().map(constDef -> new Equals(constDef.getConst(), constDef.getValue())).toArray(ABoolExpr[]::new)),
                new And(getVarsDefs().stream().map(varDef -> new VarIn(varDef.getVar(), varDef.getDomain())).toArray(ABoolExpr[]::new)),
                new And(getFunsDefs().stream().map(funDef -> new ForAll(
                        new Equiv(
                                new VarIn(new Var("i!"), funDef.getDomain()),
                                new FunIn(new Fun(funDef.getName(), new Var("i!")), funDef.getCodomain())
                        ),
                        new VarIn(new Var("i!"), new Z())
                )).toArray(ABoolExpr[]::new)),
                invariant
        ));
        this.initialisation = initialisation;
        this.events = new LinkedHashSet<>(events);
        this.assignables = new LinkedHashSet<>();
    }

    public static Machine build(String name, List<ConstDef> constsDefs, List<SetDef> setsDefs, List<VarDef> varsDefs, List<FunDef> funsDefs, Invariant invariant, ASubstitution initialisation, List<Event> events) {
        singleton = new Machine(name, constsDefs, setsDefs, varsDefs, funsDefs, invariant, initialisation, events);
        singleton.getVarsDefs().forEach(varDef -> singleton.assignables.add(varDef.getVar()));
        singleton.getFunsDefs().forEach(funDef -> funDef.getDomain().accept(new SetElementsComputer()).forEach(expr -> singleton.assignables.add(new Fun(funDef.getName(), expr))));
        return singleton;
    }

    public static Machine getSingleton() {
        if (singleton == null) {
            throw new UninitializedMachineError();
        }
        return singleton;
    }

    public String getName() {
        return name;
    }

    public LinkedHashSet<ConstDef> getConstsDefs() {
        return constsDefs;
    }

    public LinkedHashSet<SetDef> getSetsDefs() {
        return setsDefs;
    }

    public LinkedHashSet<VarDef> getVarsDefs() {
        return varsDefs;
    }

    public LinkedHashSet<FunDef> getFunsDefs() {
        return funsDefs;
    }

    public Invariant getInvariant() {
        return invariant;
    }

    public ASubstitution getInitialisation() {
        return initialisation;
    }

    public LinkedHashSet<Event> getEvents() {
        return events;
    }

    public LinkedHashSet<AAssignable> getAssignables() {
        return assignables;
    }

    public LinkedHashMap<String, ADef> getDefs() {
        return defs;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
