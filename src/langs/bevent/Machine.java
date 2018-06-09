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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gvoiron on 08/06/18.
 * Time : 09:42
 */
public final class Machine extends AObject {

    private static Machine singleton;
    private final String name;
    private final List<ConstDef> constsDefs;
    private final List<SetDef> setsDefs;
    private final List<VarDef> varsDefs;
    private final List<FunDef> funsDefs;
    private final Map<String, ADef> defs;
    private final Invariant invariant;
    private final ASubstitution initialisation;
    private final List<Event> events;
    private final List<AAssignable> assignables;

    private Machine(String name, List<ConstDef> constsDefs, List<SetDef> setsDefs, List<VarDef> varsDefs, List<FunDef> funsDefs, Invariant invariant, ASubstitution initialisation, List<Event> events) {
        this.name = name;
        this.constsDefs = constsDefs.stream().distinct().collect(Collectors.toList());
        this.setsDefs = setsDefs.stream().distinct().collect(Collectors.toList());
        this.varsDefs = varsDefs.stream().distinct().collect(Collectors.toList());
        this.funsDefs = funsDefs.stream().distinct().collect(Collectors.toList());
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
        this.events = events.stream().distinct().collect(Collectors.toList());
        this.assignables = new ArrayList<>();
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

    public List<ConstDef> getConstsDefs() {
        return constsDefs;
    }

    public List<SetDef> getSetsDefs() {
        return setsDefs;
    }

    public List<VarDef> getVarsDefs() {
        return varsDefs;
    }

    public List<FunDef> getFunsDefs() {
        return funsDefs;
    }

    public Invariant getInvariant() {
        return invariant;
    }

    public ASubstitution getInitialisation() {
        return initialisation;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<AAssignable> getAssignables() {
        return assignables;
    }

    public Map<String, ADef> getDefs() {
        return defs;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
