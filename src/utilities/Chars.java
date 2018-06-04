package utilities;

/**
 * Created by gvoiron on 04/06/18.
 * Time : 12:42
 */
public enum Chars {

    AND('∧'),
    EQDEF('≝'),
    EXISTS('∃'),
    FORALL('∀'),
    GEQ('≥'),
    IN('∈'),
    LEQ('≤'),
    N('ℕ'),
    NEQ('≠'),
    OR('∨'),
    Z('ℤ');

    private final char aChar;

    Chars(char aChar) {
        this.aChar = aChar;
    }

    @Override
    public String toString() {
        return String.valueOf(aChar);
    }

}
