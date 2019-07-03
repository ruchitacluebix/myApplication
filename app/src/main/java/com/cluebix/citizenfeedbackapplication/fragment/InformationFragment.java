package com.cluebix.citizenfeedbackapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cluebix.citizenfeedbackapplication.R;
import com.cluebix.citizenfeedbackapplication.information.AboutElectedMembersActivity;
import com.cluebix.citizenfeedbackapplication.information.AboutEquicityActivity;
import com.cluebix.citizenfeedbackapplication.information.AboutNMCActivity;
import com.cluebix.citizenfeedbackapplication.information.AboutZonalCommissionersActivity;


public class InformationFragment extends Fragment {
    View view;
    CardView cardView_abt_nmc,cardView_abt_equicity,cardView_zonal_commisionars,cardView_elected_members;
    public InformationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_information, container, false);
        init(view);


        // Inflate the layout for this fragment
        return view;
    }

    private void init(View view) {
        cardView_abt_nmc = view.findViewById(R.id.cardView_abt_nmc);
        cardView_abt_equicity = view.findViewById(R.id.cardView_abt_equicity);
        cardView_zonal_commisionars = view.findViewById(R.id.cardView_zonal_commissionars);
        cardView_elected_members = view.findViewById(R.id.cardView_electedwards_and_members);
        cardView_abt_nmc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutNMCActivity.class);
                startActivity(intent);
            }
        });
        cardView_abt_equicity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutEquicityActivity.class);
                startActivity(intent);
            }
        });
        cardView_zonal_commisionars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutZonalCommissionersActivity.class);
                startActivity(intent);
            }
        });
        cardView_elected_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutElectedMembersActivity.class);
                startActivity(intent);
            }
        });
    }
}
