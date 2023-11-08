package com.jubayer.doctorsappinmentsystemuser.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jubayer.doctorsappinmentsystemuser.AddDoctorActivity;
import com.jubayer.doctorsappinmentsystemuser.AllDoctorActivity;
import com.jubayer.doctorsappinmentsystemuser.adapter.HorizontalDoctorAdapter;
import com.jubayer.doctorsappinmentsystemuser.chatbot.LiveChatBotActivity;
import com.jubayer.doctorsappinmentsystemuser.SettingActivity;
import com.jubayer.doctorsappinmentsystemuser.databinding.FragmentHomeBinding;
import com.jubayer.doctorsappinmentsystemuser.models.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment {

     private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.floatingActionButton.setOnClickListener(view ->
                startActivity(new Intent(getContext(), AddDoctorActivity.class)));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadDoctors();

        binding.etSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                performSearch();
                return true;
            }
            return false;
        });

        binding.tvSeeAllFavourite.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireContext(), AllDoctorActivity.class);
            intent.putExtra("type", "favourite");
            startActivity(intent);
        });

        binding.tvSeeAllPopulars.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireContext(), AllDoctorActivity.class);
            intent.putExtra("type", "popular");
            startActivity(intent);
        });

        binding.btnSetting.setOnClickListener(view12 -> startActivity(new Intent(requireContext(), SettingActivity.class)));

        binding.imgLiveChat.setOnClickListener(view13 -> startActivity(new Intent(getContext(), LiveChatBotActivity.class)));
    }

    private void performSearch() {
        String query = Objects.requireNonNull(binding.etSearch.getText()).toString().trim();
        Intent intent = new Intent(requireContext(), AllDoctorActivity.class);
        intent.putExtra("type", "search");
        intent.putExtra("query", query);
        startActivity(intent);
    }

    private void loadDoctors() {
        // we wil load recipes from our database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctors");
       /* reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Doctor> recipes = new ArrayList<>();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Doctor recipe = dataSnapshot.getValue(Doctor.class);
                    recipes.add(recipe);
                }
                loadPopularRecipes(recipes);
                loadFavouriteRecipes(recipes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error", error.getMessage());
            }
        });*/

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Doctor> doctors = new ArrayList<>();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Doctor doctor = dataSnapshot.getValue(Doctor.class);
                    doctors.add(doctor);
                }
                loadPopularDoctors(doctors);
                loadFavouriteDoctors(doctors);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error", error.getMessage());
            }
        });
    }

    private void loadPopularDoctors(List<Doctor> doctors) {
        List<Doctor> popularDoctors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int random = (int) (Math.random() * doctors.size());
            popularDoctors.add(doctors.get(random));
        }
        binding.rvPopulars.setAdapter(new HorizontalDoctorAdapter());
        HorizontalDoctorAdapter adapter = (HorizontalDoctorAdapter) binding.rvPopulars.getAdapter();
        if (adapter != null) {
            adapter.setDoctorList(popularDoctors);
        }
    }
    private void loadFavouriteDoctors(List<Doctor> doctors) {
        List<Doctor> favouriteDoctors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int random = (int) (Math.random() * doctors.size());
            favouriteDoctors.add(doctors.get(random));
        }
        binding.rvFavoriteDoctor.setAdapter(new HorizontalDoctorAdapter());
        HorizontalDoctorAdapter adapter = (HorizontalDoctorAdapter) binding.rvFavoriteDoctor.getAdapter();
        if (adapter != null) {
            adapter.setDoctorList(favouriteDoctors);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}