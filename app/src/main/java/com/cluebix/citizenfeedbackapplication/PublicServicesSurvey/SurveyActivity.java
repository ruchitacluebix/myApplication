package com.cluebix.citizenfeedbackapplication.PublicServicesSurvey;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.cluebix.citizenfeedbackapplication.Helper.SqliteDatabase;
import com.cluebix.citizenfeedbackapplication.R;
import com.cluebix.citizenfeedbackapplication.model.QuestionAll;
import com.cluebix.citizenfeedbackapplication.model.Questions_Local;
import com.cluebix.citizenfeedbackapplication.model.SurveySubmit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyActivity extends AppCompatActivity {
    Toolbar toolbar;
    String category_id, category_name,citizen_id,selected_string,zone_id,ward_id,jsonArray_string,language_select;
    LinearLayoutManager linearLayoutManager;
    SessionManagement sessionManagement;
    EditText commentTV;
    RecyclerView recyclerView;
    Button cart_proceed_btn;
    SqliteDatabase sqliteDatabase;
    private List<View> questionLisView = new ArrayList<>();
    private LinearLayout queScrollView;
    SqliteDatabase sqLiteDatabase;
    private	static final String TABLE_SURVEY_SUBMISSION = "survey_submit";
    private static final String KEY_QUESTION_ID = "question_id";
    public String ALL_QUESTIONS = Connection.URL + Constant.API_ALL_QUESTIONS;
    public String ALL_QUESTIONS_MARATHI = Connection.URL + Constant.API_ALL_QUESTION_MARATHI;
    private ArrayList<Questions_Local> questionsLocalArrayList=new ArrayList<>();
    public static final String SURVEY_SAVE = Connection.URL + Constant.API_SURVEY_SAVE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_survey);
        Intent intent = getIntent();
        cart_proceed_btn = findViewById(R.id.cart_proceed_btn);
        sessionManagement = new SessionManagement(this);
        sqLiteDatabase = new SqliteDatabase(this);
        HashMap<String,String> user =sessionManagement.getUserDetails();
        citizen_id = user.get(sessionManagement.KEY_ID);
        zone_id = user.get(sessionManagement.KEY_ZONE_ID);
        ward_id = user.get(sessionManagement.KEY_WARD_ID);

        Log.e("citizen_id",citizen_id);
        Log.e("zone_id",zone_id);
        Log.e("ward_id",ward_id);

        if (intent != null) {
            category_id = intent.getStringExtra("category_id");
            category_name = intent.getStringExtra("category_name");
            language_select = intent.getStringExtra("language_select");

            Log.e("category_name",category_name);
            Log.e("category_id",category_id);
        }
        sqliteDatabase  = new SqliteDatabase(SurveyActivity.this);
       if(language_select.equals("1")){
           getData(category_id,ALL_QUESTIONS_MARATHI);
       }
       else{
           getData(category_id,ALL_QUESTIONS);
       }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(category_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    private View createView(final Questions_Local que){
        final View childView = LayoutInflater.from(this).inflate(R.layout.layout_all_questions,queScrollView,false);
        LinearLayout linear_layout,linear_layout_edt;
        RadioGroup priceGroup;
        CardView card_view;
        final RadioButton rad_opt_a,rad_opt_b,rad_opt_c,rad_opt_d,rad_opt_e,rad_opt_f,rad_opt_g,rad_opt_h;
        TextView txt_question;
        commentTV = (EditText) childView.findViewById(R.id.edttext);

       commentTV.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Questions_Local answeredQue = que;
                switch(que.getCategories_id()) {
                    case "1":
                        switch (que.getQuestion_no()) {
                            case "4":
                                Log.e("textWatcher",citizen_id);
                                View view = questionLisView.get(3);
                                commentTV = (EditText) view.findViewById(R.id.edttext);
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                            case "10":
                                Log.e("textWatcher",citizen_id);
                                View view1 = questionLisView.get(9);
                                commentTV = (EditText) view1.findViewById(R.id.edttext);
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;
                        }
                        break;
                    case "2":
                        switch (que.getQuestion_no()){
                            case "4":
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;
                            case "8":
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                            case "10":
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;
                        }
                        break;
                    case "3":
                        switch (que.getQuestion_no()){

                            case "8":
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                        }
                        break;
                    case "4":
                        switch (que.getQuestion_no()){

                            case "3":
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                        }
                        break;
                    case "6":
                        switch (que.getQuestion_no()){

                            case "3":
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                        }
                        break;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Questions_Local answeredQue = que;
                switch(que.getCategories_id()) {
                    case "1":
                        switch (que.getQuestion_no()) {
                            case "4":
                                Log.e("textWatcher",citizen_id);
                                View view = questionLisView.get(3);
                                commentTV = (EditText) view.findViewById(R.id.edttext);
                                selected_string = commentTV.getText().toString();

                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                            case "10":
                                Log.e("textWatcher",citizen_id);
                                View view1 = questionLisView.get(9);
                                commentTV = (EditText) view1.findViewById(R.id.edttext);
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;
                        }
                        break;
                    case "2":
                        switch (que.getQuestion_no()){
                            case "4":
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;
                            case "8":
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                            case "10":
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;
                        }
                        break;
                    case "3":
                        switch (que.getQuestion_no()){

                            case "8":
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                        }
                        break;
                    case "4":
                        switch (que.getQuestion_no()){

                            case "3":
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                        }
                        break;
                    case "6":
                        switch (que.getQuestion_no()){

                            case "3":
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                        }
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Questions_Local answeredQue = que;
                switch(que.getCategories_id()) {
                    case "1":
                        switch (que.getQuestion_no()) {
                            case "4":
                                Log.e("textWatcher",citizen_id);
                                View view = questionLisView.get(3);
                                commentTV = (EditText) view.findViewById(R.id.edttext);
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                            case "10":
                                Log.e("textWatcher",citizen_id);
                                View view1 = questionLisView.get(9);
                                commentTV = (EditText) view1.findViewById(R.id.edttext);
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;
                        }
                        break;
                    case "2":
                        switch (que.getQuestion_no()){
                            case "4":
                                View view = questionLisView.get(3);
                                commentTV = (EditText) view.findViewById(R.id.edttext);
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;
                            case "8":
                                View view1 = questionLisView.get(7);
                                commentTV = (EditText) view1.findViewById(R.id.edttext);
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                            case "10":
                                View view2 = questionLisView.get(9);
                                commentTV = (EditText) view2.findViewById(R.id.edttext);
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;
                        }
                        break;
                    case "3":
                        switch (que.getQuestion_no()){

                            case "8":
                                View view1 = questionLisView.get(7);
                                commentTV = (EditText) view1.findViewById(R.id.edttext);
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                        }
                        break;
                    case "4":
                        switch (que.getQuestion_no()){

                            case "3":
                                View view = questionLisView.get(2);
                                commentTV = (EditText) view.findViewById(R.id.edttext);
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                        }
                        break;
                    case "6":
                        switch (que.getQuestion_no()){

                            case "3":
                                View view = questionLisView.get(2);
                                commentTV = (EditText) view.findViewById(R.id.edttext);
                                selected_string = commentTV.getText().toString();
                                surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                break;

                        }
                        break;

                }
            }
        });

        cart_proceed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Questions_Local questions_local = que;
                Log.e("question_id",questions_local.getId());
                Log.e("category_id",questions_local.getCategories_id());
                submitSurvey(jsonArray_string);
                finish();

            }

        });

        txt_question = (TextView) childView.findViewById(R.id.txt_question);
        commentTV = (EditText) childView.findViewById(R.id.edttext);
        priceGroup = (RadioGroup) childView.findViewById(R.id.price_grp);
        rad_opt_a = (RadioButton) childView.findViewById(R.id.rad_opt_a);
        rad_opt_b = (RadioButton) childView.findViewById(R.id.rad_opt_b);
        rad_opt_c = (RadioButton) childView.findViewById(R.id.rad_opt_c);
        rad_opt_d = (RadioButton) childView.findViewById(R.id.rad_opt_d);
        rad_opt_e = (RadioButton) childView.findViewById(R.id.rad_opt_e);
        rad_opt_f = (RadioButton) childView.findViewById(R.id.rad_opt_f);
        rad_opt_g = (RadioButton) childView.findViewById(R.id.rad_opt_g);
        rad_opt_h = (RadioButton) childView.findViewById(R.id.rad_opt_h);
        card_view = (CardView) childView.findViewById(R.id.card_view);
        linear_layout = (LinearLayout) childView.findViewById(R.id.linear_layout);
        linear_layout_edt = (LinearLayout) childView.findViewById(R.id.linear_layout_edt);

        txt_question.setText(que.getName());
        String option = que.getIs_option();



        switch (option){
            case "0":
                commentTV.setVisibility(View.VISIBLE);
                linear_layout_edt.setVisibility(View.VISIBLE);
                break;

            case "1":
                rad_opt_a.setVisibility(View.VISIBLE);
                rad_opt_a.setText(que.getOption_a());
                break;

            case "2":
                rad_opt_a.setVisibility(View.VISIBLE);
                rad_opt_b.setVisibility(View.VISIBLE);
                rad_opt_a.setText(que.getOption_a());
                rad_opt_b.setText(que.getOption_b());
                break;

            case "3":
                rad_opt_a.setVisibility(View.VISIBLE);
                rad_opt_b.setVisibility(View.VISIBLE);
                rad_opt_c.setVisibility(View.VISIBLE);
                rad_opt_a.setText(que.getOption_a());
                rad_opt_b.setText(que.getOption_b());
                rad_opt_c.setText(que.getOption_c());
                break;

            case "4":
                rad_opt_a.setVisibility(View.VISIBLE);
                rad_opt_b.setVisibility(View.VISIBLE);
                rad_opt_c.setVisibility(View.VISIBLE);
                rad_opt_d.setVisibility(View.VISIBLE);
                rad_opt_a.setText(que.getOption_a());
                rad_opt_b.setText(que.getOption_b());
                rad_opt_c.setText(que.getOption_c());
                rad_opt_d.setText(que.getOption_d());
                break;

            case "5":
                rad_opt_a.setVisibility(View.VISIBLE);
                rad_opt_b.setVisibility(View.VISIBLE);
                rad_opt_c.setVisibility(View.VISIBLE);
                rad_opt_d.setVisibility(View.VISIBLE);
                rad_opt_e.setVisibility(View.VISIBLE);
                rad_opt_a.setText(que.getOption_a());
                rad_opt_b.setText(que.getOption_b());
                rad_opt_c.setText(que.getOption_c());
                rad_opt_d.setText(que.getOption_d());
                rad_opt_e.setText(que.getOption_e());
                break;

            case "6":
                rad_opt_a.setVisibility(View.VISIBLE);
                rad_opt_b.setVisibility(View.VISIBLE);
                rad_opt_c.setVisibility(View.VISIBLE);
                rad_opt_d.setVisibility(View.VISIBLE);
                rad_opt_e.setVisibility(View.VISIBLE);
                rad_opt_f.setVisibility(View.VISIBLE);
                rad_opt_a.setText(que.getOption_a());
                rad_opt_b.setText(que.getOption_b());
                rad_opt_c.setText(que.getOption_c());
                rad_opt_d.setText(que.getOption_d());
                rad_opt_e.setText(que.getOption_e());
                rad_opt_f.setText(que.getOption_f());
                break;
        }
        rad_opt_f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Questions_Local answeredQue = que;
                    switch(que.getCategories_id()) {
                        case "3":
                            switch (que.getQuestion_no()) {
                                case "3":
                                    selected_string = "Other";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                    }

                }
            }
        });

        rad_opt_e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Questions_Local answeredQue = que;
                    switch(que.getCategories_id()) {
                                case "1":
                                    switch (que.getQuestion_no()) {
                                        case "1":
                                            questionLisView.get(1).setVisibility(View.VISIBLE);
                                            questionLisView.get(2).setVisibility(View.VISIBLE);
                                            questionLisView.get(3).setVisibility(View.VISIBLE);
                                            selected_string = "Open Defecation";
                                            surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                            break;
                                    }
                                    break;
                                case "3":
                                    switch (que.getQuestion_no()){
                                        case "3":
                                            selected_string = "Tanker";
                                            surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                            break;
                                        case "4":
                                            selected_string = "No Supply";
                                            surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                            break;
                                    }
                                    break;
                        case "5":
                            switch (que.getQuestion_no()){
                                case "3":
                                    selected_string = "Other";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;

                    }

                }
            }
        });

        rad_opt_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Questions_Local answeredQue = que;
                    switch(que.getCategories_id()){
                        case "1":
                            switch (que.getQuestion_no()){
                                case "1":
                                    questionLisView.get(1).setVisibility(View.GONE);
                                    questionLisView.get(2).setVisibility(View.GONE);
                                    questionLisView.get(3).setVisibility(View.GONE);
                                    selected_string = "Individual Household (IHHL) Toilet";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;

                                case "2":
                                    questionLisView.get(2).setVisibility(View.VISIBLE);
                                    questionLisView.get(3).setVisibility(View.GONE);
                                    selected_string = "Yes";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "3":
                                    selected_string = "Yes";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "4":
                                    selected_string = commentTV.getText().toString() ;
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;

                                case "5":
                                    questionLisView.get(5).setVisibility(View.GONE);
                                    selected_string = "Municipal Sewer Pipeline System";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "6":
                                    selected_string = "Unaffordable";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "7":
                                    selected_string = "Within 100 m";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "8":
                                    selected_string = "Excellent";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "9":
                                    selected_string = "Excellent";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "10":
                                    selected_string = commentTV.getText().toString();
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                        case "2":
                            switch(que.getQuestion_no()){
                                case "1":
                                    selected_string = "Everyday";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "2":
                                  questionLisView.get(2).setVisibility(View.GONE);
                                  questionLisView.get(3).setVisibility(View.GONE);
                                  questionLisView.get(4).setVisibility(View.VISIBLE);
                                    selected_string = "Door to Door Collection";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "6":
                                    questionLisView.get(7).setVisibility(View.GONE);
                                    questionLisView.get(6).setVisibility(View.VISIBLE);
                                    selected_string = "Yes";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "3":
                                    questionLisView.get(3).setVisibility(View.GONE);
                                    selected_string = "Yes";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "4":
                                    selected_string = commentTV.getText().toString();
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "5":
                                    selected_string = "Everyday";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "7":
                                    selected_string = "Yes";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "8":
                                    selected_string = commentTV.getText().toString();
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "9":
                                    selected_string = "Excellent";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;

                                case "10":
                                    selected_string = commentTV.getText().toString();
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                        case "3":
                            switch (que.getQuestion_no()){
                                case "1":
                                    questionLisView.get(1).setVisibility(View.VISIBLE);
                                    questionLisView.get(2).setVisibility(View.GONE);
                                    selected_string = "Yes";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "2":
                                    selected_string = "Yes";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "3":
                                    selected_string = "Public Well";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "4":
                                    selected_string = "24 x 7";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "5":
                                    selected_string = "1 to 2 hrs";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "6":
                                    selected_string = "Drinkable";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "7":
                                    selected_string = "Excellent";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "8":
                                    selected_string = commentTV.getText().toString();
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;

                        case "4":
                            switch(que.getQuestion_no()){
                                case "1":
                                    selected_string = "Yes";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "2":
                                    questionLisView.get(2).setVisibility(View.GONE);
                                    selected_string = "Yes";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "3":
                                    selected_string = commentTV.getText().toString();
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                        case "5":
                            switch (que.getQuestion_no()){
                                case "1":
                                    questionLisView.get(1).setVisibility(View.VISIBLE);
                                    questionLisView.get(2).setVisibility(View.VISIBLE);
                                    questionLisView.get(3).setVisibility(View.VISIBLE);
                                    questionLisView.get(4).setVisibility(View.VISIBLE);
                                    selected_string = "Yes";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "2":
                                    selected_string = "Application";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "3":
                                    selected_string = "Sewerage System";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "4":
                                    selected_string = "Within 24 hrs";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "5":
                                    selected_string = "Excellent";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                        case "6":
                            switch (que.getQuestion_no()){
                                case"1":
                                    selected_string = "Yes";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "2":
                                    questionLisView.get(2).setVisibility(View.GONE);
                                    selected_string = "Yes";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case"3":
                                    selected_string = commentTV.getText().toString();
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "4":
                                    selected_string = "Excellent";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;

                    }


                }
            }
        });

        rad_opt_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Questions_Local answeredQue = que;
                    switch(que.getCategories_id()) {
                        case "1":
                            switch (que.getQuestion_no()) {
                                case "1":
                                    questionLisView.get(1).setVisibility(View.GONE);
                                    questionLisView.get(2).setVisibility(View.GONE);
                                    questionLisView.get(3).setVisibility(View.GONE);
                                    selected_string = "Public Toilet";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;

                                case "2":
                                    questionLisView.get(2).setVisibility(View.GONE);
                                    questionLisView.get(3).setVisibility(View.VISIBLE);
                                    selected_string = "No";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "3":
                                    selected_string = "No";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "4":
                                    selected_string = commentTV.getText().toString();
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;

                                case "5":
                                    selected_string = "Septic Tank";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    questionLisView.get(5).setVisibility(View.VISIBLE);
                                    break;
                                case "6":
                                    selected_string = "Absence of Main Sewer";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "7":
                                    selected_string = "100 m to 300 m";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "8":
                                    selected_string = "Good";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "9":
                                    selected_string = "Good";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                        case "2":
                            switch(que.getQuestion_no()){
                                case "1":
                                    selected_string = "Alternate day";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "2":
                                    questionLisView.get(2).setVisibility(View.VISIBLE);
                                    questionLisView.get(3).setVisibility(View.VISIBLE);
                                    questionLisView.get(4).setVisibility(View.GONE);
                                    selected_string = "at Dumping point";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "3":
                                    questionLisView.get(3).setVisibility(View.VISIBLE);
                                    selected_string = "No";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "5":
                                    selected_string = "Alternate day";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "6":
                                    questionLisView.get(6).setVisibility(View.GONE);
                                    questionLisView.get(7).setVisibility(View.VISIBLE);
                                    selected_string = "No";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "7":
                                    selected_string = "No";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "9":
                                    selected_string = "Good";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                        case "3":
                            switch (que.getQuestion_no()){
                                case "1":
                                    questionLisView.get(2).setVisibility(View.VISIBLE);
                                    questionLisView.get(1).setVisibility(View.GONE);
                                    selected_string = "No";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "2":
                                    selected_string = "No";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "3":
                                    selected_string = "Hand Pump";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "4":
                                    selected_string = "Once a day";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "5":
                                    selected_string = "2 to 6 hrs";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "6":
                                    selected_string = "Drinkable after filtering";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "7":
                                    selected_string = "Good";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                        case "4":
                            switch (que.getQuestion_no()){
                                case "1":
                                    selected_string = "No";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;

                                case "2":
                                    questionLisView.get(2).setVisibility(View.VISIBLE);
                                    selected_string = "No";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                        case "5":
                            switch (que.getQuestion_no()){
                                case "1":
                                    questionLisView.get(1).setVisibility(View.GONE);
                                    questionLisView.get(2).setVisibility(View.GONE);
                                    questionLisView.get(3).setVisibility(View.GONE);
                                    questionLisView.get(4).setVisibility(View.GONE);
                                    selected_string = "No";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "2":
                                    selected_string = "Verbal";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "3":
                                    selected_string = "Solid Waste Management";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "4":
                                    selected_string = "Within 7 days";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "5":
                                    selected_string = "Good";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;

                            }
                            break;
                        case "6":
                            switch (que.getQuestion_no()){
                                case "1":
                                    selected_string = "No";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "2":
                                    questionLisView.get(2).setVisibility(View.VISIBLE);
                                    selected_string = "No";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "4":
                                    selected_string = "Good";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                    }


                }
            }
        });

        rad_opt_c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Questions_Local answeredQue = que;
                    switch(que.getCategories_id()) {
                        case "1":
                            switch (que.getQuestion_no()) {
                                case "1":
                                    questionLisView.get(1).setVisibility(View.GONE);
                                    questionLisView.get(2).setVisibility(View.GONE);
                                    questionLisView.get(3).setVisibility(View.GONE);
                                    selected_string = "Community Toilet";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;


                                case "5":
                                    questionLisView.get(5).setVisibility(View.VISIBLE);
                                    selected_string = "Pit Latrine";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "6":
                                    selected_string = "Other";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "7":
                                    selected_string = "300 m to 500 m";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "8":
                                    selected_string = "Average";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "9":
                                    selected_string = "Average";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;


                            }
                            break;
                        case "2":
                            switch (que.getQuestion_no()) {
                                case "1":
                                    selected_string = "Once in a week";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;

                                case "2":
                                    questionLisView.get(2).setVisibility(View.VISIBLE);
                                    questionLisView.get(3).setVisibility(View.VISIBLE);
                                    questionLisView.get(4).setVisibility(View.GONE);
                                    selected_string = "on RoadSide";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "5":
                                    selected_string = "Once in a week";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "9":
                                    selected_string = "Average";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;

                            }
                            break;
                        case"3":
                            switch (que.getQuestion_no()){
                                case "3":
                                    selected_string = "Borewell";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "4":
                                    selected_string = "Alternate day";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "5":
                                    selected_string = "6 to 8 hrs";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "6":
                                    selected_string = "Non-drinkable even after filtering";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "7":
                                    selected_string = "Average";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                                break;

                        case"5":
                            switch (que.getQuestion_no()) {
                                case "2":
                                    selected_string = "Online";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "3":
                                    selected_string = "Water Supply System";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "4":
                                    selected_string = "More than 7 days";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "5":
                                    selected_string = "Average";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }

                            break;
                        case"6":
                            switch (que.getQuestion_no()) {
                                case "4":
                                    selected_string = "Average";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;

                    }


                }
            }
        });

        rad_opt_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Questions_Local answeredQue = que;
                    switch(que.getCategories_id()) {
                        case "1":
                            switch (que.getQuestion_no()) {
                                case "1":
                                    questionLisView.get(1).setVisibility(View.GONE);
                                    questionLisView.get(2).setVisibility(View.GONE);
                                    questionLisView.get(3).setVisibility(View.GONE);
                                    selected_string = "Group Toilet";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "5":
                                    questionLisView.get(5).setVisibility(View.VISIBLE);
                                    selected_string = "Other";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "7":
                                    selected_string = "more than 500 m";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "8":
                                    selected_string = "Poor";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "9":
                                    selected_string = "Average";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                        case "2":
                            switch (que.getQuestion_no()) {
                                case "1":
                                    selected_string = "Never";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "2":
                                    questionLisView.get(2).setVisibility(View.VISIBLE);
                                    questionLisView.get(3).setVisibility(View.VISIBLE);
                                    questionLisView.get(4).setVisibility(View.GONE);
                                    selected_string = "In Sewer/Nallah/River";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "5":
                                    selected_string = "Never";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "9":
                                    selected_string = "Poor";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                        case "3":
                            switch (que.getQuestion_no()) {
                                case "3":
                                    selected_string = "Private Well";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "4":
                                    selected_string = "Once in 3 days";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "5":
                                    selected_string = "More than 8 hrs";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "7":
                                    selected_string = "Poor";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                        case "5":
                            switch (que.getQuestion_no()) {
                                case "3":
                                    selected_string = "Storm Water Drainage System";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "4":
                                    selected_string = "Not known";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                                case "5":
                                    selected_string = "Poor";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;
                        case "6":
                            switch (que.getQuestion_no()) {
                                case "4":
                                    selected_string = "Poor";
                                    surveyDataAddUpdate(answeredQue,selected_string,citizen_id,zone_id,ward_id);
                                    break;
                            }
                            break;

                    }


                }
            }
        });

        return  childView;
    }

    private void init() {
        recyclerView = findViewById(R.id.question_list);
        queScrollView = findViewById(R.id.que_scroll);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        linearLayoutManager = new LinearLayoutManager(SurveyActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(SurveyActivity.this));
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if(questionsLocalArrayList.size()>0){
            for(int i=0; i < questionsLocalArrayList.size(); i++){
                View qView = createView(questionsLocalArrayList.get(i));
                queScrollView.addView(qView);
                questionLisView.add(qView);
            }
        }




    }

    private void getData(final String category_id ,String Language) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Language,
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
                                sqliteDatabase.deleteAllRows();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonobject = jsonArray.getJSONObject(i);
                                    QuestionAll questionAll = new QuestionAll(SurveyActivity.this);
                                    questionAll.setId(jsonobject.optString("id"));
                                    questionAll.setName(jsonobject.optString("name"));
                                    questionAll.setCategories_id(jsonobject.optString("categories_id"));
                                    questionAll.setIs_option(jsonobject.optString("is_option"));
                                    Log.e("is_option",jsonobject.optString("is_option"));
                                    questionAll.setQuestion_no(jsonobject.optString("question_no"));
                                    questionAll.setOption_a(jsonobject.optString("option_a"));
                                    questionAll.setOption_b(jsonobject.optString("option_b"));
                                    questionAll.setOption_c(jsonobject.optString("option_c"));
                                    questionAll.setOption_d(jsonobject.optString("option_d"));
                                    questionAll.setOption_e(jsonobject.optString("option_e"));
                                    questionAll.setOption_f(jsonobject.optString("option_f"));
                                    questionAll.setOption_g(jsonobject.optString("option_g"));
                                    questionAll.setOption_h(jsonobject.optString("option_h"));
                                    questionAll.setTrue_ans(jsonobject.optString("true_ans"));
                                    questionAll.setTrue_question_no(jsonobject.optString("true_question_no"));
                                    questionAll.setFalse_ans(jsonobject.optString("false_ans"));
                                    questionAll.setFalse_question_no(jsonobject.optString("false_question_no"));
                                    String is_active = jsonobject.optString("is_active");
                                    String is_deleted = jsonobject.optString("is_deleted");
                                    Questions_Local questions_local = new Questions_Local(jsonobject.optString("categories_id") ,jsonobject.optString("id"),jsonobject.optString("is_option")
                                            ,jsonobject.optString("question_no"),jsonobject.optString("name"),jsonobject.optString("option_a")
                                            ,jsonobject.optString("option_b"),jsonobject.optString("option_c"),jsonobject.optString("option_d")
                                            ,jsonobject.optString("option_e"),jsonobject.optString("option_f"),jsonobject.optString("option_g")
                                            ,jsonobject.optString("option_h"),jsonobject.optString("true_ans"),jsonobject.optString("true_question_no")
                                            ,jsonobject.optString("false_ans"),jsonobject.optString("false_question_no"),is_active,is_deleted);
                                    sqliteDatabase.addQuestions(questions_local);






                                    //questionAllArrayList.add(questionAll);
                                   // questionAllAdapter.notifyDataSetChanged();


                                }
                                questionsLocalArrayList = sqliteDatabase.question_List();

                                Questions_Local [] Questions = new Questions_Local[questionsLocalArrayList.size()];
                                Log.e("Size ", String.valueOf(questionsLocalArrayList.size()));
                                Questions = questionsLocalArrayList.toArray(Questions);
                                for (Questions_Local x : Questions){
                                    Log.e("id", x.getId());
                                    Log.e("question", x.getName());
                                    Log.e("category_id", x.getCategories_id());
                                    Log.e("is_option_local", x.getIs_option());
                                    Log.e("question_no", x.getQuestion_no());
                                    Log.e("option_a", x.getOption_a());
                                    Log.e("option_b", x.getOption_b());
                                    Log.e("option_c", x.getOption_c());
                                    Log.e("option_d", x.getOption_d());
                                    Log.e("option_e", x.getOption_e());
                                    Log.e("option_f", x.getOption_h());
                                    Log.e("option_g", x.getOption_g());
                                    Log.e("option_h", x.getOption_h());
                                    Log.e("true_ans", x.getTrue_ans());
                                    Log.e("true_question_no", x.getTrue_question_no());
                                    Log.e("false_ans", x.getFalse_ans());
                                    Log.e("false_question_no", x.getFalse_question_no());
                                    Log.e("is_active", x.getIs_active());
                                    Log.e("is_deleted", x.getIs_deleted());

                                }
                                init();


                            } else if (response.contains("500")) {

                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                Log.e("message", message);
                                Toast.makeText(SurveyActivity.this, message, Toast.LENGTH_SHORT).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse == null) {
                            if (error.getClass().equals(TimeoutError.class)) {
                                Toast.makeText(SurveyActivity.this, "time out error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }) {
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put(Constant.KEY_CATEGORIES_ID, category_id);

                Log.e(Constant.KEY_CATEGORIES_ID, category_id);
                return params;
            }

        };
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(SurveyActivity.this);
        requestQueue.add(stringRequest);
    }

  public void surveyDataAddUpdate(Questions_Local que, String selected_string, String citizen_id,String zone_id,String ward_id){
     Log.e("surveyAddUpdate_c_id",citizen_id);
      String sql = "select  *  from " + TABLE_SURVEY_SUBMISSION + " where " +KEY_QUESTION_ID+ " = ' "+que.getId()+" ' ";
      android.database.sqlite.SQLiteDatabase db = sqLiteDatabase.getReadableDatabase();
       Cursor cursor = db.rawQuery(sql, null);
   //   Toast.makeText(SurveyActivity.this, String.valueOf(cursor.getCount()), Toast.LENGTH_SHORT).show();

      if(cursor.getCount()>0){

          SurveySubmit surveySubmit = new SurveySubmit(que.getId(),que.getQuestion_no(),que.getCategories_id(), selected_string, citizen_id,zone_id,ward_id);
          sqLiteDatabase.getSurveyUpdate(surveySubmit);
          String sql_survey = "select * from " + TABLE_SURVEY_SUBMISSION;
          android.database.sqlite.SQLiteDatabase sqLiteDatabase1 = sqLiteDatabase.getReadableDatabase();
          Cursor cursor1 = sqLiteDatabase1.rawQuery(sql_survey, null);
          JSONArray jsonArray = cur2Json(cursor1);
          jsonArray_string = String.valueOf(jsonArray);
          Log.e("SQLiteJsonArr", String.valueOf("Booking :" + jsonArray));
          Log.e("jsonArray_string", jsonArray_string);


      }
      else {
          SurveySubmit surveySubmit = new SurveySubmit(que.getId(),que.getQuestion_no(),que.getCategories_id(), selected_string, citizen_id,zone_id,ward_id);
          sqliteDatabase.addSurvey(surveySubmit);
          String sql_survey = "select * from " + TABLE_SURVEY_SUBMISSION;
          android.database.sqlite.SQLiteDatabase sqLiteDatabase1 = sqLiteDatabase.getReadableDatabase();
          Cursor cursor1 = sqLiteDatabase1.rawQuery(sql_survey, null);
          JSONArray jsonArray = cur2Json(cursor1);
          jsonArray_string = String.valueOf(jsonArray);
          Log.e("SQLiteJsonArr", String.valueOf("Booking :" + jsonArray));
          Log.e("jsonArray_string", jsonArray_string);
      }


  }
    public JSONArray cur2Json(Cursor cursor) {

        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();
            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        rowObject.put(cursor.getColumnName(i),
                                cursor.getString(i));
                    } catch (Exception e) {
                        Log.e("SQLITE", e.getMessage());
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }

        cursor.close();
        return resultSet;

    }


    private void submitSurvey(final String jsonArray_string) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SURVEY_SAVE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response_register",response);
                        if (response.contains("200")) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.optString("status");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                        else if(response.contains("500")){

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.optString("status");
                                String message = jsonObject.optString("message");

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
                                Toast.makeText(SurveyActivity.this, "time out error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }) {
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put("Survey", jsonArray_string);
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
