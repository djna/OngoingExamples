package org.djna.citizen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Contrived example using collections to keep some objects

public class Register {

    // list of people eligible to vote
    static private List<Person>voters = new ArrayList<>();

    // record of when a voter is added to the elctoral role
    static private Map<Person, VoterRegistration> electoralRoll = new HashMap<>();

    public static void main(String[] args) {

        registerVoter("Abe", 23);
        registerVoter("Baker", 70);
        registerVoter("Baker", 45);
        registerVoter("Baker", 19);
        registerVoter("Charlie", 83);

        System.out.printf("List of people:\n%s\n\n", voters);


        printVoter("Abe", 23);
        printVoter("Baker", 45);

        printElectoralRollEntry("Baker", 45);

    }

    static void printAllVoters(){
        System.out.printf("List of registered voters:\n%s\n\n", voters);
    }

    // print whether a voter has been registered
    static void printVoter( String name, int age){
        Person voter = new Person(name, age);

        System.out.printf("%s : %s\n", voter, voters.contains(voter) ? "is regiestered " : "unknown, not in list of registered voters");
    }

    // look for an electoral roll entry for a voter
    static void printElectoralRollEntry( String name, int age){
        Person voter = new Person(name, age);
        VoterRegistration registration = electoralRoll.get(voter);
        System.out.printf("%s : %s\n", voter, registration  ==  null
                                              ? "not on roll " : "registered on " + registration);
    }

    // create a voter record and add to electoral roll
    static void registerVoter(String name, int age){
        Person voter = new Person(name, age);
        voters.add( voter );
        VoterRegistration registration = new VoterRegistration();
        electoralRoll.put(voter, registration);
    }
}
