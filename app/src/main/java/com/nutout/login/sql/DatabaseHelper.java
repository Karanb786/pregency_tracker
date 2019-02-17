package com.nutout.login.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nutout.login.modal.User;
import com.nutout.login.modal.addwomenclass;

import java.util.ArrayList;
import java.util.List;


    public class DatabaseHelper extends SQLiteOpenHelper {

        // Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        private static final String DATABASE_NAME = "UserManager.db";

        // User table name
        private static final String TABLE_USER = "user";
        public static final String TABLE_WOMEN = "women";

        // User Table Columns names
        private static final String COLUMN_USER_ID = "user_id";
        private static final String COLUMN_USER_NAME = "user_name";
        private static final String COLUMN_USER_EMAIL = "user_email";
        private static final String COLUMN_USER_PASSWORD = "user_password";

        private static final String ID = "women_id";
        private static final String firstname = "fname";
        private static final String middlename = "mname";
       private static  final String lastname = "lname";
        private static final String dob = "dob";
        private static final String age = "age";
        private static final String mstatus = "women_mstatus";
        private static final String bloodgrp = "women_bgrp";
        private static final String address= "women_address";
        private static  final String state="women_state";
        private static final String lmp = "women_lmp";
        private static  final String edd = "women_edd";
        private static  final String twin = "women_twin";
        private  static  final String pph = "women_pph";
        private static  final String ppw = "women_ppw";
        private static final String pbmi = "women_pbmi";
        private static final String bmicat="women_bmicat";

        // create table sql query
        private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

        private String CREATE_WOMEN_TABLE = "CREATE TABLE "+TABLE_WOMEN +"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT ," +firstname+" TEXT," +middlename+" TEXT,"
               +dob+"TEXT,"+age+"TEXT,"+mstatus+"TEXT,"+bloodgrp+"TEXT,"+address+"TEXT,"+state+"TEXT,"+lmp+"TEXT,"
                +edd+"TEXT,"+twin+"TEXT,"+pph+"TEXT, "+ppw+"TEXT,"+pbmi+"TEXT,"+bmicat+"TEXT"+")";


        // drop table sql query
        private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
        private String DROP_WOMEN_TABLE = "DROP TABLE IF EXISTS " +TABLE_WOMEN;

        /**
         * Constructor
         *
         * @param context
         */
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(CREATE_USER_TABLE);
            db.execSQL(CREATE_WOMEN_TABLE);

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            //Drop User Table if exist
            db.execSQL(DROP_USER_TABLE);
            db.execSQL(DROP_WOMEN_TABLE);

            // Create tables again
            onCreate(db);

        }

        /**
         * This method is to create user record
         *
         * @param user
         *
         *
         * yha se
         */
        public void addUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, user.getName());
            values.put(COLUMN_USER_EMAIL, user.getEmail());
            values.put(COLUMN_USER_PASSWORD, user.getPassword());

            // Inserting Row
            db.insert(TABLE_USER, null, values);
            db.close();
        }

        /**
         * This method is to fetch all user and return the list of user records
         *
         * @return list
         */
        public List<User> getAllUser() {
            // array of columns to fetch
            String[] columns = {
                    COLUMN_USER_ID,
                    COLUMN_USER_EMAIL,
                    COLUMN_USER_NAME,
                    COLUMN_USER_PASSWORD
            };
            // sorting orders
            String sortOrder =
                    COLUMN_USER_NAME + " ASC";
            List<User> userList = new ArrayList<User>();

            SQLiteDatabase db = this.getReadableDatabase();

            // query the user table
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
             */
            Cursor cursor = db.query(TABLE_USER, //Table to query
                    columns,    //columns to return
                    null,        //columns for the WHERE clause
                    null,        //The values for the WHERE clause
                    null,       //group the rows
                    null,       //filter by row groups
                    sortOrder); //The sort order


            // Traversing through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                    user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                    user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                    user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                    // Adding user record to list
                    userList.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

            // return user list
            return userList;
        }

        /**
         * This method to update user record
         *
         * @param user
         */
        public void updateUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, user.getName());
            values.put(COLUMN_USER_EMAIL, user.getEmail());
            values.put(COLUMN_USER_PASSWORD, user.getPassword());

            // updating row
            db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                    new String[]{String.valueOf(user.getId())});
            db.close();
        }

        /**
         * This method is to delete user record
         *
         * @param user
         */
        public void deleteUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();
            // delete user record by id
            db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                    new String[]{String.valueOf(user.getId())});
            db.close();
        }

        /**
         * This method to check user exist or not
         *
         * @param email
         * @return true/false
         */
        public boolean checkUser(String email) {

            // array of columns to fetch
            String[] columns = {
                    COLUMN_USER_ID
            };
            SQLiteDatabase db = this.getReadableDatabase();

            // selection criteria
            String selection = COLUMN_USER_EMAIL + " = ?";

            // selection argument
            String[] selectionArgs = {email};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor cursor = db.query(TABLE_USER, //Table to query
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
                    COLUMN_USER_ID
            };
            SQLiteDatabase db = this.getReadableDatabase();
            // selection criteria
            String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

            // selection arguments
            String[] selectionArgs = {email, password};

            // query user table with conditions
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
             */
            Cursor cursor = db.query(TABLE_USER, //Table to query
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


        public void addWomen(addwomenclass women){
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(firstname,women.getFname());
            values.put(middlename,women.getMname());
         /*   values.put(dob,women.getDob());
            values.put(age,women.getAge());
            values.put(mstatus,women.getMstatus());
            values.put(bloodgrp,women.getBloodgrp());
            values.put(address,women.getAddress());
            values.put(state,women.getState());
            values.put(lmp,women.getLmp());
            values.put(edd,women.getEdd());
            values.put(twin,women.getTwins());
            values.put(pph,women.getPph());
            values.put(ppw,women.getPpw());
            values.put(pbmi,women.getPpBMI());
            values.put(bmicat,women.getPpBMICatogey());*/



            db.insert(TABLE_WOMEN, null, values);
            db.close();
        }
        public List<addwomenclass> getAllWomen() {
            // array of columns to fetch
            String[] columns = {
                    ID,
                    firstname,
                    middlename,
               /*     dob,
                    age,
                    mstatus,
                    bloodgrp,
                    address,
                    state,
                    lmp,
                    edd,
                    twin,
                    pph,
                    ppw,
                    pbmi,
                    bmicat*/
            };
            // sorting orders
            String sortOrder =
                    firstname + " ASC";
            List<addwomenclass> womenList = new ArrayList<addwomenclass>();

            SQLiteDatabase db = this.getReadableDatabase();

            // query the user table
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
             */
            Cursor cursor = db.query(TABLE_WOMEN, //Table to query
                    columns,    //columns to return
                    null,        //columns for the WHERE clause
                    null,        //The values for the WHERE clause
                    null,       //group the rows
                    null,

                    //filter by row groups
                    sortOrder); //The sort order


            // Traversing through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    addwomenclass women = new addwomenclass();
                    women.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID))));
                    women.setFname(cursor.getString(cursor.getColumnIndex(firstname)));
                    women.setMname(cursor.getString(cursor.getColumnIndex(middlename)));

                  women.setDob(cursor.getString(cursor.getColumnIndex(dob)));
                    women.setAge(cursor.getString(cursor.getColumnIndex(age)));
                    women.setMstatus(cursor.getString(cursor.getColumnIndex(mstatus)));
                    women.setBloodgrp(cursor.getString(cursor.getColumnIndex(bloodgrp)));
                    women.setAddress(cursor.getString(cursor.getColumnIndex(address)));
                    women.setState(cursor.getString(cursor.getColumnIndex(state)));
                    women.setLmp(cursor.getString(cursor.getColumnIndex(lmp)));
                    women.setEdd(cursor.getString(cursor.getColumnIndex(edd)));
                    women.setTwins(cursor.getString(cursor.getColumnIndex(twin)));
                    women.setPpw(cursor.getString(cursor.getColumnIndex(ppw)));
                    women.setPph(cursor.getString(cursor.getColumnIndex(pph)));
                    women.setPpBMI(cursor.getString(cursor.getColumnIndex(pbmi)));
                    women.setPpBMICatogey(cursor.getString(cursor.getColumnIndex(bmicat)));
                    // Adding user record to list
                    womenList.add(women);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

            // return user list
            return womenList;
        }
        public void updateWomen(addwomenclass addwomen) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(firstname,addwomen.getFname());
            values.put(middlename,addwomen.getMname());
         //   values.put(lastname,addwomen.getLname());
         /*   values.put(dob,addwomen.getDob());
            values.put(age,addwomen.getAge());
            values.put(mstatus,addwomen.getMstatus());
            values.put(bloodgrp,addwomen.getBloodgrp());
            values.put(address,addwomen.getAddress());
            values.put(state,addwomen.getState());
            values.put(lmp,addwomen.getLmp());
            values.put(edd,addwomen.getEdd());
            values.put(twin,addwomen.getTwins());
            values.put(pph,addwomen.getPph());
            values.put(ppw,addwomen.getPpw());
            values.put(pbmi,addwomen.getPpBMI());
            values.put(bmicat,addwomen.getPpBMICatogey());*/


            // updating row
            db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                    new String[]{String.valueOf(addwomen.getId())});
            db.close();
        }
        public void deleteWomen(addwomenclass women) {
            SQLiteDatabase db = this.getWritableDatabase();
            // delete user record by id
            db.delete(TABLE_WOMEN, ID + " = ?",
                    new String[]{String.valueOf(women.getId())});
            db.close();
        }


        public boolean checkWomen(String name) {

            // array of columns to fetch
            String[] columns = {
                    ID
            };
            SQLiteDatabase db = this.getReadableDatabase();

            // selection criteria
            String selection = firstname + " = ?";

            // selection argument
            String[] selectionArgs = {name};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor cursor = db.query(TABLE_WOMEN, //Table to query
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


    }

