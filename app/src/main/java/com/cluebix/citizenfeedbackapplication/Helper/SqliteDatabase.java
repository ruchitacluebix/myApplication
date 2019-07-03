package com.cluebix.citizenfeedbackapplication.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cluebix.citizenfeedbackapplication.model.Questions_Local;
import com.cluebix.citizenfeedbackapplication.model.SurveySubmit;

import java.util.ArrayList;
import java.util.Set;

public class SqliteDatabase extends SQLiteOpenHelper {

    private	static final int DATABASE_VERSION = 1;
    private	static final String	DATABASE_NAME = "survey";
    private	static final String TABLE_QUESTION = "questions";
    private	static final String TABLE_SURVEY_SUBMISSION = "survey_submit";
    Context context;
    String sql;
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ID_SURVEY = "survey_id";
    private static final String COLUMN_CATEGORY_ID = "categories_id";
    private static final String COLUMN_IS_OPTIONS = "is_option";
    private static final String COLUMN_QUESTION_NO = "question_no";
    private static final String COLUMN_QUESTION_NAME = "name";
    private static final String COLUMN_OPTION_A = "option_a";
    private static final String COLUMN_OPTION_B = "option_b";
    private static final String COLUMN_OPTION_C = "option_c";
    private static final String COLUMN_OPTION_D = "option_d";
    private static final String COLUMN_OPTION_E = "option_e";
    private static final String COLUMN_OPTION_F = "option_f";
    private static final String COLUMN_OPTION_G = "option_g";
    private static final String COLUMN_OPTION_H = "option_h";
    private static final String COLUMN_TRUE_ANS = "true_ans";
    private static final String COLUMN_TRUE_QUESTION_NO = "true_question_no";
    private static final String COLUMN_FALSE_ANS = "false_ans";
    private static final String COLUMN_FALSE_QUESTION_NO = "false_question_no";
    private static final String COLUMN_IS_ACTIVE = "is_active";
    private static final String COLUMN_IS_DELETED = "is_deleted";
    private static final String KEY_ID = "id";
    private static final String KEY_QUESTION_ID = "question_id";
    private static final String KEY_CATEGORY_ID = "categories_id";
    private static final String KEY_SELECTED_OPTIONS = "true_ans";
    private static final String KEY_CITIZEN_ID = "citizen_id";
    private static final String KEY_ZONE_ID = "zone_id";
    private static final String KEY_WARD_ID = "ward_id";
    public SqliteDatabase( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_QUESTION +
                "("+ COLUMN_CATEGORY_ID + " TEXT ,"+COLUMN_ID_SURVEY+" TEXT ,"+COLUMN_IS_OPTIONS+" TEXT ,"+COLUMN_QUESTION_NO + " TEXT ,"
                + COLUMN_ID + " INTEGER PRIMARY KEY , " + COLUMN_QUESTION_NAME+ " TEXT ,"
                +COLUMN_OPTION_A+ " TEXT ,"
                +COLUMN_OPTION_B+ " TEXT ,"
                + COLUMN_OPTION_C+ " TEXT ,"
                + COLUMN_OPTION_D+ " TEXT ,"
                + COLUMN_OPTION_E+ " TEXT ,"
                + COLUMN_OPTION_F+ " TEXT ,"
                + COLUMN_OPTION_G+ " TEXT ,"
                + COLUMN_OPTION_H+ " TEXT ,"
                + COLUMN_TRUE_ANS+ " TEXT ,"
                + COLUMN_TRUE_QUESTION_NO+ " TEXT ,"
                + COLUMN_FALSE_ANS+ " TEXT ,"
                + COLUMN_FALSE_QUESTION_NO+ " TEXT ,"
                + COLUMN_IS_ACTIVE+ " TEXT ,"
                + COLUMN_IS_DELETED+ " TEXT "+ ");";


         String query_submit = "CREATE TABLE " + TABLE_SURVEY_SUBMISSION
                + "(" + KEY_ID + "  TEXT ," + KEY_QUESTION_ID + "  INTEGER PRIMARY KEY," + KEY_CATEGORY_ID + " TEXT," + KEY_CITIZEN_ID + " TEXT,"+ KEY_ZONE_ID + " TEXT,"+ KEY_WARD_ID + " TEXT,"
                + KEY_SELECTED_OPTIONS + " TEXT" + ")";

        db.execSQL(query);
        db.execSQL(query_submit);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SURVEY_SUBMISSION);
        onCreate(db);
    }

    public void addQuestions(Questions_Local questions_local){
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_ID, questions_local.getCategories_id());
        values.put(COLUMN_ID_SURVEY, questions_local.getId());
        values.put(COLUMN_IS_OPTIONS, questions_local.getIs_option());
        values.put(COLUMN_QUESTION_NO, questions_local.getQuestion_no());
        values.put(COLUMN_QUESTION_NAME, questions_local.getName());
        values.put(COLUMN_OPTION_A, questions_local.getOption_a());
        values.put(COLUMN_OPTION_B, questions_local.getOption_b());
        values.put(COLUMN_OPTION_C, questions_local.getOption_c());
        values.put(COLUMN_OPTION_D, questions_local.getOption_d());
        values.put(COLUMN_OPTION_E, questions_local.getOption_e());
        values.put(COLUMN_OPTION_F, questions_local.getOption_f());
        values.put(COLUMN_OPTION_G, questions_local.getOption_g());
        values.put(COLUMN_OPTION_H, questions_local.getOption_h());
        values.put(COLUMN_TRUE_ANS, questions_local.getTrue_ans());
        values.put(COLUMN_TRUE_QUESTION_NO, questions_local.getTrue_question_no());
        values.put(COLUMN_FALSE_ANS, questions_local.getFalse_ans());
        values.put(COLUMN_FALSE_QUESTION_NO, questions_local.getFalse_question_no());
        values.put(COLUMN_IS_ACTIVE, questions_local.getIs_active());
        values.put(COLUMN_IS_DELETED, questions_local.getIs_deleted());
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_QUESTION, null, values);
        Log.e("Data Added","Success");
    }

   public void addSurvey(SurveySubmit surveySubmit){
       ContentValues values = new ContentValues();

       values.put(KEY_QUESTION_ID, surveySubmit.getId());
       values.put(KEY_CATEGORY_ID, surveySubmit.getCategories_id());
       values.put(KEY_CITIZEN_ID, surveySubmit.getCitizen_id());
       values.put(KEY_ZONE_ID, surveySubmit.getZone_id());
       values.put(KEY_WARD_ID, surveySubmit.getWard_id());
       values.put(KEY_SELECTED_OPTIONS, surveySubmit.getSelected_option());
       android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
       db.insert(TABLE_SURVEY_SUBMISSION, null, values);
       Log.e("Data Added","Success");
   }


    public void deleteAllRows(){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_QUESTION);
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_SURVEY_SUBMISSION);

        sqLiteDatabase.close();
        Log.e("Data Deleted","Success");

    }

    public void updateChecked(Questions_Local questions_local){
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_ID, questions_local.getCategories_id());
        values.put(COLUMN_ID_SURVEY, questions_local.getId());
        values.put(COLUMN_IS_OPTIONS, questions_local.getIs_option());
        values.put(COLUMN_QUESTION_NO, questions_local.getQuestion_no());
        values.put(COLUMN_QUESTION_NAME, questions_local.getName());
        values.put(COLUMN_OPTION_A, questions_local.getOption_a());
        values.put(COLUMN_OPTION_B, questions_local.getOption_b());
        values.put(COLUMN_OPTION_C, questions_local.getOption_c());
        values.put(COLUMN_OPTION_D, questions_local.getOption_d());
        values.put(COLUMN_OPTION_E, questions_local.getOption_e());
        values.put(COLUMN_OPTION_F, questions_local.getOption_f());
        values.put(COLUMN_OPTION_G, questions_local.getOption_g());
        values.put(COLUMN_OPTION_H, questions_local.getOption_h());
        values.put(COLUMN_TRUE_ANS, questions_local.getTrue_ans());
        values.put(COLUMN_TRUE_QUESTION_NO, questions_local.getTrue_question_no());
        values.put(COLUMN_FALSE_ANS, questions_local.getFalse_ans());
        values.put(COLUMN_FALSE_QUESTION_NO, questions_local.getFalse_question_no());
        values.put(COLUMN_IS_ACTIVE, questions_local.getIs_active());
        values.put(COLUMN_IS_DELETED, questions_local.getIs_deleted());
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_QUESTION, values, COLUMN_IS_DELETED	+ "	= ?", new String[] { "1"});
        Log.e("Data update","Success");
    }


    public void getSurveyUpdate(SurveySubmit surveySubmit){
        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION_ID, surveySubmit.getId());
        values.put(KEY_CATEGORY_ID, surveySubmit.getCategories_id());
        values.put(KEY_CITIZEN_ID, surveySubmit.getCitizen_id());
        values.put(KEY_ZONE_ID, surveySubmit.getZone_id());
        values.put(KEY_WARD_ID, surveySubmit.getWard_id());
        values.put(KEY_SELECTED_OPTIONS, surveySubmit.getSelected_option());
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_SURVEY_SUBMISSION, values, KEY_QUESTION_ID	+ "	= ?", new String[]  { String.valueOf(surveySubmit.getId())});
        Log.e("Data update","Success");
    }

    public ArrayList<Questions_Local> question_List(){
        String sql = "select * from " + TABLE_QUESTION;
        android.database.sqlite.SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Questions_Local> getQuestion_arraylist = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                String categories_id = (cursor.getString(0));
                String survey_id = cursor.getString(1);
                String  is_option = cursor.getString(2);
                String  question_no = cursor.getString(3);
                int  id =Integer.parseInt(cursor.getString(4));
                String  question_name = cursor.getString(5);
                String option_a = cursor.getString(6);
                String option_b = cursor.getString(7);
                String option_c = cursor.getString(8);
                String option_d = cursor.getString(9);
                String option_e = cursor.getString(10);
                String option_f = cursor.getString(11);
                String option_g = cursor.getString(12);
                String option_h = cursor.getString(13);
                String true_ans = cursor.getString(14);
                String true_question_no = cursor.getString(15);
                String false_ans = cursor.getString(16);
                String false_question_no = cursor.getString(17);
                String is_active = cursor.getString(18);
                String is_delete = cursor.getString(19);

                getQuestion_arraylist.add(new Questions_Local(categories_id,survey_id,is_option,question_no,question_name,option_a,option_b,option_c,option_d,option_e,option_f,option_g,option_h,true_ans,true_question_no,false_ans,false_question_no,is_active,is_delete));
            }while (cursor.moveToNext());
        }


        cursor.close();
        return getQuestion_arraylist;
    }

    /*public ArrayList<SurveySubmit> surveyData(){
        String sql = "select * from " + TABLE_SURVEY_SUBMISSION;
        android.database.sqlite.SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<SurveySubmit> questionsLocalArrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{

                String  id =(cursor.getString(0));
                String  question_id = cursor.getString(1);
                String category_id = cursor.getString(2);
                String citizen_id = cursor.getString(3);
                String selected_option = cursor.getString(4);

                Log.e("id", id);
                Log.e("question_id", question_id);
                Log.e("category_id", category_id);
                Log.e("database_id", String.valueOf(id));
                Log.e("citizen_id", citizen_id);
                Log.e("selected_option", selected_option);




                questionsLocalArrayList.add(new SurveySubmit(id,question_id,category_id,citizen_id,selected_option));
            }while (cursor.moveToNext());
        }


        cursor.close();
        return questionsLocalArrayList;
    }*/

    public Questions_Local getData(String question_id){

        String sql = "select * from " + TABLE_QUESTION + " where question_no= ' " +question_id+" ' ";
        android.database.sqlite.SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Questions_Local> getQuestion_arraylist = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToNext()){

                String categories_id = (cursor.getString(0));
                String survey_id = cursor.getString(1);
                String is_option = cursor.getString(2);
                String question_no = cursor.getString(3);
                int id = Integer.parseInt(cursor.getString(4));
                String question_name = cursor.getString(5);
                String option_a = cursor.getString(6);
                String option_b = cursor.getString(7);
                String option_c = cursor.getString(8);
                String option_d = cursor.getString(9);
                String option_e = cursor.getString(10);
                String option_f = cursor.getString(11);
                String option_g = cursor.getString(12);
                String option_h = cursor.getString(13);
                String true_ans = cursor.getString(14);
                String true_question_no = cursor.getString(15);
                String false_ans = cursor.getString(16);
                String false_question_no = cursor.getString(17);
                String is_active = cursor.getString(18);
                String is_delete = cursor.getString(19);
                return new Questions_Local(categories_id,survey_id,is_option,question_no,question_name,option_a,option_b,option_c,option_d,option_e,option_f,option_g,option_h,true_ans,true_question_no,false_ans,false_question_no,is_active,is_delete);



        }
        cursor.close();
        return null;
    }


    public ArrayList<Questions_Local> getUpdatedQuestion(String sql_query){
        Log.e("true_question_nos",sql_query);
       /*  int length = true_question_nos.length();
       if(length==1){
            char first_position = true_question_nos.charAt(0);
            sql = "select * from " + TABLE_QUESTION+ " where question_no  NOT BETWEEN " +first_position+ "AND "+first_position ;

        }
        else if(length==0){
            sql = "select * from " + TABLE_QUESTION;
        }
        else{
            char first_position = true_question_nos.charAt(0);
            char last_position = true_question_nos.charAt(length-1);
            sql = "select * from " + TABLE_QUESTION+ " where question_no  NOT BETWEEN " +first_position+ " AND "+last_position ;
        }
        Log.e("length_str", String.valueOf(length));*/

        android.database.sqlite.SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Questions_Local> getQuestion_arraylist = new ArrayList<>();
        sql = "select * from " + TABLE_QUESTION + " "+sql_query;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                String categories_id = (cursor.getString(0));
                String survey_id = cursor.getString(1);
                String  is_option = cursor.getString(2);
                String  question_no = cursor.getString(3);
                int  id =Integer.parseInt(cursor.getString(4));
                String  question_name = cursor.getString(5);
                String option_a = cursor.getString(6);
                String option_b = cursor.getString(7);
                String option_c = cursor.getString(8);
                String option_d = cursor.getString(9);
                String option_e = cursor.getString(10);
                String option_f = cursor.getString(11);
                String option_g = cursor.getString(12);
                String option_h = cursor.getString(13);
                String true_ans = cursor.getString(14);
                String true_question_no = cursor.getString(15);
                String false_ans = cursor.getString(16);
                String false_question_no = cursor.getString(17);
                String is_active = cursor.getString(18);
                String is_delete = cursor.getString(19);




                getQuestion_arraylist.add(new Questions_Local(categories_id,survey_id,is_option,question_no,question_name,option_a,option_b,option_c,option_d,option_e,option_f,option_g,option_h,true_ans,true_question_no,false_ans,false_question_no,is_active,is_delete));
            }while (cursor.moveToNext());
        }


        cursor.close();
        return getQuestion_arraylist;
    }


}
