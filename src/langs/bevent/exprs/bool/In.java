package langs.bevent.exprs.bool;

import formatters.IObjectFormatter;
import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.sets.ASetExpr;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:32
 */
public final class In<Value extends AArithExpr> extends AIn<Value> {

    public In(Value expr, ASetExpr domain) {
        super(expr, domain);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
