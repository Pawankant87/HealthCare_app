package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import dev.pawan.healthcare_app.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
   ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    @Override
    protected void onStart() {
        super.onStart();
        binding.login.setOnClickListener(v -> {
            String userFullName = binding.loginInputUserFullName.getText().toString();
            String userPassword = binding.loginInputPassword.getText().toString();
            Database db =new Database(getApplicationContext(),"healthcare",null,1);


            if(userFullName.length()==0||userPassword.length()==0){
                Toast.makeText(getApplicationContext(),"Please fill all details",Toast.LENGTH_SHORT).show();
            }else {
                if(db.login(userFullName,userPassword)==1){
                    Toast.makeText(getApplicationContext(),"Successfully login",Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =sharedPreferences.edit();
                    editor.putString("userFullName",userFullName);
                    editor.putString("userPassword",userPassword);
                    editor.apply();
                    startActivity(new Intent(this, HomeActivity.class));
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(),"Invalid email and password",Toast.LENGTH_SHORT).show();

                }
            }

        });

        binding.newRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class));
            finish();
        });

    }
}