package com.jubayer.doctorsappinmentsystemuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jubayer.doctorsappinmentsystemuser.R;
import com.jubayer.doctorsappinmentsystemuser.models.AppoinmentData;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.MyHolder> {
    Context context;
    ArrayList<AppoinmentData> list;


    public AppointmentAdapter(Context context, ArrayList<AppoinmentData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AppointmentAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.details_list_item, parent, false);
        return  new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.MyHolder holder, int position) {

        AppoinmentData data = list.get(position);

        holder.nameTv.setText("Name: "+data.getName());
        holder.moblieTv.setText("Phone Number :"+data.getMobile());
        holder.timeTv.setText("Appointment Time: "+data.getTime());
        holder.dayTv.setText("Day of Week: "+data.getDay());
        holder.statusTv.setText("Status: "+data.getStatus());
        holder.drnameTv.setText("Dr. Name: "+data.getDrname());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView nameTv, moblieTv, timeTv, dayTv, statusTv, drnameTv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.nameTv);
            moblieTv = itemView.findViewById(R.id.mobileTv);
            timeTv = itemView.findViewById(R.id.timeTv);
            dayTv = itemView.findViewById(R.id.dayTv);
            statusTv = itemView.findViewById(R.id.statusTv);
            drnameTv = itemView.findViewById(R.id.drnameTv);

        }
    }
}
