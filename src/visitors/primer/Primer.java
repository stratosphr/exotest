package visitors.primer;

import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.*;

import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * Created by gvoiron on 09/06/18.
 * Time : 19:28
 */
public final class Primer implements IPrimer {

    private final LinkedHashSet<String> assignablesNames;
    private final String primeSuffix;

    public Primer(String... assignables) {
        this.assignablesNames = new LinkedHashSet<>(Arrays.asList(assignables));
        this.primeSuffix = "_";
    }

    public Primer(LinkedHashSet<AAssignable> assignables) {
        this(assignables.stream().map(AAssignable::getName).toArray(String[]::new));
    }

    public Primer(AAssignable... assignables) {
        this(new LinkedHashSet<>(Arrays.asList(assignables)));
    }

    @Override
    public Const visit(Const aConst) {
        return new Const(aConst.getName());
    }

    @Override
    public Int visit(Int anInt) {
        return new Int(anInt.getValue());
    }

    @Override
    public Var visit(Var var) {
        return assignablesNames.contains(var.getName()) ? new Var(var.getName() + getPrimeSuffix()) : new Var(var.getName());
    }

    @Override
    public Fun visit(Fun fun) {
        return assignablesNames.contains(fun.getName()) ? new Fun(fun.getName() + "_", fun.getParam().accept(this)) : new Fun(fun.getName() + "_", fun.getParam().accept(this));
    }

    @Override
    public Plus visit(Plus plus) {
        return new Plus(plus.getOperands().stream().map(operand -> operand.accept(this)).toArray(AArithExpr[]::new));
    }

    @Override
    public Minus visit(Minus minus) {
        return new Minus(minus.getOperands().stream().map(operand -> operand.accept(this)).toArray(AArithExpr[]::new));
    }

    @Override
    public Times visit(Times times) {
        return new Times(times.getOperands().stream().map(operand -> operand.accept(this)).toArray(AArithExpr[]::new));
    }

    @Override
    public Div visit(Div div) {
        return new Div(div.getLeft().accept(this), div.getRight().accept(this));
    }

    @Override
    public Invariant visit(Invariant invariant) {
        return new Invariant(invariant.getExpr().accept(this));
    }

    @Override
    public False visit(False aFalse) {
        return new False();
    }

    @Override
    public True visit(True aTrue) {
        return new True();
    }

    @Override
    public Not visit(Not not) {
        return new Not(not.getOperand().accept(this));
    }

    @Override
    public And visit(And and) {
        return new And(and.getOperands().stream().map(operand -> operand.accept(this)).toArray(ABoolExpr[]::new));
    }

    @Override
    public Or visit(Or or) {
        return new Or(or.getOperands().stream().map(operand -> operand.accept(this)).toArray(ABoolExpr[]::new));
    }

    @Override
    public Implies visit(Implies implies) {
        return new Implies(implies.getLeft().accept(this), implies.getRight().accept(this));
    }

    @Override
    public Equiv visit(Equiv equiv) {
        return new Equiv(equiv.getLeft().accept(this), equiv.getRight().accept(this));
    }

    @Override
    public Equals visit(Equals equals) {
        return new Equals(equals.getOperands().stream().map(operand -> operand.accept(this)).toArray(AArithExpr[]::new));
    }

    @Override
    public NEq visit(NEq nEq) {
        return new NEq(nEq.getLeft().accept(this), nEq.getRight().accept(this));
    }

    @Override
    public LT visit(LT lt) {
        return new LT(lt.getLeft().accept(this), lt.getRight().accept(this));
    }

    @Override
    public LEq visit(LEq lEq) {
        return new LEq(lEq.getLeft().accept(this), lEq.getRight().accept(this));
    }

    @Override
    public GT visit(GT gt) {
        return new GT(gt.getLeft().accept(this), gt.getRight().accept(this));
    }

    @Override
    public GEq visit(GEq gEq) {
        return new GEq(gEq.getLeft().accept(this), gEq.getRight().accept(this));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <Value extends AArithExpr<Value>> In<Value> visit(In<Value> in) {
        return new In<>(in.getExpr().accept(this), in.getDomain());
    }

    @Override
    public VarIn visit(VarIn varIn) {
        return new VarIn(varIn.getExpr().accept(this), varIn.getDomain());
    }

    @Override
    public FunIn visit(FunIn funIn) {
        return new FunIn(funIn.getFun().accept(this), funIn.getDomain());
    }

    @Override
    public Exists visit(Exists exists) {
        return new Exists(exists.getExpr().accept(this), exists.getQuantifiedVarsDefs().stream().map(varIn -> varIn.accept(this)).toArray(VarIn[]::new));
    }

    @Override
    public ForAll visit(ForAll forAll) {
        return new ForAll(forAll.getExpr().accept(this), forAll.getQuantifiedVarsDefs().stream().map(varIn -> varIn.accept(this)).toArray(VarIn[]::new));
    }

    public String getPrimeSuffix() {
        return primeSuffix;
    }

}
