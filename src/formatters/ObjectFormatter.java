package formatters;

import langs.bevent.exprs.arith.*;
import langs.bevent.exprs.bool.*;

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
    public String visit(Var var) {
        return var.getName();
    }

    @Override
    public String visit(Const aConst) {
        return aConst.getName();
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

}
