package formatters;

import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.*;
import langs.bevent.exprs.defs.ConstDef;
import langs.bevent.exprs.defs.FunDef;
import langs.bevent.exprs.defs.VarDef;
import langs.bevent.exprs.sets.Range;
import langs.bevent.exprs.sets.Set;

import java.util.stream.Collectors;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:50
 */
public final class ObjectFormatter implements IObjectFormatter {

    @Override
    public String visit(Int anInt) {
        return anInt.getValue().toString();
    }

    @Override
    public String visit(Const aConst) {
        return aConst.getName();
    }

    @Override
    public String visit(Var var) {
        return var.getName();
    }

    @Override
    public String visit(Fun fun) {
        return fun.getName() + "(" + fun.getParam().accept(this) + ")";
    }

    @Override
    public String visit(Plus plus) {
        return plus.getOperands().size() == 1 ? plus.getOperands().get(0).accept(this) : plus.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" + ", "(", ")"));
    }

    @Override
    public String visit(Minus minus) {
        return minus.getOperands().size() == 1 ? "(-" + minus.getOperands().get(0).accept(this) + ")" : minus.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" - ", "(", ")"));
    }

    @Override
    public String visit(Times times) {
        return times.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" * ", "(", ")"));
    }

    @Override
    public String visit(Div div) {
        return div.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" / ", "(", ")"));
    }

    @Override
    public String visit(False aFalse) {
        return "false";
    }

    @Override
    public String visit(True aTrue) {
        return "true";
    }

    @Override
    public String visit(Not not) {
        return "not(" + not.getOperand().accept(this) + ")";
    }

    @Override
    public String visit(And and) {
        return and.getOperands().isEmpty() ? new False().accept(this) : and.getOperands().size() == 1 ? and.getOperands().get(0).accept(this) : and.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" and ", "(", ")"));
    }

    @Override
    public String visit(Or or) {
        return or.getOperands().isEmpty() ? new True().accept(this) : or.getOperands().size() == 1 ? or.getOperands().get(0).accept(this) : or.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" or ", "(", ")"));
    }

    @Override
    public String visit(Implies implies) {
        return implies.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" => ", "(", ")"));
    }

    @Override
    public String visit(Equiv equiv) {
        return equiv.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" <==> ", "(", ")"));
    }

    @Override
    public <Value extends AArithExpr> String visit(In<Value> in) {
        return in.getExpr().accept(this) + " in " + in.getDomain().accept(this);
    }

    @Override
    public String visit(Equals equals) {
        return equals.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" = ", "(", ")"));
    }

    @Override
    public String visit(ConstDef constDef) {
        return new Equals(constDef.getConst(), constDef.getValue()).accept(this);
    }

    @Override
    public String visit(VarDef varDef) {
        return new In<>(varDef.getVar(), varDef.getDomain()).accept(this);
    }

    @Override
    public String visit(FunDef funDef) {
        return funDef.getName() + " : " + funDef.getDomain().accept(this) + " --> " + funDef.getCodomain().accept(this);
    }

    @Override
    public String visit(Set set) {
        return set.getElements().stream().map(element -> element.accept(this)).collect(Collectors.joining(", ", "{", "}"));
    }

    @Override
    public String visit(Range range) {
        return range.getLowerBound().accept(this) + ".." + range.getUpperBound().accept(this);
    }

}
