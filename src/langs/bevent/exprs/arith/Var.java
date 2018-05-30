package langs.bevent.exprs.arith;

import com.microsoft.z3.ArithExpr;
import visitors.formatters.object.IObjectFormatter;
import visitors.encoders.smt.ISMTEncoder;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class Var extends AArithExpr {

    private final String name;

    public Var(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public ArithExpr accept(ISMTEncoder generator) {
        return generator.visit(this);
    }

}
