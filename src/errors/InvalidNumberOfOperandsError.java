package errors;

import langs.bevent.exprs.AExpr;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:57
 */
public final class InvalidNumberOfOperandsError extends Error {

    public InvalidNumberOfOperandsError(AExpr object, int minOperands) {
        super("The \"" + object.getClass().getSimpleName() + "\" operator expects at least " + minOperands + " operands.");
    }

    public InvalidNumberOfOperandsError(AExpr object, int minOperands, int maxOperands) {
        super("The \"" + object.getClass().getSimpleName() + "\" operator expects at least " + minOperands + " operands and at most " + maxOperands + " operands.");
    }

}
