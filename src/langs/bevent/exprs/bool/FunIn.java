package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.arith.Fun;
import langs.bevent.exprs.sets.ASetExpr;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;

/**
 * Created by gvoiron on 04/06/18.
 * Time : 14:53
 */
public final class FunIn extends AIn<Fun> {

    public FunIn(Fun expr, ASetExpr domain) {
        super(expr, domain);
    }

    public Fun getFun() {
        return getExpr();
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
