package langs.bevent.exprs.arith;

import errors.InvalidNumberOfOperandsError;
import langs.bevent.exprs.AExpr;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:55
 */
public abstract class ANaryArithExpr<Operand extends AExpr, Primed extends ANaryArithExpr<Operand, Primed>> extends AArithExpr<Primed> {

    private final List<Operand> operands;

    public ANaryArithExpr(List<Operand> operands, int minOperands) {
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
