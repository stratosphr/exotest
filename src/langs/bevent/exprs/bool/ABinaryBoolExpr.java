package langs.bevent.exprs.bool;

import langs.bevent.exprs.AExpr;

import java.util.Arrays;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 00:17
 */
public abstract class ABinaryBoolExpr<Operand extends AExpr> extends ANaryBoolExpr<Operand> {

    private final Operand left;
    private final Operand right;

    public ABinaryBoolExpr(Operand left, Operand right) {
        super(Arrays.asList(left, right), 2);
        this.left = left;
        this.right = right;
    }

    public Operand getLeft() {
        return left;
    }

    public Operand getRight() {
        return right;
    }

}
