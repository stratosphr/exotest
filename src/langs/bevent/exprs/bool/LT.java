package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.arith.AArithExpr;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;
import visitors.primer.IPrimer;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 11:19
 */
public final class LT extends ABinaryBoolExpr<AArithExpr, LT> {

    public LT(AArithExpr left, AArithExpr right) {
        super(left, right);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public BoolExpr accept(IZ3Encoder generator) {
        return generator.visit(this);
    }

    @Override
    public LT accept(IPrimer primer) {
        return primer.visit(this);
    }

}
