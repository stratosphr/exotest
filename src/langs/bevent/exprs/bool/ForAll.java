package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;
import visitors.primer.IPrimer;

import java.util.Arrays;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 23:11
 */
public final class ForAll extends AQuantifier<ForAll> {

    public ForAll(ABoolExpr expr, VarIn... quantifiedVarsDefs) {
        super(new Implies(new And(Arrays.stream(quantifiedVarsDefs).toArray(ABoolExpr[]::new)), expr), quantifiedVarsDefs);
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
    public ForAll accept(IPrimer primer) {
        return primer.visit(this);
    }

}
