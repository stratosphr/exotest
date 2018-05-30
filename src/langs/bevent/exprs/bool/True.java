package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import visitors.formatters.object.IObjectFormatter;
import visitors.encoders.smt.ISMTEncoder;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 00:07
 */
public final class True extends ABoolExpr {

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public BoolExpr accept(ISMTEncoder generator) {
        return generator.visit(this);
    }

}
