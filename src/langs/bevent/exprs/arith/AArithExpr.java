package langs.bevent.exprs.arith;

import com.microsoft.z3.ArithExpr;
import langs.bevent.exprs.AExpr;
import visitors.encoders.z3.IZ3Encodable;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:52
 */
public abstract class AArithExpr extends AExpr implements IZ3Encodable<ArithExpr> {
}
