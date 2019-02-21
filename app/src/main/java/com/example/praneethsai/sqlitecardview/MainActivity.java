package com.example.praneethsai.sqlitecardview;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.method.DateTimeKeyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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
    Calendar calendar =Calendar.getInstance();
    DatePicker picker;
    private DatePickerDialog mDatePickerDialog,mDatePickerlmp,mDatePickerEdd;
    private EditText edDate;


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
        int pph =Integer.parseInt(wpph.getText().toString());
        int ppw  = Integer.parseInt(wppw.getText().toString());

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

        setDateTimeField();
        radioactivity();
        getlmp();
        getEdd();

        int bmi = ppw /(pph*pph);
        wbmi.setText(bmi);
        int bmicat;
        if(bmi<18.5){
            wbmicat.setText("Underweight, Recommended Weight Gain :13-18 kg");
        }else if(bmi>18.5 && bmi<24.9){
            wbmicat.setText("Normal, Recommended Weight Gain :11-16 kg");
        }else if(bmi>25 && bmi<29.9){
            wbmicat.setText("Overweight, Recommended Weight Gain :7-11 kg");
        }else if(bmi>30){
            wbmicat.setText("Obese, Recommended Weight Gain :5-6 kg");
        }

            etRoll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDatePickerDialog.show();
                }
            });
            wlmp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        mDatePickerlmp.show();
                }
            });

            wedd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDatePickerEdd.show();

                }
            });








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



    private void getlmp() {
        mDatePickerlmp = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            newDate.add(Calendar.DAY_OF_MONTH,-280);
            SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
            final Date startDate = newDate.getTime();
            String fdate = sd.format(startDate);
            wlmp.setText(fdate);

        }
    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerlmp.getDatePicker();

    }
   private void getEdd(){
       mDatePickerEdd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
           public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
               Calendar newDate = Calendar.getInstance();
               newDate.set(year, monthOfYear, dayOfMonth);
               newDate.add(Calendar.DAY_OF_MONTH,280);
               SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
               final Date startDate = newDate.getTime();
               String fdate = sd.format(startDate);
               wedd.setText(fdate);

           }
       }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
       mDatePickerEdd.getDatePicker().setMaxDate(System.currentTimeMillis());


   }


    private void setDateTimeField() {

        Calendar calendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                Calendar yeardate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                final Date startDate = newDate.getTime();
                String fdate = sd.format(startDate);
                etRoll.setText(fdate);



                Calendar localdate = Calendar.getInstance(TimeZone.getDefault());

                int cyear = localdate.get(Calendar.YEAR);
                int age = (int) (cyear- year);
                etAddress.setText(String.valueOf(age));

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }


    private void radioactivity() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = (RadioButton) radioGroup.findViewById(i);

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



