package com.cluebix.citizenfeedbackapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.cluebix.citizenfeedbackapplication.R;
import com.cluebix.citizenfeedbackapplication.adapter.CategoryAdapter;
import com.cluebix.citizenfeedbackapplication.adapter.DashboardDataAdapter;
import com.cluebix.citizenfeedbackapplication.model.Category;
import com.cluebix.citizenfeedbackapplication.model.DashboardData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    RecyclerView recyclerView;
    TextView textView_comment_count,textView_registered_user_count,textView_total_survey_count;
    View view;
    LinearLayoutManager linearLayoutManager;
    public  String DASHBOARD = Connection.URL + Constant.API_DASHBOARD;
    private ArrayList<DashboardData> dashboardDataArrayList;
    private DashboardDataAdapter dashboardDataAdapter;
    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        dashboardDataArrayList = new ArrayList<>();
        dashboardDataAdapter  = new  DashboardDataAdapter(getActivity(),dashboardDataArrayList);
        recyclerView = view.findViewById(R.id.recyclerView);
        textView_comment_count = view.findViewById(R.id.textView_comment_count);
        textView_registered_user_count = view.findViewById(R.id.textView_registered_user_count);
        textView_total_survey_count = view.findViewById(R.id.textView_total_survey_count);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(dashboardDataAdapter);
        getRecyclerViewData();
     //   getData();
        return view;
    }

    private void getRecyclerViewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DASHBOARD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response_paid", response);
                        try {
                            if (response.contains("200")) {

                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                String data = jsonObject.getString("data");

                                Log.e("status", status);
                                Log.e("data", data);
                                JSONObject json1  = new JSONObject(data);
                                String totalCountCitizen = json1.optString("totalCountCitizen");
                                String totalCountSurvey = json1.optString("totalCountSurvey");
                                String totalCountComment = json1.optString("totalCountComment");

                                textView_comment_count.setText(totalCountComment);
                                textView_registered_user_count.setText(totalCountCitizen);
                                textView_total_survey_count.setText(totalCountSurvey);




                                JSONArray jsonArray = json1.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonobject = jsonArray.getJSONObject(i);
                                    DashboardData dashboardData = new DashboardData(getActivity());
                                    dashboardData.setZoneName(jsonobject.optString("zoneName"));
                                    dashboardData.setCitizeCount(jsonobject.optString("citizeCount"));
                                    dashboardData.setSurveyCount(jsonobject.optString("surveyCount"));
                                    dashboardData.setCommentCount(jsonobject.optString("commentCount"));


                                    dashboardDataArrayList.add(dashboardData);
                                    dashboardDataAdapter.notifyDataSetChanged();


                                }

                            }
                            else if(response.contains("500")){

                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                Log.e("message", message);
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                            }


                        }
                        catch (JSONException e ) {
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse == null) {
                            if (error.getClass().equals(TimeoutError.class)) {
                                Toast.makeText(getActivity(), "time out error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }) {


        };
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DASHBOARD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response_paid", response);
                        try {
                            if (response.contains("200")) {

                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                String data = jsonObject.getString("data");
                                JSONObject json = new JSONObject(data);
                              /*  dashboardData.setTotalCountCitizen(json.optString("totalCountCitizen"));
                                dashboardData.setTotalCountSurvey(json.optString("totalCountSurvey"));
                                dashboardData.setTotalCountComment(json.optString("totalCountComment"));*/

                               // Log.e("json1", String.valueOf(json));
                              /*  String totalCountCitizen = json.optString("totalCountCitizen");
                                String totalCountSurvey = json.optString("totalCountSurvey");
                                String totalCountComment = json.optString("totalCountComment");
*/
                                JSONArray jsonArray = json.getJSONArray("zoneAll");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    DashboardData dashboardData = new DashboardData(getActivity());
                                    JSONObject jsonobject = jsonArray.getJSONObject(i);
                                    dashboardData.setZoneName(jsonobject.optString("zoneName"));
                                    dashboardData.setCitizeCount(jsonobject.optString("citizeCount"));
                                    dashboardData.setSurveyCount(jsonobject.optString("surveyCount"));
                                    dashboardData.setCommentCount(jsonobject.optString("commentCount"));


                                    dashboardDataArrayList.add(dashboardData);



                                }
                               Log.e("size_dashbaorad",String.valueOf(dashboardDataArrayList.size()));
                               /*  textView_comment_count.setText(dashboardData.getTotalCountComment());
                                textView_registered_user_count.setText(dashboardData.getTotalCountCitizen());
                                textView_total_survey_count.setText(dashboardData.getTotalCountSurvey());
*/

                            }
                            else if(response.contains("500")){

                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                Log.e("message", message);
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                            }


                        }
                        catch (JSONException e ) {
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse == null) {
                            if (error.getClass().equals(TimeoutError.class)) {
                                Toast.makeText(getActivity(), "time out error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }) {


        };
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
