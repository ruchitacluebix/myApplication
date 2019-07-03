package com.cluebix.citizenfeedbackapplication.adapter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cluebix.citizenfeedbackapplication.Constant.Connection;
import com.cluebix.citizenfeedbackapplication.Constant.Constant;
import com.cluebix.citizenfeedbackapplication.Constant.SessionManagement;
import com.cluebix.citizenfeedbackapplication.R;
import com.cluebix.citizenfeedbackapplication.model.QuestionAll;
import com.cluebix.citizenfeedbackapplication.model.login;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class QuestionAllAdapter extends RecyclerView.Adapter<QuestionAllAdapter.ViewHolder>{
    String option,true_ans,ans,false_ans,citizen_id,question_id,selected,categories_id;
    int position;
    private ArrayList<QuestionAll> questionAllList;
    private Context context;
    SessionManagement sessionManagement;
    public static final String SURVEY_SAVE = Connection.URL + Constant.API_SURVEY_SAVE;

    public QuestionAllAdapter(ArrayList<QuestionAll> questionAllList, Context ctx) {
        this.questionAllList = questionAllList;
        context = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_all_questions, viewGroup   , false);
        sessionManagement = new SessionManagement(context);
        HashMap<String,String> user =sessionManagement.getUserDetails();
        citizen_id = user.get(sessionManagement.KEY_ID);

        return new QuestionAllAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final QuestionAll questionAll = questionAllList.get(i);
        viewHolder.txt_question.setText(questionAll.getName());
        option = questionAll.getIs_option();

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
            viewHolder.rad_opt_a.setText(questionAll.getOption_a());

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
            viewHolder.rad_opt_a.setText(questionAll.getOption_a());
            viewHolder.rad_opt_b.setText(questionAll.getOption_b());
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
            viewHolder.rad_opt_a.setText(questionAll.getOption_a());
            viewHolder.rad_opt_b.setText(questionAll.getOption_b());
            viewHolder.rad_opt_c.setText(questionAll.getOption_c());
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
            viewHolder.rad_opt_a.setText(questionAll.getOption_a());
            viewHolder.rad_opt_b.setText(questionAll.getOption_b());
            viewHolder.rad_opt_c.setText(questionAll.getOption_c());
            viewHolder.rad_opt_d.setText(questionAll.getOption_d());
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
            viewHolder.rad_opt_a.setText(questionAll.getOption_a());
            viewHolder.rad_opt_b.setText(questionAll.getOption_b());
            viewHolder.rad_opt_c.setText(questionAll.getOption_c());
            viewHolder.rad_opt_d.setText(questionAll.getOption_d());
            viewHolder.rad_opt_e.setText(questionAll.getOption_e());
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
            viewHolder.rad_opt_a.setText(questionAll.getOption_a());
            viewHolder.rad_opt_b.setText(questionAll.getOption_b());
            viewHolder.rad_opt_c.setText(questionAll.getOption_c());
            viewHolder.rad_opt_d.setText(questionAll.getOption_d());
            viewHolder.rad_opt_e.setText(questionAll.getOption_e());
            viewHolder.rad_opt_f.setText(questionAll.getOption_f());
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
            viewHolder.rad_opt_a.setText(questionAll.getOption_a());
            viewHolder.rad_opt_b.setText(questionAll.getOption_b());
            viewHolder.rad_opt_c.setText(questionAll.getOption_c());
            viewHolder.rad_opt_d.setText(questionAll.getOption_d());
            viewHolder.rad_opt_e.setText(questionAll.getOption_e());
            viewHolder.rad_opt_f.setText(questionAll.getOption_f());
            viewHolder.rad_opt_g.setText(questionAll.getOption_g());
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
            viewHolder.rad_opt_a.setText(questionAll.getOption_a());
            viewHolder.rad_opt_b.setText(questionAll.getOption_b());
            viewHolder.rad_opt_c.setText(questionAll.getOption_c());
            viewHolder.rad_opt_d.setText(questionAll.getOption_d());
            viewHolder.rad_opt_e.setText(questionAll.getOption_e());
            viewHolder.rad_opt_f.setText(questionAll.getOption_f());
            viewHolder.rad_opt_g.setText(questionAll.getOption_g());
            viewHolder.rad_opt_h.setText(questionAll.getOption_h());
        }

        //  addRadioButtons(radio_options,viewHolder,questionAll);

    }



    @Override
    public int getItemCount() {
        return questionAllList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_question;
        RadioButton radioButton;
        TextView edttext;
        LinearLayout linear_layout,linear_layout_edt;
        public RadioGroup priceGroup;
        CardView card_view;
        RadioButton rad_opt_a,rad_opt_b,rad_opt_c,rad_opt_d,rad_opt_e,rad_opt_f,rad_opt_g,rad_opt_h;
        Button btn_submit;

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

           // btn_submit = (Button) itemView.findViewById(R.id.btn_submit);

            /*btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected = edttext.getText().toString().trim();
                    position = getAdapterPosition();
                    question_id = questionAllList.get(position).getQuestion_no();
                    if(selected.length()!=0){

                        // submit(position,priceGroup,selected,question_id);
                        btn_submit.setEnabled(false);
                    }
                    else{
                        Toast.makeText(context, "Please add comment", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/





            priceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    position =getAdapterPosition();
                    categories_id = questionAllList.get(position).getCategories_id();
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) itemView.findViewById(selectedId);
                    true_ans = questionAllList.get(position).getTrue_ans();
                    false_ans = questionAllList.get(position).getFalse_ans();
                    question_id = questionAllList.get(position).getQuestion_no();
                    selected = String.valueOf(radioButton.getText());
                    if(categories_id.equals("1")||categories_id.equals("7")) {
                        if(radioButton.isChecked()) {


                          /*  if (question_id.equals("1")) {
                                if (true_ans.equalsIgnoreCase(selected)) {
                                    notifyDataSetChanged();

                                   submit(position, priceGroup, selected,question_id);
                                } else {
                                    for (int k = 0; k <= 2; k++) {
                                        position = getAdapterPosition() + 1;
                                        questionAllList.remove(position);
                                        position = getAdapterPosition();

                                    }
                                    notifyDataSetChanged();

                                    submit(position, priceGroup, selected,question_id);
                                }

                            } else if (question_id.equals("2")) {
                                if (true_ans.equalsIgnoreCase(selected)) {
                                    position = getAdapterPosition() + 2;
                                    questionAllList.remove(position);
                                    notifyDataSetChanged();
                                } else {
                                    position = getAdapterPosition() + 1;
                                    questionAllList.remove(position);
                                    notifyDataSetChanged();
                                }

                                submit(position, priceGroup, selected,question_id);
                            } else if (question_id.equals("5")) {
                                if (false_ans.equalsIgnoreCase(selected)) {
                                    position = getAdapterPosition() + 1;
                                    questionAllList.remove(position);
                                    notifyDataSetChanged();
                                } else {
                                    notifyDataSetChanged();
                                }

                                submit(position, priceGroup, selected,question_id);
                            }
                            else{

                                submit(position, priceGroup, selected,question_id);
                            }*/

                        }


                    }

                    else if(categories_id.equals("2")||categories_id.equals("8")){


                        if(radioButton.isChecked()) {
                            /* if (question_id.equals("2")) {
                                if (true_ans.contains(selected)) {

                                    notifyDataSetChanged();
                                } else {
                                    for (int k = 0; k <= 1; k++) {
                                        position = getAdapterPosition() + 1;
                                        questionAllList.remove(position);
                                        notifyDataSetChanged();
                                        position = getAdapterPosition();
                                    }


                                }
                             //   submit(position, priceGroup, selected,question_id);
                            }

                            else if (question_id.equals("3")) {
                                if (true_ans.contains(selected)) {
                                    position = getAdapterPosition() + 1;
                                    questionAllList.remove(position);
                                    notifyDataSetChanged();
                                } else {
                                    notifyDataSetChanged();
                                }
                              //  submit(position, priceGroup, selected,question_id);
                            }

                             else if (question_id.equals("6")) {
                                 if (true_ans.contains(selected)) {

                                     notifyDataSetChanged();
                                 } else {
                                     position = getAdapterPosition() + 1;
                                     questionAllList.remove(position);
                                     notifyDataSetChanged();
                                 }
                                // submit(position, priceGroup, selected,question_id);
                             }
                             else{
                                 //submit(position, priceGroup, selected,question_id);
                             }*/
                        }
                    }
                    else if(categories_id.equals(categories_id.equals("3")||categories_id.equals("9"))){

                        if(radioButton.isChecked()) {

                        }
                    }
                    else if(categories_id.equals(categories_id.equals("4")||categories_id.equals("10"))){

                        if(radioButton.isChecked()) {

                        }
                    }
                }
            });
        }
    }
    public int getItemViewType(int position) {
        return position;
    }


   /* private void submit(int position, final RadioGroup priceGroup, final String selected, final String question_id) {
       //  question_id = questionAllList.get(position).getQuestion_no();
        Toast.makeText(context, question_id, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SURVEY_SAVE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response_register",response);
                        if (response.contains("200")) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.optString("status");
                                String data = jsonObject.optString("data");
                                Toast.makeText(context, "id"+data, Toast.LENGTH_SHORT).show();
                                if (data.equals(question_id)){

                                    for ( int i = 0; i < priceGroup.getChildCount();i++ ){
                                        View view = priceGroup.getChildAt(i);
                                        view.setEnabled(false); // Or whatever you want to do with the view.
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                        else if(response.contains("500")){

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.optString("status");
                                String message = jsonObject.optString("message");
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                               for ( int i = 0; i < priceGroup.getChildCount(); i++ ){
                                    View view = priceGroup.getChildAt(i);
                                    view.setEnabled(false); // Or whatever you want to do with the view.
                                }
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
                                Toast.makeText(context, "time out error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }) {
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put(Constant.KEY_CITIZEN_ID, citizen_id);
                params.put(Constant.KEY_QUESTION_ID, question_id);
                params.put(Constant.KEY_TRUE_ANS, selected);

                Log.e(Constant.KEY_CITIZEN_ID, citizen_id);
                Log.e(Constant.KEY_QUESTION_ID, question_id);
                Log.e(Constant.KEY_TRUE_ANS, selected);
                return params;
            }


        };
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }*/


}
