package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.AExpr;
import visitors.encoders.z3.IZ3Encodable;
import visitors.encoders.z3.IZ3Encoder;
import visitors.primer.IPrimable;
import visitors.primer.IPrimer;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 00:02
 */
public abstract class ABoolExpr<Primed extends ABoolExpr> extends AExpr implements IZ3Encodable<BoolExpr>, IPrimable<Primed> {

    @Override
    public abstract BoolExpr accept(IZ3Encoder generator);

    @Override
    public abstract Primed accept(IPrimer primer);

}
