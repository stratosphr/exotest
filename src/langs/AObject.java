package langs;

import visitors.formatters.object.IObjectFormattable;
import visitors.formatters.object.IObjectFormatter;
import visitors.formatters.object.ObjectFormatter;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:46
 */
public abstract class AObject implements IObjectFormattable {

    public abstract String accept(IObjectFormatter formatter);

    @Override
    public final String toString() {
        return accept(new ObjectFormatter());
    }

}
