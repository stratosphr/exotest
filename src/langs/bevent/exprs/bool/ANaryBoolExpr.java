package langs.bevent.exprs.bool;

import errors.InvalidNumberOfArgumentsError;
import langs.bevent.exprs.AExpr;

import java.util.List;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 00:02
 */
public abstract class ANaryBoolExpr<Operand extends AExpr> extends ABoolExpr {

    private final List<Operand> operands;

    public ANaryBoolExpr(List<Operand> operands, int minOperands) {
        this.operands = operands;
        if (operands.size() < minOperands) {
            throw new InvalidNumberOfArgumentsError(this, minOperands);
        }
    }

    public final List<Operand> getOperands() {
        return operands;
    }

}
