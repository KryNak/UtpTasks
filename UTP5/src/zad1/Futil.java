package zad1;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {

    public static void processDir(String startDir, String resultFile) {

        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.txt");

        Path startDirPath = Paths.get(startDir);
        Path resultFilePath = Paths.get(resultFile);

        Charset inCharset = Charset.forName("Cp1250");
        Charset outCharset = StandardCharsets.UTF_8;

        try {

            new PrintWriter(resultFile, outCharset).append("").close();

            Files.walkFileTree(startDirPath, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    if (matcher.matches(file.getFileName()))
                        Files.write(resultFilePath, Files.readAllLines(file, inCharset), outCharset, StandardOpenOption.APPEND);


                    return FileVisitResult.CONTINUE;
                }

            });

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
