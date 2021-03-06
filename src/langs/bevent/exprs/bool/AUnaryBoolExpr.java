package langs.bevent.exprs.bool;

import langs.bevent.exprs.AExpr;

import java.util.Collections;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 00:11
 */
public abstract class AUnaryBoolExpr<Operand extends AExpr, Primed extends AUnaryBoolExpr<Operand, Primed>> extends ANaryBoolExpr<Operand, Primed> {

    private final Operand operand;

    public AUnaryBoolExpr(Operand operand) {
        super(Collections.singletonList(operand), 1);
        this.operand = operand;
    }

    public final Operand getOperand() {
        return operand;
    }

}
