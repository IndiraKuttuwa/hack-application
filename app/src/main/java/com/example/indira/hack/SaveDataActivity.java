package com.example.indira.hack;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Polygon;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SaveDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatabaseReference mDataBase;
    Spinner spinner2;
    Spinner districtspinner;
    Spinner landspinner;
    TextView crop;
    TextView yield1;
    Button savedatatodb;
    List<String> landTypes;
    List<String> states;
    List<String> districts;
    EditText cropname;
    EditText PattaNumber;
    EditText yield;
    FirebaseAuth mAuth;
    Button back;
    Button logout;
    Button proceed;
    String[] lands={" ","agriculture", "Commercial", "Habitual", "Non-habitual", "Forest","Barren"};

    PolygonPoints polygonInfoReceived;
     ImageButton btn;
    static  final int CAM_Request=1;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);


        mAuth = FirebaseAuth.getInstance();
        polygonInfoReceived = (PolygonPoints) getIntent().getSerializableExtra("coordinates");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

       /* FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("YourApplicationId")
                .setApiKey("yourApiKey")
                .setDatabaseUrl("https://gpsbasedlandsurvey.firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(this.getContext(), options, "Patwaricropdetails");
        FirebaseApp secondApp = FirebaseApp.getInstance("Patwaricropdetails");

        FirebaseDatabase firstDatabase = FirebaseDatabase.getInstance();  //default db from JSON
        FirebaseDatabase secondDatabase = FirebaseDatabase.getInstance(secondApp);*/

        spinner2 = (Spinner) findViewById(R.id.spinner);
        districtspinner = (Spinner) findViewById(R.id.districtspinner);
        landspinner=(Spinner)findViewById(R.id.landspinner);
        landspinner.setOnItemSelectedListener(this);
        ArrayAdapter aa=new ArrayAdapter(this,android.R.layout.simple_spinner_item,lands);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landspinner.setAdapter(aa);

        states = new ArrayList<String>();
        districts = new ArrayList<String>();
        savedatatodb = (Button) findViewById(R.id.savedatatodb);
        back = (Button) findViewById(R.id.back);
        logout = (Button) findViewById(R.id.logout);
        cropname = (EditText) findViewById(R.id.cropname);
        yield = (EditText) findViewById(R.id.yielddata);
         crop = (TextView) findViewById(R.id.crop);
         yield1 = (TextView) findViewById(R.id.yield);
         PattaNumber = (EditText) findViewById(R.id.PattaNumber);
         proceed = (Button) findViewById(R.id.proceed);
        savedatatodb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PolygonInfo newdata = new PolygonInfo(polygonInfoReceived,Integer.parseInt(String.valueOf(PattaNumber.getText())), landspinner.getSelectedItem().toString(), cropname.getText().toString(),yield.getText().toString(), states.get(spinner2.getSelectedItemPosition()), districts.get(districtspinner.getSelectedItemPosition()), mAuth.getCurrentUser().getEmail(),(new Date()).toString());
                database.getReference("DataCollected").push().setValue(newdata);

                Toast.makeText(getApplicationContext(),"Data saved successfully", Toast.LENGTH_LONG).show();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SaveDataActivity.this,MapsActivity.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SaveDataActivity.this,StartUpActivity.class);
                startActivity(i);
            }
        });
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SaveDataActivity.this,CompareActivity.class);
                startActivity(i);
            }
        });


        //String text = landspinner.getSelectedItem().toString();
        /*public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {

        }*/
        /*if(text=="agriculture")
        {
            crop.setVisibility(View.VISIBLE);
            cropname.setVisibility(View.VISIBLE);
            yield.setVisibility(View.VISIBLE);
            yield1.setVisibility(View.VISIBLE);

        }
        else
        {
            crop.setVisibility(View.INVISIBLE);
            cropname.setVisibility(View.INVISIBLE);
            yield.setVisibility(View.INVISIBLE);
            yield1.setVisibility(View.INVISIBLE);

        }*/

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),"Item " + states.get(position).toString() + " clicked", Toast.LENGTH_LONG).show();
                //If states node is used, use database.getRefernce("States/" + states.get(position.tost
                database.getReference("States/" + states.get(position).toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        districts.clear();
                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                            districts.add(childDataSnapshot.getValue().toString());
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                                R.layout.spinner_item_layout, districts);
                        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                        districtspinner.setAdapter(dataAdapter);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //If states node is created use database.getReference("States")
        database.getReference("States").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("SaveDataTesting",""+ "Entered the onDataChange");
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    states.add(childDataSnapshot.getKey());
                    Log.v("SaveDataActivity",""+ childDataSnapshot.getKey()); //displays the key for the node
                    //Log.v(TAG,""+ childDataSnapshot.child(--ENTER THE KEY NAME eg. firstname or email etc.--).getValue());   //gives the value for given keyname
                }


                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.spinner_item_layout, states);
                dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                spinner2.setAdapter(dataAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*database.getReference("LandType").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("SaveDataTesting",""+ "Entered the onDataChange");
                for(DataSnapshot childDataSnapshot : dataSnapshot.getChildren()){
                    landTypes.add(childDataSnapshot.getKey());
                    Log.v("SaveDataActivity",""+ childDataSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    /*    btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file=getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent,CAM_Request);

            }
            });*/
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(),lands[position],Toast.LENGTH_LONG).show();
        String item=landspinner.getItemAtPosition(position).toString();
        if(item.equals("agriculture"))
        {
            crop.setVisibility(View.VISIBLE);
            cropname.setVisibility(View.VISIBLE);
            yield.setVisibility(View.VISIBLE);
            yield1.setVisibility(View.VISIBLE);
        }
        else {
            crop.setVisibility(View.INVISIBLE);
            cropname.setVisibility(View.INVISIBLE);
            yield.setVisibility(View.INVISIBLE);
            yield1.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    /*private File getFile(){
        File folder=new File("sdcard/camera_app");
        if(!folder.exists())
        {
            folder.mkdir();
        }
        File image_file=new File(folder,"cam_image.jpg");
        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path="sdcard/camera_app/cam_image.jpg";
        imageView.setImageDrawable(Drawable.createFromPath(path));
    }*/

}
