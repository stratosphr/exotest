package errors;

import com.microsoft.z3.Status;
import langs.bevent.exprs.bool.ABoolExpr;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 15:14
 */
public final class ModelUnavailableError extends Error {

    public ModelUnavailableError(Status status, ABoolExpr formula) {
        super(status == Status.UNKNOWN ? "The satisfiability of the formula \"" + formula + "\" is unknown. Therefore, no model can be produced." : "The formula \"" + formula + "\" is unsatisfiable. Therefore, no model can be produced.");
    }

}
