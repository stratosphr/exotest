package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import visitors.formatters.object.IObjectFormatter;
import langs.bevent.exprs.arith.AArithExpr;
import visitors.encoders.smt.ISMTEncoder;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:40
 */
public final class Equals extends ANaryBoolExpr<AArithExpr> {

    public Equals(AArithExpr... operands) {
        super(Arrays.stream(operands).distinct().collect(Collectors.toList()), 2);
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
