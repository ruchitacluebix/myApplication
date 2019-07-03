package com.cluebix.citizenfeedbackapplication.information;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
import com.cluebix.citizenfeedbackapplication.model.ElectedMemberInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AboutElectedMembersActivity extends AppCompatActivity {
Toolbar toolbar;
RecyclerView recyclerView;
LinearLayoutManager linearLayoutManager;
    public  String MEMBER_LIST = Connection.URL + Constant.API_MEMBER_LIST;
    private ArrayList<ElectedMemberInfo> electedMemberInfoArrayList;
    private AboutElectedMemberAdapter aboutElectedMemberAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_elected_members);
        electedMemberInfoArrayList = new ArrayList<>();
        aboutElectedMemberAdapter  = new AboutElectedMemberAdapter(AboutElectedMembersActivity.this,electedMemberInfoArrayList);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.member_list);
        linearLayoutManager = new LinearLayoutManager(AboutElectedMembersActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(AboutElectedMembersActivity.this));
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(aboutElectedMemberAdapter);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ELECTED MEMBERS(WARD)");


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getData();
    }

    private void getData() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, MEMBER_LIST,
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
                                    ElectedMemberInfo electedMemberInfo = new ElectedMemberInfo(AboutElectedMembersActivity.this);
                                    electedMemberInfo.setId(jsonobject.optString("id"));
                                    electedMemberInfo.setMember_name(jsonobject.optString("name"));
                                    electedMemberInfo.setContact_no(jsonobject.optString("mobile_no"));
                                    electedMemberInfo.setParty(jsonobject.optString("party"));
                                    electedMemberInfo.setPrabhag(jsonobject.optString("parbhag"));


                                    electedMemberInfoArrayList.add(electedMemberInfo);
                                    aboutElectedMemberAdapter.notifyDataSetChanged();


                                }

                            }
                            else if(response.contains("500")){

                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                Log.e("message", message);
                                Toast.makeText(AboutElectedMembersActivity.this, message, Toast.LENGTH_SHORT).show();

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
                                Toast.makeText(AboutElectedMembersActivity.this, "time out error", Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(AboutElectedMembersActivity.this);
        requestQueue.add(stringRequest);
    }
}
