package langs.bevent.exprs.bool;

import errors.InvalidNumberOfQuantifiedVarsError;
import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.arith.Var;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 23:05
 */
public abstract class AQuantifier<Primed extends AQuantifier<Primed>> extends ABoolExpr<Primed> {

    private final ABoolExpr expr;
    private final LinkedHashSet<VarIn> quantifiedVarsDefs;
    private final LinkedHashSet<Var> quantifiedVars;

    public AQuantifier(ABoolExpr expr, VarIn[] quantifiedVarsDefs) {
        this.expr = expr;
        this.quantifiedVarsDefs = new LinkedHashSet<>(Arrays.asList(quantifiedVarsDefs));
        this.quantifiedVars = Arrays.stream(quantifiedVarsDefs).map(AIn::getExpr).collect(Collectors.toCollection(LinkedHashSet::new));
        if (quantifiedVarsDefs.length == 0) {
            throw new InvalidNumberOfQuantifiedVarsError(this);
        }
    }

    public final ABoolExpr getExpr() {
        return expr;
    }

    public final LinkedHashSet<VarIn> getQuantifiedVarsDefs() {
        return quantifiedVarsDefs;
    }

    public final LinkedHashSet<Var> getQuantifiedVars() {
        return quantifiedVars;
    }

    @Override
    public final LinkedHashSet<Const> getRequiredConsts() {
        return getExpr().getRequiredConsts();
    }

}
