package com.cluebix.citizenfeedbackapplication;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.cluebix.citizenfeedbackapplication.model.login;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

public class LoginActivity extends AppCompatActivity {
TextView textView_register_now;
TextInputEditText txt_input_edittext_mob_no;
Button login_button;
SessionManagement sessionManagement;
    public static final String LOGIN = Connection.URL + Constant.API_LOGIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManagement = new SessionManagement(LoginActivity.this);
        init();
    }

    private void init() {
        textView_register_now = findViewById(R.id.txt_register_now);
        txt_input_edittext_mob_no = findViewById(R.id.txt_input_edittext_mob_no);
        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String mob_no = txt_input_edittext_mob_no.getText().toString().trim();
               if(mob_no.length()==0){
                   Toast.makeText(LoginActivity.this, "Please add mobile number", Toast.LENGTH_SHORT).show();
               }
               else {
                   StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN,
                           new Response.Listener<String>() {
                               @Override
                               public void onResponse(String response) {
                                   Log.e("response_register",response);
                                   if (response.contains("200")) {
                                       try {
                                           JSONObject jsonObject = new JSONObject(response);
                                           String status = jsonObject.optString("status");
                                           String data = jsonObject.optString("data");
                                           JSONObject  jsonObject1 = new JSONObject(data);
                                           String message = jsonObject1.optString("message");
                                           String content = jsonObject1.optString("content");
                                           JSONObject jsonObject2 =  new JSONObject(content);
                                           String id =  jsonObject2.optString("id");
                                           String role_id =  jsonObject2.optString("role_id");
                                           String zone_id =  jsonObject2.optString("zone_id");
                                           String ward_id =  jsonObject2.optString("ward_id");
                                           String first_name =  jsonObject2.optString("first_name");
                                           String last_name =  jsonObject2.optString("last_name");
                                           String gender =  jsonObject2.optString("gender");
                                           String mobile_no =  jsonObject2.optString("mobile_no");
                                           String email =  jsonObject2.optString("email");
                                           String otp_code =  jsonObject2.optString("otp_code");
                                           String age =  jsonObject2.optString("age");
                                           String address =  jsonObject2.optString("address");
                                           login login =  new login(LoginActivity.this);
                                           login.setId(id);
                                           login.setRole_id(role_id);
                                           login.setZone_id(zone_id);
                                           login.setWard_id(ward_id);
                                           login.setFirst_name(first_name);
                                           login.setLast_name(last_name);
                                           login.setGender(gender);
                                           login.setMobile_no(mobile_no);
                                           login.setEmail(email);
                                           login.setOtp_code(otp_code);
                                           login.setAge(age);
                                           login.setAddress(address);
                                           sessionManagement.createLogin(id,role_id,first_name,last_name,email,age,gender,mob_no,address,zone_id,ward_id);
                                           Intent intent = new Intent(LoginActivity.this,SelectLanguageActivity.class);
                                           startActivity(intent);
                                           finish();

                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }


                                   }
                                   else if(response.contains("500")){

                                       try {
                                           JSONObject jsonObject = jsonObject = new JSONObject(response);
                                           String status = jsonObject.optString("status");
                                           String message = jsonObject.optString("message");
                                           Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }

                                   }
                               }
                           },
                           new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {
                                   Log.e("error_login",error.getMessage());
                                   if (error.networkResponse == null) {
                                       if (error.getClass().equals(TimeoutError.class)) {
                                           // Show timeout error message
                                           Toast.makeText(getApplicationContext(), "time out error", Toast.LENGTH_LONG).show();

                                       }
                                   }
                               }
                           }) {
                       protected Map<String, String> getParams() {

                           Map<String, String> params = new HashMap<>();

                           params.put(Constant.KEY_MOBILE_NO, mob_no);

                           Log.e(Constant.KEY_MOBILE_NO, mob_no);
                           return params;
                       }


                   };
                   int socketTimeout = 30000; // 30 seconds. You can change it
                   RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                           DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                           DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                   stringRequest.setRetryPolicy(policy);
                   RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                   requestQueue.add(stringRequest);
               }
            }
        });
        textView_register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }


}
