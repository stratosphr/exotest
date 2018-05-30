package visitors.encoders.z3;

import com.microsoft.z3.Expr;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 10:36
 */
public interface IZ3Encodable<Return extends Expr> {

    Return accept(IZ3Encoder generator);

}
