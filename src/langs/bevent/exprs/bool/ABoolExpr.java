package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.AExpr;
import visitors.encoders.smt.ISMTEncodable;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 00:02
 */
public abstract class ABoolExpr extends AExpr implements ISMTEncodable<BoolExpr> {

}
