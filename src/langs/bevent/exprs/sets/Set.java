package langs.bevent.exprs.sets;

import langs.bevent.exprs.AExpr;
import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.bool.ABoolExpr;
import visitors.computers.ISetElementsComputer;
import visitors.formatters.object.IObjectFormatter;
import visitors.generators.sets.IDomainConstraintGenerator;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:25
 */
public final class Set extends AFiniteSetExpr {

    private final List<AArithExpr> elements;

    public Set(AArithExpr... elements) {
        this.elements = Arrays.stream(elements).distinct().collect(Collectors.toList());
    }

    public List<AArithExpr> getElements() {
        return elements;
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
        return getElements().stream().map(AExpr::getRequiredConsts).flatMap(Collection::stream).collect(Collectors.toList());
    }

}
