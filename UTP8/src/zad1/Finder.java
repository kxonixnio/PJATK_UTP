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
import java.util.stream.Collectors;

public class Finder {

    private String fname;
    private List<String> fileContentLineByLine;
    private String fileContentWordByWord = "";

    public Finder(String fname) {
        this.fname = fname;
        fileContentLineByLine = new ArrayList<>();
        readFile(fname);

        //Regex usuwający komentarze blokowe ze Stringa(!)
        fileContentWordByWord = fileContentWordByWord.replaceAll("\\/\\*([\\S\\s]+?)\\*\\/", "");
//        System.out.println(fileContentWordByWord);

        //"Przekonwertowanie" Stringa na Listę Stringów (Każda linia to osobny String)
        StringBuilder sb = new StringBuilder();
        for(char c : fileContentWordByWord.toCharArray()) {
            if(c != '\n') {
                sb.append(c);
            }else{
                fileContentLineByLine.add(sb.toString());
                sb = new StringBuilder();
            }
        }

        //Usunięcie komentarzy liniowych (//) (tego, co po nich)
        fileContentLineByLine = fileContentLineByLine.stream().map(s -> {
            if(s.contains("//")) {
                return s.substring(0, s.indexOf("//"));
            }
            return s;
        }).collect(Collectors.toList());
    }

    public void readFile(String fname) {
        try(BufferedReader br = new BufferedReader(new FileReader(fname))) {
            for(String line; (line = br.readLine()) != null; ) {
//                fileContentLineByLine.add(line);
                fileContentWordByWord += line += "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getIfCount() {
        /*
        Zwrócenie wszystkich linii posiadających jakkolwiek "if"
        Usunięcie wszystkich białych znaków (Tak, żeby z " if ( " zrobić " if( "
        Usunięcie wszystkich literałów napisowych. Nie chcemy liczyć ifów, które są napisem.
        Założeniem jest, że kod jest kompilowalny, zatem wystarczy, że sprawdzimy czy if ma nawias otwierający ( "if(" )

         */
        List<String> copy =
                fileContentLineByLine
                        .stream()
                        .filter(s -> { return s.contains("if"); })
                        .map(s -> { return s.trim().replaceAll(" +", ""); })
                        .map(s -> {
                            if(s.contains("\"")){
                                int firstQuotationMarkIndex = s.indexOf("\"");
                                int secondQuotationMarkIndex = s.indexOf("\"", firstQuotationMarkIndex + 1);
                                s = s.substring(0, firstQuotationMarkIndex);
                                s = s.substring(secondQuotationMarkIndex, s.length());

                                return s;
                            }
                            return s;
                        })
                        .collect(Collectors.toList());

        return (int) copy.stream().filter(s -> { return s.contains("if("); }).count();
    }

    public int getStringCount(String pattern) {
        /*
        Zwrócenie wszystkich linii posiadających jakikolwiek literał napisowy
        Usunięcie zbędnych spacji
        "Wyciągnięcie" samych literałów napisowych

        Można to ulepszyć, bo:
        -w jednej linii mogą być dwa literały napisowe (String x = "wariant"; String y = "wariant";
        Ale absolutnie nikt tak nie pisze, więc nie biorę tego przypadku pod uwagę
         */
        List<String> copy =
                fileContentLineByLine
                        .stream()
                        .filter(s -> { return s.contains("\""); })
                        .map(s -> { return s.trim().replaceAll(" +", " "); })
                        .map(s -> {
                            s = s.substring(s.indexOf("\"") + 1);
                            s = s.substring(0, s.indexOf("\""));
                            return s;
                        })
                        .collect(Collectors.toList());

        //W jednym literale napisowym (w "linii") "wariant" może wystąpić kilka razy, stąd podział na pojedyncze słowa
        List<String> words = copy.stream().flatMap(s -> Arrays.stream(s.split(" "))).collect(Collectors.toList());
        return (int) words.stream().filter(s -> { return s.contains(pattern); }).count();

//        return (int) copy.stream().filter(s -> { return s.contains(pattern); }).count();
    }
}


//https://stackoverflow.com/questions/24323383/remove-all-comments-in-java-files
//https://stackoverflow.com/questions/5192512/how-can-i-clear-or-empty-a-stringbuilder
//https://stackoverflow.com/questions/4510136/how-to-check-if-a-char-is-equal-to-an-empty-space/4510147
//https://stackoverflow.com/questions/196830/what-is-the-easiest-best-most-correct-way-to-iterate-through-the-characters-of-a
//https://stackoverflow.com/questions/3756657/replace-remove-string-between-two-character
//https://stackoverflow.com/questions/12595019/how-to-get-a-string-between-two-characters/12595052
//https://stackoverflow.com/questions/2932392/java-how-to-replace-2-or-more-spaces-with-single-space-in-string-and-delete-lead