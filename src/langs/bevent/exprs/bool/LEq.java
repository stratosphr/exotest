package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.arith.AArithExpr;
import visitors.encoders.smt.ISMTEncoder;
import visitors.formatters.object.IObjectFormatter;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 11:21
 */
public final class LEq extends ABinaryBoolExpr<AArithExpr> {

    public LEq(AArithExpr left, AArithExpr right) {
        super(left, right);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public BoolExpr accept(ISMTEncoder generator) {
        return generator.visit(this);
    }

}
