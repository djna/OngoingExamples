package org.djna;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.partitioningBy;


public class Person implements Comparable<Person>, Printable {
    private String name;
    private LocalDate dateOfBirth;
    private int favouriteNumber;

    public Person(String name, LocalDate dateOfBirth, int favouriteNumber) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.favouriteNumber = favouriteNumber;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return favouriteNumber == person.favouriteNumber && Objects.equals(name, person.name) && Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, favouriteNumber);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", favouriteNumber=" + favouriteNumber +
                "}\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getFavouriteNumber() {
        return favouriteNumber;
    }

    public void setFavouriteNumber(int favouriteNumber) {
        this.favouriteNumber = favouriteNumber;
    }

    @Override
    public void printSelf(){
        System.out.printf("%s", this);
    }

    static void printPeople( List<Person> people, Comparator<Person> comparator){
        people.stream().sorted(comparator).forEach(Person::printSelf);
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(
            this.favouriteNumber,
            o.favouriteNumber
            );
    }

/*
    @Override
    public int compareTo(Person o) {
        return dateOfBirth.compareTo(o.dateOfBirth);
    } */

    public boolean isSuitable( CheckPerson checker){
        return checker.test(this);
    }

    public static int compareByDob( Person p1, Person p2){
        return p1.dateOfBirth.compareTo(p2.dateOfBirth);
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Adam", LocalDate.of(1977, 5, 25), 32));
        people.add(new Person("Beatrice", LocalDate.of(1978, 3, 18), 66));
        people.add(new Person("Charles", LocalDate.of(2010, 2, 8), 8));
        people.add(new Person("Delilah", LocalDate.of(2013, 3, 25), 7));
        people.add(new Person("Evan", LocalDate.of(1953, 4, 4), 16));
        people.add(new Manager("Frida", LocalDate.of(1956, 11, 20), 3));

        System.out.printf("People %s%n", people);

        Person theMax = Algorithm.max(people, 1, 3);
        System.out.printf("The max %s%n", theMax);

        Collections.sort(people);

        System.out.printf("%nSorted People %s%n", people);

        Comparator<Person> favoriteNumberComparator = new Comparator<>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.favouriteNumber, o2.favouriteNumber);
            }
        };

        Collections.sort(people, favoriteNumberComparator);

        System.out.printf("%nSorted People by favourite number %n%s%n", people);


        Collections.sort(people,
                new Comparator<Person>() {
                    @Override
                    public int compare(Person o1, Person o2) {
                        return Integer.compare(o2.favouriteNumber, o1.favouriteNumber);
                    }
                }
        );

        Collections.sort(people, Person::compareByDob);

        System.out.printf("%n!!!Sorted People using dob %n%s%n", people);

        Collections.sort(people,
                (o1,  o2) ->
                         Integer.compare(o1.favouriteNumber, o2.favouriteNumber)

        );

        System.out.printf("%nSorted People by favourite number %n%s%n", people);

        System.out.printf("%nForEach%n");

        people.stream().forEach(
                person -> System.out.printf("%s%n", person)
        );

        System.out.printf("%nForEach with method reference%n");

        people.stream().forEach(
                Person::printSelf
        );

        HashMap<String, Comparator<Person>> comparators = new HashMap<>();
        comparators.put (
                "favouriteNumber",
                (o1,  o2) ->
                        Integer.compare(o1.favouriteNumber, o2.favouriteNumber)
        );
        comparators.put (
                "name",
                Comparator.comparing(
                        Person::getName,
                        String.CASE_INSENSITIVE_ORDER)
        );

        System.out.printf("%nPrint sorted by name %n");
        printPeople(people, comparators.get("name") );

        people.stream().filter(
               candidate -> candidate.isSuitable( p -> p.favouriteNumber % 2 == 0)
            ).forEach(
                    p ->  System.out.printf("%n suitable person %s %n", p)
            );

        System.out.printf("%nAll%n");
        people.stream().forEach(
                p ->  System.out.printf("%s", p)
        );

        System.out.printf("%nSkip 3%n");
        people.stream().skip(3).forEach(
                p ->  System.out.printf("%s", p)
        );

        System.out.printf("%nSkip 3%n");
        people.stream().skip(3).forEach(
                p ->  System.out.printf("%s", p)
        );

        System.out.printf("%nFirst%n");
        Optional<Person> maybeFirst = people.stream().findFirst();

        maybeFirst.ifPresentOrElse(
                p -> System.out.printf("%s", p),
                () -> System.out.printf("Not found")
        );

        System.out.printf("%nFirst in Empty%n");
        List<Person> empty = new ArrayList<>();
        maybeFirst = empty.stream().findFirst();

        maybeFirst.ifPresentOrElse(
                p -> System.out.printf("%s", p),
                () -> System.out.printf("Not found")
        );


        System.out.printf("%nMarch people%n");
        people.stream().filter(
                p -> p.dateOfBirth.getMonth() == Month.MARCH
        ).forEach(
                p ->  System.out.printf("%s", p)
        );

        System.out.printf("%nMarch people%n");
        long mayCount = people.stream().filter(
                p -> p.dateOfBirth.getMonth() == Month.MAY
        ).count();
        System.out.printf("%n%d May people%n", mayCount);

        System.out.printf("%nAverage Favor%n");
        int total = people.stream().map(
                p -> p.favouriteNumber
        ).reduce(Integer::sum).get();

        System.out.printf("Total %d Count %d Average %d%n",
                total, people.size(), total/people.size());

        System.out.printf("%nBefore 2000%n");
        people.stream().filter(
                p-> p.dateOfBirth.getYear() < 2000
        ).map(
                p -> p.name + ", " + p.dateOfBirth.getYear() + "; "
        ).forEach(
                p ->  System.out.printf("%s ", p)
        );

        Map<Boolean, List<Person> > partitions =
                people.stream()
                        .collect(partitioningBy(p -> p.dateOfBirth.getYear() >= 2000));

        System.out.printf("%nPartition Before 2000%n");
        partitions.get(false)
                        .stream()
                        .forEach(
                                p ->  System.out.printf("%s ", p)
                        );

        System.out.printf("%nPartition After 2000%n");
        partitions.get(true)
                .stream()
                .forEach(
                        p ->  System.out.printf("%s ", p)
                );

        System.out.printf("%nNo I in Collection %n");

        people.stream()
                .map( p -> p.getName().toLowerCase() )
                .filter ( n ->  n.contains("i") )
                .forEach(
                        p ->  System.out.printf("%s, ", p)
                );

        System.out.printf("%n%nC me %n");

        List<Person> collectedC = people.stream()
                .filter ( p -> p.getName().toLowerCase().contains("c") )
                .collect(Collectors.toList());

        System.out.printf("%n Collected C %s %n", collectedC);


        System.out.printf("%nCount after 2000%n");
        long theCount = people.stream().filter(
                p-> p.dateOfBirth.getYear() > 2000
        ).count();
        System.out.printf("%nCount aftr 2000 %d %n", theCount);

        System.out.printf("%nMapping%n");

        Map<String, LocalDate> nameDateMap = people.stream()
                .filter(p -> p.favouriteNumber > 8)
                .collect( Collectors.toMap( Person::getName, Person::getDateOfBirth)
        );

        System.out.printf("%nMapping %s %n", nameDateMap);

        String[] skills = {"Java", "SQL", "C#", "Perl", "Java", "Perl", "SQL"};
        List<String> skillList = Arrays.asList(skills);

        skillList.stream().distinct().forEach(
                s -> System.out.printf("%s%n", s)
        );
    }


}
