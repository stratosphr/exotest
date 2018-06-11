package visitors.primer;

/**
 * Created by gvoiron on 09/06/18.
 * Time : 19:23
 */
public interface IPrimable<Primed> {

    Primed accept(IPrimer primer);

}
