package com.example.indira.hack;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CompareActivity extends AppCompatActivity {
    Button compare;
    Query query;
    List<String> patwariDetails;

    private DatabaseReference mDatabase;
    TextView area;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    //String Patta

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        area = (TextView) findViewById(R.id.diffinarea);
        compare = (Button) findViewById(R.id.compare);
        patwariDetails = new ArrayList<String>();
      /*  compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mDatabase = new database.getReference("https://my.gpsbasedlandsurvey.firebaseio.com/").limitToLast(10);
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            PatwariDetails patwariDetails = messageSnapshot.getValue(PatwariDetails.class);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });*/
        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("value").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        area.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });
    }
}