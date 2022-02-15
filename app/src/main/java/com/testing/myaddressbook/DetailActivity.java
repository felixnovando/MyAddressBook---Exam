package com.testing.myaddressbook;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.testing.myaddressbook.Helper.DatabaseHelper;
import com.testing.myaddressbook.Helper.Helper;
import com.testing.myaddressbook.Helper.StaticStorage;
import com.testing.myaddressbook.Models.Employee;
import com.testing.myaddressbook.databinding.ActivityDetailBinding;

import java.util.ArrayList;

public class DetailActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityDetailBinding binding;
    private int id;
    private boolean isContact;
    private Employee employee;
    private DatabaseHelper dbHelper;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ctx = this;
        dbHelper = new DatabaseHelper(ctx);
        Intent intent = getIntent();
        try {
            id = Integer.parseInt(intent.getStringExtra("id"));
            employee = StaticStorage.getEmployeeWithID(id);

            //check uda di contact blom
            ArrayList<Employee> listEmployee = dbHelper.getAllEmployee();
            isContact = false;
            for(Employee e : listEmployee){
                if(e.getId() == id){
                    isContact = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            id = -1;
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //display datas
        binding.nameView.setText(employee.getDisplayName());
        binding.cityView.setText("City: " + employee.getDisplayLocation());
        binding.phoneView.setText("Phone: " + employee.getPhone() + " / " + employee.getCell());
        binding.memberDateView.setText("Member Since: " + Helper.convertDate(employee.getRegisterDate()));
        binding.emailView.setText("Email : " + employee.getEmail());

        if(isContact == true){
            binding.removeBtn.setVisibility(View.VISIBLE);
            binding.addBtn.setVisibility(View.GONE);
        }else{
            binding.removeBtn.setVisibility(View.GONE);
            binding.addBtn.setVisibility(View.VISIBLE);
        }

        binding.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.delete(id);
                binding.removeBtn.setVisibility(View.GONE);
                binding.addBtn.setVisibility(View.VISIBLE);
            }
        });

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean success = dbHelper.addData(employee);
                if(success){
                    binding.removeBtn.setVisibility(View.VISIBLE);
                    binding.addBtn.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng location = new LatLng(employee.getLatitude(), employee.getLongitude());
        mMap.addMarker(new MarkerOptions().position(location).title("Employee Location"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 5f));
    }
}