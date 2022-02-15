package com.testing.myaddressbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.testing.myaddressbook.Helper.StaticStorage;
import com.testing.myaddressbook.Models.Employee;
import com.testing.myaddressbook.Models.GetEmployee;
import com.testing.myaddressbook.RetrofitClient.EmployeeClient;
import com.testing.myaddressbook.RetrofitInterface.EmployeeInterface;
import com.testing.myaddressbook.fragments.AddressFragment;
import com.testing.myaddressbook.fragments.ContactFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EmployeeInterface employeeInterface = EmployeeClient.getClientInstance().create(EmployeeInterface.class);
    BottomNavigationView bottomNavigationView;
    Context ctx;
    ArrayList<Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        employees = new ArrayList<>();

        //inits
        bottomNavigationView = findViewById(R.id.bottom_nav);
        getEmployeeList();
        StaticStorage.employees = employees;

        //set fragment ke fragment container
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container, ContactFragment.getInstance(), null)
                .commit();

        //set listener ke navbar bottom
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.menu_contact:
                        fragment = ContactFragment.getInstance();
                        break;
                    case R.id.menu_address:
                        fragment = AddressFragment.getInstance();
                        break;
                }
                //change fragment sesuai dengan yang dipilih
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, fragment, null)
                        .commit();

                return true;
            }
        });
    }

    public void getEmployeeList(){
        Call<GetEmployee> employeeListCall = employeeInterface.getEmployeeList();
        employeeListCall.enqueue(new Callback<GetEmployee>() {
            @Override
            public void onResponse(Call<GetEmployee> call, Response<GetEmployee> response) {
                List<Employee> employeeList = response.body().getEmployeeList();
                for(Employee e : employeeList){
                    employees.add(e);
                }
            }
            @Override
            public void onFailure(Call<GetEmployee> call, Throwable t) {
                Log.i("BADUT", "Wrong API");
                Toast.makeText(ctx, "FETCH API Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}