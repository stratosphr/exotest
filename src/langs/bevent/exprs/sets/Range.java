package langs.bevent.exprs.sets;

import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.arith.Int;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.bool.ABoolExpr;
import langs.bevent.exprs.bool.And;
import langs.bevent.exprs.bool.Equals;
import utilities.Maths;
import visitors.formatters.object.IObjectFormatter;
import visitors.generators.sets.IDomainConstraintGenerator;
import z3.Model;
import z3.Z3;
import z3.Z3Result;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:25
 */
public final class Range extends AFiniteSetExpr<Int> {

    private final AArithExpr lowerBound;
    private final AArithExpr upperBound;

    public Range(AArithExpr lowerBound, AArithExpr upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public AArithExpr getLowerBound() {
        return lowerBound;
    }

    public AArithExpr getUpperBound() {
        return upperBound;
    }

    @Override
    public List<Int> computeElementsValues(ABoolExpr constraint) {
        Var lowerBoundFresh = new Var("lowerBound!fresh");
        Var upperBoundFresh = new Var("upperBound!fresh");
        Z3Result result = Z3.checkSAT(new And(
                constraint,
                new Equals(lowerBoundFresh, lowerBound),
                new Equals(upperBoundFresh, upperBound)
        ));
        Model model = result.getModel(Arrays.asList(lowerBoundFresh, upperBoundFresh));
        return Maths.range(model.get(lowerBoundFresh).getValue(), model.get(upperBoundFresh).getValue()).stream().map(Int::new).collect(Collectors.toList());
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public ABoolExpr accept(IDomainConstraintGenerator generator) {
        return generator.visit(this);
    }

}
