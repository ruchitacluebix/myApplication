package com.cluebix.citizenfeedbackapplication.PublicServicesSurvey;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cluebix.citizenfeedbackapplication.R;


public class CitizenEnagagement extends Fragment {


    public CitizenEnagagement() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_citizen_enagagement, container, false);
    }

}
