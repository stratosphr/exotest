package visitors.computers;

import langs.bevent.exprs.arith.AArithExpr;
import langs.bevent.exprs.sets.NamedSet;
import langs.bevent.exprs.sets.Range;
import langs.bevent.exprs.sets.Set;

import java.util.LinkedHashSet;

/**
 * Created by gvoiron on 08/06/18.
 * Time : 21:26
 */
public interface ISetElementsComputer {

    LinkedHashSet<AArithExpr> visit(Range range);

    LinkedHashSet<AArithExpr> visit(Set set);

    LinkedHashSet<AArithExpr> visit(NamedSet namedSet);

}
