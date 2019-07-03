package com.cluebix.citizenfeedbackapplication.information.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.cluebix.citizenfeedbackapplication.R;
import com.cluebix.citizenfeedbackapplication.adapter.AboutElectedMemberAdapter;
import com.cluebix.citizenfeedbackapplication.information.AboutElectedMembersActivity;
import com.cluebix.citizenfeedbackapplication.information.adapter.DepartmentalAdapter;
import com.cluebix.citizenfeedbackapplication.model.DepartmentalList;
import com.cluebix.citizenfeedbackapplication.model.ElectedMemberInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DepartmentFragment extends Fragment {
    RecyclerView recyclerView;
    View view;
    LinearLayoutManager linearLayoutManager;
    public  String DEPARTMENTAL_LIST = Connection.URL + Constant.API_DEPARTMENTAL_LIST;
    private ArrayList<DepartmentalList> departmentalListArrayList;
    private DepartmentalAdapter departmentalAdapter;
    public DepartmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_department, container, false);
        departmentalListArrayList = new ArrayList<>();
        departmentalAdapter  = new DepartmentalAdapter(getActivity(),departmentalListArrayList);
        recyclerView = view.findViewById(R.id.department_list);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(departmentalAdapter);
        getData();
        // Inflate the layout for this fragment
        return view;
    }

    private void getData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DEPARTMENTAL_LIST,
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
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonobject = jsonArray.getJSONObject(i);
                                    DepartmentalList departmentalList = new DepartmentalList(getActivity());
                                    departmentalList.setId(jsonobject.optString("id"));
                                    departmentalList.setDepartmentHead(jsonobject.optString("name"));
                                    departmentalList.setContact_no(jsonobject.optString("mobile_no"));
                                    departmentalList.setDesignation(jsonobject.optString("designation"));
                                    departmentalList.setDepartment(jsonobject.optString("department"));


                                    departmentalListArrayList.add(departmentalList);
                                    departmentalAdapter.notifyDataSetChanged();


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


}
