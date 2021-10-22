package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    T t;

    public Maybe(T t) {
        this.t = t;
    }
    public Maybe() { }

    @Override
    public String toString() {
        if(t != null){
            return "Maybe has value " + t;
        }else{
            return "Maybe is empty";
        }
    }

    public static <V> Maybe<V> of(V v){
        return new Maybe<>(v);
    }

    public void ifPresent(Consumer consumer){
        if(t != null){
            consumer.accept(t);
        }
    }

    public <U> Maybe<U> map(Function<T, U> function){
//        return new Maybe<>(function.apply(t));
        if(this.t != null){
            return new Maybe<>(function.apply(t));
        }else{
            return new Maybe<>();
        }
    }

    public T get() throws NoSuchElementException{
        if(this.t == null){
            throw new NoSuchElementException("maybe is empty");
        }else{
            return t;
        }
    }

    public boolean isPresent(){
        return t != null;
    }

    public T orElse(T defVal){
        if(this.t == null){
            return defVal;
        }else{
            return t;
        }
    }

    public Maybe<T> filter(Predicate<T> predicate){
        if(!this.isPresent() || predicate.test(this.t)){        //predicate.test(this.t) || this.t == null
            return this;
        }else{
            return new Maybe<>();
        }
    }

}

