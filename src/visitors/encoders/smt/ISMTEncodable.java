package visitors.encoders.smt;

import com.microsoft.z3.Expr;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 10:36
 */
public interface ISMTEncodable<Return extends Expr> {

    Return accept(ISMTEncoder generator);

}
