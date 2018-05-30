package langs.bevent.exprs.arith;

import com.microsoft.z3.ArithExpr;
import visitors.formatters.object.IObjectFormatter;
import visitors.encoders.z3.IZ3Encoder;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class Fun extends AArithExpr {

    private final String name;
    private final AArithExpr param;

    public Fun(String name, AArithExpr param) {
        this.name = name;
        this.param = param;
    }

    public String getName() {
        return name;
    }

    public AArithExpr getParam() {
        return param;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public ArithExpr accept(IZ3Encoder generator) {
        return generator.visit(this);
    }

}
