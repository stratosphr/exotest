package langs.bevent.exprs.defs;

import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.sets.AFiniteSetExpr;
import visitors.formatters.object.IObjectFormatter;

import java.util.LinkedHashSet;

/**
 * Created by gvoiron on 08/06/18.
 * Time : 10:20
 */
public final class SetDef extends ADef {

    public SetDef(String name, AFiniteSetExpr domain) {
        super(name, domain);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public LinkedHashSet<Const> getRequiredConsts() {
        return getDomain().getRequiredConsts();
    }

}
