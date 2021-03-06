package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.arith.Const;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;
import visitors.primer.IPrimer;

import java.util.LinkedHashSet;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 00:07
 */
public final class True extends ABoolExpr<True> {

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
        return new LinkedHashSet<>();
    }

    @Override
    public True accept(IPrimer primer) {
        return primer.visit(this);
    }

}
