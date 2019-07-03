package com.cluebix.citizenfeedbackapplication;

import android.content.DialogInterface;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {
    TextInputEditText txt_input_editText_zone,txt_input_editext_ward,txt_input_editext_address,txt_input_editext_f_name,txt_input_editext_l_name,txt_input_editext_age,txt_input_editext_mob_no,txt_input_editext_email_id;
    ArrayList<Zone> zoneArrayList ;
    ArrayList<Ward> wardArrayList ;
    String zone_id,ward_id,gender,first_name,last_name,mob_no,email,age,address,zone_no,ward_no,zone_name,id,ward_name;
    RadioButton rad_female,rad_male;
    ProgressBar progress_bar;
    Button register_button;
    CharSequence [] charSequencesZone;
    CharSequence [] charSequencesWard;
    private ArrayList<String> zone_type_array;
    private ArrayList<String> zone__id_type_array;
    private ArrayList<String> ward_type_array;
    private ArrayList<String> ward_id_type_array;
    public static final String ZONE_LIST = Connection.URL + Constant.API_ZONE;
    public static final String WARD_LIST = Connection.URL + Constant.API_WARD;
    public static final String GET_UPDATED_DATA = Connection.URL + Constant.API_GET_UPDATED_DATA;
    public static final String UPDATE = Connection.URL + Constant.API_UPDATE;
    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        init();
        sessionManagement = new SessionManagement(UpdateActivity.this);
        zoneArrayList = new ArrayList<Zone>();
        wardArrayList = new ArrayList<Ward>();
        zone_type_array = new ArrayList<>();
        zone__id_type_array = new ArrayList<>();
        ward_type_array = new ArrayList<>();
        ward_id_type_array = new ArrayList<>();
        HashMap<String,String> user =sessionManagement.getUserDetails();
        id = user.get(sessionManagement.KEY_ID);
        getData(id);
        getZone();
      //  getWard(zone_id);
       /* HashMap<String,String> user =sessionManagement.getUserDetails();
        first_name = user.get(sessionManagement.KEY_FIRST_NAME);
        last_name = user.get(sessionManagement.KEY_LAST_NAME);
        mob_no = user.get(sessionManagement.KEY_MOBILE_NUMBER);
        email = user.get(sessionManagement.KEY_EMAIL);
        gender = user.get(sessionManagement.KEY_GENDER);
        age = user.get(sessionManagement.KEY_AGE);
        address = user.get(sessionManagement.KEY_ADDRESS);
        zone_no = user.get(sessionManagement.KEY_ZONE_ID);
        ward_no = user.get(sessionManagement.KEY_WARD_ID);
        zoneArrayList = new ArrayList<Zone>();
        wardArrayList = new ArrayList<Ward>();
        zone_type_array = new ArrayList<String>();
        zone_id_type_array = new ArrayList<String>();
        ward_type_array = new ArrayList<String>();*/

        //

    }

    private void getData(final String id) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_UPDATED_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response_register",response);
                        if (response.contains("200")) {
                            progress_bar.setVisibility(View.GONE);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.optString("status");
                                String data = jsonObject.optString("data");
                                JSONObject jsonObject1 = new JSONObject(data);
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
                                String is_language = jsonObject1.optString("is_language");
                                String is_logged_in = jsonObject1.optString("is_logged_in");
                                setData(id,role_id,zone_id,ward_id,first_name,last_name,gender,mobile_no,email,otp_code,age,address,is_language,is_logged_in);

                                sessionManagement.updateUserData(first_name,last_name,email,mobile_no,age,address,gender,zone_id,ward_id);
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

                params.put(Constant.KEY_ID, id);

                Log.e(Constant.KEY_ID, id);

                return params;
            }


        };
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(UpdateActivity.this);
        requestQueue.add(stringRequest);
    }

  private void setData(String id, String role_id, String zone_id, String ward_id, String first_name, String last_name, String gender, String mobile_no, String email, String otp_code, String age, String address, String is_language, String is_logged_in) {
        txt_input_editText_zone.setText(zone_id);
        txt_input_editext_ward.setText(ward_id);
        txt_input_editext_f_name.setText(first_name);
        txt_input_editext_l_name.setText(last_name);
        txt_input_editext_mob_no.setText(mobile_no);
        txt_input_editext_email_id.setText(email);
        txt_input_editext_age.setText(age);
        txt_input_editext_address.setText(address);
        if(gender.equalsIgnoreCase("Female")){
            rad_female.setChecked(true);
        }
        else if(gender.equalsIgnoreCase("Male")){
            rad_male.setChecked(true);
        }
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
        rad_female = findViewById(R.id.rad_f);
        rad_male = findViewById(R.id.rad_m);
        progress_bar = findViewById(R.id.progress_bar);
        register_button = findViewById(R.id.register_button);
        rad_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gender = "Male";
               // Toast.makeText(UpdateActivity.this, gender, Toast.LENGTH_SHORT).show();

            }
        });
        rad_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gender = "Female";
            //    Toast.makeText(UpdateActivity.this, gender, Toast.LENGTH_SHORT).show();

            }
        });
        txt_input_editText_zone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_input_editext_ward.setText(" ");
                txt_input_editText_zone.setText(" ");

                android.support.v7.app.AlertDialog.Builder mBuilder = new android.support.v7.app.AlertDialog.Builder(UpdateActivity.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setItems(charSequencesZone, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        zone_id = zone__id_type_array.get(which);
                        zone_name = zone_type_array.get(which);
                        txt_input_editText_zone.setText(zone_name);
                        Log.e("zone_id",zone_id);
                        Log.e("zone_name",zone_name);
                      //  Toast.makeText(UpdateActivity.this, zone_name, Toast.LENGTH_SHORT).show();
                        getWard(zone_id);
                        dialog.dismiss();

                     /*   zone_id = zone_id_type_array.get(which);
                        zone_name = zone_type_array.get(which);
                        txt_input_editText_zone.setText(zone_name);
                        Toast.makeText(UpdateActivity.this, zone_id, Toast.LENGTH_SHORT).show();*/

                        dialog.dismiss();
                    }
                });
                if(zone__id_type_array.size()!=0 && zone_type_array.size()!=0) {
                    AlertDialog mDialog = mBuilder.create();
                    mDialog.show();
                }
            }
        });
        txt_input_editext_ward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder mBuilder = new android.support.v7.app.AlertDialog.Builder(UpdateActivity.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setItems(charSequencesWard, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ward_id = ward_id_type_array.get(which);
                        ward_name = ward_type_array.get(which);
                        txt_input_editext_ward.setText(ward_name);
                       /* ward_id = ward_type_array.get(which);
                        txt_input_editext_ward.setText(ward_id);*/
                        dialog.dismiss();
                    }
                });
                if(ward_id_type_array.size()!=0 && ward_type_array.size()!=0) {
                    AlertDialog mDialog = mBuilder.create();
                    mDialog.show();
                }
            }
        });
       register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_bar.setVisibility(View.VISIBLE);
                final String f_name = txt_input_editext_f_name.getText().toString().trim();
                final String l_name = txt_input_editext_l_name.getText().toString().trim();
                final String mob = txt_input_editext_mob_no.getText().toString().trim();
                final String email = txt_input_editext_email_id.getText().toString().trim();
                final String age = txt_input_editext_age.getText().toString().trim();
                final String address = txt_input_editext_address.getText().toString().trim();
                final String zone = zone_id;
                final String ward = ward_id;
                if(rad_female.isChecked()){
                    gender="Female";
                }
                if(rad_male.isChecked()){
                    gender="Male";
                }

                Log.e("f_name",f_name);
                Log.e("l_name",l_name);
                Log.e("mob",mob);
                Log.e("email",email);
                Log.e("age",age);
                Log.e("address",address);
                Log.e("zone",zone);
                Log.e("ward",ward);
                Log.e("gender",gender);








                if (f_name.length() == 0) {
                    Toast.makeText(UpdateActivity.this, "Please add first name", Toast.LENGTH_SHORT).show();
                } else if (l_name.length() == 0) {
                    Toast.makeText(UpdateActivity.this, "Please add last name", Toast.LENGTH_SHORT).show();
                }
                else if (mob.length() == 0) {
                    Toast.makeText(UpdateActivity.this, "Please add mobile number", Toast.LENGTH_SHORT).show();
                }
                else if (email.length() == 0) {
                    Toast.makeText(UpdateActivity.this, "Please add email id", Toast.LENGTH_SHORT).show();
                }
                else if (age.length() == 0) {
                    Toast.makeText(UpdateActivity.this, "Please add age", Toast.LENGTH_SHORT).show();
                }
                else if (address.length() == 0) {
                    Toast.makeText(UpdateActivity.this, "Please add address", Toast.LENGTH_SHORT).show();
                }
                else if (ward.length() == 0) {
                    Toast.makeText(UpdateActivity.this, "Please add ward", Toast.LENGTH_SHORT).show();
                }
                else if (zone.length() == 0) {
                    Toast.makeText(UpdateActivity.this, "Please add zone", Toast.LENGTH_SHORT).show();
                }
                else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, UPDATE,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.e("response_register",response);
                                    if (response.contains("200")) {
                                        progress_bar.setVisibility(View.GONE);
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String status = jsonObject.optString("status");
                                            String message = jsonObject.optString("message");
                                            Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                                            sessionManagement.updateUserData(f_name,l_name,email,mob,age,address,gender,zone,ward);
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
                            params.put(Constant.KEY_EMAIL, email);
                            params.put(Constant.KEY_GENDER, gender);
                            params.put(Constant.KEY_AGE, age);
                            params.put(Constant.KEY_ADDRESS, address);
                            Log.e(Constant.KEY_ZONE_ID, zone);
                            Log.e(Constant.KEY_WARD_ID, ward);
                            Log.e(Constant.KEY_FIRST_NAME, f_name);
                            Log.e(Constant.KEY_LAST_NAME, l_name);
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
                    RequestQueue requestQueue = Volley.newRequestQueue(UpdateActivity.this);
                    requestQueue.add(stringRequest);
                }
            }
        });

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
                                    Zone zone = new Zone(UpdateActivity.this);
                                    zone.setId(json2.optString("id"));
                                    zone_id = json2.optString("id");
                                    zone.setName(json2.optString("name"));
                                    zone_name = json2.optString("id");
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
                                    Ward ward = new Ward(UpdateActivity.this);
                                    ward.setId(json2.optString("id"));
                                    ward_id = json2.optString("id");
                                    ward.setZone_id(json2.optString("zone_id"));
                                    ward.setWard_name(json2.optString("ward_name"));
                                    ward_name = json2.optString("ward_name");

                                    wardArrayList.add(ward);
                                    ward_type_array.add(json2.optString("ward_name"));
                                    ward_id_type_array.add(json2.optString("id"));
                                    Log.e("json_arraylist_length", String.valueOf(zoneArrayList.size()));
                                    Log.e("json_array_length", String.valueOf(zone_type_array.size()));

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            charSequencesWard = ward_type_array.toArray(new CharSequence[ward_type_array.size()]);

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
