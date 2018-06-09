package visitors.computers;

import langs.bevent.exprs.arith.AArithExpr;

import java.util.List;

/**
 * Created by gvoiron on 09/06/18.
 * Time : 18:33
 */
public interface ISetElementsComputable {

    List<AArithExpr> accept(ISetElementsComputer computer);

}
