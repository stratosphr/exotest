package langs.bevent.exprs.sets;

import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.bool.ABoolExpr;
import visitors.computers.ISetElementsComputer;
import visitors.formatters.object.IObjectFormatter;
import visitors.generators.sets.IDomainConstraintGenerator;

import java.util.Collections;
import java.util.List;

/**
 * Created by gvoiron on 08/06/18.
 * Time : 10:58
 */
public final class NamedSet extends AFiniteSetExpr {

    private final String name;

    public NamedSet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public ABoolExpr accept(IDomainConstraintGenerator generator) {
        return generator.visit(this);
    }

    @Override
    public List<AArithExpr> accept(ISetElementsComputer computer) {
        return computer.visit(this);
    }

    @Override
    public List<Const> getRequiredConsts() {
        return Collections.emptyList();
    }

}
