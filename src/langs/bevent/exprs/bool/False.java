package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.arith.Const;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;
import visitors.primer.IPrimer;

import java.util.Collections;
import java.util.List;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class False extends ABoolExpr<False> {

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
        return Collections.emptyList();
    }

    @Override
    public False accept(IPrimer primer) {
        return primer.visit(this);
    }

}
