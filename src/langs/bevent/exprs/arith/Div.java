package langs.bevent.exprs.arith;

import com.microsoft.z3.ArithExpr;
import visitors.encoders.smt.ISMTEncoder;
import visitors.formatters.object.IObjectFormatter;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class Div extends ABinaryArithExpr<AArithExpr> {

    public Div(AArithExpr left, AArithExpr right) {
        super(left, right);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public ArithExpr accept(ISMTEncoder generator) {
        return generator.visit(this);
    }

}
