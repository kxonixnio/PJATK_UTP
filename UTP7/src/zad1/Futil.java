package zad1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.stream.Stream;

public class Futil {

//    private static List<String> content = new ArrayList<>();
    private static Stream<String> stream = Stream.empty();

    public static void processDir(String dirName, String resultFileName) {

        try(Stream<Path> paths = Files.walk(Paths.get(dirName))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    try {
//                        readContent(filePath);
                        stream = Stream.concat(stream, readContentStream(filePath));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

//            Files.write(Paths.get(resultFileName), content, StandardCharsets.UTF_8);
            Files.write(Paths.get(resultFileName), (Iterable<String>) stream::iterator, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public static void readContent(Path filePath) throws IOException{
//        content.add(Files.readAllLines(filePath, Charset.forName("cp1250")).get(0));
//    }

    public static Stream<String> readContentStream(Path filePath) throws IOException{
        return Files.lines(filePath, Charset.forName("cp1250"));
    }
}

//https://www.netjstech.com/2017/04/reading-all-files-in-folder-java-program.html
//https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file