package com.cluebix.citizenfeedbackapplication.information.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cluebix.citizenfeedbackapplication.R;
import com.cluebix.citizenfeedbackapplication.model.DepartmentalList;
import com.cluebix.citizenfeedbackapplication.model.ZonalList;

import java.util.ArrayList;

public class DepartmentalAdapter extends RecyclerView.Adapter<DepartmentalAdapter.ViewHolder> {

    private ArrayList<DepartmentalList> departmentalListArrayList;
    Context context;

    public DepartmentalAdapter(Context context, ArrayList<DepartmentalList> departmentalListArrayList){
        this.context = context;
        this.departmentalListArrayList = departmentalListArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_departmental_list, viewGroup, false);
        return new DepartmentalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final DepartmentalList departmentalList = departmentalListArrayList.get(i);

        viewHolder.txt_contact_no.setText(departmentalList.getContact_no());
        viewHolder.txt_sr_no.setText(departmentalList.getId());
        viewHolder.txt_department_name.setText(departmentalList.getDepartment());
        viewHolder.txt_department_head.setText(departmentalList.getDepartmentHead());
        viewHolder.txt_designation.setText(departmentalList.getDesignation());
    }

    @Override
    public int getItemCount() {
        return departmentalListArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_sr_no,txt_department_name,txt_department_head,txt_designation,txt_contact_no;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_contact_no = itemView.findViewById(R.id.txt_contact_no);
            txt_sr_no = itemView.findViewById(R.id.txt_sr_no);
            txt_department_name = itemView.findViewById(R.id.txt_department);
            txt_department_head = itemView.findViewById(R.id.txt_department_head);
            txt_designation = itemView.findViewById(R.id.txt_designation);
        }
    }
}
