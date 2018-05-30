package langs.bevent.exprs.arith;

import com.microsoft.z3.ArithExpr;
import langs.bevent.exprs.AExpr;
import visitors.encoders.smt.ISMTEncodable;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:52
 */
public abstract class AArithExpr extends AExpr implements ISMTEncodable<ArithExpr> {
}
