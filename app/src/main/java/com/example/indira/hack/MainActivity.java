package com.example.indira.hack;

/**
 * Created by Indira on 3/21/2018.
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import  android.app.ProgressDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    EditText name,email,password;
    Button Submit,login;
    ProgressDialog progressDialog;
    ConnectionClass connectionClass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
   getSupportActionBar().hide();
    name = (EditText) findViewById(R.id.name);
    email = (EditText) findViewById(R.id.email);
    password = (EditText) findViewById(R.id.password);
    Submit = (Button) findViewById(R.id.Submit);
    progressDialog = new ProgressDialog(this);
    connectionClass = new ConnectionClass();

    Submit.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            //DoSubmit doSubmit = new DoSubmit();
            //doSubmit.execute("");
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Account Created Successfully", Toast.LENGTH_LONG);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Account Creation Failure", Toast.LENGTH_LONG);
                            }
                        }
                    });
        }
    });
    }
   public class DoSubmit extends AsyncTask<String,String,String>
   {
       String namestr = name.getText().toString();
       String emailstr = email.getText().toString();
       String passwordstr = password.getText().toString();
       String z="";
       boolean isSuccess=false;

       @Override
    protected void onPreExecute(){
   progressDialog.setMessage("Loading...");
   progressDialog.show();
   }
   protected String doInBackground(String... params) {
       if (namestr.trim().equals("") || emailstr.trim().equals("") || passwordstr.trim().equals(""))
           z = "please enter all the fields...";
       else try {
           Class.forName("classs");
           Connection con = DriverManager.getConnection("url", "un", "password");
          // conn = DriverManager.getConnection(ConnURL);

           if (con == null) {
               z = "check your internet connection";
           } else {
               String query = "insert into user_details values('" + namestr + "','" + emailstr + "','" + passwordstr + "')";

               Statement stmt = con.createStatement();
               stmt.executeUpdate(query);

               z = "Register Successful";
               isSuccess = true;
           }
       } catch (Exception ex) {
           isSuccess = false;
           z = "Exceptions" + ex;
       }
       return z;
   }
@Override
       protected void onPostExecute(String s)
{
    if(isSuccess){
        Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();
    }
    progressDialog.hide();
}
    }
   }



