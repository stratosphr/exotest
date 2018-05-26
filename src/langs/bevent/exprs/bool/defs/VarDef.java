package langs.bevent.exprs.bool.defs;

import formatters.IObjectFormatter;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.sets.ASetExpr;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:16
 */
public final class VarDef extends ADef<Var> {

    public VarDef(Var var, ASetExpr domain) {
        super(var, domain);
    }

    public Var getVar() {
        return super.getExpr();
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
