package z3;

import com.microsoft.z3.Context;
import langs.bevent.exprs.arith.*;
import visitors.decoders.IModelValueDecoder;
import visitors.encoders.z3.Z3Encoder;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 15:19
 */
public final class Model extends TreeMap<AAssignable, AValue> implements IModelValueDecoder {

    private final com.microsoft.z3.Model model;
    private final Context context;

    public Model(com.microsoft.z3.Model model, Context context, List<AAssignable> assignables) {
        this.model = model;
        this.context = context;
        System.out.println(Arrays.toString(model.getConstDecls()));
        assignables.forEach(var -> put(var, var.accept(this)));
    }

    @Override
    public AValue visit(Var var) {
        return new Int(Integer.parseInt(model.eval(context.mkIntConst(var.getName()), true).toString()));
    }

    @Override
    public AValue visit(Fun fun) {
        return new Int(Integer.parseInt(model.eval(context.mkApp(context.mkFuncDecl(fun.getName(), context.mkIntSort(), context.mkIntSort()), fun.getParam().accept(new Z3Encoder(context))), true).toString()));
    }

}
