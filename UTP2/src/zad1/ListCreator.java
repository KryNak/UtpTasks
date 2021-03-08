package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<E> {

    private List<E> destinations;

    private ListCreator(List<E> destinations){
        this.destinations = destinations;
    }

    public static <E> ListCreator<E> collectFrom(List<E> destinations) {
        return new ListCreator<E>(destinations);
    }

    public ListCreator<E> when(Predicate<E> predicate) {
        List<E> list = new ArrayList<>();

        destinations.forEach(e ->{
            if(predicate.test(e)){
                list.add(e);
            }
        });

        destinations = list;

        return this;
    }

    public List<E> mapEvery(Function<E, E> function) {
        List<E> list = new ArrayList<>();

        destinations.forEach(e -> {
            list.add(function.apply(e));
        });

        destinations = list;

        return destinations;
    }
}
