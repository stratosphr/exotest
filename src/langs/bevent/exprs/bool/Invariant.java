package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.arith.Const;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;

import java.util.List;

/**
 * Created by gvoiron on 08/06/18.
 * Time : 09:51
 */
public final class Invariant extends ABoolExpr {

    private final ABoolExpr expr;

    public Invariant(ABoolExpr expr) {
        this.expr = expr;
    }

    public ABoolExpr getExpr() {
        return expr;
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
        return expr.getRequiredConsts();
    }

}
