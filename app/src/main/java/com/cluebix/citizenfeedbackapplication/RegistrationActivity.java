package com.cluebix.citizenfeedbackapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
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
import com.cluebix.citizenfeedbackapplication.model.Ward;
import com.cluebix.citizenfeedbackapplication.model.Zone;
import com.cluebix.citizenfeedbackapplication.model.registration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    TextInputEditText txt_input_editText_zone,txt_input_editext_ward,txt_input_editext_address,txt_input_editext_f_name,txt_input_editext_l_name,txt_input_editext_age,txt_input_editext_mob_no,txt_input_editext_email_id;
    ArrayList<Zone> zoneArrayList ;
    ArrayList<Ward> wardArrayList ;
    String zone_id,ward_id,gender,zone_name,ward_name;
    RadioButton rad_female,rad_male;
    ProgressBar progress_bar;
    TextView textView_login;
    AlertDialog mDialog;
    Button register_button;
    SessionManagement sessionManagement;
    CharSequence [] charSequencesZone;
    CharSequence [] charSequencesWard;
    private ArrayList<String> zone_type_array;
    private ArrayList<String> zone__id_type_array;
    private ArrayList<String> ward_type_array;
    private ArrayList<String> ward_id_type_array;
    public static final String ZONE_LIST = Connection.URL + Constant.API_ZONE;
    public static final String WARD_LIST = Connection.URL + Constant.API_WARD;
    public static final String REGISTRATION = Connection.URL + Constant.API_REGISTER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        zoneArrayList = new ArrayList<Zone>();
        wardArrayList = new ArrayList<Ward>();
        zone_type_array = new ArrayList<String>();
        zone__id_type_array = new ArrayList<String>();
        ward_type_array = new ArrayList<String>();
        ward_id_type_array = new ArrayList<String>();
        sessionManagement = new SessionManagement(RegistrationActivity.this);
        getZone();
        init();

    }

    private void init() {
        txt_input_editText_zone = findViewById(R.id.txt_input_editext_zone);
        txt_input_editext_ward = findViewById(R.id.txt_input_editext_ward);
        txt_input_editext_address = findViewById(R.id.txt_input_editext_address);
        txt_input_editext_f_name = findViewById(R.id.txt_input_editext_f_name);
        txt_input_editext_l_name = findViewById(R.id.txt_input_editext_l_name);
        txt_input_editext_age = findViewById(R.id.txt_input_editext_age);
        txt_input_editext_mob_no = findViewById(R.id.txt_input_editext_mob_no);
        txt_input_editext_email_id = findViewById(R.id.txt_input_editext_email);
        textView_login = findViewById(R.id.textView_login);
        rad_female = findViewById(R.id.rad_f);
        rad_male = findViewById(R.id.rad_m);
        progress_bar = findViewById(R.id.progress_bar);
        register_button = findViewById(R.id.register_button);
        textView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        rad_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gender = "Male";
                Toast.makeText(RegistrationActivity.this, gender, Toast.LENGTH_SHORT).show();
            }
        });
        rad_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gender = "Female";
                Toast.makeText(RegistrationActivity.this, gender, Toast.LENGTH_SHORT).show();
            }
        });
        txt_input_editText_zone.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              txt_input_editext_ward.setText(" ");
              txt_input_editText_zone.setText(" ");
              android.support.v7.app.AlertDialog.Builder mBuilder = new android.support.v7.app.AlertDialog.Builder(RegistrationActivity.this);
              mBuilder.setTitle("Choose an item");
              mBuilder.setItems(charSequencesZone, new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {

                    /*  zone_id = zone__id_type_array.get(which-1);
                      zone_name = zone_type_array.get(which-1);*/
                      zone_id = zone__id_type_array.get(which);
                      zone_name = zone_type_array.get(which);
                      txt_input_editText_zone.setText(zone_name);
                      Toast.makeText(RegistrationActivity.this, zone_name, Toast.LENGTH_SHORT).show();
                      getWard(zone_id);
                      dialog.dismiss();
                  }
              });
              if(zone__id_type_array.size()!=0 && zone_type_array.size()!=0){
                  mDialog = mBuilder.create();
                  mDialog.show();
              }

          }
      });
        txt_input_editext_ward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder mBuilder = new android.support.v7.app.AlertDialog.Builder(RegistrationActivity.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setItems(charSequencesWard, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      /*  ward_id = ward_type_array.get(which-1);*/
                        ward_id = ward_id_type_array.get(which);
                        ward_name = ward_type_array.get(which);
                        txt_input_editext_ward.setText(ward_name);
                        dialog.dismiss();
                    }
                });
                if(ward_id_type_array.size()!=0 && ward_type_array.size()!=0){
                mDialog = mBuilder.create();
                mDialog.show();
                }
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String f_name = txt_input_editext_f_name.getText().toString().trim();
                final String l_name = txt_input_editext_l_name.getText().toString().trim();
                final String mob = txt_input_editext_mob_no.getText().toString().trim();
                final String email = txt_input_editext_email_id.getText().toString().trim();
                final String age = txt_input_editext_age.getText().toString().trim();
                final String address = txt_input_editext_address.getText().toString().trim();
                final String zone = zone_id;
                final String ward = ward_id;
                if (f_name.length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please add first name", Toast.LENGTH_SHORT).show();
                } else if (l_name.length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please add last name", Toast.LENGTH_SHORT).show();
                }
                else if (mob.length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please add mobile number", Toast.LENGTH_SHORT).show();
                }
                else if (email.length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please add email id", Toast.LENGTH_SHORT).show();
                }
                else if (age.length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please add age", Toast.LENGTH_SHORT).show();
                }
                else if (address.length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please add address", Toast.LENGTH_SHORT).show();
                }
                else if (ward.length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please add ward", Toast.LENGTH_SHORT).show();
                }
                else if (zone.length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Please add zone", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(rad_male.isChecked()){
                        gender = "Male";
                    }
                    else if(rad_female.isChecked()){
                        gender="Female";
                    }
                   submit(f_name,l_name,mob,email,age,address,zone,ward,gender);
                }
            }
        });

    }

    private void submit(final String f_name, final String l_name, final String mob, final String email, final String age, final String address, final String zone, final String ward, final String gender) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTRATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response_register",response);
                        if (response.contains("200")) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.optString("status");
                                String data = jsonObject.optString("data");
                                JSONObject jsonObject1 = new JSONObject(data);
                                registration registration = new registration(RegistrationActivity.this);
                                String id = jsonObject1.optString("id");
                                String role_id = jsonObject1.optString("role_id");
                                String zone_id = jsonObject1.optString("zone_id");
                                String ward_id = jsonObject1.optString("ward_id");
                                String first_name = jsonObject1.optString("first_name");
                                String last_name = jsonObject1.optString("last_name");
                                String gender = jsonObject1.optString("gender");
                                String mobile_no = jsonObject1.optString("mobile_no");
                                String email = jsonObject1.optString("email");
                                String otp_code = jsonObject1.optString("otp_code");
                                String age = jsonObject1.optString("age");
                                String address = jsonObject1.optString("address");
                                registration.setId(id);
                                registration.setRole_id(role_id);
                                registration.setZone_id(zone_id);
                                registration.setWard_id(ward_id);
                                registration.setFirst_name(first_name);
                                registration.setLast_name(last_name);
                                registration.setGender(gender);
                                registration.setMobile_no(mobile_no);
                                registration.setEmail(email);
                                registration.setAge(age);
                                registration.setAddress(address);
                                Toast.makeText(RegistrationActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                                sessionManagement.createLogin(id,role_id,first_name,last_name,email,age,gender,mobile_no,address,zone_id,ward_id);
                                Intent intent = new Intent(RegistrationActivity.this,SelectLanguageActivity.class);
                                startActivity(intent);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                        else if(response.contains("500")){

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.optString("status");
                                String message = jsonObject.optString("message");
                                Toast.makeText(RegistrationActivity.this, message, Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getApplicationContext(), "time out error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }) {
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put(Constant.KEY_ZONE_ID, zone);
                params.put(Constant.KEY_WARD_ID, ward);
                params.put(Constant.KEY_FIRST_NAME, f_name);
                params.put(Constant.KEY_LAST_NAME, l_name);
                params.put(Constant.KEY_MOBILE_NO, mob);
                params.put(Constant.KEY_EMAIL, email);
                params.put(Constant.KEY_GENDER, gender);
                params.put(Constant.KEY_AGE, age);
                params.put(Constant.KEY_ADDRESS, address);
                Log.e(Constant.KEY_ZONE_ID, zone);
                Log.e(Constant.KEY_WARD_ID, ward);
                Log.e(Constant.KEY_FIRST_NAME, f_name);
                Log.e(Constant.KEY_LAST_NAME, l_name);
                Log.e(Constant.KEY_MOBILE_NO, mob);
                Log.e(Constant.KEY_EMAIL, email);
                Log.e(Constant.KEY_GENDER, gender);
                Log.e(Constant.KEY_AGE, age);
                Log.e(Constant.KEY_ADDRESS, address);
                return params;
            }


        };
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(RegistrationActivity.this);
        requestQueue.add(stringRequest);
    }

    private void getZone() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ZONE_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        zoneArrayList.clear();
                        zone_type_array.clear();
                        zone__id_type_array.clear();
                        if (response.contains("200")) {

                            try {
                                Log.e("Response zone ", response);
                                zoneArrayList = new ArrayList<Zone>();
                                JSONObject json = new JSONObject(response);
                                String status = json.getString("status");
                                String data = json.getString("data");
                                Log.e("status",status);
                                Log.e("data",data);
                                JSONArray jsonArray = json.optJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json2 = jsonArray.getJSONObject(i);
                                    Zone zone = new Zone(RegistrationActivity.this);
                                    zone.setId(json2.optString("id"));
                                    zone.setName(json2.optString("name"));
                                    zoneArrayList.add(zone);
                                    zone_type_array.add(json2.optString("name"));
                                    zone__id_type_array.add(json2.optString("id"));
                                    Log.e("json_arraylist_length", String.valueOf(zoneArrayList.size()));
                                    Log.e("json_array_length", String.valueOf(zone_type_array.size()));

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            charSequencesZone = zone_type_array.toArray(new CharSequence[zone_type_array.size()]);

                        }
                        else if (response.contains("500")){
                            mDialog.hide();
                            mDialog.dismiss();
                            try {
                                JSONObject json  = new JSONObject(response);
                                String status = json.getString("status");
                                String message = json.getString("message");
                                Toast.makeText(RegistrationActivity.this, message, Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getApplicationContext(), "time out error", Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void getWard(final String zone_id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, WARD_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        wardArrayList.clear();
                        ward_type_array.clear();
                        ward_id_type_array.clear();
                        if (response.contains("200")) {

                            try {
                                Log.e("Response ward ", response);
                                zoneArrayList = new ArrayList<Zone>();
                                JSONObject json = new JSONObject(response);
                                String status = json.getString("status");
                                String data = json.getString("data");
                                Log.e("status",status);
                                Log.e("data",data);
                                JSONArray jsonArray = json.optJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json2 = jsonArray.getJSONObject(i);
                                    Ward ward = new Ward(RegistrationActivity.this);
                                    ward.setId(json2.optString("id"));
                                    ward.setWard_name(json2.optString("ward_name"));
                                    ward.setZone_id(json2.optString("zone_id"));
                                    wardArrayList.add(ward);
                                    ward_type_array.add(json2.optString("ward_name"));
                                    ward_id_type_array.add(json2.optString("id"));

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            charSequencesWard = ward_type_array.toArray(new CharSequence[ward_type_array.size()]);

                        }

                        else if (response.contains("500")){
                            mDialog.hide();
                            mDialog.dismiss();
                            try {
                                JSONObject json  = new JSONObject(response);
                                String status = json.getString("status");
                                String message = json.getString("message");
                                Toast.makeText(RegistrationActivity.this, message, Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getApplicationContext(), "time out error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }) {
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put(Constant.KEY_ZONE_ID, zone_id);
                Log.e(Constant.KEY_ZONE_ID, zone_id);
                return params;
            }


        };
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
