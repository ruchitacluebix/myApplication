package com.cluebix.citizenfeedbackapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class EquicityActivity extends AppCompatActivity {
Button btn_information,btn_public_service_survey,btn_comment,btn_dashboard;
String language;
ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equicity);
        init();
        Intent intent = getIntent();
        if(intent!=null){
            language = intent.getStringExtra("language");
        }
    }

    private void init() {
        btn_information = findViewById(R.id.btn_information);
        btn_public_service_survey = findViewById(R.id.btn_public_service_survey);
        btn_comment = findViewById(R.id.btn_comment);
        btn_dashboard = findViewById(R.id.btn_dashboard);
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EquicityActivity.this,NavigationActivity.class);
                intent.putExtra("info_btn","information");
                intent.putExtra("language",language);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        btn_public_service_survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EquicityActivity.this,NavigationActivity.class);
                intent.putExtra("public_services_survey_btn","public_services_survey");
                intent.putExtra("language",language);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EquicityActivity.this,NavigationActivity.class);
                intent.putExtra("comment_suggestion_btn","comment_suggestion");
                intent.putExtra("language",language);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        btn_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EquicityActivity.this,NavigationActivity.class);
                intent.putExtra("dashboard_btn","dashboard");
                intent.putExtra("language",language);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }
}
