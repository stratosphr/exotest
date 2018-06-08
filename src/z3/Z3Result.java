package z3;

import com.microsoft.z3.Context;
import com.microsoft.z3.Solver;
import com.microsoft.z3.Status;
import errors.ModelUnavailableError;
import langs.bevent.exprs.arith.AAssignable;
import langs.bevent.exprs.bool.ABoolExpr;

import java.util.List;

import static com.microsoft.z3.Status.*;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 15:08
 */
public final class Z3Result {

    private final ABoolExpr formula;
    private final Status status;
    private final Context context;
    private final Solver solver;

    public Z3Result(ABoolExpr formula, Status status, Context context, Solver solver) {
        this.formula = formula;
        this.status = status;
        this.context = context;
        this.solver = solver;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isSAT() {
        return status == SATISFIABLE;
    }

    public boolean isUNKNOWN() {
        return status == UNKNOWN;
    }

    public boolean isUNSAT() {
        return status == UNSATISFIABLE;
    }

    public Model getModel(List<? extends AAssignable> vars) {
        if (status == SATISFIABLE) {
            return new Model(solver.getModel(), context, vars);
        } else {
            throw new ModelUnavailableError(status, formula);
        }
    }

}
