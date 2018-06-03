package langs.bevent.substitutions;

import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.arith.AAssignable;
import langs.bevent.exprs.arith.Fun;
import langs.bevent.exprs.bool.ABoolExpr;
import langs.bevent.exprs.bool.Equals;
import visitors.formatters.object.IObjectFormatter;

import java.util.Set;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 19:59
 */
public final class FunAssignment extends AAssignment<Fun> {

    public FunAssignment(Fun assignable, AArithExpr value) {
        super(assignable, value);
    }

    public Fun getFun() {
        return getAssignable();
    }

    @Override
    public ABoolExpr getPrdInMultipleAssignments() {
        return new Equals(new Fun(getFun().getName() + "_", getFun().getParam()), getValue());
    }

    @Override
    public ABoolExpr getPrd(Set<AAssignable> assignables) {
        return new MultipleAssignment(this).getPrd(assignables);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
