package langs.bevent.exprs.defs;

import langs.bevent.exprs.sets.AFiniteSetExpr;
import langs.bevent.exprs.sets.ASetExpr;
import visitors.formatters.object.IObjectFormatter;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:16
 */
public final class FunDef extends ADef {

    private final ASetExpr codomain;

    public FunDef(String name, AFiniteSetExpr domain, ASetExpr codomain) {
        super(name, domain);
        this.codomain = codomain;
    }

    public ASetExpr getCodomain() {
        return codomain;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
