package com.nutout.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.nutout.login.helper.InputValidation;
import com.nutout.login.modal.User;
import com.nutout.login.modal.addwomenclass;
import com.nutout.login.sql.DatabaseHelper;

public class addwomen extends AppCompatActivity {

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private addwomenclass women;
    private final AppCompatActivity activity = addwomen.this;
    EditText fname,mname,lname,dob,age,address;
    Spinner sp1,sp2,sp3;
    ArrayAdapter<CharSequence> adapter1,adapter2,adapter3;
    Button registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwomen);


        initViews();
        initObjects();


    }

    private void initViews() {
        fname= findViewById(R.id.firstname);
        mname=findViewById(R.id.middlename);
        lname = findViewById(R.id.lastname);
        dob= findViewById(R.id.dob);
        age=findViewById(R.id.age);
        address=findViewById(R.id.address);
        registerbtn = findViewById(R.id.reg);

        sp1 = (Spinner) findViewById(R.id.maritalspinner);
        adapter1 = ArrayAdapter.createFromResource(this, R.array.maritalstatus_grp, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter1);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp2 = (Spinner) findViewById(R.id.bloodspinner);
        adapter2 = ArrayAdapter.createFromResource(this, R.array.blood_grp, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter2);

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp3 = (Spinner) findViewById(R.id.statespinner);
        adapter3 = ArrayAdapter.createFromResource(this, R.array.state_names, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter3);

        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataToSQLite();
            }
        });

    }

    private void postDataToSQLite() {
        if(!databaseHelper.checkUser(fname.getText().toString().trim())){
            women.setFname(fname.getText().toString().trim());
            women.setMname(mname.getText().toString().trim());
            women.setLname(lname.getText().toString().trim());
            women.setDob(dob.getText().toString().trim());
            women.setAge(age.getText().toString().trim());
            women.setMstatus(sp1.getSelectedItem().toString().trim());
            women.setBloodgrp(sp2.getSelectedItem().toString().trim());
            women.setAddress(address.getText().toString().trim());
            women.setState(sp3.getSelectedItem().toString().trim());
            databaseHelper.addWomen(women);
           Intent in = new Intent(addwomen.this,dashboard.class);
           startActivity(in);



        }
    }


    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        women = new addwomenclass();

    }
}
