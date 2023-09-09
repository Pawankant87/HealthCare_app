package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import dev.pawan.healthcare_app.databinding.ActivityHealthArticlesBinding;
import dev.pawan.healthcare_app.databinding.ActivityHealthArticlesDetailsBinding;

public class HealthArticlesDetailsActivity extends AppCompatActivity {

    ActivityHealthArticlesDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHealthArticlesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent =getIntent();
        binding.hADTitle.setText(intent.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
            int resId =bundle.getInt("text2");
            binding.hADImage.setImageResource(resId);
        }
    }
    protected void onStart() {
        super.onStart();
        binding.hADBack.setOnClickListener(v -> {
            startActivity(new Intent(this,HealthArticlesActivity.class));
        });
    }
}