package org.djna.citizen;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class ElectoralWard {
    private String name;
    private String postcode;
    private Set<Voter> voters;

    public ElectoralWard(String name, String postcode) {
        this.name = name;
        this.postcode = postcode;
        this.voters = new HashSet<>();
    }

    @Override
    public String toString() {
        return "ElectoralWard{" +
                "name='" + name + '\'' +
                ", voters=" + voters +
                '}';
    }

    // attempt to add voter to ward
    // voter added only if postcode matches and not already in ward
    // returns true if added, false otherwise
    public boolean addVoter(Voter voter){

        if ( voters.contains(voter)){
            return false;
        }

        if ( this.postcode.equals( voter.getPostcode() )){
            return voters.add(voter);
        } else {
            return false;
        }
    }

    public boolean voterRegistered(Voter voter){
        return voters.contains(voter);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostcode() {
        return postcode;
    }


}
