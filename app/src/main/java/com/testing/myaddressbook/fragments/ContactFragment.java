package com.testing.myaddressbook.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.testing.myaddressbook.Adapters.ContactAdapter;
import com.testing.myaddressbook.Helper.StaticStorage;
import com.testing.myaddressbook.Models.Employee;
import com.testing.myaddressbook.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

public class ContactFragment extends Fragment {

    private static ContactFragment instance = null;
    private Context ctx;
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Employee> displayedEmployee = new ArrayList<>();
    private ContactAdapter adapter;

    private TextView searchTextView;
    private EditText edtSearch;

    public static ContactFragment getInstance(){
        if(instance == null) instance = new ContactFragment();
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
        edtSearch.setText("");
        displayedEmployee.clear();
        displayedEmployee.addAll(StaticStorage.employees);
        adapter.notifyItemRangeChanged(0, employees.size());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        adapter = new ContactAdapter(ctx, displayedEmployee);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx);

        RecyclerView contactRecycleView = view.findViewById(R.id.rcrContact);
        contactRecycleView.setLayoutManager(layoutManager);
        contactRecycleView.setAdapter(adapter);

        edtSearch = view.findViewById(R.id.edtSearch);
        searchTextView = view.findViewById(R.id.clickSearch);
        employees = StaticStorage.employees;

        searchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edtSearch.getText().toString().trim().toLowerCase();
                displayedEmployee.clear();
                for(Employee e : employees){
                    if(e.getDisplayName().toLowerCase().contains(text)){
                        displayedEmployee.add(e);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}
