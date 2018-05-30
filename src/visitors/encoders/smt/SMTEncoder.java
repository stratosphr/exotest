package visitors.encoders.smt;

import com.microsoft.z3.ArithExpr;
import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.*;
import visitors.sets.DomainConstraintGenerator;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 10:33
 */
public final class SMTEncoder implements ISMTEncoder {

    private Context context;

    public SMTEncoder(Context context) {
        this.context = context;
    }

    @Override
    public BoolExpr visit(False aFalse) {
        return context.mkFalse();
    }

    @Override
    public BoolExpr visit(True aTrue) {
        return context.mkTrue();
    }

    @Override
    public BoolExpr visit(Not not) {
        return context.mkNot(not.getOperand().accept(this));
    }

    @Override
    public BoolExpr visit(And and) {
        return context.mkAnd(and.getOperands().stream().map(operand -> operand.accept(this)).toArray(BoolExpr[]::new));
    }

    @Override
    public BoolExpr visit(Or or) {
        return context.mkOr(or.getOperands().stream().map(operand -> operand.accept(this)).toArray(BoolExpr[]::new));
    }

    @Override
    public BoolExpr visit(Implies implies) {
        return context.mkImplies(implies.getLeft().accept(this), implies.getRight().accept(this));
    }

    @Override
    public BoolExpr visit(Equiv equiv) {
        return context.mkEq(equiv.getLeft().accept(this), equiv.getRight().accept(this));
    }

    @Override
    public <Value extends AArithExpr> BoolExpr visit(In<Value> in) {
        return in.getDomain().accept(new DomainConstraintGenerator(in.getExpr())).accept(this);
    }

    @Override
    public BoolExpr visit(Equals equals) {
        return context.mkAnd(equals.getOperands().subList(1, equals.getOperands().size()).stream().map(operand -> context.mkEq(operand.accept(this), equals.getOperands().get(0).accept(this))).toArray(BoolExpr[]::new));
    }

    @Override
    public BoolExpr visit(NEq nEq) {
        return new Not(new Equals(nEq.getLeft(), nEq.getRight())).accept(this);
    }

    @Override
    public BoolExpr visit(LT lt) {
        return context.mkLt(lt.getLeft().accept(this), lt.getRight().accept(this));
    }

    @Override
    public BoolExpr visit(LEq lEq) {
        return context.mkLe(lEq.getLeft().accept(this), lEq.getRight().accept(this));
    }

    @Override
    public BoolExpr visit(GT gt) {
        return context.mkGt(gt.getLeft().accept(this), gt.getRight().accept(this));
    }

    @Override
    public BoolExpr visit(GEq gEq) {
        return context.mkGe(gEq.getLeft().accept(this), gEq.getRight().accept(this));
    }

    @Override
    public ArithExpr visit(Int anInt) {
        return context.mkInt(anInt.getValue());
    }

    @Override
    public ArithExpr visit(Const aConst) {
        return context.mkIntConst(aConst.getName());
    }

    @Override
    public ArithExpr visit(Var var) {
        return context.mkIntConst(var.getName());
    }

    @Override
    public ArithExpr visit(Fun fun) {
        throw new Error("Not implemented");
    }

    @Override
    public ArithExpr visit(Plus plus) {
        return context.mkAdd(plus.getOperands().stream().map(operand -> operand.accept(this)).toArray(ArithExpr[]::new));
    }

    @Override
    public ArithExpr visit(Minus minus) {
        return context.mkSub(minus.getOperands().stream().map(operand -> operand.accept(this)).toArray(ArithExpr[]::new));
    }

    @Override
    public ArithExpr visit(Times times) {
        return context.mkMul(times.getOperands().stream().map(operand -> operand.accept(this)).toArray(ArithExpr[]::new));
    }

    @Override
    public ArithExpr visit(Div div) {
        return context.mkDiv(div.getLeft().accept(this), div.getRight().accept(this));
    }

}
