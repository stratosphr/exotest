package langs.bevent.exprs.defs;

import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.sets.AFiniteSetExpr;
import visitors.formatters.object.IObjectFormatter;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:16
 */
public final class VarDef extends ADef {

    private final Var var;

    public VarDef(Var var, AFiniteSetExpr domain) {
        super(var.getName(), domain);
        this.var = var;
    }

    public Var getVar() {
        return var;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
