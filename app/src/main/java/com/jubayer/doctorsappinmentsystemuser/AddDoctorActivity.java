package com.jubayer.doctorsappinmentsystemuser;

import static java.lang.System.currentTimeMillis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jubayer.doctorsappinmentsystemuser.databinding.ActivityAddDoctorBinding;
import com.jubayer.doctorsappinmentsystemuser.models.Category;
import com.jubayer.doctorsappinmentsystemuser.models.Doctor;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AddDoctorActivity extends AppCompatActivity {
    ActivityAddDoctorBinding binding;
    private boolean isImageSelected = false;
    private ProgressDialog dialog;
    boolean isEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // load from the categories from the firebase database
        loadCategories();

        // user data input
        binding.btnAddDoctor.setOnClickListener(view ->
                getData()
        );
        binding.imgDoctor.setOnClickListener(view ->
                pickImage()
        );

        // for edit purpose
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        if (isEdit) {
            editDoctor();
        }
    }

    private void editDoctor() {
        Doctor doctor = (Doctor) getIntent().getSerializableExtra("doctor");
        isImageSelected = true;

        binding.etDoctorName.setText(doctor.getName());
        binding.etDescription.setText(doctor.getDescription());
        binding.etSheduleTime.setText(doctor.getTime());
        binding.etCategory.setText(doctor.getCategory());
        binding.etDate.setText(doctor.getDate());


        Glide
                .with(binding.getRoot().getContext())
                .load(doctor.getImage())
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(binding.imgDoctor);
        binding.btnAddDoctor.setText("Update Doctor");
    }

    private void loadCategories() {

        List<String> categories = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);

        binding.etCategory.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Categories");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() && snapshot.hasChildren()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        categories.add(dataSnapshot.getValue(Category.class).getName());
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void pickImage() {
        PickImageDialog.build(new PickSetup()).show(AddDoctorActivity.this).setOnPickResult(r -> {
            Log.e("ProfileFragment", "onPickResult: " + r.getUri());
            binding.imgDoctor.setImageBitmap(r.getBitmap());
            binding.imgDoctor.setScaleType(ImageView.ScaleType.CENTER_CROP);
            isImageSelected = true;
        }).setOnPickCancel(() -> Toast.makeText(AddDoctorActivity.this, "Cancelled", Toast.LENGTH_SHORT).show());
    }

    private void getData() {

        //Fetch all the data from the user in variables
        String doctorName = Objects.requireNonNull(binding.etDoctorName.getText()).toString();
        String doctorDescription = Objects.requireNonNull(binding.etDescription.getText()).toString();
        String sheduleTime = Objects.requireNonNull(binding.etSheduleTime.getText()).toString();
        String doctorCategory = binding.etCategory.getText().toString();
        String date = Objects.requireNonNull(binding.etDate.getText()).toString();
        // we will validate the data
        if (doctorName.isEmpty()) {
            binding.etDoctorName.setError("Please enter Doctor Name");
        } else if (doctorDescription.isEmpty()) {
            binding.etDescription.setError("Please enter Doctor Description");
        } else if (sheduleTime.isEmpty()) {
            binding.etSheduleTime.setError("Please enter Cooking Time");
        } else if (doctorCategory.isEmpty()) {
            binding.etCategory.setError("Please enter Doctor Category");
        } else if (date.isEmpty()) {
            binding.etDate.setError("Please enter Calories");
        } else if (!isImageSelected) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
        } else {
            dialog = new ProgressDialog(this);
            dialog.setMessage("Uploading Doctor...");
            dialog.setCancelable(false);
            dialog.show();
            //created a recipe object will auto generate
            Doctor doctor = new Doctor(doctorName, doctorDescription, sheduleTime, doctorCategory, date, "", FirebaseAuth.getInstance().getUid());
            // we will uploaded the image to the firebase storage
            uploadImage(doctor);
        }
    }

    private String uploadImage(Doctor doctor) {
        // we will uploaded the image to the firebase storage
        final String[] url = {""};
        binding.imgDoctor.setDrawingCacheEnabled(true);
        Bitmap bitmap = ((BitmapDrawable) binding.imgDoctor.getDrawable()).getBitmap();
        binding.imgDoctor.setDrawingCacheEnabled(false);
        String id = isEdit ? doctor.getId() : currentTimeMillis() + "";
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("images/" + id + "_recipe.jpg");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = storageRef.putBytes(data);
        uploadTask.continueWithTask(task -> {
            if (!task.isSuccessful()) {
                throw Objects.requireNonNull(task.getException());
            }
            // Continue with the task to get the download URL
            return storageRef.getDownloadUrl();
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Uri downloadUri = task.getResult();
                // download url in firebase database
                url[0] = downloadUri.toString();
                Toast.makeText(AddDoctorActivity.this, "Image uploaded Successfully", Toast.LENGTH_SHORT).show();
                saveDataInDatabase(doctor, url[0]);
            } else {
                // Handle failures
                Toast.makeText(this, "Error uploading image", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Log.e("ProfileFragment", "onComplete: " + Objects.requireNonNull(task.getException()).getMessage());
            }
        });
        return url[0];
    }

    private void saveDataInDatabase(Doctor doctor, String url) {
        doctor.setImage(url);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Doctors");
        if (isEdit) {
            reference.child(doctor.getId()).setValue(doctor).addOnCompleteListener(task -> {
                dialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Doctor Updated Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Error in updating recipe", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            String id = reference.push().getKey();
            doctor.setId(id);
            if (id != null) {
                reference.child(id).setValue(doctor).addOnCompleteListener(task -> {
                    dialog.dismiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Doctor Added Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        dialog.dismiss();
                        Toast.makeText(this, "Error in adding doctor", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}