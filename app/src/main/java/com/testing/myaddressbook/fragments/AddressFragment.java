package com.testing.myaddressbook.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.testing.myaddressbook.Adapters.AddressAdapter;
import com.testing.myaddressbook.Adapters.ContactAdapter;
import com.testing.myaddressbook.Helper.DatabaseHelper;
import com.testing.myaddressbook.Helper.StaticStorage;
import com.testing.myaddressbook.Models.Employee;
import com.testing.myaddressbook.R;

import java.util.ArrayList;

public class AddressFragment extends Fragment {

    private static AddressFragment instance = null;
    private Context ctx;
    private ArrayList<Employee> employees;
    private DatabaseHelper dbHelper;
    private AddressAdapter adapter;

    public static AddressFragment getInstance(){
        if(instance == null) instance = new AddressFragment();
        return instance;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ctx = context;
    }

    @Override
    public void onResume() {
        super.onResume();

        employees.clear();
        employees.addAll(dbHelper.getAllEmployee());
        adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dbHelper = new DatabaseHelper(ctx);
        employees = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        adapter = new AddressAdapter(ctx, employees);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx);
        RecyclerView contactRecycleView = view.findViewById(R.id.rcrAddress);
        contactRecycleView.setLayoutManager(layoutManager);
        contactRecycleView.setAdapter(adapter);

        return view;
    }
}
