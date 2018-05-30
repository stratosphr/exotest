package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import visitors.formatters.object.IObjectFormatter;
import visitors.encoders.smt.ISMTEncoder;

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

    @Override
    public BoolExpr accept(ISMTEncoder generator) {
        return generator.visit(this);
    }

}
