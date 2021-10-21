/**
 *
 *  @author Tomczyk Mikołaj S22472
 *
 */

public interface Mapper<T, V> { // Uwaga: interfejs musi by� sparametrtyzowany

    T map(V mapping);

}  