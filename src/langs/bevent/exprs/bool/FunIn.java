package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.arith.Fun;
import langs.bevent.exprs.sets.ASetExpr;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;
import visitors.primer.IPrimer;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gvoiron on 04/06/18.
 * Time : 14:53
 */
public final class FunIn extends AIn<Fun, FunIn> {

    public FunIn(Fun expr, ASetExpr domain) {
        super(expr, domain);
    }

    public Fun getFun() {
        return getExpr();
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
    public LinkedHashSet<Const> getRequiredConsts() {
        return Stream.of(getExpr().getRequiredConsts(), getDomain().getRequiredConsts()).flatMap(Collection::stream).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public FunIn accept(IPrimer primer) {
        return primer.visit(this);
    }

}
