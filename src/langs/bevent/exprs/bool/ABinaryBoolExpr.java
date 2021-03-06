package langs.bevent.exprs.bool;

import langs.bevent.exprs.AExpr;

import java.util.Arrays;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 00:17
 */
public abstract class ABinaryBoolExpr<Operand extends AExpr, Primed extends ABinaryBoolExpr<Operand, Primed>> extends ANaryBoolExpr<Operand, Primed> {

    private final Operand left;
    private final Operand right;

    public ABinaryBoolExpr(Operand left, Operand right) {
        super(Arrays.asList(left, right), 2);
        this.left = left;
        this.right = right;
    }

    public final Operand getLeft() {
        return left;
    }

    public final Operand getRight() {
        return right;
    }

}
