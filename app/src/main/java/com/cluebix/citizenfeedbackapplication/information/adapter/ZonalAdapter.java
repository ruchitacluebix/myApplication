package com.cluebix.citizenfeedbackapplication.information.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cluebix.citizenfeedbackapplication.R;
import com.cluebix.citizenfeedbackapplication.model.ZonalList;

import java.util.ArrayList;

public class ZonalAdapter extends RecyclerView.Adapter<ZonalAdapter.ViewHolder> {

    private ArrayList<ZonalList> zonalListArrayList;
    Context context;

    public ZonalAdapter(Context context, ArrayList<ZonalList> zonalListArrayList){
        this.context = context;
        this.zonalListArrayList = zonalListArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_commisioner_list, viewGroup, false);
        return new ZonalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ZonalList zonalList = zonalListArrayList.get(i);

        viewHolder.txt_contact_no.setText(zonalList.getContact_no());
        viewHolder.txt_sr_no.setText(zonalList.getId());
        viewHolder.txt_zone_name.setText(zonalList.getZone_name());
        viewHolder.txt_department_head.setText(zonalList.getDepartment_head());
        viewHolder.txt_designation.setText(zonalList.getDesignation());
    }

    @Override
    public int getItemCount() {
        return zonalListArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_sr_no,txt_zone_name,txt_department_head,txt_designation,txt_contact_no;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_contact_no = itemView.findViewById(R.id.txt_contact_no);
            txt_sr_no = itemView.findViewById(R.id.txt_sr_no);
            txt_zone_name = itemView.findViewById(R.id.txt_zone);
            txt_department_head = itemView.findViewById(R.id.txt_department_head);
            txt_designation = itemView.findViewById(R.id.txt_designation);
        }
    }
}
