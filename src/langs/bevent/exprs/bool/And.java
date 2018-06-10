package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;
import visitors.primer.IPrimer;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class And extends ANaryBoolExpr<ABoolExpr, And> {

    public And(ABoolExpr... operands) {
        super(Arrays.stream(operands).distinct().collect(Collectors.toList()), 0);
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
    public And accept(IPrimer primer) {
        return primer.visit(this);
    }

}
