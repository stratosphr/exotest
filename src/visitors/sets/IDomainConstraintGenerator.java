package visitors.sets;

import langs.bevent.exprs.bool.ABoolExpr;
import langs.bevent.exprs.sets.Range;
import langs.bevent.exprs.sets.Set; /**
 * Created by gvoiron on 30/05/18.
 * Time : 11:46
 */
public interface IDomainConstraintGenerator {

    ABoolExpr visit(Set set);

    ABoolExpr visit(Range range);

}
