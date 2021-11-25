package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {

    private List<String> fileContent;

    /*
    Read file line by line and add each line to List<String>
     */
    public ProgLang(String fileName) {
        fileContent = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.add(line.replaceAll("\\s+", " "));
            }
        }  catch (Exception exc) {
            System.out.println("Wadliwy konstruktor: " + exc);
        }
        //https://javarevisited.blogspot.com/2016/10/how-to-split-string-in-java-by-whitespace-or-tabs.html#axzz7BeVCsu7p

    }

    /*
    Split line after first " ",
    1st part is a language name
    2nd part contains programmers "names" (as one string) - this part is splitted to get each programmers "name"
     */
    public Map<String, ArrayList<String>> getLangsMap() {
        Map<String, ArrayList<String>> languageMap = new LinkedHashMap<>();

        for(String line : fileContent) {
            String[] arr = line.split(" ", 2);
            String key = arr[0];
            LinkedHashSet<String> values = new LinkedHashSet(Arrays.asList(arr[1].split(" ")));
            ArrayList<String> list = new ArrayList<>(values);

            languageMap.put(key, list);
        }

        return languageMap;
    }

    public Map<String, ArrayList<String>> getProgsMap() {
        LinkedHashSet<String> programmersOrder = new LinkedHashSet<>();

        //Extract programmers name
        for(String line : fileContent) {
            String[]arr = line.split(" ", 2);
            programmersOrder.addAll(Arrays.asList(arr[1].split(" ")));
        }

        Map<String, ArrayList<String>> languageMap = getLangsMap();
        Map<String, ArrayList<String>> programmersAndLanguagesMap = new LinkedHashMap<>();


        /*
        For each programmer finds his languages (finds which "languageList" contains his name)
         */
        for(String programmer : programmersOrder) {
            ArrayList<String> languages = new ArrayList();
            languageMap.entrySet().stream().forEach(
                    (e) -> {
                        String language = e.getKey();
                        if(e.getValue().contains(programmer)){
                            languages.add(language);
                        }
                    }
            );

            programmersAndLanguagesMap.put(programmer, languages);
        }

        return programmersAndLanguagesMap;
    }


    public Map<String, ArrayList<String>> getLangsMapSortedByNumOfProgs() {
        /*
        //Compare by values size
        Map<String, ArrayList<String>> languageMap = getLangsMap()
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt((e) -> e.getValue().size()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));

        //How to reverse languageMap's order more "gently"?
        //https://stackoverflow.com/questions/32995559/reverse-a-comparator-in-java-8 ?

        //Reverse order of the map
        Map<String, ArrayList<String>> result = new LinkedHashMap<>();
        List<String> allKeys = new ArrayList<>(languageMap.keySet());
        Collections.reverse(allKeys);
        for(String strKey : allKeys){
            result.put(strKey, languageMap.get(strKey));
        }

        return result;
        */
        return sorted(getLangsMap(),
                (e1, e2) -> {
                    int difference = e2.getValue().size() - e1.getValue().size();

                    if (difference == 0) {
                        return e1.getKey().compareTo(e2.getKey());
                    }

                    return difference;
                });
    }

    public Map<String, ArrayList<String>> getProgsMapSortedByNumOfLangs() {
        /*
        //Changing the "nature" of sorting a map, now it's sorting A-Z
        TreeMap<String, ArrayList<String>> treemap = new TreeMap(Comparator.reverseOrder());
        treemap.putAll(getProgsMap());

        //Compare by values size
        Map<String, ArrayList<String>> programmersAndLanguagesMap = treemap.entrySet()
                .stream()
                .sorted(Comparator.comparingInt((e) -> e.getValue().size()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));

        //Reverse order of the map
        Map<String, ArrayList<String>> result = new LinkedHashMap<>();
        List<String> allKeys = new ArrayList<>(programmersAndLanguagesMap.keySet());
        Collections.reverse(allKeys);
        for(String strKey : allKeys){
            result.put(strKey, programmersAndLanguagesMap.get(strKey));
        }

        return result;
        */
         return sorted(getProgsMap(),
                 (e1, e2) -> {
                     int difference = e2.getValue().size() - e1.getValue().size();

                     if (difference == 0) {
                         return e1.getKey().compareTo(e2.getKey());
                     }

                     return difference;
                 });
    }

    public Map<String, ArrayList<String>> getProgsMapForNumOfLangsGreaterThan(int i) {
/*
        Map<String, ArrayList<String>> progsMap = getProgsMap();
        return progsMap.entrySet().stream().filter(
                (x) -> {
                    return x.getValue().size() > i;
                }
        ).collect(Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue,
                (a, b) -> { throw new AssertionError(); },
                LinkedHashMap::new
        ));
 */
        return filtered(getProgsMap(), e -> e.getValue().size() > i);
    }

    /*
        Dopiero po zrobieniu całego zadania doczytałem, że trzeba napisać sorted/filtered...
        To co jest zakomentarzowane zostało napisane PRZED napisaniem metod sorted i filtered
     */

    public <T, U> Map<T, U> sorted(Map<T, U> map, Comparator<Map.Entry<T, U>> function) {
        return map
                .entrySet()
                .stream()
                .sorted(function)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));
    }

    public <T, U> Map<T, U> filtered(Map<T, U> map, Predicate<Map.Entry<T, U>> function) {
        return map
                .entrySet()
                .stream()
                .filter(function)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));
    }

}

//https://stackoverflow.com/questions/30853117/how-can-i-sort-a-map-based-upon-on-the-size-of-its-collection-values
//collectowanie mapy^