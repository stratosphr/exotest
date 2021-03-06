package langs.bevent.exprs.arith;

import com.microsoft.z3.ArithExpr;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;
import visitors.primer.IPrimer;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class Div extends ABinaryArithExpr<AArithExpr, Div> {

    public Div(AArithExpr left, AArithExpr right) {
        super(left, right);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public ArithExpr accept(IZ3Encoder generator) {
        return generator.visit(this);
    }

    @Override
    public Div accept(IPrimer primer) {
        return primer.visit(this);
    }

}
