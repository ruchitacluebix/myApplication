package com.cluebix.citizenfeedbackapplication.model;

import android.content.Context;

import java.io.Serializable;

public class ElectedMemberInfo implements Serializable {
    String id,prabhag,member_name,party,contact_no;
    Context context;

    public ElectedMemberInfo(Context context){
        this.context = context;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrabhag() {
        return prabhag;
    }

    public void setPrabhag(String prabhag) {
        this.prabhag = prabhag;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }
}
