package com.example.indira.hack;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Indira on 3/21/2018.
 */

public class MainActivity1 extends AppCompatActivity {
    EditText name, password;
    Button login;
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().hide();
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);
        connectionClass = new ConnectionClass();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dologin dologin = new Dologin();
                dologin.execute();
            }
        });
    }

    public class Dologin extends AsyncTask<String,String,String> {
        String namestr = name.getText().toString();
        String passwordstr = password.getText().toString();
        String z = "";
        boolean isSuccess = false;
        String nm, pwd;


        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            if (namestr.trim().equals("") || passwordstr.trim().equals(""))
                z = "please enter all the fields...";
            else
                {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "check your internet connection";
                    } else {
                        String query = "select * from user_details where name='" + namestr + "' and password='" + passwordstr + "'";

                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            nm = rs.getString(1);
                            pwd = rs.getString(2);
                            if (nm.equals(namestr) && pwd.equals(passwordstr)) {
                                setContentView(R.layout.activity_maps);
                                isSuccess=true;
                            }
                            else
                                isSuccess=false;
                        }

                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    z = "Exception" + ex;
                }
            }
            return z;
        }
    }
}