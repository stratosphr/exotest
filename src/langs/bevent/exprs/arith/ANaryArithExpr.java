package langs.bevent.exprs.arith;

import errors.InvalidNumberOfArgumentsError;
import langs.bevent.exprs.AExpr;

import java.util.List;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:55
 */
public abstract class ANaryArithExpr<Operand extends AExpr> extends AArithExpr {

    private final List<Operand> operands;

    public ANaryArithExpr(List<Operand> operands, int minOperands) {
        this.operands = operands;
        if (operands.size() < minOperands) {
            throw new InvalidNumberOfArgumentsError(this, minOperands);
        }
    }

    public List<Operand> getOperands() {
        return operands;
    }

}
