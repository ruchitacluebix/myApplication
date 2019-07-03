package com.cluebix.citizenfeedbackapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cluebix.citizenfeedbackapplication.R;
import com.cluebix.citizenfeedbackapplication.model.ElectedMemberInfo;

import java.util.ArrayList;

public class AboutElectedMemberAdapter extends RecyclerView.Adapter<AboutElectedMemberAdapter.ViewHolder>{
    private ArrayList<ElectedMemberInfo> electedMemberInfoArrayList;
    Context context;

    public AboutElectedMemberAdapter(Context context, ArrayList<ElectedMemberInfo> electedMemberInfoArrayList){
         this.context = context;
         this.electedMemberInfoArrayList = electedMemberInfoArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_elected_member, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ElectedMemberInfo electedMemberInfo = electedMemberInfoArrayList.get(i);

        viewHolder.txt_member_name.setText(electedMemberInfo.getMember_name());
        viewHolder.txt_contact_no.setText(electedMemberInfo.getContact_no());
        viewHolder.txt_prabhag.setText(electedMemberInfo.getPrabhag());
        viewHolder.txt_party.setText(electedMemberInfo.getParty());
    }

    @Override
    public int getItemCount() {
        return electedMemberInfoArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
    TextView txt_member_name,txt_contact_no,txt_prabhag,txt_party;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_contact_no = itemView.findViewById(R.id.txt_contact_no);
            txt_member_name = itemView.findViewById(R.id.txt_member_name);
            txt_prabhag = itemView.findViewById(R.id.txt_prabhag);
            txt_party = itemView.findViewById(R.id.txt_party);
        }
    }
}
