package langs.bevent.exprs.bool;

import com.microsoft.z3.BoolExpr;
import visitors.encoders.z3.IZ3Encoder;
import visitors.formatters.object.IObjectFormatter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gvoiron on 03/06/18.
 * Time : 23:10
 */
public final class Exists extends AQuantifier {

    public Exists(ABoolExpr expr, VarIn... quantifiedVarsDefs) {
        super(new And(Stream.of(Arrays.stream(quantifiedVarsDefs).collect(Collectors.toList()), Collections.singletonList(expr)).flatMap(Collection::stream).toArray(ABoolExpr[]::new)), quantifiedVarsDefs);
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public BoolExpr accept(IZ3Encoder generator) {
        return generator.visit(this);
    }

}
