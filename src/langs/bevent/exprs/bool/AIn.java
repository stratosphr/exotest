package langs.bevent.exprs.bool;

import langs.bevent.exprs.sets.ASetExpr;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:35
 */
public abstract class AIn<Expr> extends ABoolExpr {

    private final Expr expr;
    private final ASetExpr domain;

    public AIn(Expr expr, ASetExpr domain) {
        this.expr = expr;
        this.domain = domain;
    }

    public final Expr getExpr() {
        return expr;
    }

    public final ASetExpr getDomain() {
        return domain;
    }

}
