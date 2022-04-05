package org.djna.citizen;

import java.util.Objects;

// minimal Voter class
// name, age and postcode deemed sufficient
// no attempt to deal with two people with same name in same postcode
public class Voter {
    private String name;
    private int age;
    private String postcode;

    public Voter(String name, int age, String postcode) {
        this.name = name;
        this.age = age;
        this.postcode = postcode;
    }
    
    private int hash = 0;
    @Override
    public int hashCode() {
        // not good citizen
        // return 7;

        // Bad citizen
        return hash++;
    }

    @Override
    public String toString() {
        return "Voter{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", postcode='" + postcode + '\'' +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
