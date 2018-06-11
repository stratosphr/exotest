package langs.bevent.substitutions;

import errors.InvalidNumberOfQuantifiedVarsError;
import langs.bevent.exprs.arith.AAssignable;
import langs.bevent.exprs.bool.ABoolExpr;
import langs.bevent.exprs.bool.Exists;
import langs.bevent.exprs.bool.VarIn;
import visitors.formatters.object.IObjectFormatter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 11/06/18.
 * Time : 02:42
 */
public final class Any extends ASubstitution {

    private final ABoolExpr condition;
    private final List<VarIn> quantifiedVars;
    private final ASubstitution substitution;

    public Any(ABoolExpr condition, ASubstitution substitution, VarIn... quantifiedVars) {
        if (quantifiedVars.length == 0) {
            throw new InvalidNumberOfQuantifiedVarsError(this);
        }
        this.condition = condition;
        this.substitution = substitution;
        this.quantifiedVars = Arrays.stream(quantifiedVars).distinct().collect(Collectors.toList());
    }

    @Override
    public ABoolExpr getPrd(Set<AAssignable> assignables) {
        return new Exists(new Select(condition, substitution).getPrd(assignables), quantifiedVars.toArray(new VarIn[0]));
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    public ABoolExpr getCondition() {
        return condition;
    }

    public ASubstitution getSubstitution() {
        return substitution;
    }

    public List<VarIn> getQuantifiedVarsDefs() {
        return quantifiedVars;
    }

}
