package langs.bevent.exprs.defs;

import langs.bevent.exprs.AExpr;
import langs.bevent.exprs.sets.AFiniteSetExpr;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:16
 */
public abstract class ADef extends AExpr {

    private final String name;
    private final AFiniteSetExpr domain;

    public ADef(String name, AFiniteSetExpr domain) {
        this.name = name;
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public AFiniteSetExpr getDomain() {
        return domain;
    }

}
