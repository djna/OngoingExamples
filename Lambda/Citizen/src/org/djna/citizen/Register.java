package org.djna.citizen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Register {

    static private List<Person>voters = new ArrayList<>();
    static private Map<Person, VoterRegistration> electoralRoll = new HashMap<>();

    public static void main(String[] args) {

        Person abe = new Person("Abe", 23);
        Person bakerSenior  = new Person("Baker", 70);
        Person bakerJunior  = new Person("Baker", 45);
        Person bakerTheThird  = new Person("Baker", 19);
        Person charlie =  new Person("Charlie", 83);

        voters.add( abe );
        voters.add( bakerSenior);
        voters.add( bakerJunior);
        voters.add( bakerTheThird);
        voters.add( charlie);

        System.out.printf("List of people:\n%s\n\n", voters);

        registerVoter(bakerJunior);
        registerVoter(bakerSenior);
        registerVoter(bakerTheThird);

        System.out.printf("Baker junior: %s : %b\n", bakerJunior, voters.contains(bakerJunior));

        printVoter("Abe", 23);
        printVoter("Baker", 45);

    }

    static void printVoter( String name, int age){
        Person voter = new Person(name, age);

        System.out.printf("%s : %b\n", voter, voters.contains(voter));



    }

    static void registerVoter(Person voter){
        VoterRegistration registration = new VoterRegistration();
        electoralRoll.put(voter, registration);
    }
}
