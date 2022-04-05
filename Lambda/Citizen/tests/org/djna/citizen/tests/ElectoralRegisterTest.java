package org.djna.citizen.tests;



import org.djna.citizen.ElectoralRegister;

import org.djna.citizen.ElectoralWard;
import org.djna.citizen.Voter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ElectoralRegister")
public class ElectoralRegisterTest {
    private ElectoralRegister register;

    @BeforeEach
    public void populateRegister(){
        register = new ElectoralRegister();
        register.registerVoter( new Voter("Abe", 23, "HA4") );
        register.registerVoter( new Voter("Brian", 35, "PL1") );

    }



    @Test
    @DisplayName("register one voter")
    public void registerVoter(){

        // adding a new voter
        Voter zach = new Voter("Zach", 76, "RE1");

        boolean zachRegistered
                = register.registerVoter( zach );

        assertTrue(zachRegistered);

    }

    @Test
    @DisplayName("bad ward, no registration")
    public void registerVoterFail(){

        // adding a new voter with invalid ward, should fail
        Voter zach = new Voter("Zach", 76, "HA1");

        boolean zachRegistered
                = register.registerVoter( zach );

        assertFalse(zachRegistered);

    }

    @Test
    @DisplayName("Get ward for voter")
    public void getWard(){

        // voter should already be in test set
        Voter abe = new Voter("Abe", 23, "HA4");
        ElectoralWard abeWard = register.getWardForVoter(abe);
        assertNotNull(abeWard);
        assertEquals(abe.getPostcode(), abeWard.getPostcode());
    }

    @Test
    @DisplayName("Get cached ward for voter")
    public void getCachedWard(){
        // voter should already be in test set
        Voter abe = new Voter("Abe", 23, "HA4");
        ElectoralWard abeWard = register.getCachedWardForVoter(abe);
        assertNotNull(abeWard);
        assertEquals(abe.getPostcode(), abeWard.getPostcode());
    }
}
