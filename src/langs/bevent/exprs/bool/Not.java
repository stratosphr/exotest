package langs.bevent.exprs.bool;

import formatters.IObjectFormatter;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class Not extends AUnaryBoolExpr<ABoolExpr> {

    public Not(ABoolExpr operand) {
        super(operand);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
