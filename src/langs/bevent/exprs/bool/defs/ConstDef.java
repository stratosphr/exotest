package langs.bevent.exprs.bool.defs;

import formatters.IObjectFormatter;
import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.sets.Set;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:16
 */
public final class ConstDef extends ADef<Const> {

    private final AArithExpr value;

    public ConstDef(Const aConst, AArithExpr value) {
        super(aConst, new Set(value));
        this.value = value;
    }

    public AArithExpr getValue() {
        return value;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
