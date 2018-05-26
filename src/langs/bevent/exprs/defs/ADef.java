package langs.bevent.exprs.defs;

import langs.bevent.exprs.AExpr;
import langs.bevent.exprs.sets.ASetExpr;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:16
 */
public abstract class ADef extends AExpr {

    private final String name;
    private final ASetExpr domain;

    public ADef(String name, ASetExpr domain) {
        this.name = name;
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public ASetExpr getDomain() {
        return domain;
    }

}
