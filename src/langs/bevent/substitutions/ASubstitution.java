package langs.bevent.substitutions;

import langs.AObject;
import langs.bevent.exprs.arith.AAssignable;
import langs.bevent.exprs.bool.ABoolExpr;

import java.util.Set;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 19:51
 */
public abstract class ASubstitution extends AObject {

    public abstract ABoolExpr getPrd(Set<AAssignable> assignables);

}
