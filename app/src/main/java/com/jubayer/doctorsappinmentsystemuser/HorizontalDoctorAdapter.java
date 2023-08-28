package com.jubayer.doctorsappinmentsystemuser;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jubayer.doctorsappinmentsystemuser.databinding.ItemDoctorHorizontalBinding;
import com.jubayer.doctorsappinmentsystemuser.models.Doctor;

import java.util.ArrayList;
import java.util.List;


public class HorizontalDoctorAdapter extends RecyclerView.Adapter<HorizontalDoctorAdapter.RecipeHolder> {
    List<Doctor> recipeList = new ArrayList<>();

    public void setRecipeList(List<Doctor> recipeList) {

        this.recipeList.clear();

        this.recipeList = recipeList;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HorizontalDoctorAdapter.RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeHolder(ItemDoctorHorizontalBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalDoctorAdapter.RecipeHolder holder, int position) {
        Doctor recipe = recipeList.get(position);
        holder.onBind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class RecipeHolder extends RecyclerView.ViewHolder {
        ItemDoctorHorizontalBinding binding;
        public RecipeHolder(@NonNull ItemDoctorHorizontalBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(Doctor recipe) {
            // here perform all our recipe binding operation datastore
            Glide
                    .with(binding.getRoot().getContext())
                    .load(recipe.getImage())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(binding.bgImgRecipe);
            binding.tvRecipeName.setText(recipe.getName());

            binding.getRoot().setOnClickListener(view -> {
                Intent intent = new Intent(binding.getRoot().getContext(), DoctorDetailsActivity.class);
                intent.putExtra("recipe", recipe);
                binding.getRoot().getContext().startActivity(intent);
            });
        }
    }
}
