package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.AExpr;
import langs.bevent.exprs.arith.Const;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class Or extends ANaryBoolExpr<ABoolExpr> {

    public Or(ABoolExpr... operands) {
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
    public List<Const> getRequiredConsts() {
        return getOperands().stream().map(AExpr::getRequiredConsts).flatMap(Collection::stream).collect(Collectors.toList());
    }

}
