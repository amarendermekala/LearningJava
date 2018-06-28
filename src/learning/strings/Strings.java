package learning.strings;

import java.util.StringJoiner;
import java.util.stream.IntStream;

public class Strings {

    public static void main(String... args) {
        String s = "hello world";
        IntStream stream = s.chars();
        stream.mapToObj(letter -> (char) letter)
                .map(Character::toUpperCase)
                .forEach(System.out::print);
        // HELLO WORLD

        System.out.println();

        String s1 = "Hello";
        String s2 = "World";

        String s3 = s1 + " " + s2;

        System.out.println(s3);
        // Hello World

        StringBuilder sb1 = new StringBuilder();
        sb1.append("Hi").append(" ").append("Amar");
        // Hi Amar

        // Thread Safe
        StringBuffer sb2 = new StringBuffer();
        sb2.append("Hi").append(" ").append("Amar");
        // Hi Amar

        System.out.println(sb1.toString());
        System.out.println(sb2.toString());

        StringJoiner sj1 = new StringJoiner(", ");
        sj1.add("one").add("two").add("three");
        System.out.println(sj1.toString());
        // one, two, three

        StringJoiner sj2 = new StringJoiner(", ", "{", "}");
        sj2.add("one").add("two").add("three");
        System.out.println(sj2.toString());
        // {one, two, three}

        sj2 = new StringJoiner(", ", "{", "}");
        System.out.println(sj2.toString());
        // {}

        String stringJoin = String.join(", ", "one", "two", "three");
        System.out.println(stringJoin);
        // one, two, three

        String[] tab = {"one", "two", "three"};
        String stringJoin2 = String.join(", ", tab);
        System.out.println(stringJoin2);
        // one, two, three
    }

}
