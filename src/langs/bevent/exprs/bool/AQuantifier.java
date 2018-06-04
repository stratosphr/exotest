package langs.bevent.exprs.bool;

import errors.InvalidNumberOfQuantifiedVarsError;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.defs.VarDef;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 23:05
 */
public abstract class AQuantifier extends ABoolExpr {

    private final ABoolExpr expr;
    private final List<VarDef> quantifiedVarsDefs;
    private final List<Var> quantifiedVars;

    public AQuantifier(ABoolExpr expr, VarDef... quantifiedVarsDefs) {
        this.expr = expr;
        this.quantifiedVarsDefs = Arrays.asList(quantifiedVarsDefs);
        this.quantifiedVars = Arrays.stream(quantifiedVarsDefs).map(VarDef::getVar).collect(Collectors.toList());
        if (quantifiedVarsDefs.length == 0) {
            throw new InvalidNumberOfQuantifiedVarsError(this);
        }
    }

    public ABoolExpr getExpr() {
        return expr;
    }

    public List<VarDef> getQuantifiedVarsDefs() {
        return quantifiedVarsDefs;
    }

    public List<Var> getQuantifiedVars() {
        return quantifiedVars;
    }

}
