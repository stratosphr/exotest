package langs.bevent;

import langs.AObject;
import langs.bevent.substitutions.ASubstitution;
import visitors.formatters.object.IObjectFormatter;

/**
 * Created by gvoiron on 08/06/18.
 * Time : 09:56
 */
public final class Event extends AObject {

    private final String name;
    private final ASubstitution substitution;

    public Event(String name, ASubstitution substitution) {
        this.name = name;
        this.substitution = substitution;
    }

    public String getName() {
        return name;
    }

    public ASubstitution getSubstitution() {
        return substitution;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
