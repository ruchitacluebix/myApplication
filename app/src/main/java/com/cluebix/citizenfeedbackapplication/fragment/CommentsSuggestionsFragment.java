package com.cluebix.citizenfeedbackapplication.fragment;


import android.annotation.SuppressLint;
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
import com.cluebix.citizenfeedbackapplication.Constant.SessionManagement;
import com.cluebix.citizenfeedbackapplication.R;
import com.cluebix.citizenfeedbackapplication.adapter.CategoryAdapter;
import com.cluebix.citizenfeedbackapplication.adapter.CategoryCommentAdapter;
import com.cluebix.citizenfeedbackapplication.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentsSuggestionsFragment extends Fragment {
    View view;
    String citizen_id,ward_id,zone_id;
    private CategoryCommentAdapter categoryCommentAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    public  String CATEGORY_LIST = Connection.URL + Constant.API_CATEGORIES_COMMENT;
    public  String CATEGORY_MARARTHI = Connection.URL + Constant.API_CATEGORIES_COMMENT_MARATHI;
    private static final String TAG_PUBLIC_SEVICES_SURVEY = "public_services_survey";
    private ArrayList<Category> categoryArrayList;
    String language_select;
    SessionManagement sessionManagement;
    public CommentsSuggestionsFragment() {
        // Required empty public constructor
    }
    @SuppressLint("ValidFragment")
    public CommentsSuggestionsFragment(String language){
        language_select=language;
        Log.e("language",language_select);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_comments_suggestions, container, false);
        sessionManagement = new SessionManagement(getActivity());
        HashMap<String,String> user =  sessionManagement.getUserDetails();
        citizen_id = user.get(sessionManagement.KEY_ID);
        categoryArrayList = new ArrayList<>();
        categoryCommentAdapter  = new   CategoryCommentAdapter(getActivity(),categoryArrayList,language_select);
        recyclerView = view.findViewById(R.id.category_list);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(categoryCommentAdapter);
        if(language_select.equals("1")){
            getData(CATEGORY_MARARTHI);
        }
        else{
            getData(CATEGORY_LIST);
        }
        // Inflate the layout for this fragment
        return view;
    }
    private void getData(String CATEGORY) {
        categoryArrayList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, CATEGORY,
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
                                    Category category = new Category(getActivity());
                                    category.setId(jsonobject.optString("id"));
                                    category.setName(jsonobject.optString("name"));
                                    category.setCheck_id(jsonobject.optString("is_active"));

                                    categoryArrayList.add(category);
                                    categoryCommentAdapter.notifyDataSetChanged();


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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onPause() {
        super.onPause();

        if(language_select.equals("1")){
            categoryArrayList.clear();
            getData(CATEGORY_MARARTHI);
        }
        else{
            categoryArrayList.clear();
            getData(CATEGORY_LIST);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
