package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import visitors.encoders.smt.ISMTEncoder;
import visitors.formatters.object.IObjectFormatter;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class And extends ANaryBoolExpr<ABoolExpr> {

    public And(ABoolExpr... operands) {
        super(Arrays.stream(operands).distinct().collect(Collectors.toList()), 0);
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
