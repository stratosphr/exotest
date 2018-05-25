package formatters;

import langs.bevent.exprs.arith.*;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:48
 */
public interface IObjectFormatter {

    String visit(Int anInt);

    String visit(Plus plus);

    String visit(Minus minus);

    String visit(Times times);

    String visit(Div div);

}
