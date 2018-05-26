package langs.bevent.exprs.arith;

import langs.bevent.exprs.AExpr;

import java.util.Arrays;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 18:19
 */
public abstract class ABinaryArithExpr<Operand extends AExpr> extends ANaryArithExpr<Operand> {

    private final Operand left;
    private final Operand right;

    public ABinaryArithExpr(Operand left, Operand right) {
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
