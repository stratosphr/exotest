package langs.bevent;

import langs.AObject;
import visitors.formatters.object.IObjectFormatter;

/**
 * Created by gvoiron on 08/06/18.
 * Time : 09:42
 */
public final class Machine extends AObject {

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}
