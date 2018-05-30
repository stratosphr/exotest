package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import visitors.formatters.object.IObjectFormatter;
import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.sets.ASetExpr;
import visitors.encoders.z3.IZ3Encoder;

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

    @Override
    public BoolExpr accept(IZ3Encoder generator) {
        return generator.visit(this);
    }

}
