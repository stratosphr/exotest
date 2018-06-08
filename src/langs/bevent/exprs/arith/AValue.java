package langs.bevent.exprs.arith;

/**
 * Created by gvoiron on 30/05/18.
 * Time : 15:49
 */
public abstract class AValue extends AArithExpr {

    private Integer value;

    public AValue(Integer value) {
        this.value = value;
    }

    public final Integer getValue() {
        return value;
    }

}
