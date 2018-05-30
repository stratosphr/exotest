package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import visitors.formatters.object.IObjectFormatter;
import visitors.encoders.z3.IZ3Encoder;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class False extends ABoolExpr {

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public BoolExpr accept(IZ3Encoder generator) {
        return generator.visit(this);
    }

}
