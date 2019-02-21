package com.example.praneethsai.sqlitecardview;

/**
 * Created by praneethsai on 05-07-2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PRABHU on 11/12/2015.
 */
public class DatabaseHelpher extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="student";
    private static final int DATABASE_VERSION = 1;
    private static final String STUDENT_TABLE = "stureg";
    private static final String STU_TABLE = "create table "+STUDENT_TABLE +"(name TEXT  ,dob TEXT primary key,age TEXT, mob TEXT ,adress TEXT,lmp TEXT,edd TEXT, twins TEXT,pph TEXT,ppw TEXT)";

    Context context;

    public DatabaseHelpher(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(STU_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);

        // Create tables again
        onCreate(db);
    }
    /* Insert into database*/
    public void insertIntoDB(String name,String dob,String age,String mob,String adress,String lmp, String edd, String twins, String pph, String ppw){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("dob", dob);
        values.put("age", age);
        values.put("mob", mob);
        values.put("adress",adress);
        values.put("lmp",lmp);
        values.put("edd",edd);
        values.put("twins",twins);
        values.put("pph",pph);
        values.put("ppw",ppw);


        // 3. insert
        db.insert(STUDENT_TABLE, null, values);
        // 4. close
        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }
    /* Retrive  data from database */
    public List<DatabaseModel> getDataFromDB(){
        List<DatabaseModel> modelList = new ArrayList<DatabaseModel>();
        String query = "select * from "+STUDENT_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                DatabaseModel model = new DatabaseModel();
                model.setname(cursor.getString(0));
                model.setdob(cursor.getString(1));
                model.setage(cursor.getString(2));
                model.setmob(cursor.getString(3));
                model.setadress(cursor.getString(4));
                model.setlmp(cursor.getString(5));
                model.setedd(cursor.getString(6));
                model.settwins(cursor.getString(7));
                model.setpph(cursor.getString(8));
                model.setppw(cursor.getString(9));

                modelList.add(model);
            }while (cursor.moveToNext());
        }


        Log.d("student data", modelList.toString());


        return modelList;
    }


    /*delete a row from database*/

    public void deleteARow(String email){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(STUDENT_TABLE, "email" + " = ?", new String[] { email });
        db.close();
    }

    public List<DatabaseModel> search(String keyword) {
        List<DatabaseModel> contacts = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + STUDENT_TABLE + " where " + "name" + " like ?", new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()) {
                contacts = new ArrayList<DatabaseModel>();
                do {
                    DatabaseModel contact = new DatabaseModel();
                    contact.setname(cursor.getString(0));
                    contact.setdob(cursor.getString(1));
                    contact.setage(cursor.getString(2));
                    contact.setmob(cursor.getString(3));
                    contact.setadress(cursor.getString(4));
                    contact.setlmp(cursor.getString(5));
                    contacts.add(contact);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            contacts = null;
        }
        return contacts;
    }

}
