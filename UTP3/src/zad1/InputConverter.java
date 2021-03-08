package zad1;

import java.util.function.Function;

public class InputConverter<S> {

    private S fname;

    public InputConverter(S fname) {
        this.fname = fname;
    }

    public <T> T convertBy(Function... function){

        Object data = fname;
        for(int i = 0; i < function.length; i++){
            data = function[i].apply(data);
        }

        return (T) data;

    }
}
