package z3;

import com.microsoft.z3.Context;
import com.microsoft.z3.Solver;
import com.microsoft.z3.Status;
import errors.ModelUnavailableError;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.bool.ABoolExpr;

import java.util.List;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 15:08
 */
public final class Z3Result {

    private ABoolExpr formula;
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

    public Model getModel(List<Var> vars) {
        if (status == Status.SATISFIABLE) {
            return new Model(solver.getModel(), context, vars);
        } else {
            throw new ModelUnavailableError(status, formula);
        }
    }

}
