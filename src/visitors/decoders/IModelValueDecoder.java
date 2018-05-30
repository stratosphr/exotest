package visitors.decoders;

import langs.bevent.exprs.arith.AValue;
import langs.bevent.exprs.arith.Fun;
import langs.bevent.exprs.arith.Var;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 15:44
 */
public interface IModelValueDecoder {

    AValue visit(Var var);

    AValue visit(Fun fun);

}
