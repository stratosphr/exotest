package langs.bevent.exprs.defs;

import visitors.formatters.object.IObjectFormatter;
import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.sets.Set;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:16
 */
public final class ConstDef extends ADef {

    private final Const aConst;
    private final AArithExpr value;

    public ConstDef(Const aConst, AArithExpr value) {
        super(aConst.getName(), new Set(value));
        this.aConst = aConst;
        this.value = value;
    }

    public Const getConst() {
        return aConst;
    }

    public AArithExpr getValue() {
        return value;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
