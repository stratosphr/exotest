package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;
import visitors.primer.IPrimer;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 23:10
 */
public final class Exists extends AQuantifier<Exists> {

    public Exists(ABoolExpr expr, VarIn... quantifiedVarsDefs) {
        super(new And(new And(quantifiedVarsDefs), expr), quantifiedVarsDefs);
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
    public Exists accept(IPrimer primer) {
        return primer.visit(this);
    }

}
