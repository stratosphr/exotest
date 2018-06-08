package utilities;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by gvoiron on 04/06/18.
 * Time : 15:19
 */
public final class Maths {

    public static List<Integer> range(Integer aValue, Integer aValue1) {
        return IntStream.rangeClosed(aValue, aValue1).boxed().collect(Collectors.toList());
    }

}
