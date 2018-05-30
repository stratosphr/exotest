package visitors.decoders;

import langs.bevent.exprs.arith.AValue;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 15:46
 */
public interface IModelValueDecodable {

    AValue accept(IModelValueDecoder decoder);

}
