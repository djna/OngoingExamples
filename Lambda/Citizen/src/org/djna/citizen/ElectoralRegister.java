package org.djna.citizen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Contrived example using collections to keep some objects

public class ElectoralRegister {

    // some collections to exercise

    // list of wards
    private List<ElectoralWard>wards = new ArrayList<>();

    // map from voter to ward, not really necessary as ward has information
    // but we could think of this as a cache
    private HashMap<Voter, ElectoralWard>voterElectoralWardMap = new HashMap<>();

    public ElectoralRegister(){
        wards.add( new ElectoralWard("North", "HA4"));
        wards.add( new ElectoralWard("South", "PL1"));
        wards.add( new ElectoralWard("East", "DA8"));
        wards.add( new ElectoralWard("West", "RE1"));
    }

    public boolean registerVoter(Voter voter){
            // offer Voter to each ward in turn
            for ( ElectoralWard ward: wards ) {
                if ( ward.addVoter(voter) ) {
                    voterElectoralWardMap.put(voter, ward);
                    return true;
                }
            }
            return false;
    }

    // direct query against ward -
    public ElectoralWard getWardForVoter(Voter voter){
        for ( ElectoralWard ward: wards ) {
            if ( ward.voterRegistered(voter) ) {
                return ward;
            }
        }
        return null;
    }

    // example of going to cache
    public ElectoralWard getCachedWardForVoter(Voter voter){
        return voterElectoralWardMap.get(voter);
    }

    @Override
    public String toString() {
        return "ElectoralRegister {" +
                "wards=" + wards +
                '}';
    }
}
