package langs.bevent.exprs.sets;

import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.bool.ABoolExpr;
import visitors.computers.ISetElementsComputer;
import visitors.formatters.object.IObjectFormatter;
import visitors.generators.sets.IDomainConstraintGenerator;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:25
 */
public final class Range extends AFiniteSetExpr {

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

    /*@Override
    public List<AArithExpr> computeElementsValues() {
        Var lowerBoundFresh = new Var("lowerBound!fresh");
        Var upperBoundFresh = new Var("upperBound!fresh");
        Z3Result result = Z3.checkSAT(new And(
                Machine.getSingleton().getInvariant(),
                new Equals(lowerBoundFresh, lowerBound),
                new Equals(upperBoundFresh, upperBound)
        ));
        Model model = result.getModel(Arrays.asList(lowerBoundFresh, upperBoundFresh));
        return Maths.range(model.get(lowerBoundFresh).getValue(), model.get(upperBoundFresh).getValue()).stream().map(Int::new).collect(Collectors.toList());
    }*/

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public ABoolExpr accept(IDomainConstraintGenerator generator) {
        return generator.visit(this);
    }

    @Override
    public List<AArithExpr> accept(ISetElementsComputer computer) {
        return computer.visit(this);
    }

    @Override
    public List<Const> getRequiredConsts() {
        return Stream.of(getLowerBound().getRequiredConsts(), getUpperBound().getRequiredConsts()).flatMap(Collection::stream).collect(Collectors.toList());
    }

}
