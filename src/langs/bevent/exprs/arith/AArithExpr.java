package langs.bevent.exprs.arith;

import com.microsoft.z3.ArithExpr;
import langs.bevent.exprs.AEncodableExpr;
import visitors.encoders.z3.IZ3Encoder;
import visitors.primer.IPrimer;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:52
 */
public abstract class AArithExpr<Primed extends AArithExpr<Primed>> extends AEncodableExpr<ArithExpr, Primed> {

    @Override
    public abstract ArithExpr accept(IZ3Encoder generator);

    @Override
    public abstract Primed accept(IPrimer primer);

}
