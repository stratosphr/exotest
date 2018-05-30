package langs.bevent.exprs.sets;

import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.bool.ABoolExpr;
import visitors.formatters.object.IObjectFormatter;
import visitors.sets.IDomainConstraintGenerator;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:25
 */
public final class Range extends ASetExpr {

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
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public ABoolExpr accept(IDomainConstraintGenerator generator) {
        return generator.visit(this);
    }

}
