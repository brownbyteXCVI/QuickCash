package com.example.quickcashg12;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    SearchView search_bar;
    ListView search_list;
    Button locate;
    TextView city;
    // change arraylist to store a Job object instead of String
    ArrayList<String> search_list_array;
    ArrayAdapter<String> search_list_arrayAdapter;

    // create an instance member for DatabaseReference
    DatabaseReference reference;
    FusedLocationProviderClient fusedLocationProviderClient;

    /*
        -> Reviewed by Neuer Gao on Nov 8, 2020
        -> Problems: Too many died codes, waste space. Need to improve coding style.
                    All method are written in one method, this will increase the cost of change,
                    and cause technical debt in the future.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // get the database reference to the jobs child. This will be used to read all the posted
        // jobs from the firebase and populate the search_list
        reference = FirebaseDatabase.getInstance().getReference().child("Job");
        locate = findViewById(R.id.getLoc);
        city = findViewById(R.id.city);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check permission
                if (ActivityCompat.checkSelfPermission(SearchActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //When permission granted
                    getLocation();
                } else {
                    //When permission denied
                    ActivityCompat.requestPermissions(SearchActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
        // instantiate the ArrayList before adding the ValueEventListener
        search_list_array = new ArrayList<>();

        // add ValueEventListener that will be used to read the data from the firebase and populate
        // the search_list
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // each child of snapshot represents one job. Add it to the search_list
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    // use the getValue method to retrieve a Job object
                    Job currentJob = childSnapshot.getValue(Job.class);
                    // add the name of the job to the search_list_array
                    search_list_array.add(currentJob.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //hooks to all xml elements in activity_search.xml
        search_bar = findViewById(R.id.Search_bar);  //search view
        search_list = findViewById(R.id.search_list);  // list view

        //adding to the array search list

        /*
            Too many died codes, waste space. - Neuer Gao
         */

        // commented the values that were being added statically as the application will now
        // retrieve the jobs dynamically from the firebase

//        search_list_array.add("Bartender");
//        search_list_array.add("Lawn Mower");
//        search_list_array.add("Chauffeur");
//        search_list_array.add("App Developer");
//        search_list_array.add("Car Mechanic");
//        search_list_array.add("Sales Associate");
//        search_list_array.add("Software Engineer");
//        search_list_array.add("Web Developer");
//        search_list_array.add("Teacher Wanted");
//        search_list_array.add("Waiter Needed");

        search_list_arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, search_list_array);
        search_list.setAdapter(search_list_arrayAdapter);

        /*
            All method are written in one method, this will increase the cost of change, and cause technical debt in the future.
         */
        search_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                search_list_arrayAdapter.getFilter().filter(s);
                return false;
            }
        });
    }

    private void getLocation() {
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
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //initial location
                Location location = task.getResult();
                if (location != null) {

//                    jingdu.setText("经度是"+ location.getLongitude());
//                    weidu.setText("纬度是"+location.getLatitude());
                    //Initial address list
                    List<Address> addresses;
                    try {
                        Geocoder geocoder = new Geocoder(SearchActivity.this,
                                Locale.getDefault());
                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        if (addresses != null && addresses.size() > 0) {
                            Address returnAddress = addresses.get(0);
                            String localityString = returnAddress.getLocality();
                            String name = returnAddress.getFeatureName();
                            String subLocality = returnAddress.getSubLocality();
                            String country = returnAddress.getCountryName();
                            String region_code = returnAddress.getCountryCode();
                            String zipcode = returnAddress.getPostalCode();
                            String state = returnAddress.getAdminArea();
                            city.setText("the result is " + localityString);
                            search_bar.setQuery(localityString, false);
                            search_bar.clearFocus();
                        } else {
                            city.setText("Found error");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        city.setText("Found error IO");
                    }

                } else city.setText("Found error");
            }
        });
    }
}