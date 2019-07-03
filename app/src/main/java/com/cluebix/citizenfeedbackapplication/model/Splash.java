package com.cluebix.citizenfeedbackapplication.model;

import android.content.Context;

import java.io.Serializable;

public class Splash implements Serializable {
    String id,title,messsage,image_url;
    Context context;
public  Splash(Context context){
    this.context = context;
}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
