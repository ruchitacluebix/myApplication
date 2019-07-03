package com.cluebix.citizenfeedbackapplication.model;

import java.io.Serializable;

public class SurveySubmit implements Serializable {
    String id,question_no,categories_id,selected_option,citizen_id,zone_id,ward_id;

    public SurveySubmit(String id, String question_no, String categories_id, String selected_option, String citizen_id,String zone_id,String ward_id) {
        this.id = id;
        this.question_no = question_no;
        this.categories_id = categories_id;
        this.selected_option = selected_option;
        this.citizen_id = citizen_id;
        this.zone_id = zone_id;
        this.ward_id = ward_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion_no() {
        return question_no;
    }

    public void setQuestion_no(String question_no) {
        this.question_no = question_no;
    }

    public String getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(String categories_id) {
        this.categories_id = categories_id;
    }

    public String getSelected_option() {
        return selected_option;
    }

    public void setSelected_option(String selected_option) {
        this.selected_option = selected_option;
    }

    public String getCitizen_id() {
        return citizen_id;
    }

    public void setCitizen_id(String citizen_id) {
        this.citizen_id = citizen_id;
    }

    public String getZone_id() {
        return zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public String getWard_id() {
        return ward_id;
    }

    public void setWard_id(String ward_id) {
        this.ward_id = ward_id;
    }
}
