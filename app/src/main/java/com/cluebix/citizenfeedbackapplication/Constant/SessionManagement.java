package com.cluebix.citizenfeedbackapplication.Constant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.cluebix.citizenfeedbackapplication.LoginActivity;

import java.util.HashMap;

public class SessionManagement {
  SharedPreferences sharedPreferences;
  SharedPreferences.Editor editor;
  Context context;
  int PRIVATE_MODE = 0;
  private static final String PREF_NAME = "citizen_feebback";
  private static final String IS_LOGIN = "IsLoggedIn";
  public static final String KEY_ROLE_ID = "role_id";
  public static final String KEY_ID = "id";
  public static final String KEY_MOBILE_NUMBER = "mobile_number";
  public static final String KEY_FIRST_NAME = "f_name";
  public static final String KEY_LAST_NAME = "l_name";
  public static final String KEY_AGE = "age";
  public static final String KEY_GENDER = "gender";
  public static final String KEY_ADDRESS = "address";
  public static final String KEY_EMAIL = "email";
  public static final String KEY_ZONE_ID = "zone_id";
  public static final String KEY_WARD_ID = "ward_id";


  public SessionManagement(Context context){
    this.context = context;
    sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
    editor = sharedPreferences.edit();
  }

  public void logoutUser(){
    editor.clear();
    editor.commit();
    Intent i = new Intent(context, LoginActivity.class);
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    context.startActivity(i);
  }
  public void updateUserData(String first_name,String last_name,String email,String mobileNumber,String age,String address,String gender,String zone_id,String ward_id){

    editor.putString(KEY_MOBILE_NUMBER, mobileNumber);
    editor.putString(KEY_FIRST_NAME,first_name);
    editor.putString(KEY_LAST_NAME,last_name);
    editor.putString(KEY_EMAIL,email);
    editor.putString(KEY_MOBILE_NUMBER,mobileNumber);
    editor.putString(KEY_AGE,age);
    editor.putString(KEY_ADDRESS,address);
    editor.putString(KEY_GENDER,gender);
    editor.putString(KEY_ZONE_ID,zone_id);
    editor.putString(KEY_WARD_ID,ward_id);
    editor.apply();

  }
  public void createLogin(String id,String role_id,String f_name,String l_name,String email,String age,String gender,String mob_no,String address,String zone_id,String ward_id) {

    editor.putString(KEY_ID, id);
    editor.putBoolean(IS_LOGIN, true);
    editor.putString(KEY_ROLE_ID, role_id);
    editor.putString(KEY_FIRST_NAME, f_name);
    editor.putString(KEY_LAST_NAME, l_name);
    editor.putString(KEY_EMAIL, email);
    editor.putString(KEY_AGE, age);
    editor.putString(KEY_GENDER, gender);
    editor.putString(KEY_MOBILE_NUMBER, mob_no);
    editor.putString(KEY_ADDRESS, address);
    editor.putString(KEY_ZONE_ID, zone_id);
    editor.putString(KEY_WARD_ID, ward_id);


    editor.commit();
  }

  public HashMap<String, String> getUserDetails(){
    HashMap<String, String> user = new HashMap<String, String>();

    // user name
    user.put(KEY_ID, sharedPreferences.getString(KEY_ID, null));
    user.put(KEY_ROLE_ID, sharedPreferences.getString(KEY_ROLE_ID, null));
    user.put(KEY_FIRST_NAME, sharedPreferences.getString(KEY_FIRST_NAME, null));
    user.put(KEY_LAST_NAME, sharedPreferences.getString(KEY_LAST_NAME, null));
    user.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));
    user.put(KEY_AGE, sharedPreferences.getString(KEY_AGE, null));
    user.put(KEY_GENDER, sharedPreferences.getString(KEY_GENDER, null));
    user.put(KEY_MOBILE_NUMBER, sharedPreferences.getString(KEY_MOBILE_NUMBER, null));
    user.put(KEY_ADDRESS, sharedPreferences.getString(KEY_ADDRESS, null));
    user.put(KEY_ZONE_ID, sharedPreferences.getString(KEY_ZONE_ID, null));
    user.put(KEY_WARD_ID, sharedPreferences.getString(KEY_WARD_ID, null));





    // return user
    return user;
  }
  public boolean isLoggedIn() {
    return sharedPreferences.getBoolean(IS_LOGIN, false);
  }
}
