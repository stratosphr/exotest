package visitors.formatters.object;

import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.*;
import langs.bevent.exprs.defs.ConstDef;
import langs.bevent.exprs.defs.FunDef;
import langs.bevent.exprs.defs.VarDef;
import langs.bevent.exprs.sets.Range;
import langs.bevent.exprs.sets.Set;
import langs.bevent.substitutions.*;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:48
 */
public interface IObjectFormatter {

    String visit(Int anInt);

    String visit(Const aConst);

    String visit(Var var);

    String visit(Fun fun);

    String visit(Plus plus);

    String visit(Minus minus);

    String visit(Times times);

    String visit(Div div);

    @SuppressWarnings("SameReturnValue")
    String visit(False aFalse);

    @SuppressWarnings("SameReturnValue")
    String visit(True aTrue);

    String visit(Not not);

    String visit(And and);

    String visit(Or or);

    String visit(Implies implies);

    String visit(Equiv equiv);

    <Value extends AArithExpr> String visit(In<Value> in);

    String visit(Equals equals);

    String visit(NEq nEq);

    String visit(LT lt);

    String visit(LEq lEq);

    String visit(GT gt);

    String visit(GEq gEq);

    String visit(ConstDef constDef);

    String visit(VarDef varDef);

    String visit(FunDef funDef);

    String visit(Set set);

    String visit(Range range);

    @SuppressWarnings("SameReturnValue")
    String visit(Skip skip);

    String visit(VarAssignment varAssignment);

    String visit(FunAssignment funAssignment);

    String visit(MultipleAssignment multipleAssignment);

    String visit(Select select);

    String visit(Parallel parallel);

}
