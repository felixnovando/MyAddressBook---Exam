package com.testing.myaddressbook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.testing.myaddressbook.DetailActivity;
import com.testing.myaddressbook.Models.Employee;
import com.testing.myaddressbook.R;
import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder>{

    private ArrayList<Employee> employees;
    private Context ctx;
    public AddressAdapter(Context ctx, ArrayList<Employee> employees) {
        this.employees = employees;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        return new AddressViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        Employee employee = employees.get(position);
        Glide.with(ctx).load(employee.getPicture().thumbnailUrl).into(holder.profilePicture);
        holder.nameView.setText(employee.getDisplayName());
        holder.cityView.setText("City : " + employee.getDisplayLocation());

        holder.callBtn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                String s = "tel:" + employee.getPhone();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(s));
                ctx.startActivity(intent);
            }
        });

        holder.emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "mailto:" + employee.getEmail();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(s));
                ctx.startActivity(Intent.createChooser(emailIntent, "Choose Title"));
            }
        });

        holder.clickLayout.setOnClickListener(new View.OnClickListener() {
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

    class AddressViewHolder extends RecyclerView.ViewHolder{
        ImageView profilePicture;
        TextView nameView, cityView;
        MaterialButton callBtn, emailButton;
        LinearLayout clickLayout;
        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            clickLayout = itemView.findViewById(R.id.clickLayout);
            profilePicture = itemView.findViewById(R.id.profile_picture);
            nameView = itemView.findViewById(R.id.nameView);
            cityView = itemView.findViewById(R.id.cityView);
            callBtn = itemView.findViewById(R.id.callBtn);
            emailButton = itemView.findViewById(R.id.emailBtn);
        }
    }


}
