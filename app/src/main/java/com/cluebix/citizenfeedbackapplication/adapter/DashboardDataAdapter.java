package com.cluebix.citizenfeedbackapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cluebix.citizenfeedbackapplication.Constant.SessionManagement;
import com.cluebix.citizenfeedbackapplication.R;
import com.cluebix.citizenfeedbackapplication.model.Category;
import com.cluebix.citizenfeedbackapplication.model.CheckSurvey;
import com.cluebix.citizenfeedbackapplication.model.DashboardData;


import java.util.ArrayList;


public class DashboardDataAdapter extends RecyclerView.Adapter<DashboardDataAdapter.ViewHolder> {

    private ArrayList<DashboardData> dashboardDataArrayList;
    Context context;
    String zone_name,citizeCount,surveyCount,commentCount;
    public DashboardDataAdapter(Context context,ArrayList<DashboardData> dashboardDataArrayList){
        this.context = context;
        this.dashboardDataArrayList = dashboardDataArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_survey, viewGroup, false);
        return new DashboardDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
     //   dashboardDataArrayList = new ArrayList<>();
        final DashboardData dashboardData = dashboardDataArrayList.get(i);
       /* zone_name = dashboardData.getZoneName();
        citizeCount = dashboardData.getCitizeCount();
        surveyCount = dashboardData.getSurveyCount();
        commentCount = dashboardData.getCommentCount();*/
        viewHolder.textView_citizen_count.setText(dashboardData.getCitizeCount());
        viewHolder.textView_zone_name.setText(dashboardData.getZoneName());
        viewHolder.textView_survey_count.setText(dashboardData.getSurveyCount());
        viewHolder.textView_comment_count.setText(dashboardData.getCommentCount());

    }

    @Override
    public int getItemCount() {
        return dashboardDataArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
       TextView textView_zone_name,textView_survey_count,textView_citizen_count,textView_comment_count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_citizen_count = itemView.findViewById(R.id.textView_citizen_count);
            textView_zone_name = itemView.findViewById(R.id.textView_zone_name);
            textView_survey_count = itemView.findViewById(R.id.textView_survey_count);
            textView_comment_count = itemView.findViewById(R.id.textView_comment_count);
        }
    }
}
