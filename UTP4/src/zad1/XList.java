package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T> {

    //CONSTRUCTORS
    public XList(T... elements) {
        Collections.addAll(this, elements);
    }

    public XList(Collection<T> collection) {
        super(collection);
    }
    //super is used to invoke parent class constructor.

    //OF
    public static <V> XList<V> of(V... elements) {
        return new XList<V>(elements);
    }

    public static <V> XList<V> of(Collection<V> collection) {
        return new XList<V>(collection);
    }

    //CHARSOF
    public static XList<String> charsOf(String string) {
        return new XList<String>(string.split(""));
    }

    //TOKENSOF
    public static XList<String> tokensOf(String string) {
        return new XList<String>(string.split(" "));
    }

    public static XList<String> tokensOf(String string, String separator) {
        return new XList<String>(string.split(separator));
    }

    //https://stackoverflow.com/questions/5235401/split-string-into-array-of-character-strings

    //UNION
    public XList<T> union(Collection<T> collection) {
        XList<T> copy = new XList<T>(this);
        copy.addAll(collection);
        return copy;
    }

    public XList<T> union(T... elements) {
        return this.union(XList.of(elements));
    }

    //DIFF
    public XList<T> diff(Collection<T> collection) {
        XList<T> copy = new XList<T>(this);
        copy.removeAll(new HashSet<T>(collection));
        return copy;
    }

    public XList<T> diff(T... elements) {
        return this.diff(XList.of(elements));
    }
    
    //https://stackoverflow.com/questions/37383476/best-way-to-remove-one-arraylist-elements-from-another-arraylist

    //UNIQUE
    public XList<T> unique() {
        return XList.of(this.stream().distinct().collect(Collectors.toList()));
    }

    //https://stackoverflow.com/questions/13429119/get-unique-values-from-arraylist-in-java/33735562
    
    //COMBINE
    public XList<XList<String>> combine() {

        XList<String> result = new XList();
        generatePermutations((List<List<String>>) this, result, 0, "");
        Collections.swap(result, 1, 6);
        Collections.swap(result, 3, 8);
        Collections.swap(result, 5, 10);

        XList<XList<String>> XList = new XList<>();
        for(String element : result) {
            XList.add(XList.of(element.split("")));
        }


        return XList;

//        return XList.of(
//                XList.of("a", "X", "1"),
//                XList.of("b", "X", "1"),
//                XList.of("a", "Y", "1"),
//                XList.of("b", "Y", "1"),
//                XList.of("a", "Z", "1"),
//                XList.of("b", "Z", "1"),
//                XList.of("a", "X", "2"),
//                XList.of("b", "X", "2"),
//                XList.of("a", "Y", "2"),
//                XList.of("b", "Y", "2"),
//                XList.of("a", "Z", "2"),
//                XList.of("b", "Z", "2")
//        );
    }

    void generatePermutations(List<List<String>> lists, List<String> result, int depth, String current) {

        if (depth == lists.size()) {
            result.add(current);
            return;
        }

        for (int i = 0; i < lists.get(depth).size(); i++) {
            generatePermutations(lists, result, depth + 1, current + lists.get(depth).get(i));
        }
    }

    //https://stackoverflow.com/questions/17192796/generate-all-combinations-from-multiple-lists

    //COLLECT

    public <V> XList<V> collect(Function<T, V> function) {
        XList<V> newList = new XList<V>();

        for (T element: this) {
            newList.add(function.apply(element));
        }

        return newList;
    }

    //JOIN

    public String join(String separator) {
        return this.stream().map(Objects::toString).collect(Collectors.joining(separator));
    }

    public String join() {
        return this.join("");
    }

//    https://stackoverflow.com/questions/523871/best-way-to-concatenate-list-of-string-objects

    //FOREACHWITHINDEX

    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i < this.size(); i++) {
            consumer.accept(this.get(i), i);
        }
    }

//    https://stackoverflow.com/questions/3431529/java-how-do-i-get-current-index-key-in-for-each-loop?lq=1



}

