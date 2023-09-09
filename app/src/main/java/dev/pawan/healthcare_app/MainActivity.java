package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler(getMainLooper()).postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String loggedIn =sharedPreferences.getString("userFullName","").toString();
            Intent intent;
            if (!loggedIn.equals("")) {
                intent = new Intent(MainActivity.this, HomeActivity.class);
            } else {
                intent = new Intent(MainActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
        },2000);

    }

}