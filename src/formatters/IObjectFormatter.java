package formatters;

import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.*;
import langs.bevent.exprs.bool.defs.ConstDef;
import langs.bevent.exprs.bool.defs.VarDef;
import langs.bevent.exprs.sets.Set;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:48
 */
public interface IObjectFormatter {

    String visit(Int anInt);

    String visit(Const aConst);

    String visit(Var var);

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

    <Value extends AArithExpr> String visit(In<Value> in);

    String visit(Equals equals);

    String visit(ConstDef constDef);

    String visit(VarDef varDef);

    String visit(Set set);

}
