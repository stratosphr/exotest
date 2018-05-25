package langs.bevent.exprs.bool;

import formatters.IObjectFormatter;

import java.util.Arrays;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class And extends ANaryBoolExpr<ABoolExpr> {

    public And(ABoolExpr... operands) {
        super(Arrays.asList(operands), 0);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
