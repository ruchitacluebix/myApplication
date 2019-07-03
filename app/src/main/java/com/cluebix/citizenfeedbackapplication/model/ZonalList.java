package com.cluebix.citizenfeedbackapplication.model;

import android.content.Context;

import java.io.Serializable;

public class ZonalList implements Serializable {
    Context context;
    String id,zone_name,department_head,designation,contact_no;
    public ZonalList(Context context){
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZone_name() {
        return zone_name;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }

    public String getDepartment_head() {
        return department_head;
    }

    public void setDepartment_head(String department_head) {
        this.department_head = department_head;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }
}
