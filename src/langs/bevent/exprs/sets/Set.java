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
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:25
 */
public final class Set extends AFiniteSetExpr {

    private final LinkedHashSet<AArithExpr> elements;

    public Set(AArithExpr... elements) {
        this.elements = Arrays.stream(elements).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<AArithExpr> getElements() {
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
    public LinkedHashSet<AArithExpr> accept(ISetElementsComputer computer) {
        return computer.visit(this);
    }

    @Override
    public LinkedHashSet<Const> getRequiredConsts() {
        return getElements().stream().map(AExpr::getRequiredConsts).flatMap(Collection::stream).collect(Collectors.toCollection(LinkedHashSet::new));
    }

}
