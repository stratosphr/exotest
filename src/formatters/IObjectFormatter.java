package formatters;

import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.*;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:48
 */
public interface IObjectFormatter {

    String visit(Int anInt);

    String visit(Var var);

    String visit(Const aConst);

    String visit(Plus plus);

    String visit(Minus minus);

    String visit(Times times);

    String visit(Div div);

    String visit(False aFalse);

    String visit(True aTrue);

    String visit(Not not);

    String visit(And and);

    String visit(Or or);

    String visit(Implies implies);

    String visit(Equiv equiv);

}
