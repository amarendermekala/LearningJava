package learning.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

    public static void main(String[] args) {
        Person p1 = new Person("Amar", 1);
        Person p2 = new Person("Reddy", 2);
        Person p4 = new Person("Reddy", 123);
        Person p5 = new Person("Reddy", 123);
        Person p3 = new Person("Mekala", 3);

        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);
        persons.add(p5);

        persons.stream().forEach(System.out::println); // Method reference
        persons.stream().forEach(p -> System.out.println(p)); // Lambdas
        // Accepts consumer


        System.out.println(persons.stream().collect(Collectors.groupingBy(Person::getName, Collectors.groupingBy(Person::getAge))));

    }
}
