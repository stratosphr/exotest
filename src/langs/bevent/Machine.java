package langs.bevent;

import errors.UndefinedConstantError;
import errors.UninitializedMachineError;
import langs.AObject;
import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.*;
import langs.bevent.exprs.defs.*;
import langs.bevent.exprs.sets.*;
import langs.bevent.exprs.sets.Set;
import langs.bevent.substitutions.ASubstitution;
import utilities.Maths;
import utilities.Streams;
import visitors.computers.IElementsComputer;
import visitors.formatters.object.IObjectFormatter;
import z3.Model;
import z3.Z3;
import z3.Z3Result;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 08/06/18.
 * Time : 09:42
 */
public final class Machine extends AObject implements IElementsComputer {

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
    private List<AAssignable> assignables;

    private Machine(String name, List<ConstDef> constsDefs, List<SetDef> setsDefs, List<VarDef> varsDefs, List<FunDef> funsDefs, Invariant invariant, ASubstitution initialisation, List<Event> events) {
        this.name = name;
        this.constsDefs = constsDefs.stream().distinct().collect(Collectors.toList());
        this.setsDefs = setsDefs.stream().distinct().collect(Collectors.toList());
        this.varsDefs = varsDefs.stream().distinct().collect(Collectors.toList());
        this.funsDefs = funsDefs.stream().distinct().collect(Collectors.toList());
        this.defs = new LinkedHashMap<>();
        getConstsDefs().forEach(constDef -> defs.put(constDef.getName(), constDef));
        getSetsDefs().forEach(setDef -> defs.put(setDef.getName(), setDef));
        getVarsDefs().forEach(varDef -> defs.put(varDef.getName(), varDef));
        getFunsDefs().forEach(funDef -> defs.put(funDef.getName(), funDef));
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
        singleton.getFunsDefs().forEach(funDef -> funDef.getDomain().accept(singleton).forEach(expr -> singleton.assignables.add(new Fun(funDef.getName(), expr))));
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

    private void assertRequiredConstsDefined(AFiniteSetExpr expr) {
        List<Const> requiredConsts = expr.getRequiredConsts();
        List<Const> missingConsts = requiredConsts.stream().filter(aConst -> !getDefs().keySet().contains(aConst.getName())).collect(Collectors.toList());
        if (!missingConsts.isEmpty()) {
            throw new UndefinedConstantError(expr, missingConsts);
        }
    }

    @Override
    public List<AArithExpr> visit(Range range) {
        assertRequiredConstsDefined(range);
        Var lowerBoundFresh = new Var("lowerBound!fresh");
        Var upperBoundFresh = new Var("upperBound!fresh");
        Z3Result result = Z3.checkSAT(new And(
                getInvariant(),
                new Equals(lowerBoundFresh, range.getLowerBound()),
                new Equals(upperBoundFresh, range.getUpperBound())
        ));
        Model model = result.getModel(Arrays.asList(lowerBoundFresh, upperBoundFresh));
        return Maths.range(model.get(lowerBoundFresh).getValue(), model.get(upperBoundFresh).getValue()).stream().map(Int::new).collect(Collectors.toList());
    }

    @Override
    public List<AArithExpr> visit(Set set) {
        assertRequiredConstsDefined(set);
        List<Var> elementsVars = Streams.mapWithIndex(set.getElements().stream(), (index, element) -> new Var("element!" + index + "!fresh")).collect(Collectors.toList());
        Z3Result result = Z3.checkSAT(new And(
                getInvariant(),
                new And(Streams.mapWithIndex(elementsVars.stream(), (index, var) -> new Equals(var, set.getElements().get(index))).toArray(ABoolExpr[]::new))
        ));
        return result.getModel(elementsVars).values().stream().map(aValue -> new Int(aValue.getValue())).distinct().collect(Collectors.toList());
    }

    @Override
    public List<AArithExpr> visit(NamedSet namedSet) {
        return getDefs().get(namedSet.getName()).getDomain().accept(this);
    }

}
