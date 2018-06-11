package errors;

/**
 * Created by gvoiron on 11/06/18.
 * Time : 12:08
 */
public final class InvalidNumberOfChoicesError extends Error {

    public InvalidNumberOfChoicesError() {
        super("The CHOICE substitution requires at least one choice substitution (none given).");
    }

}
