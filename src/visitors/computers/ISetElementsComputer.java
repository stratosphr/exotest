package visitors.computers;

import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.sets.NamedSet;
import langs.bevent.exprs.sets.Range;
import langs.bevent.exprs.sets.Set;

import java.util.List;

/**
 * Created by gvoiron on 08/06/18.
 * Time : 21:26
 */
public interface ISetElementsComputer {

    List<AArithExpr> visit(Range range);

    List<AArithExpr> visit(Set set);

    List<AArithExpr> visit(NamedSet namedSet);

}
