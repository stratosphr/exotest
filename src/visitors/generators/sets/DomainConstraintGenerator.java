package visitors.generators.sets;

import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.bool.*;
import langs.bevent.exprs.sets.Range;
import langs.bevent.exprs.sets.Set;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 11:46
 */
public final class DomainConstraintGenerator implements IDomainConstraintGenerator {

    private final AArithExpr expr;

    public DomainConstraintGenerator(AArithExpr expr) {
        this.expr = expr;
    }

    @Override
    public ABoolExpr visit(Set set) {
        return new Or(set.getElements().stream().map(element -> new Equals(expr, element)).toArray(ABoolExpr[]::new));
    }

    @Override
    public ABoolExpr visit(Range range) {
        return new And(new GEq(expr, range.getLowerBound()), new LEq(expr, range.getUpperBound()));
    }

}
