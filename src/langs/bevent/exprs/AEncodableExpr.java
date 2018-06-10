package langs.bevent.exprs;

import com.microsoft.z3.Expr;
import visitors.encoders.z3.IZ3Encodable;

/**
 * Created by gvoiron on 10/06/18.
 * Time : 23:18
 */
public abstract class AEncodableExpr<Encoded extends Expr> extends AExpr implements IZ3Encodable<Encoded> {
}
