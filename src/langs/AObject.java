package langs;

import formatters.IObjectFormatter;
import formatters.ObjectFormatter;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:46
 */
public abstract class AObject {

    public abstract String accept(IObjectFormatter formatter);

    @Override
    public final String toString() {
        return accept(new ObjectFormatter());
    }

}
