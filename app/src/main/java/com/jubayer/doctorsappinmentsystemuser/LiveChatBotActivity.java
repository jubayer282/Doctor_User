package com.jubayer.doctorsappinmentsystemuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jubayer.doctorsappinmentsystemuser.databinding.ActivityLiveChatBotBinding;

public class LiveChatBotActivity extends AppCompatActivity {
    ActivityLiveChatBotBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLiveChatBotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}