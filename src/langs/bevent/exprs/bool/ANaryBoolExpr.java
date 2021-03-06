package langs.bevent.exprs.bool;

import errors.InvalidNumberOfOperandsError;
import langs.bevent.exprs.AExpr;
import langs.bevent.exprs.arith.Const;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 00:02
 */
public abstract class ANaryBoolExpr<Operand extends AExpr, Primed extends ANaryBoolExpr<Operand, Primed>> extends ABoolExpr<Primed> {

    private final List<Operand> operands;

    public ANaryBoolExpr(List<Operand> operands, int minOperands) {
        this.operands = operands;
        if (operands.size() < minOperands) {
            throw new InvalidNumberOfOperandsError(this, minOperands);
        }
    }

    public final List<Operand> getOperands() {
        return operands;
    }

    @Override
    public final LinkedHashSet<Const> getRequiredConsts() {
        return getOperands().stream().map(AExpr::getRequiredConsts).flatMap(Collection::stream).collect(Collectors.toCollection(LinkedHashSet::new));
    }

}
