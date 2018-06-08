package langs.bevent.exprs.defs;

import langs.bevent.exprs.arith.Const;
import langs.bevent.exprs.sets.AFiniteSetExpr;
import langs.bevent.exprs.sets.ASetExpr;
import visitors.formatters.object.IObjectFormatter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 11:16
 */
public final class FunDef extends ADef {

    private final ASetExpr codomain;

    public FunDef(String name, AFiniteSetExpr domain, ASetExpr codomain) {
        super(name, domain);
        this.codomain = codomain;
    }

    public ASetExpr getCodomain() {
        return codomain;
    }

    @Override
    public String accept(IObjectFormatter formatter) {
        return formatter.visit(this);
    }

    @Override
    public List<Const> getRequiredConsts() {
        return Stream.of(getDomain().getRequiredConsts(), getCodomain().getRequiredConsts()).flatMap(Collection::stream).collect(Collectors.toList());
    }

}
