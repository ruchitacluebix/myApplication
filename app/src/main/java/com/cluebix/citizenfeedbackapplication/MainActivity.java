package com.cluebix.citizenfeedbackapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.cluebix.citizenfeedbackapplication.Constant.CheckNetwork;
import com.cluebix.citizenfeedbackapplication.Constant.Connection;
import com.cluebix.citizenfeedbackapplication.Constant.Constant;
import com.cluebix.citizenfeedbackapplication.Constant.SessionManagement;
import com.cluebix.citizenfeedbackapplication.adapter.viewPagerAdapter;
import com.cluebix.citizenfeedbackapplication.model.Splash;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView textView_skip,textView_title,textView_desc;
    private final int SPLASH_DISPLAY_LENGTH =6000;
    ViewPager pager;
    private TextView[] dots;
    ArrayList<String> sliderImageUrlGlobalList;
    LinearLayout sliderDotspanel;
    private int dotscount;
    viewPagerAdapter adapter;
    private LinearLayout llPagerDots;
    Context fragmentContext;

    ArrayList<Splash> splashArrayList = new ArrayList<>();
    public static final String SPLASH_MESSAGE = Connection.URL + Constant.API_SPLASH;
    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManagement = new SessionManagement(MainActivity.this);
        init();
        sliderImageUrlGlobalList = new ArrayList<>();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 3000, 5000);





        splashMessage();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                boolean checkConnection = new CheckNetwork().checkConnection(MainActivity.this);
                if (checkConnection) {

                    if (sessionManagement.isLoggedIn()) {
                        Intent intent = new Intent(MainActivity.this, SelectLanguageActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();

                    } else {
                        Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent2);
                        finish();
                    }
                } else {
                    String message = "No network found.Please try again.";
                    alertDialog(message);
                }



            }
        }, SPLASH_DISPLAY_LENGTH);


    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout relativeLayout=(RelativeLayout) findViewById(R.id.relative_layout);
        relativeLayout.clearAnimation();
        relativeLayout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        ImageView nmc_logo = (ImageView) findViewById(R.id.nmc_logo);
        iv.clearAnimation();
        iv.startAnimation(anim);
        nmc_logo.clearAnimation();
        nmc_logo.startAnimation(anim);
        textView_desc.clearAnimation();
        textView_desc.startAnimation(anim);
        textView_title.clearAnimation();
        textView_title.startAnimation(anim);
    }

    private void init() {
        textView_skip = findViewById(R.id.txt_skip);
        textView_title = findViewById(R.id.txt_title);
        textView_desc = findViewById(R.id.txt_discription);
//        adapter=new viewPagerAdapter(getBaseContext(),sale_image_array,sliderImageUrlGlobalList);




        textView_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });
    }

    private void launchHomeScreen() {
        startActivity(new Intent(MainActivity.this, SelectLanguageActivity.class));
        finish();
    }

    private void initView(Context context){
        adapter=new viewPagerAdapter(this,sliderImageUrlGlobalList);

        MainActivity anotherContext = (MainActivity) context;

        fragmentContext = context;

        pager= findViewById(R.id.viewPager);
        llPagerDots =findViewById(R.id.pager_dots);
        pager.setAdapter(adapter);

        addBottomDots(0);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    public void alertDialog(String msg) {
        android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Check Internet Connection");
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }
    private void addBottomDots(int currentPage) {



//      previously working code  dots = new TextView[sale_image_array.length];
        dots = new TextView[sliderImageUrlGlobalList.size()];
        llPagerDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#000000"));
            llPagerDots.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#FFFFFF"));
    }


    private class SliderTimer extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                  previously working  if (pager.getCurrentItem() < sale_image_array.length - 1) {
                    pager= findViewById(R.id.viewPager);
                    if (pager.getAdapter() != null){

                        if (pager.getCurrentItem() < pager.getAdapter().getCount() - 1) {
                            pager.setCurrentItem(pager.getCurrentItem() + 1);
                        } else {
                            pager.setCurrentItem(0);
                        }
                    }

                }

            });
        }
    }

    public void loadSlider(ArrayList<String> sliderImageUrlList,Context context){

        sliderImageUrlGlobalList = new ArrayList<>();
        sliderImageUrlGlobalList.clear();
        sliderImageUrlGlobalList = sliderImageUrlList;

        initView(context);

    }

    public void splashMessage(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SPLASH_MESSAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("200")) {

                            try {
                                Log.e("Response zone ", response);

                                JSONObject json = new JSONObject(response);
                                String status = json.getString("status");
                                String data = json.getString("data");
                                Log.e("status",status);
                                Log.e("data",data);
                                JSONArray jsonArray = json.optJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json2 = jsonArray.getJSONObject(i);
                                    Splash splash = new Splash(MainActivity.this);
                                    splash.setId(json2.optString("id"));
                                    splash.setTitle(json2.optString("title"));
                                    splash.setMesssage(json2.optString("photo"));
                                    splashArrayList.add(splash);
                                    sliderImageUrlGlobalList.add(json2.optString("photo"));
                                    textView_desc.setText(splash.getTitle());


                                }
                                StartAnimations();
                                loadSlider(sliderImageUrlGlobalList,fragmentContext);
                               // Toast.makeText(MainActivity.this, String.valueOf(sliderImageUrlGlobalList.size()), Toast.LENGTH_SHORT).show();

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
}
