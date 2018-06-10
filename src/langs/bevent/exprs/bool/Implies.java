package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;
import visitors.primer.IPrimer;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 00:07
 */
public final class Implies extends ABinaryBoolExpr<ABoolExpr, Implies> {

    public Implies(ABoolExpr left, ABoolExpr right) {
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
    public Implies accept(IPrimer primer) {
        return primer.visit(this);
    }

}
