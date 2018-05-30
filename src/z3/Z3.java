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

    public static void checkSAT(ABoolExpr expr) {
        solver.add(expr.accept(new Z3Encoder(context)));
        Status status = solver.check();
        System.out.println(status);
        if (status == Status.SATISFIABLE) {
            System.out.println(solver.getModel());
        }
    }

}
