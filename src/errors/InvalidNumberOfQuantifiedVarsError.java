package errors;

import langs.bevent.exprs.bool.AQuantifier;

/**
 * Created by gvoiron on 04/06/18.
 * Time : 12:25
 */
public final class InvalidNumberOfQuantifiedVarsError extends Error {

    public InvalidNumberOfQuantifiedVarsError(AQuantifier quantifier) {
        super("The \"" + quantifier.getClass().getSimpleName().toLowerCase() + "\" quantifier requires at least one quantified variable.");
    }

}
