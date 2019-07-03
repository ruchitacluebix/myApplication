package com.cluebix.citizenfeedbackapplication.adapter;

import android.app.Dialog;
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
import android.widget.EditText;
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
import com.cluebix.citizenfeedbackapplication.EquicityActivity;
import com.cluebix.citizenfeedbackapplication.NavigationActivity;
import com.cluebix.citizenfeedbackapplication.R;
import com.cluebix.citizenfeedbackapplication.model.Category;
import com.cluebix.citizenfeedbackapplication.model.CheckSurvey;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CategoryCommentAdapter extends RecyclerView.Adapter<CategoryCommentAdapter.ViewHolder> {
    private ArrayList<Category> categoryArrayList;
    private ArrayList<CheckSurvey> checkSurveyArrayList;
    SessionManagement sessionManagement;
    Context context;
    String language_select,citizen_id,category_id,check,category_name,zone_id,ward_id;
    public static final String SAVE_COMMENT = Connection.URL + Constant.API_COMMENT_SAVE;

    public CategoryCommentAdapter(Context context,  ArrayList<Category> categoryArrayList,String language_select){
        this.context = context;
        this.categoryArrayList = categoryArrayList;
        this.language_select = language_select;

    }


    @NonNull
    @Override
    public CategoryCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_category, viewGroup, false);
        sessionManagement = new SessionManagement(context);
        checkSurveyArrayList = new ArrayList<>();
        HashMap<String,String> user =sessionManagement.getUserDetails();
        citizen_id = user.get(sessionManagement.KEY_ID);
        zone_id = user.get(sessionManagement.KEY_ZONE_ID);
        ward_id = user.get(sessionManagement.KEY_WARD_ID);

        return new CategoryCommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryCommentAdapter.ViewHolder viewHolder, int i) {
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

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_comment);


                if(check.equals("true")){
                    Toast.makeText(context, "Already Added Comment ", Toast.LENGTH_SHORT).show();

                }
                else if(check.equals("false")){
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setTitle(category_name);

                    Button button_comment_submit = dialog.findViewById(R.id.button_comment_submit);
                    final EditText editText_comment = dialog.findViewById(R.id.editText_comment);
                    final TextView textView_title = dialog.findViewById(R.id.textView_title);
                    textView_title.setText(category_name);

                    button_comment_submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String comment = editText_comment.getText().toString().trim();
                            if(comment.isEmpty()){
                                Toast.makeText(context, "Please add Comment", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                saveComment(citizen_id,category_id,comment,dialog,zone_id,ward_id);
                            }


                        }
                    });
                }




            }
        });
    }

    private void saveComment(final String citizen_id, final String category_id, final String comment, final Dialog dialog, final String zone_id, final String ward_id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SAVE_COMMENT,
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

                                Intent intent = new Intent(context, NavigationActivity.class);
                                intent.putExtra("comment_suggestion_btn","comment_suggestion");
                                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                context.startActivity(intent);
                                dialog.cancel();
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
                params.put(Constant.KEY_CATEGORIES_ID, category_id);
                params.put(Constant.KEY_COMMENT, comment);
                params.put(Constant.KEY_ZONE_ID, zone_id);
                params.put(Constant.KEY_WARD_ID, ward_id);


                Log.e(Constant.KEY_CITIZEN_ID, citizen_id);
                Log.e(Constant.KEY_CATEGORIES_ID, category_id);
                Log.e(Constant.KEY_COMMENT, comment);

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
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        Button btn_category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btn_category = itemView.findViewById(R.id.btn_category);

        }
    }
}
