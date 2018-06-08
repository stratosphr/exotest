package errors;

/**
 * Created by gvoiron on 08/06/18.
 * Time : 11:23
 */
public final class UninitializedMachineError extends Error {

    public UninitializedMachineError() {
        super("No machine built yet.");
    }

}
