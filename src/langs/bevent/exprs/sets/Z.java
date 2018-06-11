package langs.bevent.exprs.sets;

import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.bool.ABoolExpr;
import visitors.formatters.object.IObjectFormatter;
import visitors.generators.sets.IDomainConstraintGenerator;

import java.util.LinkedHashSet;

/**
 * Created by gvoiron on 04/06/18.
 * Time : 13:24
 */
public final class Z extends ASetExpr {

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public ABoolExpr accept(IDomainConstraintGenerator generator) {
        return generator.visit(this);
    }

    @Override
    public LinkedHashSet<Const> getRequiredConsts() {
        return new LinkedHashSet<>();
    }

}
