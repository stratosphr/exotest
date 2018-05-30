package langs.bevent.exprs.arith;

import com.microsoft.z3.ArithExpr;
import visitors.encoders.smt.ISMTEncoder;
import visitors.formatters.object.IObjectFormatter;

import java.util.Arrays;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class Minus extends ANaryArithExpr<AArithExpr> {

    public Minus(AArithExpr... operands) {
        super(Arrays.asList(operands), 1);
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
