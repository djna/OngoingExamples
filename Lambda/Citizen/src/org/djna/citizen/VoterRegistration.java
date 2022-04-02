package org.djna.citizen;

import java.util.Date;

public class VoterRegistration {
    private Date registered;

    public VoterRegistration() {
        this.registered = new Date();
    }

    @Override
    public String toString() {
        return "VoterId{" +
                "registered=" + registered +
                '}';
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }
}
