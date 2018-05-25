package langs.bevent.exprs.arith;

import formatters.IObjectFormatter;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:51
 */
public final class Int extends AArithExpr {

    private Integer value;

    public Int(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
