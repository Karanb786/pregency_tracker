package com.example.praneethsai.sqlitecardview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class login extends AppCompatActivity {
    DatabaseHelper2 helper = new DatabaseHelper2(this);
    EditText username , passs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.TFusername);
        passs = (EditText) findViewById(R.id.TFPassword);

    }

    public void onButtonClick(View view)
    {
        if (view.getId() == R.id.appCompatButtonLogin)
        {
            EditText a = (EditText) findViewById(R.id.TFusername);
            String string = a.getText().toString();

            EditText b = (EditText) findViewById(R.id.TFPassword);
            String pass = b.getText().toString();

            String password = helper.searchPass(string);
            verifyFromSQLite();

            if (pass.equals(password))
            {
               /* Intent intent = new Intent(login.this, SecondActivity.class);
                intent.putExtra("Username", string);
                startActivity(intent);*/
            }

            else
            {

            }


        }
        if (view.getId() == R.id.textViewLinkRegister)
        {
            Intent intent = new Intent(login.this, sign_up.class);
            startActivity(intent);
        }
    }
    private void verifyFromSQLite() {
        String email = username.getText().toString().trim();
        String pass = passs.getText().toString().trim();
        if(helper.checkUser(email,pass)){
            Intent accountsIntent = new Intent(login.this, MainActivity.class);
            emptyInputEditText();
            startActivity(accountsIntent);

        }
    }

    private void emptyInputEditText() {
        username.setText(null);
        passs.setText(null);
    }



}
