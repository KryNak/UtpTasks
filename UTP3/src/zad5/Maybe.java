package zad5;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<Z> {

    private Z value;

    private Maybe(){
        value = null;
    }

    private Maybe(Z value){
        this.value = value;
    }

    public static <Z> Maybe<Z> of(Z value){
        return new Maybe<>(value);
    }

    public void ifPresent(Consumer<? super Z> consumer){
        if(value != null) consumer.accept(value);
    }

    public <T> Maybe<T> map(Function<? super Z, ? extends T> function){
        if(value == null){
            return (Maybe<T>) new Maybe<>();
        }
        else return new Maybe<>(function.apply(value));
    }

    public Z get(){
        if(value != null){
            return value;
        }
        else throw new NoSuchElementException("maybe is empty");
    }

    public boolean isPresent(){
        return value != null;
    }

    public Z orElse(Z defVal){
        return value != null ? value : defVal;
    }

    public Maybe<Z> filter(Predicate<? super Z> pred){
        if(value == null) return this;
        else if(pred.test(value)) return this;
        else return (Maybe<Z>) new Maybe<>();
    }

    @Override
    public String toString() {
        if(value != null) return "Maybe has value " + value;
        else return "Maybe is empty";
    }
}
