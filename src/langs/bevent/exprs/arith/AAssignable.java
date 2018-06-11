package langs.bevent.exprs.arith;

import visitors.decoders.IModelValueDecodable;
import visitors.primer.IPrimer;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 15:43
 */
public abstract class AAssignable<Primed extends AAssignable<Primed>> extends AArithExpr<Primed> implements IModelValueDecodable {

    private final String name;

    public AAssignable(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    @Override
    public abstract Primed accept(IPrimer primer);

}
