package langs.bevent.exprs.sets;

import langs.bevent.exprs.arith.AArithExpr;
import visitors.computers.IElementsComputer;

import java.util.List;

/**
 * Created by gvoiron on 04/06/18.
 * Time : 15:03
 */
public abstract class AFiniteSetExpr extends ASetExpr {

    public abstract List<AArithExpr> accept(IElementsComputer computer);

}
