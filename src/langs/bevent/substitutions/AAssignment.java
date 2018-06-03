package langs.bevent.substitutions;

import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.arith.AAssignable;
import langs.bevent.exprs.bool.ABoolExpr;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 19:54
 */
public abstract class AAssignment<Assignable extends AAssignable> extends ASubstitution {

    private final Assignable assignable;
    private final AArithExpr value;

    public AAssignment(Assignable assignable, AArithExpr value) {
        this.assignable = assignable;
        this.value = value;
    }

    public abstract ABoolExpr getPrdInMultipleAssignments();

    public final Assignable getAssignable() {
        return assignable;
    }

    public final AArithExpr getValue() {
        return value;
    }

}
