package langs.bevent.substitutions;

import langs.bevent.exprs.arith.AAssignable;
import langs.bevent.exprs.arith.Var;
import langs.bevent.exprs.bool.ABoolExpr;
import langs.bevent.exprs.bool.And;
import langs.bevent.exprs.bool.Equals;
import visitors.formatters.object.IObjectFormatter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 20:23
 */
public final class MultipleAssignment extends ASubstitution {

    private final List<AAssignment> assignments;

    public MultipleAssignment(AAssignment... assignments) {
        this.assignments = Arrays.asList(assignments);
    }

    public List<AAssignment> getAssignments() {
        return assignments;
    }

    @Override
    public ABoolExpr getPrd(Set<AAssignable> assignables) {
        return new And(Stream.of(
                assignments.stream().filter(assignment -> assignables.contains(assignment.getAssignable())).map(AAssignment::getPrdInMultipleAssignments).collect(Collectors.toList()),
                assignables.stream().filter(assignable -> assignable instanceof Var && assignments.stream().noneMatch(assignment -> assignable.equals(assignment.getAssignable()))).map(assignable -> new Equals(new Var(assignable.getName() + "_"), assignable)).collect(Collectors.toList())
                //assignables.stream().filter(assignable -> assignable instanceof Fun).collect(Collectors.groupingBy(AAssignable::getName)).keySet().stream().map(funName -> new ForAll(new Implies(new And(assignments.stream().filter(assignment -> assignment instanceof FunAssignment && assignment.getAssignable().getName().equals(funName)).map(assignment -> new NotEquals(new Var("i!"), ((FunAssignment) assignment).getAssignable().getParameter())).toArray(ABoolExpr[]::new)), new Equals(new Fun(funName, new Var("i!")).accept(new Primer(1)), new Fun(funName, new Var("i!")))), new VarInDomain(new Var("i!"), new Z()))).collect(Collectors.toList())
        ).flatMap(Collection::stream).toArray(ABoolExpr[]::new));
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
