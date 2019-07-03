package com.cluebix.citizenfeedbackapplication.model;

import android.content.Context;


public class DashboardData {

   String  zoneName,citizeCount,surveyCount,commentCount,totalCountCitizen,totalCountSurvey,totalCountComment;
   Context context;
    public DashboardData(Context context){
        this.context = context;
    }
    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getCitizeCount() {
        return citizeCount;
    }

    public void setCitizeCount(String citizeCount) {
        this.citizeCount = citizeCount;
    }

    public String getSurveyCount() {
        return surveyCount;
    }

    public void setSurveyCount(String surveyCount) {
        this.surveyCount = surveyCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }


    public String getTotalCountCitizen() {
        return totalCountCitizen;
    }

    public void setTotalCountCitizen(String totalCountCitizen) {
        this.totalCountCitizen = totalCountCitizen;
    }

    public String getTotalCountSurvey() {
        return totalCountSurvey;
    }

    public void setTotalCountSurvey(String totalCountSurvey) {
        this.totalCountSurvey = totalCountSurvey;
    }

    public String getTotalCountComment() {
        return totalCountComment;
    }

    public void setTotalCountComment(String totalCountComment) {
        this.totalCountComment = totalCountComment;
    }
}
