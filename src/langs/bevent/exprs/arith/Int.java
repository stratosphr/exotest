package langs.bevent.exprs.arith;

import com.microsoft.z3.ArithExpr;
import langs.AObject;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;
import visitors.primer.IPrimer;

import java.util.LinkedHashSet;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class Int extends AValue<Int> {

    public Int(Integer value) {
        super(value);
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
    public int compareTo(AObject object) {
        return object instanceof Int ? getValue().compareTo(((Int) object).getValue()) : super.compareTo(object);
    }

    @Override
    public LinkedHashSet<Const> getRequiredConsts() {
        return new LinkedHashSet<>();
    }

    @Override
    public Int accept(IPrimer primer) {
        return primer.visit(this);
    }

}
