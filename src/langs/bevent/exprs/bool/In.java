package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.sets.ASetExpr;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;
import visitors.primer.IPrimer;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:32
 */
public final class In<Value extends AArithExpr<Value>> extends AIn<Value, In<Value>> {

    public In(Value expr, ASetExpr domain) {
        super(expr, domain);
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
        return Stream.of(getExpr().getRequiredConsts(), getDomain().getRequiredConsts()).flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override
    public In<Value> accept(IPrimer primer) {
        return primer.visit(this);
    }

}
