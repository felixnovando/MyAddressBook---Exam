package com.testing.myaddressbook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.testing.myaddressbook.DetailActivity;
import com.testing.myaddressbook.Helper.Helper;
import com.testing.myaddressbook.Models.Employee;
import com.testing.myaddressbook.R;
import com.testing.myaddressbook.fragments.ContactFragment;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    private ArrayList<Employee> employees;
    private Context ctx;
    public ContactAdapter(Context ctx, ArrayList<Employee> employees) {
        this.employees = employees;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Employee employee = employees.get(position);
        Glide.with(ctx).load(employee.getPicture().thumbnailUrl).into(holder.profilePicture);
        holder.nameView.setText(employee.getDisplayName());
        holder.cityView.setText("City : " + employee.getDisplayLocation());
        holder.phoneView.setText("Phone : " + employee.getPhone() + " / " + employee.getCell());
        holder.memberDateView.setText("Member since : " + Helper.convertDate(employee.getRegisterDate()));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(ctx, DetailActivity.class);
                detailIntent.putExtra("id", employee.getId() + "");
                ctx.startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{
        ImageView profilePicture;
        TextView nameView, cityView, phoneView, memberDateView;
        LinearLayout linearLayout;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            profilePicture = itemView.findViewById(R.id.profile_picture);
            nameView = itemView.findViewById(R.id.nameView);
            cityView = itemView.findViewById(R.id.cityView);
            phoneView = itemView.findViewById(R.id.phoneView);
            memberDateView = itemView.findViewById(R.id.memberDateView);
        }
    }

}