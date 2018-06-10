package langs.bevent.exprs.arith;

import com.microsoft.z3.ArithExpr;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;
import visitors.primer.IPrimer;

import java.util.Collections;
import java.util.List;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class Const extends AArithExpr<Const> {

    private final String name;

    public Const(String name) {
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
    public ArithExpr accept(IZ3Encoder generator) {
        return generator.visit(this);
    }

    @Override
    public List<Const> getRequiredConsts() {
        return Collections.singletonList(this);
    }

    @Override
    public Const accept(IPrimer primer) {
        return primer.visit(this);
    }

}
