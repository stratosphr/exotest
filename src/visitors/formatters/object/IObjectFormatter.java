package visitors.formatters.object;

import langs.bevent.Event;
import langs.bevent.Machine;
import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.*;
import langs.bevent.exprs.defs.ConstDef;
import langs.bevent.exprs.defs.FunDef;
import langs.bevent.exprs.defs.SetDef;
import langs.bevent.exprs.defs.VarDef;
import langs.bevent.exprs.sets.NamedSet;
import langs.bevent.exprs.sets.Range;
import langs.bevent.exprs.sets.Set;
import langs.bevent.exprs.sets.Z;
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

    String visit(Invariant invariant);

    @SuppressWarnings("SameReturnValue")
    String visit(False aFalse);

    @SuppressWarnings("SameReturnValue")
    String visit(True aTrue);

    String visit(Not not);

    String visit(And and);

    String visit(Or or);

    String visit(Implies implies);

    String visit(Equiv equiv);

    <Value extends AArithExpr<Value>> String visit(In<Value> in);

    String visit(VarIn varIn);

    String visit(FunIn funIn);

    String visit(Equals equals);

    String visit(NEq nEq);

    String visit(LT lt);

    String visit(LEq lEq);

    String visit(GT gt);

    String visit(GEq gEq);

    String visit(Exists exists);

    String visit(ForAll forAll);

    String visit(ConstDef constDef);

    String visit(VarDef varDef);

    String visit(FunDef funDef);

    String visit(SetDef setDef);

    String visit(Z z);

    String visit(Set set);

    String visit(Range range);

    String visit(NamedSet namedSet);

    @SuppressWarnings("SameReturnValue")
    String visit(Skip skip);

    String visit(VarAssignment varAssignment);

    String visit(FunAssignment funAssignment);

    String visit(MultipleAssignment multipleAssignment);

    String visit(Select select);

    String visit(Any any);

    String visit(Choice choice);

    String visit(Parallel parallel);

    String visit(Machine machine);

    String visit(Event event);

}
