package com.example.praneethsai.sqlitecardview;

/**
 * Created by praneethsai on 05-07-2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    DatabaseHelpher helpher;
    List<DatabaseModel> dbList;
    int position;
    TextView tvname,tvemail,tvroll,tvaddress,tvbranch,tvmstatus,tvbgrp,tvlmp,tvedd,tvtwins,tvpph,tvppw,tvbmi,tvbmicat;
    ImageView menubtn;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        menubtn = (ImageView) findViewById(R.id.menubtnclick);
        dl = (DrawerLayout) findViewById(R.id.DrawerLayout);
        nv = (NavigationView) findViewById(R.id.nv);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // 5. get status value from bundle
        position = bundle.getInt("position");

        tvname =(TextView)findViewById(R.id.name);
        tvemail =(TextView)findViewById(R.id.email);
        tvroll =(TextView)findViewById(R.id.roll);
        tvaddress =(TextView)findViewById(R.id.address);
        tvbranch =(TextView)findViewById(R.id.branch);
        tvmstatus = (TextView) findViewById(R.id.mstatus);
        tvbgrp = (TextView) findViewById(R.id.bgrp);
        tvlmp = (TextView) findViewById(R.id.lmp);
        tvedd= (TextView) findViewById(R.id.edd);
        tvtwins = (TextView) findViewById(R.id.twins);
        tvpph = (TextView) findViewById(R.id.pph);
        tvppw = (TextView) findViewById(R.id.ppw);
        tvbmi = (TextView) findViewById(R.id.bmi);
        tvbmicat = (TextView) findViewById(R.id.bmicat);
        helpher = new DatabaseHelpher(this);
        dbList= new ArrayList<DatabaseModel>();
        dbList = helpher.getDataFromDB();

        if(dbList.size()>0){
            String name= dbList.get(position).getname();
            String email=dbList.get(position).getdob();
            String roll=dbList.get(position).getage();
            String address=dbList.get(position).getmob();
            String branch=dbList.get(position).getadress();
            String lmp = dbList.get(position).getlmp();
            String edd = dbList.get(position).getedd();
            String twins = dbList.get(position).gettwins();
            String pph = dbList.get(position).getpph();
            String ppw = dbList.get(position).getppw();


            tvname.setText(name);
            tvemail.setText(email);
            tvroll.setText(roll);
            tvaddress.setText(address);
            tvbranch.setText(branch);
            tvlmp.setText(lmp);
            tvedd.setText(edd);
            tvtwins.setText(twins);
            tvpph.setText(pph);
            tvppw.setText(ppw);
        }

        Toast.makeText(DetailsActivity.this, dbList.toString(), Toast.LENGTH_LONG);

        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dl.openDrawer(Gravity.LEFT);
            }
        });
    }


   /* public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
