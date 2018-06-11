package visitors.computers;

import langs.bevent.exprs.arith.AArithExpr;

import java.util.LinkedHashSet;

/**
 * Created by gvoiron on 09/06/18.
 * Time : 18:33
 */
public interface ISetElementsComputable {

    LinkedHashSet<AArithExpr> accept(ISetElementsComputer computer);

}
