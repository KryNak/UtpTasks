package zad3;

import java.util.function.Function;

class InputConverter<T1> {

    private T1 fname;

    public InputConverter(T1 fname) {
        this.fname = fname;
    }


    public <R> R convertBy(Function<?, ?>... functions) {

        Object t = fname;
        for(int i = 0; i < functions.length; i++){
            helper(functions[i]);
        }


        return null;
    }

    private static <T2, R2> R2 helper(Function<T2, R2> function){

        return null;
    }

}
