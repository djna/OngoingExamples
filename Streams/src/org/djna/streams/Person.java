package org.djna.Streams;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

import static java.util.stream.Collectors.partitioningBy;


public class Person implements Comparable<Person> {
    private String name;
    private LocalDate dateOfBirth;
    private int favouriteNumber;
    private String department;

    public Person(String name, LocalDate dateOfBirth, int favouriteNumber, String dept) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.favouriteNumber = favouriteNumber;
        this.department = dept;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return favouriteNumber == person.favouriteNumber && Objects.equals(name, person.name) && Objects.equals(dateOfBirth, person.dateOfBirth) && Objects.equals(department, person.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, favouriteNumber, department);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", favouriteNumber=" + favouriteNumber +
                ", department=" + department +
                "}\n";
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
    public int compareTo(Person o) {
        return Integer.compare(
                this.favouriteNumber,
                o.favouriteNumber
        );
    }




    public static int compareByDob( Person p1, Person p2){
        return p1.dateOfBirth.compareTo(p2.dateOfBirth);
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Adam", LocalDate.of(1977, 5, 25), 32, "D01"));
        people.add(new Person("Beatrice", LocalDate.of(1978, 3, 18), 66, "D01"));
        people.add(new Person("Charles", LocalDate.of(2010, 2, 8), 8, "D02"));
        people.add(new Person("Delilah", LocalDate.of(2013, 3, 25), 7, "D01"));
        people.add(new Person("Evan", LocalDate.of(1953, 4, 4), 16, "D02"));

        // Accumulate names into a List
        List<String> names = people.stream().map(Person::getName).collect(Collectors.toList());
        System.out.printf("list of names  %s%n", names);

        List<Person> list = people.stream()
                .sorted((p1, p2) -> Integer.compare(p1.favouriteNumber, p2.favouriteNumber) )
                .collect(toList());

        System.out.printf("sorted by number  %s%n", list);

        // Accumulate names into a TreeSet
        Set<String> set = people.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));
        System.out.printf("set %s%n", set);
        // Convert elements to strings and concatenate them, separated by commas
        String joined = list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        System.out.printf("joined list %s%n", joined);

        // Compute sum of salaries of employee
        int total = people.stream()
                .collect(Collectors.summingInt(Person::getFavouriteNumber));

        IntSummaryStatistics stats = people.stream()
                .collect(summarizingInt(Person::getFavouriteNumber));
        System.out.printf("total %s%n", stats);

        Map<String, Person> namePersonMap = people.stream()
                .collect( toMap(
                        Person::getName,
                        Function.identity()
                        )
                );
        System.out.printf("namePerson %s%n", namePersonMap);

        Map<String, List<Person> > byDepartment = people.stream()
                .collect( groupingBy(Person::getDepartment ) );
        System.out.printf("%nby department %s%n", byDepartment);

        Map<String, Integer> sumByDept = people.stream()
                .collect(
                        groupingBy( Person::getDepartment, summingInt( Person::getFavouriteNumber) )
                        ) ;
        System.out.printf("%nby department %s%n", sumByDept);
    }


}
