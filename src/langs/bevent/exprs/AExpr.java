package langs.bevent.exprs;

import langs.AObject;
import langs.bevent.exprs.arith.Const;

import java.util.List;

/**
 * Created by gvoiron on 25/05/18.
 * Time : 17:52
 */
public abstract class AExpr extends AObject {

    public abstract List<Const> getRequiredConsts();

}
