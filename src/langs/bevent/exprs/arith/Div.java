package langs.bevent.exprs.arith;

import formatters.IObjectFormatter;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class Div extends ABinaryArithExpr<AArithExpr> {

    public Div(AArithExpr numerator, AArithExpr denominator) {
        super(numerator, denominator);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
