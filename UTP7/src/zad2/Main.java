/**
 *
 *  @author Tomczyk Mikołaj S22472
 *
 */

package zad2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream is = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").openConnection().getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        Stream<String> stream = reader.lines();

        Map<String, List<String>> anagrams = stream.collect(Collectors.groupingBy(w -> sorted(w)));
        Integer maxSize = anagrams.values().stream().map(List::size).max(Integer::compare).get();
        anagrams.values().stream().filter(e -> e.size() == maxSize).forEach(e -> System.out.println(e.stream().collect(Collectors.joining(" "))));
    }

    public static String sorted(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}

/*
https://stackoverflow.com/questions/40756599/searching-anagrams-with-java-8
https://stackoverflow.com/questions/36782231/printing-a-java-map-mapstring-object-how
 */