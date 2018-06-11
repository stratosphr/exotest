package errors;

import langs.AObject;
import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.sets.AFiniteSetExpr;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 08/06/18.
 * Time : 21:55
 */
public class UndefinedConstantError extends Error {

    public UndefinedConstantError(AFiniteSetExpr set, LinkedHashSet<Const> consts) {
        super("Unable to compute elements in set \"" + set + "\" because the following constants where not defined : " + consts.stream().map(AObject::toString).collect(Collectors.joining(", ")) + ".");
    }

}
