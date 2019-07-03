package com.cluebix.citizenfeedbackapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cluebix.citizenfeedbackapplication.Constant.Connection;
import com.cluebix.citizenfeedbackapplication.Constant.Constant;
import com.cluebix.citizenfeedbackapplication.Constant.SessionManagement;
import com.cluebix.citizenfeedbackapplication.PublicServicesSurvey.SurveyActivity;
import com.cluebix.citizenfeedbackapplication.R;
import com.cluebix.citizenfeedbackapplication.information.AboutElectedMembersActivity;
import com.cluebix.citizenfeedbackapplication.model.Category;
import com.cluebix.citizenfeedbackapplication.model.CheckSurvey;
import com.cluebix.citizenfeedbackapplication.model.ElectedMemberInfo;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<Category> categoryArrayList;
    private ArrayList<CheckSurvey> checkSurveyArrayList;
    SessionManagement sessionManagement;
    Context context;
    int row_index = -1;
    String language_select,citizen_id,category_id,check,category_name;
    public static final String RETURN_SURVEY = Connection.URL + Constant.API_RETURN_SURVEY;
    public CategoryAdapter(Context context,  ArrayList<Category> categoryArrayList,String language_select){
        this.context = context;
        this.categoryArrayList = categoryArrayList;
        this.language_select = language_select;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_category, viewGroup, false);
        sessionManagement = new SessionManagement(context);
        checkSurveyArrayList = new ArrayList<>();
        HashMap<String,String> user =sessionManagement.getUserDetails();
        citizen_id = user.get(sessionManagement.KEY_ID);

        return new CategoryAdapter.ViewHolder(view);
    }

    /*private void checkSurvery(final String citizen_id, final ViewHolder viewHolder) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RETURN_SURVEY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("check_survey_status",response);
                        if (response.contains("200")) {

                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                String data = jsonObject.getString("data");
                                Log.e("status", status);
                                Log.e("data", data);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonobject = jsonArray.getJSONObject(i);
                                    CheckSurvey checkSurvey = new CheckSurvey(context);
                                    checkSurvey.setId(jsonobject.optString("id"));
                                    checkSurvey.setCitizen_id(jsonobject.optString("citizen_id"));
                                    checkSurvey.setZone_id(jsonobject.optString("zone_id"));
                                    checkSurvey.setWard_id(jsonobject.optString("ward_id"));
                                    checkSurvey.setCategories_id(jsonobject.optString("categories_id"));
                                    checkSurvey.setQuestion_id(jsonobject.optString("question_id"));
                                    checkSurvey.setTrue_ans(jsonobject.optString("true_ans"));

                                    if(checkSurvey.getCategories_id()=="1"){
                                        viewHolder.btn_category.setEnabled(false);
                                    }



                                    checkSurveyArrayList.add(checkSurvey);


                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                        else if(response.contains("500")){

                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                Log.e("message", message);
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse == null) {
                            if (error.getClass().equals(TimeoutError.class)) {
                                // Show timeout error message
                                Toast.makeText(context, "time out error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }) {
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put(Constant.KEY_CITIZEN_ID, citizen_id);


                Log.e(Constant.KEY_CITIZEN_ID, citizen_id);

                return params;
            }


        };
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }*/

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Category category = categoryArrayList.get(i);

         check = category.getCheck_id();

        viewHolder.btn_category.setText(category.getName());
        if(check.equals("true")){
            viewHolder.btn_category.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.color_red));
            viewHolder.btn_category.setTextColor(Color.parseColor("#ffffff"));
        }

        viewHolder.btn_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = category.getCheck_id();
                category_id = category.getId();
                category_name = category.getName();
           //     viewHolder.linearLayout.setEnabled(false);
               // checkSurvery(citizen_id,viewHolder);

                if(check.equals("true")){
                    Toast.makeText(context, "Already Submitted Survey ", Toast.LENGTH_SHORT).show();

                }
                else if(check.equals("false")){
                    viewHolder.btn_category.setEnabled(true);
                    Intent intent = new Intent(context, SurveyActivity.class);
                    intent.putExtra("category_id",category_id);
                    intent.putExtra("category_name",category_name);
                    intent.putExtra("language_select",language_select);
                    context.startActivity(intent);
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
     Button btn_category;
     LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_layout_disable);
            btn_category = itemView.findViewById(R.id.btn_category);

        }
    }
}
