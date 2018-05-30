package langs.bevent.exprs.defs;

import visitors.formatters.object.IObjectFormatter;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.sets.ASetExpr;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:16
 */
public final class VarDef extends ADef {

    private final Var var;

    public VarDef(Var var, ASetExpr domain) {
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
