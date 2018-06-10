package langs.bevent.exprs;

import com.microsoft.z3.Expr;
import visitors.encoders.z3.IZ3Encodable;
import visitors.primer.IPrimable;
import visitors.primer.IPrimer;

/**
 * Created by gvoiron on 10/06/18.
 * Time : 23:54
 */
public abstract class AEncodableExpr<Encoded extends Expr, Primed> extends AExpr implements IZ3Encodable<Encoded>, IPrimable<Primed> {

    @Override
    public abstract Primed accept(IPrimer primer);

}
