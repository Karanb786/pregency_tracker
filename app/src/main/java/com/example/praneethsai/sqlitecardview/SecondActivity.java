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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelpher helpher;
    List<DatabaseModel> dbList;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ImageView menubtn;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        menubtn = (ImageView) findViewById(R.id.menubtnclickhome);
        dl = (DrawerLayout) findViewById(R.id.drawerhome);
        nv = (NavigationView) findViewById(R.id.nvhome);
        textViewName= (TextView) findViewById(R.id.textviewnam);



        helpher = new DatabaseHelpher(this);
        dbList= new ArrayList<DatabaseModel>();
        dbList = helpher.getDataFromDB();

        initViews();

        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapter(this,dbList);
        mRecyclerView.setAdapter(mAdapter);

        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dl.openDrawer(Gravity.LEFT);
            }
        });
        nv.setNavigationItemSelectedListener(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }*/


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerhome);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void initViews() {
        View view = nv.getHeaderView(0);
        textViewName = (TextView) view.findViewById(R.id.textviewnam);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Setting) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.home) {
            startActivity(new Intent(this, SecondActivity.class));
        } else if (id == R.id.addwomen) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.checkshedule) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.Clinical_Examination) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.Setting) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.help) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.logout) {
            startActivity(new Intent(this, MainActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerhome);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}


