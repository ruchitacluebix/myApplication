package com.cluebix.citizenfeedbackapplication.model;

import android.content.Context;

import java.io.Serializable;

public class Ward implements Serializable {
    String id,zone_id,ward_name;
    Context context;

    public Ward(Context context) {
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZone_id() {
        return zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }
}
