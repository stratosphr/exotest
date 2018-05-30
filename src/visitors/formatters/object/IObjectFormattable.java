package visitors.formatters.object;

/**
 * Created by gvoiron on 26/05/18.
 * Time : 22:24
 */
public interface IObjectFormattable {

    String accept(IObjectFormatter formatter);

}
