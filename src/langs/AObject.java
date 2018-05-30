package langs;

import visitors.formatters.object.IObjectFormattable;
import visitors.formatters.object.IObjectFormatter;
import visitors.formatters.object.ObjectFormatter;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:46
 */
public abstract class AObject implements IObjectFormattable, Comparable<AObject> {

    public abstract String accept(IObjectFormatter formatter);

    @Override
    public final int hashCode() {
        return (getClass().getSimpleName() + toString()).hashCode();
    }

    @Override
    public final boolean equals(Object o) {
        return this == o || o instanceof AObject && hashCode() == o.hashCode() && toString().equals(o.toString());
    }

    @Override
    public int compareTo(AObject object) {
        return toString().compareTo(object.toString());
    }

    @Override
    public final String toString() {
        return accept(new ObjectFormatter());
    }

}
