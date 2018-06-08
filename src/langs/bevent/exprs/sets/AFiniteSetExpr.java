package langs.bevent.exprs.sets;

import langs.bevent.exprs.bool.ABoolExpr;

import java.util.List;

/**
 * Created by gvoiron on 04/06/18.
 * Time : 15:03
 */
public abstract class AFiniteSetExpr<Elements> extends ASetExpr {

    public abstract List<Elements> computeElementsValues(ABoolExpr constraint);

}
