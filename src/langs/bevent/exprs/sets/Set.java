package langs.bevent.exprs.sets;

import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.arith.Int;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.bool.ABoolExpr;
import langs.bevent.exprs.bool.And;
import langs.bevent.exprs.bool.Equals;
import utilities.Streams;
import visitors.formatters.object.IObjectFormatter;
import visitors.generators.sets.IDomainConstraintGenerator;
import z3.Z3;
import z3.Z3Result;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:25
 */
public final class Set extends AFiniteSetExpr<Int> {

    private final List<AArithExpr> elements;

    public Set(AArithExpr... elements) {
        this.elements = Arrays.stream(elements).distinct().collect(Collectors.toList());
    }

    public List<AArithExpr> getElements() {
        return elements;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }


    @Override
    public ABoolExpr accept(IDomainConstraintGenerator generator) {
        return generator.visit(this);
    }

    @Override
    public List<Int> computeElementsValues(ABoolExpr constraint) {
        List<Var> elementsVars = Streams.mapWithIndex(elements.stream(), (index, element) -> new Var("element!" + index + "!fresh")).collect(Collectors.toList());
        Z3Result result = Z3.checkSAT(new And(
                constraint,
                new And(Streams.mapWithIndex(elementsVars.stream(), (index, var) -> new Equals(var, elements.get(index))).toArray(ABoolExpr[]::new))
        ));
        return result.getModel(elementsVars).values().stream().map(aValue -> new Int(aValue.getValue())).distinct().collect(Collectors.toList());
    }

}
