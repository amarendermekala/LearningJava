package learning.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ReadingFiles {

    public static void main(String... args) {

        try (BufferedReader reader = new BufferedReader(
                new FileReader(
                        new File("/Users/Amar/Documents/Workspace/LearningJava/src/learning/files/path.txt")))) {

            // lines method has been added on the BufferedReader class
            Stream<String> lines = reader.lines();
            lines.filter(line -> line.contains("Hi")).findFirst().ifPresent(System.out::println);


        } catch (IOException e) {

        }
        // reader.lines

        Path path = Paths.get("D:", "tmp", "path.txt");
        try(Stream<String> lines = Files.lines(path)) {
            lines.filter(line -> line.contains("Hi")).findFirst().ifPresent(System.out::println);
        } catch(IOException e) {

        }

        Predicate<String> isStringLengthGreaterThan20 = s -> s.length() > 20;
        Predicate<String> p2 = s -> s.length() < 10;
        // isStringLengthGreaterThan20.test("asdsad") && p2.test().and(isStringLengthGreaterThan20.test("3"));
        // Files.lines

        // Stream implement AutoCloseable, and will close the underlying file.
        Path path2 = Paths.get("/Users", "Amar", "Documents");
        try (Stream<Path> paths = Files.list(path2)) {
            paths.filter(path1 -> path1.toFile().isDirectory()).forEach(System.out::println);
            // Prints all paths of directories inside it
            // /Users/Amar/Documents/Learning
            // /Users/Amar/Documents/Workspace
        } catch (IOException e) {
            System.out.println(e);
        }

        // Stream implement AutoCloseable, and will close the underlying file.
        path2 = Paths.get("/Users", "Amar", "Documents");
        try (Stream<Path> paths = Files.walk(path2, 2)) {
            paths.filter(path1 -> path1.toFile().isDirectory()).forEach(System.out::println);
            // Prints all paths of directories inside it
            // /Users/Amar/Documents
            // /Users/Amar/Documents/Learning
            // /Users/Amar/Documents/Learning/Java
            // /Users/Amar/Documents/Workspace
            // /Users/Amar/Documents/Workspace/LearningJava
            // /Users/Amar/Documents/Workspace/123
            // /Users/Amar/Documents/Workspace/456
        } catch (IOException e) {
            System.out.println(e);
        }


    }

}
