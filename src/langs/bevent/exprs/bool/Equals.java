package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.arith.AArithExpr;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;
import visitors.primer.IPrimer;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:40
 */
public final class Equals extends ANaryBoolExpr<AArithExpr, Equals> {

    public Equals(AArithExpr... operands) {
        super(Arrays.stream(operands).distinct().collect(Collectors.toList()), 2);
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
    public Equals accept(IPrimer primer) {
        return primer.visit(this);
    }

}
