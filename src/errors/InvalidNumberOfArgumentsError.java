package errors;

import langs.AObject;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:57
 */
public class InvalidNumberOfArgumentsError extends Error {

    public InvalidNumberOfArgumentsError(AObject object, int minOperands) {
        super("The \"" + object.getClass().getSimpleName() + "\" operator expects at least " + minOperands + " operands.");
    }

    public InvalidNumberOfArgumentsError(AObject object, int minOperands, int maxOperands) {
        super("The \"" + object.getClass().getSimpleName() + "\" operator expects at least " + minOperands + " operands and at most " + maxOperands + " operands.");
    }

}
