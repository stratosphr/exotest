package visitors.encoders.z3;

import com.microsoft.z3.ArithExpr;
import com.microsoft.z3.BoolExpr;
import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.*;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 10:34
 */
public interface IZ3Encoder {

    ArithExpr visit(Int anInt);

    ArithExpr visit(Const aConst);

    ArithExpr visit(Var var);

    ArithExpr visit(Fun fun);

    ArithExpr visit(Plus plus);

    ArithExpr visit(Minus minus);

    ArithExpr visit(Times times);

    ArithExpr visit(Div div);

    BoolExpr visit(False aFalse);

    BoolExpr visit(True aTrue);

    BoolExpr visit(Not not);

    BoolExpr visit(And and);

    BoolExpr visit(Or or);

    BoolExpr visit(Implies implies);

    BoolExpr visit(Equiv equiv);

    <Value extends AArithExpr> BoolExpr visit(In<Value> in);

    BoolExpr visit(Equals equals);

    BoolExpr visit(NEq nEq);

    BoolExpr visit(LT lt);

    BoolExpr visit(LEq lEq);

    BoolExpr visit(GT gt);

    BoolExpr visit(GEq gEq);
}
