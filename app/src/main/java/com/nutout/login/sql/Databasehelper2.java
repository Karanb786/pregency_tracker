package com.nutout.login.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nutout.login.modal.addwomenclass;

import java.util.ArrayList;
import java.util.List;

public class Databasehelper2 extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "women.db";
    public static final String TABLE_WOMEN = "women";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "MNAME";
    public static final String COL4 = "LNAME";
    public static final String COL5 = "DOB";
    public static final String COL6 = "AGE";
    public static final String COL7 = "MSTATUS";
    public static final String COL8 = "BLOODGRP";
    public static final String COL9 = "ADDRESS";
    public static final String COL10 = "STATE";
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_WOMEN;


    public Databasehelper2(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WOMEN_TABLE = "CREATE TABLE "+TABLE_WOMEN+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                "NAME TEXT,MNAME TEXT,LNAME TEXT,DOB TEXT,AGE TEXT,MSTATUS TEXT, BLOODGRP TEXT,ADDRESS TEXT, STATE TEXT)";
        db.execSQL(CREATE_WOMEN_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }
   // String name, String mname, String lname, String dob, String age, String mstatus, String bloodgrp, String address, String state
    public void addWomen(addwomenclass addwomenclass) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL2, addwomenclass.getFname());
        values.put(COL3,addwomenclass.getMname());
        values.put(COL4,addwomenclass.getLname());
        values.put(COL5, addwomenclass.getDob());
        values.put(COL6, addwomenclass.getAge());
        values.put(COL7, addwomenclass.getMstatus());
        values.put(COL8, addwomenclass.getBloodgrp());
        values.put(COL9, addwomenclass.getAddress());
        values.put(COL10, addwomenclass.getState());

        long result = db.insert(TABLE_WOMEN, null, values);
      /*  if (result == -1) {
            return false;
        } else {
            return true;
        }*/
    }
    public List <addwomenclass> gettallWomen(){
        String [] columns={
                COL1,
                COL2,
                COL3,
                COL4,
                COL5,
                COL6,
                COL7,
                COL8,
                COL9,
                COL10
        };

        List<addwomenclass> womenlist  = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_WOMEN
        ,columns,
                null,
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()){
            do{
                addwomenclass addwomenclass = new addwomenclass();
                addwomenclass.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL1))));
                addwomenclass.setFname(cursor.getString(cursor.getColumnIndex(COL2)));
                addwomenclass.setMname(cursor.getString(cursor.getColumnIndex(COL3)));
                addwomenclass.setLname(cursor.getString(cursor.getColumnIndex(COL4)));
                addwomenclass.setDob(cursor.getString(cursor.getColumnIndex(COL5)));
                addwomenclass.setAge(cursor.getString(cursor.getColumnIndex(COL6)));
                addwomenclass.setMstatus(cursor.getString(cursor.getColumnIndex(COL7)));
                addwomenclass.setBloodgrp(cursor.getString(cursor.getColumnIndex(COL8)));
                addwomenclass.setAddress(cursor.getString(cursor.getColumnIndex(COL9)));
                addwomenclass.setState(cursor.getString(cursor.getColumnIndex(COL10)));
                womenlist.add(addwomenclass);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return womenlist;

    }
    public Cursor showData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT *FROM "+TABLE_WOMEN,null);
        return data;

    }
}
