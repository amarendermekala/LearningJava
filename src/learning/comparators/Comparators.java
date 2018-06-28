package learning.comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Comparators {
    public static void main(String... args) {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person(1, "Amar"));
        persons.add(new Person(2, "Mekala"));
        persons.add(new Person(24, "Mekala"));
        persons.add(new Person(3, "Reddy"));
        // persons.add(null); Fails

        Comparator<Person> compareLast = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getName().compareTo(o1.getName());
            }
        };

        // We need to check if o1 or o2 is null
        // If more comparisons, it becomes difficult

        Collections.sort(persons, compareLast);
        System.out.println(persons);
        // [Person{age=3, name='Reddy'}, Person{age=2, name='Mekala'}, Person{age=24, name='Mekala'}, Person{age=1, name='Amar'}]

        Comparator<Person> compareLastAndAge = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int nameDiff = o2.getName().compareTo(o1.getName());
                return nameDiff == 0 ? o2.getAge() - o1.getAge() : nameDiff;
            }
        };
        Collections.sort(persons, compareLastAndAge);
        System.out.println(persons);
        // [Person{age=3, name='Reddy'}, Person{age=24, name='Mekala'}, Person{age=2, name='Mekala'}, Person{age=1, name='Amar'}]

        Comparator<Person> personComparatorName = Comparator.comparing(Person::getName);
        Collections.sort(persons, personComparatorName);
        System.out.println(persons);

        Comparator<Person> personComparatorNameAge = Comparator.comparing(Person::getName).thenComparing(Person::getAge);
        Collections.sort(persons, personComparatorNameAge);
        System.out.println(persons);

        Comparator.naturalOrder();
        Comparator.reverseOrder();
        Comparator.naturalOrder().reversed();

        Comparator.nullsFirst(Comparator.naturalOrder());

    }
}
