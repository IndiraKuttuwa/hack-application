package com.example.indira.hack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);
    }
    protected void onClick(View view){
        setContentView(R.layout.page2);

    }
    protected void in(View view){
        setContentView(R.layout.activity_start_up);
        Intent i=new Intent(FirstActivity.this,StartUpActivity.class);
        startActivity(i);
    }

}
