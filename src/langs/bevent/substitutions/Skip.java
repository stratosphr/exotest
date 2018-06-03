package langs.bevent.substitutions;

import langs.bevent.exprs.arith.AAssignable;
import langs.bevent.exprs.bool.ABoolExpr;
import langs.bevent.exprs.bool.True;
import visitors.formatters.object.IObjectFormatter;

import java.util.Set;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 20:12
 */
public final class Skip extends ASubstitution {

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public ABoolExpr getPrd(Set<AAssignable> assignables) {
        return new True();
    }

}
