package com.cluebix.citizenfeedbackapplication.PublicServicesSurvey;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cluebix.citizenfeedbackapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SewerageAndSanitation extends Fragment {


    public SewerageAndSanitation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sewerage_and_sanitation, container, false);
    }

}
