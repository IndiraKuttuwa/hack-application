package com.example.indira.hack;

import android.Manifest;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;



import java.io.IOException;
import java.text.BreakIterator;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;
    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private static final List<PatternItem> PATTERN_POLYGON_BETA =
            Arrays.asList(DOT, GAP, DASH, GAP);

    private GoogleMap mMap;
    private boolean secondButtonClicked = false;
    private boolean firstButtonClicked = false;
    private boolean thirdButtonClicked = false;
    private boolean fourthButtonClicked = false;

    Button secondButton;
    Button thirdButton;
    Button fourthButton;
    Button firstButton;
    Polygon polygon1;
    Button confirm;

    private double firstbuttonlat = 0;
    private  double firstbuttonlong = 0;
    private double secondbuttonlat = 0;
    private double secondbuttonlong = 0;
    private double thirdbuttonlat = 0;
    private double thirdbuttonlong = 0;
    private double fourthbuttonlat = 0;
    private double fourthbuttonlong = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        confirm = (Button) findViewById(R.id.confirm);
        confirm.setVisibility(View.INVISIBLE);

       firstButton = (Button) findViewById(R.id.point1);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstButtonClicked = true;
                v.setBackgroundColor(Color.BLUE);
                if(secondButtonClicked){
                    secondButtonClicked =false;
                    secondButton.setBackgroundColor(Color.CYAN);
                }

                if(thirdButtonClicked) {
                    thirdButtonClicked = false;
                    thirdButton.setBackgroundColor(Color.CYAN);
                }
                if(fourthButtonClicked) {
                    fourthButtonClicked = false;
                    fourthButton.setBackgroundColor(Color.CYAN);
                }
            }
        });

        secondButton = (Button) findViewById(R.id.point2);
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondButtonClicked = true;
                v.setBackgroundColor(Color.BLUE);
                if(firstButtonClicked){
                    firstButtonClicked =false;
                    firstButton.setBackgroundColor(Color.CYAN);
                }

                if(thirdButtonClicked) {
                    thirdButtonClicked = false;
                    thirdButton.setBackgroundColor(Color.CYAN);
                }
                if(fourthButtonClicked) {
                    fourthButtonClicked = false;
                    fourthButton.setBackgroundColor(Color.CYAN);
                }
            }
        });
        thirdButton = (Button) findViewById(R.id.point3);
        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thirdButtonClicked = true;
                v.setBackgroundColor(Color.BLUE);
                if(secondButtonClicked){
                    secondButtonClicked =false;
                    secondButton.setBackgroundColor(Color.CYAN);
                }

                if(firstButtonClicked) {
                    firstButtonClicked = false;
                    firstButton.setBackgroundColor(Color.CYAN);
                }
                if(fourthButtonClicked) {
                    fourthButtonClicked = false;
                    fourthButton.setBackgroundColor(Color.CYAN);
                }
            }
        });
        fourthButton = (Button) findViewById(R.id.point4);
        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourthButtonClicked = true;
                v.setBackgroundColor(Color.BLUE);
                if(secondButtonClicked){
                    secondButtonClicked =false;
                    secondButton.setBackgroundColor(Color.CYAN);
                }

                if(thirdButtonClicked) {
                    thirdButtonClicked = false;
                    thirdButton.setBackgroundColor(Color.CYAN);
                }
                if(firstButtonClicked) {
                    firstButtonClicked = false;
                    firstButton.setBackgroundColor(Color.CYAN);
                }
            }
        });
        final Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourthButtonClicked=false;
                firstButtonClicked =false;
                secondButtonClicked = false;
                thirdButtonClicked = false;
                firstbuttonlat=0;
                firstbuttonlong=0;
                secondbuttonlat = 0;
                secondbuttonlong=0;
                thirdbuttonlat=0;
                thirdbuttonlong=0;
                fourthbuttonlat=0;
                fourthbuttonlong=0;
                firstButton.setVisibility(View.VISIBLE);
                secondButton.setVisibility(View.VISIBLE);
                thirdButton.setVisibility(View.VISIBLE);
                fourthButton.setVisibility(View.VISIBLE);
                polygon1.remove();

                Toast.makeText(getApplicationContext(),"Cleared data",Toast.LENGTH_LONG).show();
            }
        });
        confirm=(Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),SaveDataActivity.class);
                double area;
                String result;
                Point pt1 = new Point(firstbuttonlat,firstbuttonlong);
                Point pt2 = new Point(secondbuttonlat, secondbuttonlong);
                Point pt3 = new Point(thirdbuttonlat, thirdbuttonlong);
                Point pt4 = new Point(fourthbuttonlat, fourthbuttonlong);
                PolygonPoints polygonInfo = new PolygonPoints(pt1, pt2, pt3, pt4);
                i.putExtra("coordinates", polygonInfo);
                area = (ConvertToRadian(pt2.longitude) -ConvertToRadian( pt1.longitude)) * (2 + Math.sin(ConvertToRadian(pt1.latitude)) + Math.sin(ConvertToRadian(pt2.latitude))) +(ConvertToRadian(pt3.longitude) - ConvertToRadian(pt2.longitude)) * (2 + Math.sin(ConvertToRadian(pt2.latitude)) + Math.sin(ConvertToRadian(pt3.latitude)))+(ConvertToRadian(pt4.longitude) - ConvertToRadian(pt3.longitude)) * (2 + Math.sin(ConvertToRadian(pt3.latitude)) + Math.sin(ConvertToRadian(pt4.latitude)))+(ConvertToRadian(pt1.longitude) -ConvertToRadian( pt4.longitude)) * (2 + Math.sin(ConvertToRadian(pt4.latitude)) + Math.sin(ConvertToRadian(pt1.latitude)));
                area = area * 6378137 * 6378137 / 2;
                result=String.valueOf(area);
                Toast toast= Toast.makeText(getApplicationContext(),"Area:"+result,Toast.LENGTH_LONG);
                toast.show();
                //getAddress(firstbuttonlat,firstbuttonlong);
               /* Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geoCoder.getFromLocation(firstbuttonlat, firstbuttonlong, 1);

                    String city = "";
                    String state="";
                    if (addresses.size() > 0)
                    {
                       // for (int j=0; j<addresses.get(0).getMaxAddressLineIndex();j++)
                         //   add += addresses.get(0).getAddressLine(j) + "\n";
                       city= addresses.get(0).getLocality();
                       state=addresses.get(0).getAdminArea();

                    }

                    Toast t= Toast.makeText(getApplicationContext(),city,Toast.LENGTH_LONG);
                    t.show();
                    Toast tst= Toast.makeText(getApplicationContext(),state,Toast.LENGTH_LONG);
                    tst.show();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }*/
                startActivity(i);
            }
        });


    }
   /* public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(StartUpActivity.mContext, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();

            add = add + "\n" + obj.getAdminArea();

            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();

            Toast.makeText(getApplicationContext(),add,Toast.LENGTH_LONG).show();

            Log.v("IGA", "Address" + add);
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }*/

 //   private static double CalculatePolygonArea(Point p1,Point p2,Point p3,Point p4)   {
      //  double area;
       /* int Count=4;
        if (Count > 2)
        {
            for (int i = 0; i < Count - 1; i++)
            {
                 p1 = coordinates[i];
                  p2 = coordinates[i + 1];*/

        //        area = (p2.longitude - p1.longitude) * (2 + Math.sin(ConvertToRadian(p1.latitude)) + Math.sin(ConvertToRadian(p2.latitude))) +(p3.longitude - p2.longitude) * (2 + Math.sin(ConvertToRadian(p2.latitude)) + Math.sin(ConvertToRadian(p3.latitude)))+(p4.longitude - p3.longitude) * (2 + Math.sin(ConvertToRadian(p3.latitude)) + Math.sin(ConvertToRadian(p4.latitude)))+(p1.longitude - p4.longitude) * (2 + Math.sin(ConvertToRadian(p4.latitude)) + Math.sin(ConvertToRadian(p1.latitude)));
      //      area = area * 6378137 * 6378137 / 2;
       // }

       // return (area);
 //   }

    private static double ConvertToRadian(double input)
    {
        return input * Math.PI / 180;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(11.16, 78.69);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (firstButtonClicked) {
                    firstbuttonlat = latLng.latitude;
                    firstbuttonlong = latLng.longitude;

                    Toast.makeText(getApplicationContext(), "First button lat : " + firstbuttonlat + " First button long : " + firstbuttonlong, Toast.LENGTH_LONG).show();
                }
                if (secondButtonClicked) {
                    secondbuttonlat = latLng.latitude;
                    secondbuttonlong = latLng.longitude;
                    Toast.makeText(getApplicationContext(), "Second button lat : " + secondbuttonlat + " Second button long : " + secondbuttonlong, Toast.LENGTH_LONG).show();
                }
                if (thirdButtonClicked) {
                    thirdbuttonlat = latLng.latitude;
                    thirdbuttonlong = latLng.longitude;
                    Toast.makeText(getApplicationContext(), "third button lat : " + thirdbuttonlat + " third button long : " + thirdbuttonlong, Toast.LENGTH_LONG).show();
                }
                if (fourthButtonClicked) {
                    fourthbuttonlat = latLng.latitude;
                    fourthbuttonlong = latLng.longitude;
                    Toast.makeText(getApplicationContext(), "fourth button lat : " + fourthbuttonlat + " fourth button long : " + fourthbuttonlong, Toast.LENGTH_LONG).show();
                }
                if(firstbuttonlong != 0 && firstbuttonlat != 0 && secondbuttonlong != 0 && secondbuttonlat != 0 && thirdbuttonlong != 0 && thirdbuttonlat != 0 && fourthbuttonlong != 0 && fourthbuttonlat != 0)
                {
                    polygon1 = googleMap.addPolygon(new PolygonOptions()
                            .add(
                                    new LatLng(firstbuttonlat, firstbuttonlong),
                                    new LatLng(secondbuttonlat, secondbuttonlong),
                                    new LatLng(thirdbuttonlat, thirdbuttonlong),
                                    new LatLng(fourthbuttonlat, fourthbuttonlong)));
                    // Store a data object with the polygon, used here to indicate an arbitrary type.
                    polygon1.setTag("alpha");
                    // Style the polygon.

                    polygon1 = stylePolygon(polygon1);
                    confirm.setVisibility(View.VISIBLE);
                    firstButton.setVisibility(View.INVISIBLE);
                    secondButton.setVisibility(View.INVISIBLE);
                    thirdButton.setVisibility(View.INVISIBLE);
                    fourthButton.setVisibility(View.INVISIBLE);

                }


            }
        });

    }
    private Polygon stylePolygon(Polygon polygon) {
        String type = "";
        // Get the data object stored with the polygon.
        if (polygon.getTag() != null) {
            type = polygon.getTag().toString();
        }

        List<PatternItem> pattern = null;
        int strokeColor = COLOR_BLACK_ARGB;
        int fillColor = COLOR_WHITE_ARGB;

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "alpha":
                // Apply a stroke pattern to render a dashed line, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_GREEN_ARGB;
                fillColor = COLOR_PURPLE_ARGB;
                break;
            case "beta":
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_BETA;
                strokeColor = COLOR_ORANGE_ARGB;
                fillColor = COLOR_BLUE_ARGB;
                break;
        }

        polygon.setStrokePattern(pattern);
        polygon.setStrokeWidth(POLYGON_STROKE_WIDTH_PX);
        polygon.setStrokeColor(strokeColor);
        polygon.setFillColor(fillColor);
        return polygon;
    }


    public void onMapSearch(View view) {
        EditText locationSearch = (EditText) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address>addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    protected void search(List<Address> addresses) {

        Address address = (Address) addresses.get(0);
        double home_long = address.getLongitude();
        double home_lat = address.getLatitude();
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

        String addressText = String.format(
                "%s, %s",
                address.getMaxAddressLineIndex() > 0 ? address
                        .getAddressLine(0) : "", address.getCountryName());

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(latLng);
        markerOptions.title(addressText);

        GoogleMap map1 = null;
        map1.clear();
        map1.addMarker(markerOptions);
        map1.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map1.animateCamera(CameraUpdateFactory.zoomTo(15));
        BreakIterator locationTv = null;
        locationTv.setText("Latitude:" + address.getLatitude() + ", Longitude:"
                + address.getLongitude());


    }
    public void Action1(View v)
    {
        Intent i=new Intent(MapsActivity.this,SaveDataActivity.class);
        startActivity(i);
    }

}
