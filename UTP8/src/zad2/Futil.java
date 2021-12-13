package zad2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Futil {

    private static List<String> output = new ArrayList<>();

    public static void processDir(String dirName, String resultFileName) {
        try {
            FileVisitor<Path> fileVisitor = new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path visitedFilePath, BasicFileAttributes fileAttributes) throws IOException {
                    try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(new FileInputStream(visitedFilePath.toFile()), "cp1250"))) {
                        String str;
                        while ((str = in.readLine()) != null) {
                            output.add(str);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            };

            Files.walkFileTree(Paths.get(dirName), fileVisitor);

            Files.write(Paths.get(resultFileName), output, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//https://www.netjstech.com/2017/04/reading-all-files-in-folder-java-program.html
//https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file
//https://stackoverflow.com/questions/37858082/what-is-the-difference-between-files-list-and-files-walkfiletree-and-files-walk
//https://www.delftstack.com/howto/java/how-to-read-files-from-a-folder-in-java/