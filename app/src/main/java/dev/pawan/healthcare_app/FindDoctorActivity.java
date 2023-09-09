package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import dev.pawan.healthcare_app.databinding.ActivityFindDoctorBinding;

public class FindDoctorActivity extends AppCompatActivity {
ActivityFindDoctorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityFindDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    protected void onStart(){
        super.onStart();
        binding.cardBack.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
        });

        binding.cardFDFamilyPhy.setOnClickListener(v -> {
            Intent i = new Intent(this,DoctorDetailsActivity.class);
            i.putExtra("title","Family Physicians");
            startActivity(i);

        });
        binding.cardDietician.setOnClickListener(v -> {
            Intent i = new Intent(this,DoctorDetailsActivity.class);
            i.putExtra("title","Dietitian");
            startActivity(i);

        });

        binding.cardDentist.setOnClickListener(v -> {
            Intent i = new Intent(this,DoctorDetailsActivity.class);
            i.putExtra("title","Dentist");
            startActivity(i);

        });
        binding.cardSurgeon.setOnClickListener(v -> {
            Intent i = new Intent(this,DoctorDetailsActivity.class);
            i.putExtra("title","Surgeon");
            startActivity(i);

        });
        binding.cardCardiologists.setOnClickListener(v -> {
            Intent i = new Intent(this,DoctorDetailsActivity.class);
            i.putExtra("title","Cardiologists");
            startActivity(i);

        });

    }
}