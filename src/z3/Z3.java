package z3;

import com.microsoft.z3.Context;
import com.microsoft.z3.Solver;
import com.microsoft.z3.Status;
import langs.bevent.exprs.bool.ABoolExpr;
import visitors.encoders.z3.Z3Encoder;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 22:37
 */
public final class Z3 {

    private final static Context context = new Context();
    private final static Solver solver = context.mkSimpleSolver();

    public static Z3Result checkSAT(ABoolExpr formula) {
        solver.add(formula.accept(new Z3Encoder(context)));
        Status status = solver.check();
        System.out.println(solver);
        return new Z3Result(formula, status, context, solver);
    }

}
