package langs.bevent.exprs.sets;

import formatters.IObjectFormatter;
import langs.bevent.exprs.arith.AArithExpr;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:25
 */
public final class Set extends ASetExpr {

    public Set(AArithExpr... elements) {
        super(Arrays.stream(elements).collect(Collectors.toCollection(LinkedHashSet::new)));
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

}