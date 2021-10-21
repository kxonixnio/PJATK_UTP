package zad1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class ListCreator<T> {

    private List<T> list;

    public ListCreator(ArrayList<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> list){
        return new ListCreator<>(new ArrayList<>(list));
    }

    public ListCreator<T> when(Function<T, Boolean> lambda_function){
        List<T> list_copy = new ArrayList<T>();

        for(T departure : this.list){
            if(lambda_function.apply(departure)){
                list_copy.add(departure);
            }
        }

        this.list = list_copy;

        return this;
    }

    public <V> List<V> mapEvery(Function<T,V> lambda_function){
        List<V> list_copy = new ArrayList<V>();

        for(T departure : this.list){
            list_copy.add(lambda_function.apply(departure));
        }

        return list_copy;
    }
}