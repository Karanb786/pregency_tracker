package com.example.praneethsai.sqlitecardview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etName,etRoll,etAddress,etBranch,etEmail,wlmp,wedd,wpph,wppw;
    TextView wbmi,wbmicat;
    Button btnSubmit,btngetdata;
    DatabaseHelpher helpher;
    List<DatabaseModel> dbList;
    String selected, spinner_item;
    Spinner sp1,sp2;
    ArrayAdapter<CharSequence> adapter1,adapter2;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbList= new ArrayList<DatabaseModel>();
        etName = (EditText)findViewById(R.id.etName);
        etRoll = (EditText)findViewById(R.id.etRoll);
        etAddress =(EditText)findViewById(R.id.etAddress);
        etBranch = (EditText)findViewById(R.id.etBranch);
        etEmail = (EditText)findViewById(R.id.etEmail);
        wlmp = (EditText) findViewById(R.id.lmp);
        wedd= (EditText) findViewById(R.id.edd);
        wpph= (EditText) findViewById(R.id.pph);
        wppw= (EditText) findViewById(R.id.ppw);

        wbmi= (TextView) findViewById(R.id.wbmi);
        wbmicat= (TextView) findViewById(R.id.wbmicat);

        radioGroup= (RadioGroup) findViewById(R.id.radio_twins);
        radioGroup.clearCheck();




        btnSubmit  =(Button)findViewById(R.id.btnSubmit);
        btngetdata =(Button)findViewById(R.id.btngetdata);
        btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));

                // startActivity(new Intent(MainActivity.this, DetailsActivity.class));

            }
        });

        radioactivity();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=etName.getText().toString();
                String dob=etEmail.getText().toString();
                String age=etRoll.getText().toString();
                String mob=etAddress.getText().toString();
                String adress=etBranch.getText().toString();
                String lmp=wlmp.getText().toString();
                String edd=wedd.getText().toString();
                String twins =radioButton.getText().toString();
                String pph = wpph.getText().toString();
                String ppw = wppw.getText().toString();





                if(name.equals("") || dob.equals("") || age.equals("") ||mob.equals("")||adress.equals("")||lmp.equals("")||edd.equals("")||twins.equals("")||pph.equals("")||ppw.equals("")){
                    Toast.makeText(MainActivity.this,"Please fill all the fields",Toast.LENGTH_LONG).show();
                }else {
                    helpher = new DatabaseHelpher(MainActivity.this);
                    helpher.insertIntoDB(name, dob, age, mob, adress,lmp,edd,twins,pph,ppw);
                }
                etName.setText("");
                etRoll.setText("");
                etAddress.setText("");
                etBranch.setText("");
                etEmail.setText("");
                wlmp.setText("");
                wedd.setText("");
                radioButton.setText("");
                wpph.setText("");
                wppw.setText("");







                Toast.makeText(MainActivity.this, "insert value", Toast.LENGTH_LONG);

            }
        });

    }

    private void radioactivity() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton= (RadioButton) radioGroup.findViewById(i);

            }
        });
    }


  /*  private void spinneractivity() {
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

        sp2 = (Spinner) findViewById(R.id.bloodgrp);
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



    }*/


}

