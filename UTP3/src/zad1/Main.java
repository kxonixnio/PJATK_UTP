/**
 *
 *  @author Tomczyk Mikołaj S22472
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {

    //https://stackoverflow.com/questions/5868369/how-can-i-read-a-large-text-file-line-by-line-using-java
    Function<String, List<String>> flines = (filePath) -> {
      List<String> fileLineByLine = new ArrayList<>();

      try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
        String line;
        while((line = br.readLine()) != null){
          fileLineByLine.add(line);
        }
      } catch (IOException e) {
        e.printStackTrace();
        return new ArrayList<String>();
      }

      return fileLineByLine;
    };

    //https://stackoverflow.com/questions/523871/best-way-to-concatenate-list-of-string-objects
    Function<List<String>, String> join = (list) -> list.stream().collect(Collectors.joining());

    Function<String, List<Integer>> collectInts = (text) -> {
      //https://www.baeldung.com/java-find-numbers-in-string
      Pattern integerPattern = Pattern.compile("-?\\d+");
      Matcher matcher = integerPattern.matcher(text);

      List<Integer> integerList = new ArrayList<>();
      while (matcher.find()) {
        integerList.add(Integer.parseInt(matcher.group()));
      }

      return integerList;
    };

    //https://stackoverflow.com/questions/5963847/is-there-possibility-of-sum-of-arraylist-without-looping/5963867
    Function<List<Integer>, Integer> sum = (listOfIntegers) -> listOfIntegers.stream().mapToInt(Integer::intValue).sum();

    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
