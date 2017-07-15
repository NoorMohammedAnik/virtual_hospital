package com.example.rr.virtual_hospital.nav_drawer.askanexpert.model;

/**
 * Created by Ramim on 6/6/2017.
 */

public class Conversations  {
    private String key,userID,convoID,convoTimestamp,reply,party;

    public Conversations() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getConvoID() {
        return convoID;
    }

    public void setConvoID(String convoID) {
        this.convoID = convoID;
    }

    public String getConvoTimestamp() {
        return convoTimestamp;
    }

    public void setConvoTimestamp(String convoTimestamp) {
        this.convoTimestamp = convoTimestamp;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }
}
