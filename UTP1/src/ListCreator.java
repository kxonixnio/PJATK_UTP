/**
 *
 *  @author Tomczyk Mikołaj S22472
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListCreator<T> {

    private ArrayList<T> list;

    public ListCreator(ArrayList<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> list){
        return new ListCreator<>(new ArrayList<>(list));
    }

    public ListCreator<T> when(Selector<T> selector){
        Iterator<T> iterator = list.iterator();

        while(iterator.hasNext()){
            if(!selector.select(iterator.next())){
                iterator.remove();
            }
        }
        return this;
    }

    /*  ^^^^
    Note: Trying to remove items using a for loop or a for-each loop would not work correctly because the collection is changing size at the same time that the code is trying to loop.
     */

    public <V> List<V> mapEvery(Mapper<V, T> mapper){
        List<V> list_copy = new ArrayList<>();

        for(T element : list){
            list_copy.add(mapper.map(element));
        }

        return list_copy;
    }
}