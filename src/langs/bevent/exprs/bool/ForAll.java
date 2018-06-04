package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.defs.VarDef;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;

import java.util.Arrays;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 23:11
 */
public final class ForAll extends AQuantifier {

    public ForAll(ABoolExpr expr, VarDef... quantifiedVarsDefs) {
        super(new Implies(new And(Arrays.stream(quantifiedVarsDefs).map(varDef -> new In<>(varDef.getVar(), varDef.getDomain())).toArray(ABoolExpr[]::new)), expr), quantifiedVarsDefs);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public BoolExpr accept(IZ3Encoder generator) {
        return generator.visit(this);
    }

}
