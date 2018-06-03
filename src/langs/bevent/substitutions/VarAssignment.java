package langs.bevent.substitutions;

import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.arith.AAssignable;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.bool.ABoolExpr;
import langs.bevent.exprs.bool.Equals;
import visitors.formatters.object.IObjectFormatter;

import java.util.Set;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 19:52
 */
public final class VarAssignment extends AAssignment<Var> {

    public VarAssignment(Var var, AArithExpr value) {
        super(var, value);
    }

    public Var getVar() {
        return getAssignable();
    }

    @Override
    public ABoolExpr getPrdInMultipleAssignments() {
        return new Equals(new Var(getVar().getName() + "_"), getValue());
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
