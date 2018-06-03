package langs.bevent.substitutions;

import langs.bevent.exprs.arith.AAssignable;
import langs.bevent.exprs.bool.ABoolExpr;
import langs.bevent.exprs.bool.And;
import visitors.formatters.object.IObjectFormatter;

import java.util.Set;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 20:06
 */
public final class Select extends ASubstitution {

    private final ABoolExpr condition;
    private final ASubstitution substitution;

    public Select(ABoolExpr condition, ASubstitution substitution) {
        this.condition = condition;
        this.substitution = substitution;
    }

    public ABoolExpr getCondition() {
        return condition;
    }

    public ASubstitution getSubstitution() {
        return substitution;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public ABoolExpr getPrd(Set<AAssignable> assignables) {
        return new And(getCondition(), getSubstitution().getPrd(assignables));
    }

}
