package visitors.computers;

import errors.UndefinedConstantError;
import langs.bevent.Machine;
import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.arith.Int;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.bool.ABoolExpr;
import langs.bevent.exprs.bool.And;
import langs.bevent.exprs.bool.Equals;
import langs.bevent.exprs.sets.AFiniteSetExpr;
import langs.bevent.exprs.sets.NamedSet;
import langs.bevent.exprs.sets.Range;
import langs.bevent.exprs.sets.Set;
import utilities.Maths;
import utilities.Streams;
import z3.Model;
import z3.Z3;
import z3.Z3Result;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 09/06/18.
 * Time : 18:34
 */
public final class SetElementsComputer implements ISetElementsComputer {

    private void assertRequiredConstsDefined(AFiniteSetExpr expr) {
        List<Const> requiredConsts = expr.getRequiredConsts();
        List<Const> missingConsts = requiredConsts.stream().filter(aConst -> !Machine.getSingleton().getDefs().keySet().contains(aConst.getName())).collect(Collectors.toList());
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
                Machine.getSingleton().getInvariant(),
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
                Machine.getSingleton().getInvariant(),
                new And(Streams.mapWithIndex(elementsVars.stream(), (index, var) -> new Equals(var, set.getElements().get(index))).toArray(ABoolExpr[]::new))
        ));
        return result.getModel(elementsVars).values().stream().map(aValue -> new Int(aValue.getValue())).distinct().collect(Collectors.toList());
    }

    @Override
    public List<AArithExpr> visit(NamedSet namedSet) {
        return Machine.getSingleton().getDefs().get(namedSet.getName()).getDomain().accept(this);
    }

}
