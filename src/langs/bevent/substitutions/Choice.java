package langs.bevent.substitutions;

import errors.InvalidNumberOfChoicesError;
import langs.bevent.exprs.arith.AAssignable;
import langs.bevent.exprs.bool.ABoolExpr;
import langs.bevent.exprs.bool.Or;
import visitors.formatters.object.IObjectFormatter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 11/06/18.
 * Time : 12:04
 */
public final class Choice extends ASubstitution {

    private List<ASubstitution> choices;

    public Choice(ASubstitution... choices) {
        if (choices.length == 0) {
            throw new InvalidNumberOfChoicesError();
        }
        this.choices = Arrays.stream(choices).distinct().collect(Collectors.toList());
    }

    public List<ASubstitution> getChoices() {
        return choices;
    }

    @Override
    public ABoolExpr getPrd(Set<AAssignable> assignables) {
        return new Or(choices.stream().map(substitution -> substitution.getPrd(assignables)).toArray(ABoolExpr[]::new));
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
