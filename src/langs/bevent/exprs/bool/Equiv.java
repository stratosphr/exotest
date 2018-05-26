package langs.bevent.exprs.bool;

import formatters.IObjectFormatter;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 00:07
 */
public final class Equiv extends ABinaryBoolExpr<ABoolExpr> {

    public Equiv(ABoolExpr left, ABoolExpr right) {
        super(left, right);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}