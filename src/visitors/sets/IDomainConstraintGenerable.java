package visitors.sets;

import langs.bevent.exprs.bool.ABoolExpr;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 11:45
 */
public interface IDomainConstraintGenerable {

    ABoolExpr accept(IDomainConstraintGenerator generator);

}
