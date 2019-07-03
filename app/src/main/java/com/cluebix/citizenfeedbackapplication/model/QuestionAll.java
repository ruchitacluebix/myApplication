package com.cluebix.citizenfeedbackapplication.model;

import android.content.Context;

import java.io.Serializable;

public class QuestionAll implements Serializable {
    String id,categories_id,is_option,question_no,name,option_a,option_b,option_c,option_d,option_e,option_f,option_g,option_h,true_ans,true_question_no,false_ans,false_question_no;
    Context context;

    public QuestionAll(Context context){
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public String getOption_e() {
        return option_e;
    }

    public void setOption_e(String option_e) {
        this.option_e = option_e;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(String categories_id) {
        this.categories_id = categories_id;
    }

    public String getIs_option() {
        return is_option;
    }

    public void setIs_option(String is_option) {
        this.is_option = is_option;
    }

    public String getQuestion_no() {
        return question_no;
    }

    public void setQuestion_no(String question_no) {
        this.question_no = question_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOption_a() {
        return option_a;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public String getOption_d() {
        return option_d;
    }

    public void setOption_d(String option_d) {
        this.option_d = option_d;
    }

    public String getOption_f() {
        return option_f;
    }

    public void setOption_f(String option_f) {
        this.option_f = option_f;
    }

    public String getOption_g() {
        return option_g;
    }

    public void setOption_g(String option_g) {
        this.option_g = option_g;
    }

    public String getOption_h() {
        return option_h;
    }

    public void setOption_h(String option_h) {
        this.option_h = option_h;
    }

    public String getTrue_ans() {
        return true_ans;
    }

    public void setTrue_ans(String true_ans) {
        this.true_ans = true_ans;
    }

    public String getTrue_question_no() {
        return true_question_no;
    }

    public void setTrue_question_no(String true_question_no) {
        this.true_question_no = true_question_no;
    }

    public String getFalse_ans() {
        return false_ans;
    }

    public void setFalse_ans(String false_ans) {
        this.false_ans = false_ans;
    }

    public String getFalse_question_no() {
        return false_question_no;
    }

    public void setFalse_question_no(String false_question_no) {
        this.false_question_no = false_question_no;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
