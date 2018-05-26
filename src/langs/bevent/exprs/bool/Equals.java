package langs.bevent.exprs.bool;

import formatters.IObjectFormatter;
import langs.bevent.exprs.arith.AArithExpr;

import java.util.Arrays;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:40
 */
public final class Equals extends ANaryBoolExpr<AArithExpr> {

    public Equals(AArithExpr... operands) {
        super(Arrays.asList(operands), 2);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
