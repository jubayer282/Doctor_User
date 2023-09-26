package com.jubayer.doctorsappinmentsystemuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jubayer.doctorsappinmentsystemuser.databinding.ActivityDoctorDetailsBinding;
import com.jubayer.doctorsappinmentsystemuser.models.Doctor;
import com.jubayer.doctorsappinmentsystemuser.room.FavouriteRecipe;
import com.jubayer.doctorsappinmentsystemuser.room.RecipeRepository;


public class DoctorDetailsActivity extends AppCompatActivity {
    ActivityDoctorDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        Doctor recipe = (Doctor) getIntent().getSerializableExtra("recipe");
        binding.tvName.setText(recipe.getName());
        binding.tcCategory.setText(recipe.getCategory());
        binding.tvDescription.setText(recipe.getDescription());
        binding.tvTime.setText("Time: " + recipe.getTime());
        binding.tvCalories.setText("Schedule: " + recipe.getDate());

        /*appoinment button*/
        binding.appoinmentIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorDetailsActivity.this, GetAppoinmentActivity.class);
                intent.putExtra("dr_name", binding.tvName.getText().toString().trim());
                startActivity(intent);
            }
        });
/*
        binding.tvTime.setText(recipe.getTime());
*/

        Glide
                .with(DoctorDetailsActivity.this)
                .load(recipe.getImage())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(binding.imgRecipe);

        if (recipe.getAuthorId().equalsIgnoreCase(FirebaseAuth.getInstance().getUid())) {
            /*binding.imgEdit.setVisibility(View.VISIBLE);*/
        } else {
            /*binding.imgEdit.setVisibility(View.GONE);*/
        }
        /*binding.imgEdit.setOnClickListener(view -> {
            Intent intent = new Intent(binding.getRoot().getContext(), AddDoctorActivity.class);
            intent.putExtra("recipe", recipe);
            intent.putExtra("isEdit", true);
            binding.getRoot().getContext().startActivity(intent);
        });*/
        checkFavourite(recipe);
        binding.imgFvrt.setOnClickListener(view -> {
            favouriteRecipe(recipe);
        });
        updateDateWithFireBase(recipe.getId());
    }

    private void checkFavourite(Doctor recipe) {
        RecipeRepository repository = new RecipeRepository(getApplication());
        boolean isFavourite = repository.isFavourite(recipe.getId());
        if (isFavourite) {
            binding.imgFvrt.setColorFilter(getResources().getColor(R.color.accent));
        } else {
            binding.imgFvrt.setColorFilter(getResources().getColor(R.color.black));
        }
    }

    private void favouriteRecipe(Doctor recipe) {
        RecipeRepository repository = new RecipeRepository(getApplication());
        boolean isFavourite = repository.isFavourite(recipe.getId());
        if (isFavourite) {
            repository.delete(new FavouriteRecipe(recipe.getId()));
            binding.imgFvrt.setColorFilter(getResources().getColor(R.color.black));
        } else {
            repository.insert(new FavouriteRecipe(recipe.getId()));
            binding.imgFvrt.setColorFilter(getResources().getColor(R.color.accent));
        }
    }

    private void updateDateWithFireBase(String id) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Recipes");
        reference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Doctor recipe = snapshot.getValue(Doctor.class);
                binding.tvName.setText(recipe.getName());
                binding.tcCategory.setText(recipe.getCategory());
                binding.tvDescription.setText(recipe.getDescription());
                binding.tvCalories.setText("Schedule: " + recipe.getDate());

                Glide
                        .with(DoctorDetailsActivity.this)
                        .load(recipe.getImage())
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher)
                        .into(binding.imgRecipe);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("TAG", "onCancelled: ", error.toException());
            }
        });
    }

}