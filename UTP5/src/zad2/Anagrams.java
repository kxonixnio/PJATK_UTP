/**
 *
 *  @author Tomczyk Mikołaj S22472
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Anagrams {

    private List<String> allWords;

    public Anagrams(String fileName) {
        this.allWords = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.allWords.addAll(Arrays.asList(line.split(" ")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ArrayList<String>> getSortedByAnQty() {
        ArrayList<ArrayList<String>> listOfListsForAnagrams = new ArrayList<ArrayList<String>>();

        //For each word...
        for (String wordFromAllWordsList : this.allWords) {
            boolean isFound = false;

            //Check each of anagrams lists...
            for (ArrayList<String> anagramList: listOfListsForAnagrams) {
                String wordFromAnagramList = anagramList.get(0);

                if (areAnagrams(wordFromAnagramList, wordFromAllWordsList)) {
                    anagramList.add(wordFromAllWordsList);
                    isFound = true;
                    break;
                }
            }

            //If given word didn't find an anagram in all of the lists, create a new list
            if (!isFound) {
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(wordFromAllWordsList);
                listOfListsForAnagrams.add(newList);
            }
        }

        listOfListsForAnagrams.sort(((o1, o2) -> {
            return o2.size() - o1.size();
        }));

        return listOfListsForAnagrams;
    }
    //https://stackoverflow.com/questions/3477272/java-how-do-i-sort-multiple-arraylist-by-their-size

    public String getAnagramsFor(String searchedWord) {

        List<ArrayList<String>> listOfListsForAnagrams = this.getSortedByAnQty();
        listOfListsForAnagrams
                .stream()
                .filter((list) -> areAnagrams(searchedWord, list.get(0)))
                .findAny()
                .orElse(null);

        List<String> anagramsForSearchedWord = new ArrayList<>();

        for(List<String> list : listOfListsForAnagrams){
            if(list.contains(searchedWord)){
                anagramsForSearchedWord = list;
                break;
            }else{
                anagramsForSearchedWord = null;
            }
        }

        if (anagramsForSearchedWord == null) {
            return searchedWord + ": null";
        }

        List<String> withoutSearchedWord = new ArrayList<>(anagramsForSearchedWord);
        withoutSearchedWord.removeIf(word -> word.equals(searchedWord));

        return searchedWord + ": " + withoutSearchedWord;
    }

    static final int NO_OF_CHARS = 256;

    /* function to check whether two
    strings are anagram of each other */
    static boolean areAnagrams(String str1, String str2)
    {
        // Create two count arrays and initialize
        // all values as 0
        int[] count = new int[NO_OF_CHARS];
        int i;

        // For each character in input strings,
        // increment count in the corresponding
        // count array
        for (i = 0; i < str1.length() && i < str2.length();
             i++)
        {
            count[str1.charAt(i)]++;
            count[str2.charAt(i)]--;
        }

        // If both strings are of different length.
        // Removing this condition will make the program
        // fail for strings like "aaca" and "aca"
        if (str1.length() != str2.length())
            return false;

        // See if there is any non-zero value in
        // count array
        for (i = 0; i < NO_OF_CHARS; i++)
            if (count[i] != 0)
                return false;
        return true;
    }
    //https://www.geeksforgeeks.org/print-pairs-anagrams-given-array-strings/
}