package zad2;

import java.io.IOException;
import java.util.function.Function;

@FunctionalInterface
public interface ExceptionFunction<T, R> extends Function<T, R> {

    R checkedApply(T t) throws IOException;

    @Override
    default R apply(T t) {
        try{
            return checkedApply(t);
        } catch (RuntimeException e) {
            throw e;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
