package org.djna;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Manager extends Person {
    boolean isManager;
    public Manager(String name, LocalDate dateOfBirth, int favouriteNumber) {
        super(name, dateOfBirth, favouriteNumber);
        isManager = true;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "isManager=" + isManager +
                ", as Person " +super.toString() +
                "}\n";
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return isManager == manager.isManager;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isManager);
    }

    public static void main(String[] args) {
        List<Manager> people = new ArrayList<>();
        people.add(new Manager("Adam", LocalDate.of(1977, 5, 25), 32));
        people.add(new Manager("Beatrice", LocalDate.of(1978, 3, 18), 66));
        people.add(new Manager("Charles", LocalDate.of(2010, 2, 8), 8));
        people.add(new Manager("Delilah", LocalDate.of(2013, 3, 25), 7));
        people.add(new Manager("Evan", LocalDate.of(1953, 4, 4), 16));
        people.add(new Manager("Frida", LocalDate.of(1956, 11, 20), 3));

        System.out.printf("People %s%n", people);

        Manager theMax = Algorithm.max(people,1,3);
        Person aMax = Algorithm.max(people,1,3);
        System.out.printf("The max %s%n", theMax);
    }
}
