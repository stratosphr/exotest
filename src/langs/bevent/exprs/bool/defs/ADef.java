package langs.bevent.exprs.bool.defs;

import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.bool.AIn;
import langs.bevent.exprs.sets.ASetExpr;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:16
 */
public abstract class ADef<Value extends AArithExpr> extends AIn<Value> {

    public ADef(Value expr, ASetExpr domain) {
        super(expr, domain);
    }

}
