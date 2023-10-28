package com.jubayer.doctorsappinmentsystemuser.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jubayer.doctorsappinmentsystemuser.DoctorDetailsActivity;
import com.jubayer.doctorsappinmentsystemuser.R;
import com.jubayer.doctorsappinmentsystemuser.databinding.ItemDoctorHorizontalBinding;
import com.jubayer.doctorsappinmentsystemuser.models.Doctor;

import java.util.ArrayList;
import java.util.List;


public class HorizontalDoctorAdapter extends RecyclerView.Adapter<HorizontalDoctorAdapter.DoctorHolder> {
    List<Doctor> doctorList = new ArrayList<>();

    public void setDoctorList(List<Doctor> doctorList) {

        this.doctorList.clear();

        this.doctorList = doctorList;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HorizontalDoctorAdapter.DoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DoctorHolder(ItemDoctorHorizontalBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalDoctorAdapter.DoctorHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.onBind(doctor);
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public static class DoctorHolder extends RecyclerView.ViewHolder {
        ItemDoctorHorizontalBinding binding;
        public DoctorHolder(@NonNull ItemDoctorHorizontalBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(Doctor doctor) {
            // here perform all our recipe binding operation datastore
            Glide
                    .with(binding.getRoot().getContext())
                    .load(doctor.getImage())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(binding.bgImgDoctor);
            binding.tvDoctorName.setText(doctor.getName());

            binding.getRoot().setOnClickListener(view -> {
                Intent intent = new Intent(binding.getRoot().getContext(), DoctorDetailsActivity.class);
                intent.putExtra("doctor", doctor);
                binding.getRoot().getContext().startActivity(intent);
            });
        }
    }
}
