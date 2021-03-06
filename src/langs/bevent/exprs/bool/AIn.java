package langs.bevent.exprs.bool;

import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.sets.ASetExpr;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:35
 */
public abstract class AIn<Expr extends AArithExpr<Expr>, Primed extends AIn<Expr, Primed>> extends ABoolExpr<Primed> {

    private final Expr expr;
    private final ASetExpr domain;

    public AIn(Expr expr, ASetExpr domain) {
        this.expr = expr;
        this.domain = domain;
    }

    public Expr getExpr() {
        return expr;
    }

    public final ASetExpr getDomain() {
        return domain;
    }

}
