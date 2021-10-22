package zad1;

import java.util.function.Function;

public class InputConverter<T> {
    private T input;

    public InputConverter(T input) {
        this.input = input;
    }

    public <V> V convertBy(Function... functions){
        Object inputToConvert = this.input;
        Object result = null;

        for(Function fun : functions){
            result = fun.apply(inputToConvert);
            inputToConvert = result;
        }

        return (V) result;
    }
}
