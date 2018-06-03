package langs.bevent.substitutions;

import langs.bevent.exprs.arith.AAssignable;
import langs.bevent.exprs.bool.ABoolExpr;
import visitors.formatters.object.IObjectFormatter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 20:02
 */
public final class Parallel extends ASubstitution {

    private final List<ASubstitution> substitutions;

    public Parallel(ASubstitution... substitutions) {
        this.substitutions = Arrays.asList(substitutions);
    }

    public List<ASubstitution> getSubstitutions() {
        return substitutions;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public ABoolExpr getPrd(Set<AAssignable> assignables) {
        throw new Error("Not implemented yet.");
    }

}
