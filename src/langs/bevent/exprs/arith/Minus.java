package langs.bevent.exprs.arith;

import formatters.IObjectFormatter;

import java.util.Arrays;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class Minus extends ANaryArithExpr<AArithExpr> {

    public Minus(AArithExpr... operands) {
        super(Arrays.asList(operands), 1);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
