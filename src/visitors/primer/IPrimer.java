package visitors.primer;

import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.*;

/**
 * Created by gvoiron on 09/06/18.
 * Time : 19:26
 */
public interface IPrimer {

    Const visit(Const aConst);

    Int visit(Int anInt);

    Var visit(Var var);

    Fun visit(Fun fun);

    Plus visit(Plus plus);

    Minus visit(Minus minus);

    Times visit(Times times);

    Div visit(Div div);

    Invariant visit(Invariant invariant);

    False visit(False aFalse);

    True visit(True aTrue);

    Not visit(Not not);

    And visit(And and);

    Or visit(Or or);

    Implies visit(Implies implies);

    Equiv visit(Equiv equiv);

    Equals visit(Equals equals);

    NEq visit(NEq nEq);

    LT visit(LT lt);

    LEq visit(LEq lEq);

    GT visit(GT gt);

    GEq visit(GEq gEq);

    <Value extends AArithExpr<Value>> In<Value> visit(In<Value> in);

    VarIn visit(VarIn varIn);

    FunIn visit(FunIn funIn);

    Exists visit(Exists exists);

    ForAll visit(ForAll forAll);

}
