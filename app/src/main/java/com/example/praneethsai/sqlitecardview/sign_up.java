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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class sign_up extends AppCompatActivity {
    TextView tv;

    DatabaseHelper2 helper = new DatabaseHelper2(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tv= (TextView) findViewById(R.id.textview);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sign_up.this,login.class);
                startActivity(intent);
            }
        });


    }
    public void onSignUpClick(View view)
    {
        if (view.getId() == R.id.appCompatButtonRegister)
        {
            EditText name = (EditText) findViewById(R.id.TFName);
            EditText email = (EditText) findViewById(R.id.TFemail);
            EditText uname = (EditText) findViewById(R.id.TFuname);
            EditText pass1 = (EditText) findViewById(R.id.TFpass1);
            EditText pass2 = (EditText) findViewById(R.id.TFpass2);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if (!pass1str.equals(pass2str))
            {
                //Popup message if passwords don't match
                Toast pass = Toast.makeText(sign_up.this, "Hmmm...Passwords didn't match!", Toast.LENGTH_SHORT);
                pass.show();
            }

            else
            {
                //Insert details in the database
                Contact contact = new Contact();
                contact.setName(namestr);
                contact.setEmail(emailstr);
                contact.setUname(unamestr);
                contact.setPass(pass1str);

                helper.insertContact(contact);
                Intent intent = new Intent(sign_up.this,login.class);
                Toast pass = Toast.makeText(sign_up.this, "Registration successfull", Toast.LENGTH_SHORT);
                pass.show();
                startActivity(intent);
            }
        }
    }
}
