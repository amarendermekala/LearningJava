package learning.maps;

import learning.comparators.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Maps {

    public static void main(String[] args) {
        Map<String, String> m = new HashMap<>();
        m.put("amar", "reddy");
        m.forEach((key, value) -> System.out.println(key + " " + value));
        // amar reddy

        String d = m.get("asd");
        // d is null, but it could be that asd null is stored in the map
        System.out.println(d);
        // null

        String d1 = m.getOrDefault("asd", "defaultValue");
        System.out.println(d1);
        // defaultValue

        // m.put("amar", "asd"); // will erase previous
        m.putIfAbsent("amar", "123123");
        m.forEach((key, value) -> System.out.println(key + " " + value));
        // amar reddy

        String s = m.computeIfAbsent("amar1", (key) -> key + "asdasasd");
        System.out.println(s);
        // amar1asdasasd
        m.forEach((key, value) -> System.out.println(key + " " + value));
        // amar reddy
        // amar1 amar1asdasasd

        m.replace("amar", "asasas");
        m.forEach((key, value) -> System.out.println(key + " " + value));
        // amar asasas
        // amar1 amar1asdasasd


        Map<String, Map<String, Person>> bimap = new HashMap<>();
        bimap.computeIfAbsent("asd", key -> new HashMap<>()).put("asdasd", new Person(1, "asdas"));

        Map<String, Integer> prices = new HashMap<>();
        System.out.println("Prices map: " + prices);
        // Prices map: {}

        // Integer::sum(a,b) is perfect two-argument
        // function (BiFunction)
        prices.merge("fruits", 3, Integer::sum);
        prices.merge("fruits", 5, Integer::sum);
        prices.merge("fruits1", 5, Integer::sum);
        prices.forEach((key, value) -> System.out.println(key + " " + value));
        // fruits1 5
        // fruits 8


        List<Person> personList1 = new ArrayList<>();
        personList1.add(new Person(1, "Amar"));
        personList1.add(new Person(2, "Redy"));
        personList1.add(new Person(1, "Mekala"));


        List<Person> personList2 = new ArrayList<>();
        personList2.add(new Person(4, "abhi"));
        personList2.add(new Person(5, "jeet"));
        personList2.add(new Person(2, "pranit"));


        Map<Integer, List<Person>> m1 = mapByAge(personList1);
        Map<Integer, List<Person>> m2 = mapByAge(personList2);

        System.out.println(m1);
        // {1=[Person{age=1, name='Amar'}, Person{age=1, name='Mekala'}], 2=[Person{age=2, name='Redy'}]}
        System.out.println(m2);
        // {2=[Person{age=2, name='pranit'}], 4=[Person{age=4, name='abhi'}], 5=[Person{age=5, name='jeet'}]}


        m2.entrySet().stream()
                .forEach(
                        entry -> m1.merge(entry.getKey(),
                                entry.getValue(),
                                (l1, l2) -> {
                                    l1.addAll(l2);
                                    return l1;
                                })
                );
        System.out.println(m1);
        // {
        // 1=[Person{age=1, name='Amar'}, Person{age=1, name='Mekala'}],
        // 2=[Person{age=2, name='Redy'}, Person{age=2, name='pranit'}],
        // 4=[Person{age=4, name='abhi'}],
        // 5=[Person{age=5, name='jeet'}]
        // }


        // Bimap

        personList1 = new ArrayList<>();
        personList1.add(new Person(1, "Amar"));
        personList1.add(new Person(1, "Amar"));
        personList1.add(new Person(2, "Redy"));
        personList1.add(new Person(1, "Mekala"));

        Map<Integer, Map<String, List<Person>>> bimap1 = new HashMap<>();
        personList1.forEach(
                person ->
                        bimap1.computeIfAbsent(
                                person.getAge(),
                                HashMap::new
                        ).merge(
                                person.getName(),
                                new ArrayList<Person>(Arrays.asList(person)),
                                (l1, l2) -> {
                                    l1.addAll(l2);
                                    return l1;
                                })
        );

        System.out.println(bimap1);
        // {1={Amar=[Person{age=1, name='Amar'}, Person{age=1, name='Amar'}], Mekala=[Person{age=1, name='Mekala'}]}, 2={Redy=[Person{age=2, name='Redy'}]}}
    }

    private static Map<Integer, List<Person>> mapByAge(List<Person> persons) {
        Map<Integer, List<Person>> p = persons.stream().collect(Collectors.groupingBy(Person::getAge));
        return p;
    }
}
