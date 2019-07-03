package com.cluebix.citizenfeedbackapplication.model;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

public class Zone implements Serializable {
 Context context;
 String id,name;
    public Zone(Context context){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
