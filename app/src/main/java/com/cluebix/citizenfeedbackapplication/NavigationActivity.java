package com.cluebix.citizenfeedbackapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cluebix.citizenfeedbackapplication.Constant.SessionManagement;
import com.cluebix.citizenfeedbackapplication.fragment.CommentsSuggestionsFragment;
import com.cluebix.citizenfeedbackapplication.fragment.DashboardFragment;
import com.cluebix.citizenfeedbackapplication.fragment.InformationFragment;
import com.cluebix.citizenfeedbackapplication.fragment.PublicServicesSurveyFragment;

import java.util.HashMap;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG_INFORMATION = "information";
    private static final String TAG_PUBLIC_SEVICES_SURVEY = "public_services_survey";
    private static final String TAG_COMMENTS = "comments";
    private static final String TAG_DASHBOARD = "dashboard";
    private static final String TAG_UPDATE = "update";
    private static final String TAG_LOGOUT = "logout";
    String user_first_name,user_last_name,user_email;
    public static int navItemIndex = 0;
    public static String CURRENT_TAG = TAG_INFORMATION;
    private Handler mHandler;
    DrawerLayout drawer;
    ImageView btn_back;
    TextView txt_user_name,txt_email;
    String language,information,public_service_survey,commment,dashboard;
    NavigationView navigationView;
    View mHeaderView;
    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        btn_back = findViewById(R.id.btn_back);
        Intent intent = getIntent();
        if(intent!=null){
            language = intent.getStringExtra("language");
            information = intent.getStringExtra("info_btn");
            public_service_survey = intent.getStringExtra("public_services_survey_btn");
            commment = intent.getStringExtra("comment_suggestion_btn");
            dashboard = intent.getStringExtra("dashboard_btn");

          /*  Log.e("information",information);
            Log.e("public_service_survey",public_service_survey);
            Log.e("commment",commment);
            Log.e("dashboard",dashboard);*/


        }
        setSupportActionBar(toolbar);
        sessionManagement = new SessionManagement(NavigationActivity.this);
        final HashMap<String, String> user = sessionManagement.getUserDetails();
        user_first_name = user.get(SessionManagement.KEY_FIRST_NAME);
        user_last_name = user.get(SessionManagement.KEY_LAST_NAME);
        user_email = user.get(SessionManagement.KEY_EMAIL);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        mHeaderView =  navigationView.getHeaderView(0);
       // navigationView.getHeaderView(0).setEnabled(false);
        txt_user_name = mHeaderView.findViewById(R.id.txt_user_name);
        txt_email = mHeaderView.findViewById(R.id.txt_email);
        txt_email.setText(user_email);
        txt_user_name.setText(user_first_name + " "+user_last_name);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mHandler = new Handler();
        setTitle(" ");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if(information!=null){
            navItemIndex = 0;
            CURRENT_TAG = TAG_INFORMATION;
            loadFragment();
        }
        else if(commment!=null){
            navItemIndex =2;
            CURRENT_TAG = TAG_COMMENTS;
            loadFragment();
        }
        else if(public_service_survey!=null){
            navItemIndex = 1;
            CURRENT_TAG = TAG_PUBLIC_SEVICES_SURVEY;
            loadFragment();
        }
        else if(dashboard!=null){
            navItemIndex = 3;
            CURRENT_TAG = TAG_DASHBOARD;
            loadFragment();
        }



       /* if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_INFORMATION;
            loadFragment();
        }*/
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(public_service_survey!=null){
            navItemIndex = 1;
            CURRENT_TAG = TAG_PUBLIC_SEVICES_SURVEY;
            loadFragment();
        }
        else if(commment!=null){
            navItemIndex =2;
            CURRENT_TAG = TAG_COMMENTS;
            loadFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.e("id", String.valueOf(id));

        switch (id) {
            //Replacing the main content with ContentFragment Which is our Inbox View;
            case R.id.nav_information:
                CURRENT_TAG = TAG_INFORMATION;
                navItemIndex = 0;
                break;
             case R.id.nav_public_services_survey:
                CURRENT_TAG = TAG_PUBLIC_SEVICES_SURVEY;
                navItemIndex = 1;
                break;

            case R.id.nav_comment:
                navItemIndex =2;
                CURRENT_TAG = TAG_COMMENTS;
                break;
            case R.id.nav_update:
                Intent intent = new Intent(NavigationActivity.this,UpdateActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case R.id.nav_logout:
               sessionManagement.logoutUser();
                break;

            case R.id.nav_dashboard:
                navItemIndex =3;
                CURRENT_TAG = TAG_DASHBOARD;
                break;
           /* case R.id.nav_invoice:
                navItemIndex =5;
                CURRENT_TAG = TAG_INVOICE;
                break;
           case R.id.nav_event:
                navItemIndex =5;
                CURRENT_TAG = TAG_EVENTS;
                break;
            case R.id.nav_enquiry:
                navItemIndex =6;
                CURRENT_TAG = TAG_ENQUIRY;
                break;
            case R.id.nav_view_balance:
                CURRENT_TAG = TAG_VIEW_BALANCE;
                navItemIndex = 7;
                break;
            case R.id.nav_contact_us:
                navItemIndex = 8;
                CURRENT_TAG = TAG_CONTACT_US;
                break;
            case R.id.nav_owner_profile:
                CURRENT_TAG = TAG_OWNER_PROFILE;
                navItemIndex = 9;
                break;
            case R.id.nav_gym_profile:
                CURRENT_TAG = TAG_GYM_PROFILE;
                navItemIndex = 10;
                break;*/
            default:
                navItemIndex = 0;
        }
        loadFragment();
        return true;
    }

    private void loadFragment() {
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }


        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex){
            case 0:
                InformationFragment informationFragment = new InformationFragment();
                return informationFragment;

            case 1:
                PublicServicesSurveyFragment publicServicesSurveyFragment = new PublicServicesSurveyFragment(language);
                return publicServicesSurveyFragment;

            case 2:
                CommentsSuggestionsFragment commentsSuggestionsFragment = new CommentsSuggestionsFragment(language);
                return commentsSuggestionsFragment;
            case 3:
                DashboardFragment dashboardFragment = new DashboardFragment();
                return  dashboardFragment;




                /*Intent intent = new Intent(NavigationActivity.this, TotalRequest.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);*/

           /* case 5:
                InvoiceFragment invoiceFragment = new InvoiceFragment();
                return  invoiceFragment;*/
            default:
                return new InformationFragment();

        }

    }

   /* @Override
    public void onBackPressed() {
        // Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
        if(navItemIndex!=0 && CURRENT_TAG!=TAG_INFORMATION){
            navItemIndex = 0;
            CURRENT_TAG = TAG_INFORMATION;
            loadFragment();
            navigationView.getMenu().getItem(0).setChecked(true);
        }
        else{
            finish();
        }


        //super.onBackPressed();

    }*/


}
