package com.cluebix.citizenfeedbackapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectLanguageActivity extends AppCompatActivity {
Button btn_english,btn_marathi;
String language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        init();
    }

    private void init() {
        btn_english = findViewById(R.id.btn_english);
        btn_marathi = findViewById(R.id.btn_marathi);
        btn_marathi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language ="1";
                Intent intent = new Intent(SelectLanguageActivity.this,EquicityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("language",language);
                startActivity(intent);

            }
        });
        btn_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language ="0";
                Intent intent = new Intent(SelectLanguageActivity.this,EquicityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("language",language);
                startActivity(intent);

            }
        });
    }
}
