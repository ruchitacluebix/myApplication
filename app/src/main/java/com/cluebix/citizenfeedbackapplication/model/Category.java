package com.cluebix.citizenfeedbackapplication.model;

import android.content.Context;

public class Category {
    String id,name,is_language,check_id;
    Context context;

    public Category(Context context) {
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

    public String getIs_language() {
        return is_language;
    }

    public void setIs_language(String is_language) {
        this.is_language = is_language;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getCheck_id() {
        return check_id;
    }

    public void setCheck_id(String check_id) {
        this.check_id = check_id;
    }
}
