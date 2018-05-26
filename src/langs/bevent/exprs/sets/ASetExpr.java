package langs.bevent.exprs.sets;

import langs.bevent.exprs.AExpr;
import langs.bevent.exprs.arith.AArithExpr;

import java.util.LinkedHashSet;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:07
 */
public abstract class ASetExpr extends AExpr {

    private final LinkedHashSet<AArithExpr> elements;

    public ASetExpr(LinkedHashSet<AArithExpr> elements) {
        this.elements = elements;
    }

    public final LinkedHashSet<AArithExpr> getElements() {
        return elements;
    }

}
