package com.cluebix.citizenfeedbackapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cluebix.citizenfeedbackapplication.Helper.SqliteDatabase;
import com.cluebix.citizenfeedbackapplication.R;
import com.cluebix.citizenfeedbackapplication.model.Questions_Local;

import java.util.ArrayList;

public class QuestionLocalAdapter extends RecyclerView.Adapter<QuestionLocalAdapter.ViewHolder> {
    private Context context;
    String option,sql;
    public SqliteDatabase sqLiteDatabase;
    private ArrayList<Questions_Local> questionsLocalArrayList;
    private static ArrayList<Questions_Local> permanentQList;

public  QuestionLocalAdapter(Context context,ArrayList<Questions_Local> questionsLocalArrayList){
    this.context = context;
    this.questionsLocalArrayList = questionsLocalArrayList;
    permanentQList = questionsLocalArrayList;
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_all_questions, viewGroup   , false);
        sqLiteDatabase = new SqliteDatabase(context);
        return new QuestionLocalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Questions_Local questionsLocal = questionsLocalArrayList.get(i);
        viewHolder.txt_question.setText(questionsLocal.getName());
        option = questionsLocal.getIs_option();

        if(option.equals("0")){
            viewHolder.rad_opt_a.setVisibility(View.GONE);
            viewHolder.rad_opt_b.setVisibility(View.GONE);
            viewHolder.rad_opt_c.setVisibility(View.GONE);
            viewHolder.rad_opt_d.setVisibility(View.GONE);
            viewHolder.rad_opt_e.setVisibility(View.GONE);
            viewHolder.rad_opt_f.setVisibility(View.GONE);
            viewHolder.rad_opt_g.setVisibility(View.GONE);
            viewHolder.rad_opt_h.setVisibility(View.GONE);
            viewHolder.edttext.setVisibility(View.VISIBLE);
            viewHolder.linear_layout_edt.setVisibility(View.VISIBLE);
        }
        else if(option.equals("1")){
            viewHolder.rad_opt_a.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_b.setVisibility(View.GONE);
            viewHolder.rad_opt_c.setVisibility(View.GONE);
            viewHolder.rad_opt_d.setVisibility(View.GONE);
            viewHolder.rad_opt_e.setVisibility(View.GONE);
            viewHolder.rad_opt_f.setVisibility(View.GONE);
            viewHolder.rad_opt_g.setVisibility(View.GONE);
            viewHolder.rad_opt_h.setVisibility(View.GONE);
            viewHolder.edttext.setVisibility(View.GONE);
            viewHolder.rad_opt_a.setText(questionsLocal.getOption_a());

        }
        else if(option.equals("2")){
            viewHolder.rad_opt_a.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_b.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_c.setVisibility(View.GONE);
            viewHolder.rad_opt_d.setVisibility(View.GONE);
            viewHolder.rad_opt_e.setVisibility(View.GONE);
            viewHolder.rad_opt_f.setVisibility(View.GONE);
            viewHolder.rad_opt_g.setVisibility(View.GONE);
            viewHolder.rad_opt_h.setVisibility(View.GONE);
            viewHolder.edttext.setVisibility(View.GONE);
            viewHolder.rad_opt_a.setText(questionsLocal.getOption_a());
            viewHolder.rad_opt_b.setText(questionsLocal.getOption_b());
        }
        else if(option.equals("3")){
            viewHolder.rad_opt_a.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_b.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_c.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_d.setVisibility(View.GONE);
            viewHolder.rad_opt_e.setVisibility(View.GONE);
            viewHolder.rad_opt_f.setVisibility(View.GONE);
            viewHolder.rad_opt_g.setVisibility(View.GONE);
            viewHolder.rad_opt_h.setVisibility(View.GONE);
            viewHolder.edttext.setVisibility(View.GONE);
            viewHolder.rad_opt_a.setText(questionsLocal.getOption_a());
            viewHolder.rad_opt_b.setText(questionsLocal.getOption_b());
            viewHolder.rad_opt_c.setText(questionsLocal.getOption_c());
        }
        else if(option.equals("4")){
            viewHolder.rad_opt_a.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_b.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_c.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_d.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_e.setVisibility(View.GONE);
            viewHolder.rad_opt_f.setVisibility(View.GONE);
            viewHolder.rad_opt_g.setVisibility(View.GONE);
            viewHolder.rad_opt_h.setVisibility(View.GONE);
            viewHolder.edttext.setVisibility(View.GONE);
            viewHolder.rad_opt_a.setText(questionsLocal.getOption_a());
            viewHolder.rad_opt_b.setText(questionsLocal.getOption_b());
            viewHolder.rad_opt_c.setText(questionsLocal.getOption_c());
            viewHolder.rad_opt_d.setText(questionsLocal.getOption_d());
        }
        else if(option.equals("5")){
            viewHolder.rad_opt_a.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_b.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_c.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_d.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_e.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_f.setVisibility(View.GONE);
            viewHolder.rad_opt_g.setVisibility(View.GONE);
            viewHolder.rad_opt_h.setVisibility(View.GONE);
            viewHolder.edttext.setVisibility(View.GONE);
            viewHolder.rad_opt_a.setText(questionsLocal.getOption_a());
            viewHolder.rad_opt_b.setText(questionsLocal.getOption_b());
            viewHolder.rad_opt_c.setText(questionsLocal.getOption_c());
            viewHolder.rad_opt_d.setText(questionsLocal.getOption_d());
            viewHolder.rad_opt_e.setText(questionsLocal.getOption_e());
        }
        else  if(option.equals("6")){
            viewHolder.rad_opt_a.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_b.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_c.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_d.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_e.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_f.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_g.setVisibility(View.GONE);
            viewHolder.rad_opt_h.setVisibility(View.GONE);
            viewHolder.edttext.setVisibility(View.GONE);
            viewHolder.rad_opt_a.setText(questionsLocal.getOption_a());
            viewHolder.rad_opt_b.setText(questionsLocal.getOption_b());
            viewHolder.rad_opt_c.setText(questionsLocal.getOption_c());
            viewHolder.rad_opt_d.setText(questionsLocal.getOption_d());
            viewHolder.rad_opt_e.setText(questionsLocal.getOption_e());
            viewHolder.rad_opt_f.setText(questionsLocal.getOption_f());
        }
        else  if(option.equals("7")){
            viewHolder.rad_opt_a.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_b.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_c.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_d.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_e.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_f.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_g.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_h.setVisibility(View.GONE);
            viewHolder.edttext.setVisibility(View.GONE);
            viewHolder.rad_opt_a.setText(questionsLocal.getOption_a());
            viewHolder.rad_opt_b.setText(questionsLocal.getOption_b());
            viewHolder.rad_opt_c.setText(questionsLocal.getOption_c());
            viewHolder.rad_opt_d.setText(questionsLocal.getOption_d());
            viewHolder.rad_opt_e.setText(questionsLocal.getOption_e());
            viewHolder.rad_opt_f.setText(questionsLocal.getOption_f());
            viewHolder.rad_opt_g.setText(questionsLocal.getOption_g());
        }
        else  if(option.equals("8")){
            viewHolder.rad_opt_a.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_b.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_c.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_d.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_e.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_f.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_g.setVisibility(View.VISIBLE);
            viewHolder.rad_opt_h.setVisibility(View.VISIBLE);
            viewHolder.edttext.setVisibility(View.GONE);
            viewHolder.rad_opt_a.setText(questionsLocal.getOption_a());
            viewHolder.rad_opt_b.setText(questionsLocal.getOption_b());
            viewHolder.rad_opt_c.setText(questionsLocal.getOption_c());
            viewHolder.rad_opt_d.setText(questionsLocal.getOption_d());
            viewHolder.rad_opt_e.setText(questionsLocal.getOption_e());
            viewHolder.rad_opt_f.setText(questionsLocal.getOption_f());
            viewHolder.rad_opt_g.setText(questionsLocal.getOption_g());
            viewHolder.rad_opt_h.setText(questionsLocal.getOption_h());
        }

        viewHolder.rad_opt_e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }
            }
        });

        viewHolder.rad_opt_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    switch (questionsLocal.getQuestion_no()){
                        case "1":
                            removeQuestionByNo("2");
                            removeQuestionByNo("3");
                            removeQuestionByNo("4");
                            notifyDataSetChanged();
                            break;
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return questionsLocalArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_question;
        RadioButton radioButton;
        TextView edttext;

        LinearLayout linear_layout,linear_layout_edt;
        public RadioGroup priceGroup;
        CardView card_view;
        int position;
        String question_id,true_ans,false_ans,selected,id,category_id,is_option,question_name,option_a,option_b,option_c,option_d,option_e,option_f,option_g,option_h,true_question_no,false_question_no,is_active,is_delete;
        RadioButton rad_opt_a,rad_opt_b,rad_opt_c,rad_opt_d,rad_opt_e,rad_opt_f,rad_opt_g,rad_opt_h;
        public ViewHolder(@NonNull final View itemView) {

            super(itemView);
            txt_question = (TextView) itemView.findViewById(R.id.txt_question);
            edttext = (TextView) itemView.findViewById(R.id.edttext);
            priceGroup = (RadioGroup) itemView.findViewById(R.id.price_grp);
            rad_opt_a = (RadioButton) itemView.findViewById(R.id.rad_opt_a);
            rad_opt_b = (RadioButton) itemView.findViewById(R.id.rad_opt_b);
            rad_opt_c = (RadioButton) itemView.findViewById(R.id.rad_opt_c);
            rad_opt_d = (RadioButton) itemView.findViewById(R.id.rad_opt_d);
            rad_opt_e = (RadioButton) itemView.findViewById(R.id.rad_opt_e);
            rad_opt_f = (RadioButton) itemView.findViewById(R.id.rad_opt_f);
            rad_opt_g = (RadioButton) itemView.findViewById(R.id.rad_opt_g);
            rad_opt_h = (RadioButton) itemView.findViewById(R.id.rad_opt_h);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            linear_layout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
            linear_layout_edt = (LinearLayout) itemView.findViewById(R.id.linear_layout_edt);


//            rad_opt_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if(isChecked){
//                        int pos = getAdapterPosition();
//                        Questions_Local que = questionsLocalArrayList.get(pos);
//                        switch (que.getQuestion_no()){
//                            case "1":
//                                questionsLocalArrayList.remove(1);
//                                questionsLocalArrayList.remove(2);
//                                questionsLocalArrayList.remove(3);
//                                notifyDataSetChanged();
//                                break;
//
//                            case "2":
//                                break;
//
//                            case "3":
//                                break;
//
//                            case "4":
//                                break;
//
//                            case "5":
//                                break;
//
//                            case "6":
//                                break;
//
//                            case "7":
//                                break;
//
//
//                        }
//                    }
//
//                }
//            });
//
//            rad_opt_e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if(isChecked){
//                        System.out.println("Selected question : "+questionsLocalArrayList.get(getAdapterPosition()).getQuestion_no());
//                        int pos= getAdapterPosition();
//                        switch (questionsLocalArrayList.get(pos).getQuestion_no()){
//                            case "1":
//                                questionsLocalArrayList.add(1,permanentQList.get(1));
//                                questionsLocalArrayList.add(2,permanentQList.get(2));
//                                questionsLocalArrayList.add(3,permanentQList.get(3));
//                                notifyDataSetChanged();
//                                break;
//                        }
//                    }
//                }
//            });


           /* priceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    position =getAdapterPosition();
                    question_id = questionsLocalArrayList.get(position).getQuestion_no();
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) itemView.findViewById(selectedId);
                    id = questionsLocalArrayList.get(position).getId();
                    category_id = questionsLocalArrayList.get(position).getCategories_id();
                    is_option = questionsLocalArrayList.get(position).getIs_option();
                    question_name = questionsLocalArrayList.get(position).getName();
                    option_a = questionsLocalArrayList.get(position).getOption_a();
                    option_b = questionsLocalArrayList.get(position).getOption_b();
                    option_c = questionsLocalArrayList.get(position).getOption_c();
                    option_d = questionsLocalArrayList.get(position).getOption_d();
                    option_e = questionsLocalArrayList.get(position).getOption_e();
                    option_f = questionsLocalArrayList.get(position).getOption_f();
                    option_g = questionsLocalArrayList.get(position).getOption_g();
                    option_h = questionsLocalArrayList.get(position).getOption_h();
                    true_question_no = questionsLocalArrayList.get(position).getTrue_question_no();
                    false_question_no = questionsLocalArrayList.get(position).getFalse_question_no();
                    is_active = questionsLocalArrayList.get(position).getIs_active();
                    is_delete = questionsLocalArrayList.get(position).getIs_deleted();


                    true_ans = questionsLocalArrayList.get(position).getTrue_ans();
                    false_ans = questionsLocalArrayList.get(position).getFalse_ans();
                    question_id = questionsLocalArrayList.get(position).getQuestion_no();
                    selected = String.valueOf(radioButton.getText());
                    Toast.makeText(context, selected, Toast.LENGTH_SHORT).show();
                    Questions_Local questions_local = new Questions_Local(id,category_id,is_option,question_id,question_name,option_a,option_b,option_c,option_d,option_e,option_f,option_g,option_h,true_ans,true_question_no,false_ans,false_question_no,is_active,is_delete);
                    sqLiteDatabase.updateChecked(questions_local);
                    if(category_id.equalsIgnoreCase("1")) {
                        if (question_id.equalsIgnoreCase("1")) {
                            if (!selected.equalsIgnoreCase(true_ans)) {
                                int length = true_question_no.length();
                                if (length == 1) {
                                    char first_position = true_question_no.charAt(0);
                                    sql = " where question_no  NOT BETWEEN " + first_position + " AND " + first_position;

                                } else if (length == 0) {
                                    sql = "";
                                } else {
                                    char first_position = true_question_no.charAt(0);
                                    char last_position = true_question_no.charAt(length - 1);
                                    sql = " where question_no  NOT BETWEEN " + first_position + " AND " + last_position;
                                }
                                Log.e("length_str", String.valueOf(length));
                                questionsLocalArrayList = sqLiteDatabase.getUpdatedQuestion(sql);
                                notifyDataSetChanged();
                            } else *//*if(true_ans.equalsIgnoreCase(selected))*//* {
                                questionsLocalArrayList = sqLiteDatabase.question_List();
                                notifyDataSetChanged();
                            }
                        }
                        else if (question_id.equalsIgnoreCase("2")) {
                            if (!selected.equalsIgnoreCase(true_ans)) {
                                int length = true_question_no.length();
                                if (length == 1) {
                                    char first_position = true_question_no.charAt(0);
                                    sql = " where question_no  NOT BETWEEN " + first_position + " AND " + first_position;

                                }

                            } else if(selected.equalsIgnoreCase(true_ans)) {
                                int length = false_question_no.length();
                                if (length == 1) {
                                    char first_position = true_question_no.charAt(0);
                                    sql = " where question_no  NOT BETWEEN " + first_position + " AND " + first_position;

                                }
                                else{
                                    questionsLocalArrayList = sqLiteDatabase.question_List();
                                    notifyDataSetChanged();
                                }

                            }
                            questionsLocalArrayList = sqLiteDatabase.getUpdatedQuestion(sql);
                            notifyDataSetChanged();
                        }
                    }



                }
            });*/
        }
    }

    private boolean searchItem(String queNo){
        try {
            for (int i = 0; i < questionsLocalArrayList.size(); i++) {
                if (questionsLocalArrayList.get(i)!= null && questionsLocalArrayList.get(i).getQuestion_no().equals(queNo)) {
                    return true;
                }
            }
        }
        catch (NullPointerException ex){
            System.err.println(ex.getMessage());
        }
        return false;
    }

    private Questions_Local getQuestionByNo(String queNo){
        try {
            for (int i = 0; i < permanentQList.size(); i++) {
                if (permanentQList.get(i)!=null && permanentQList.get(i).getQuestion_no().equals(queNo)) {
                    return permanentQList.get(i);
                }
            }
        }
        catch (NullPointerException ex ){
            System.err.println(ex.getMessage());
        }
        return new Questions_Local();
    }

    private void removeQuestionByNo(String queNo){
        try {
            for (int i = 0; i < questionsLocalArrayList.size(); i++) {
                if (questionsLocalArrayList.get(i)!=null && questionsLocalArrayList.get(i).getQuestion_no().equals(queNo)) {
                    questionsLocalArrayList.remove(i);
                }
            }
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }
}
