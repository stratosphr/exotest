import langs.bevent.exprs.arith.Div;
import langs.bevent.exprs.arith.Int;
import langs.bevent.exprs.arith.Minus;
import langs.bevent.exprs.arith.Plus;

public class Main {

    public static void main(String[] args) {
        System.out.println(new Int(42));
        System.out.println(new Plus(new Int(42), new Minus(new Int(4), new Int(0), new Int(0)), new Int(54), new Minus(new Int(5))));
        System.out.println(new Div(new Int(42), new Int(3)));
    }

}
