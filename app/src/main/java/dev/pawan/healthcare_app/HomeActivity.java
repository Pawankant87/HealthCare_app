package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import dev.pawan.healthcare_app.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    @Override
    protected  void  onStart(){
        super.onStart();
        SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String useName =sharedPreferences.getString("userFullName","").toString();
        Toast.makeText(getApplicationContext(),"Welcome "+ useName,Toast.LENGTH_SHORT).show();

        binding.cardExit.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
        binding.cardFindDoctor.setOnClickListener(v -> {
            startActivity(new Intent(this, FindDoctorActivity.class));
        });
        binding.cardLabTest.setOnClickListener(v -> {
            startActivity(new Intent(this, LabTestActivity.class));
        });
        binding.cardOrderDetails.setOnClickListener(v -> {
            startActivity(new Intent(this, OrderDetailsActivity.class));
        });
        binding.cardBuyMedicine.setOnClickListener(v -> {
            startActivity(new Intent(this, BuyMedicineActivity.class));
        });
        binding.cardHealthArticles.setOnClickListener(v -> {
            startActivity(new Intent(this, HealthArticlesActivity.class));
        });
    }
}