package z3;

import com.microsoft.z3.Context;
import langs.bevent.exprs.arith.Int;
import langs.bevent.exprs.arith.Var;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 15:19
 */
public final class Model extends TreeMap<Var, Int> {

    public Model(com.microsoft.z3.Model model, Context context, List<Var> vars) {
        vars.forEach(var -> put(var, new Int(Integer.parseInt(model.eval(context.mkIntConst(var.getName()), true).toString()))));
    }

}
