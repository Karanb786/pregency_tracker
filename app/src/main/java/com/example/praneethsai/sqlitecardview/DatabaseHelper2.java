package com.example.praneethsai.sqlitecardview;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kvaib on 15-08-2017.
 */

public class DatabaseHelper2 extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";

    SQLiteDatabase sqLiteDatabase;

    private static final String TABLE_CREATE = "create table contacts (id integer primary key autoincrement , " +
            "name text not null, email text not null, uname text not null, pass text not null);";

    public DatabaseHelper2 (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(TABLE_CREATE);
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void insertContact(Contact contact)
    {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_UNAME, contact.getUname());
        values.put(COLUMN_PASS, contact.getPass());

        sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

    public String searchPass(String uname)
    {
        sqLiteDatabase = this.getReadableDatabase();
        String query = "select uname, pass from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        String a, b; //a is username while b is the password
        b = "not found";

        if (cursor.moveToFirst())
        {
            do
            {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        onCreate(sqLiteDatabase);

    }

    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASS + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
