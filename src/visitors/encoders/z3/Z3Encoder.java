package visitors.encoders.z3;

import com.microsoft.z3.*;
import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.*;
import visitors.generators.sets.DomainConstraintGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 10:33
 */
public final class Z3Encoder implements IZ3Encoder {

    private final Context context;
    private List<Var> quantifiedVars;

    public Z3Encoder(Context context) {
        this.context = context;
        this.quantifiedVars = new ArrayList<>();
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
        return quantifiedVars.contains(var) ? (ArithExpr) context.mkBound(quantifiedVars.size() - quantifiedVars.indexOf(var) - 1, context.getIntSort()) : context.mkIntConst(var.getName());
    }

    @Override
    public ArithExpr visit(Fun fun) {
        return (ArithExpr) context.mkApp(context.mkFuncDecl(fun.getName(), new Sort[]{context.getIntSort()}, context.getIntSort()), fun.getParam().accept(this));
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

    @Override
    public BoolExpr visit(Invariant invariant) {
        return invariant.getExpr().accept(this);
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
        return new And(new Implies(equiv.getLeft(), equiv.getRight()), new Implies(equiv.getRight(), equiv.getLeft())).accept(this);
    }

    @Override
    public <Value extends AArithExpr> BoolExpr visit(In<Value> in) {
        return in.getDomain().accept(new DomainConstraintGenerator(in.getExpr())).accept(this);
    }

    @Override
    public BoolExpr visit(VarIn varIn) {
        return new In<>(varIn.getVar(), varIn.getDomain()).accept(this);
    }

    @Override
    public BoolExpr visit(FunIn funIn) {
        return new In<>(funIn.getFun(), funIn.getDomain()).accept(this);
    }

    @Override
    public BoolExpr visit(Equals equals) {
        return equals.getOperands().size() == 2 ? context.mkEq(equals.getOperands().get(0).accept(this), equals.getOperands().get(1).accept(this)) : context.mkAnd(equals.getOperands().subList(1, equals.getOperands().size()).stream().map(operand -> context.mkEq(operand.accept(this), equals.getOperands().get(0).accept(this))).toArray(BoolExpr[]::new));
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
    public BoolExpr visit(Exists exists) {
        quantifiedVars.addAll(exists.getQuantifiedVars());
        Sort[] sorts = exists.getQuantifiedVars().stream().map(var -> context.getIntSort()).toArray(Sort[]::new);
        Symbol[] symbols = exists.getQuantifiedVars().stream().map(var -> context.mkSymbol(var.getName())).toArray(Symbol[]::new);
        BoolExpr expr = exists.getExpr().accept(this);
        quantifiedVars = quantifiedVars.subList(0, quantifiedVars.size() - exists.getQuantifiedVarsDefs().size());
        return context.mkExists(sorts, symbols, expr, 0, null, null, null, null);
    }

    @Override
    public BoolExpr visit(ForAll forAll) {
        quantifiedVars.addAll(forAll.getQuantifiedVars());
        Sort[] sorts = forAll.getQuantifiedVars().stream().map(var -> context.getIntSort()).toArray(Sort[]::new);
        Symbol[] symbols = forAll.getQuantifiedVars().stream().map(var -> context.mkSymbol(var.getName())).toArray(Symbol[]::new);
        BoolExpr expr = forAll.getExpr().accept(this);
        quantifiedVars = quantifiedVars.subList(0, quantifiedVars.size() - forAll.getQuantifiedVarsDefs().size());
        return context.mkForall(sorts, symbols, expr, 0, null, null, null, null);
    }

}
