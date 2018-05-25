package formatters;

import langs.bevent.exprs.arith.*;

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

}
