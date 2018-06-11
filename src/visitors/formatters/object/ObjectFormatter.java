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

import java.util.stream.Collectors;

import static utilities.Chars.*;

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
    public String visit(Invariant invariant) {
        return invariant.getExpr().accept(this);
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
        return and.getOperands().isEmpty() ? new False().accept(this) : and.getOperands().size() == 1 ? and.getOperands().get(0).accept(this) : and.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" " + AND + " ", "(", ")"));
    }

    @Override
    public String visit(Or or) {
        return or.getOperands().isEmpty() ? new True().accept(this) : or.getOperands().size() == 1 ? or.getOperands().get(0).accept(this) : or.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" " + OR + " ", "(", ")"));
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
    public <Value extends AArithExpr<Value>> String visit(In<Value> in) {
        return in.getExpr().accept(this) + " " + IN + " " + in.getDomain().accept(this);
    }

    @Override
    public String visit(VarIn varIn) {
        return new In<>(varIn.getVar(), varIn.getDomain()).accept(this);
    }

    @Override
    public String visit(FunIn funIn) {
        return new In<>(funIn.getFun(), funIn.getDomain()).accept(this);
    }

    @Override
    public String visit(Equals equals) {
        return equals.getOperands().stream().map(operand -> operand.accept(this)).collect(Collectors.joining(" = "));
    }

    @Override
    public String visit(NEq nEq) {
        return nEq.getLeft().accept(this) + " " + NEQ + " " + nEq.getRight().accept(this);
    }

    @Override
    public String visit(LT lt) {
        return lt.getLeft().accept(this) + " < " + lt.getRight().accept(this);
    }

    @Override
    public String visit(LEq lEq) {
        return lEq.getLeft().accept(this) + " " + LEQ + " " + lEq.getRight().accept(this);
    }

    @Override
    public String visit(GT gt) {
        return gt.getLeft().accept(this) + gt.getRight().accept(this);
    }

    @Override
    public String visit(GEq gEq) {
        return gEq.getLeft().accept(this) + " " + GEQ + " " + gEq.getRight().accept(this);
    }

    @Override
    public String visit(Exists exists) {
        return EXISTS + "(" + exists.getQuantifiedVars().stream().map(AAssignable::getName).collect(Collectors.joining(", ")) + ").(" + exists.getExpr().accept(this) + ")";
    }

    @Override
    public String visit(ForAll forAll) {
        return FORALL + "(" + forAll.getQuantifiedVars().stream().map(AAssignable::getName).collect(Collectors.joining(", ")) + ").(" + forAll.getExpr().accept(this) + ")";
    }

    @Override
    public String visit(ConstDef constDef) {
        return constDef.getConst().accept(this) + " " + EQDEF + " " + constDef.getValue().accept(this);
    }

    @Override
    public String visit(VarDef varDef) {
        return new VarIn(varDef.getVar(), varDef.getDomain()).accept(this);
    }

    @Override
    public String visit(FunDef funDef) {
        return funDef.getName() + " : " + funDef.getDomain().accept(this) + " --> " + funDef.getCodomain().accept(this);
    }

    @Override
    public String visit(SetDef setDef) {
        return setDef.getName() + " " + EQDEF + " " + setDef.getDomain().accept(this);
    }

    @Override
    public String visit(Z z) {
        return String.valueOf(Z);
    }

    @Override
    public String visit(Set set) {
        return set.getElements().stream().map(element -> element.accept(this)).collect(Collectors.joining(", ", "{", "}"));
    }

    @Override
    public String visit(Range range) {
        return range.getLowerBound().accept(this) + ".." + range.getUpperBound().accept(this);
    }

    @Override
    public String visit(NamedSet namedSet) {
        return namedSet.getName();
    }

    @Override
    public String visit(Skip skip) {
        return "SKIP";
    }

    @Override
    public String visit(VarAssignment varAssignment) {
        return varAssignment.getAssignable().accept(this) + " := " + varAssignment.getValue().accept(this);
    }

    @Override
    public String visit(FunAssignment funAssignment) {
        return funAssignment.getAssignable().accept(this) + " := " + funAssignment.getValue().accept(this);
    }

    @Override
    public String visit(MultipleAssignment multipleAssignment) {
        return new Parallel(multipleAssignment.getAssignments().toArray(new AAssignment[0])).accept(this);
    }

    @Override
    public String visit(Select select) {
        return "SELECT " + select.getCondition().accept(this) + " THEN " + select.getSubstitution().accept(this) + " END";
    }

    @Override
    public String visit(Any any) {
        return "ANY " + any.getQuantifiedVarsDefs().stream().map(varIn -> varIn.accept(this)).collect(Collectors.joining(", ")) + " WHERE " + any.getCondition().accept(this) + " THEN " + any.getSubstitution().accept(this) + " END";
    }

    @Override
    public String visit(Parallel parallel) {
        return parallel.getSubstitutions().stream().map(substitution -> substitution.accept(this)).collect(Collectors.joining(" || "));
    }

    @Override
    public String visit(Machine machine) {
        return machine.getName() + "\n" +
                "CONSTS DEFS" + "\n" +
                machine.getConstsDefs().stream().map(constDef -> constDef.accept(this)).collect(Collectors.joining("\n")) + "\n" +
                "SETS DEFS" + "\n" +
                machine.getSetsDefs().stream().map(setDef -> setDef.accept(this)).collect(Collectors.joining("\n")) + "\n" +
                "VARS DEFS" + "\n" +
                machine.getVarsDefs().stream().map(varDef -> varDef.accept(this)).collect(Collectors.joining("\n")) + "\n" +
                "FUNS DEFS" + "\n" +
                machine.getFunsDefs().stream().map(varDef -> varDef.accept(this)).collect(Collectors.joining("\n")) + "\n" +
                "INVARIANT" + "\n" +
                machine.getInvariant() + "\n" +
                "INITIALISATION" + "\n" +
                machine.getInitialisation().accept(this) + "\n" +
                "EVENTS" + "\n" +
                machine.getEvents().stream().map(event -> event.accept(this)).collect(Collectors.joining("\n"));
    }

    @Override
    public String visit(Event event) {
        return event.getName() + " " + EQDEF + " " + event.getSubstitution().accept(this);
    }

}
