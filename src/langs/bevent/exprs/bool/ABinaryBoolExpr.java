package langs.bevent.exprs.bool;

import langs.bevent.exprs.AExpr;
import langs.bevent.exprs.arith.Const;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public final Operand getLeft() {
        return left;
    }

    public final Operand getRight() {
        return right;
    }

    @Override
    public final List<Const> getRequiredConsts() {
        return Stream.of(getLeft().getRequiredConsts(), getRight().getRequiredConsts()).flatMap(Collection::stream).collect(Collectors.toList());
    }

}
