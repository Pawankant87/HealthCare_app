package dev.pawan.healthcare_app;

import static android.opengl.ETC1.isValid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Objects;

import dev.pawan.healthcare_app.databinding.ActivityLoginBinding;
import dev.pawan.healthcare_app.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    protected void onStart(){
        super.onStart();
        binding.buttonSignUp.setOnClickListener(v -> {
            String  userFullName = Objects.requireNonNull(binding.signUpInputFullName.getText()).toString();
            String  userMobile = Objects.requireNonNull(binding.signUpInputMobile.getText()).toString();
            String  userEmail = Objects.requireNonNull(binding.signUpInputEmail.getText()).toString();
            String  userPassword = Objects.requireNonNull(binding.signUpInputPassword.getText()).toString();
            String  userConfirmPassword = Objects.requireNonNull(binding.signUpInputCfmPassword.getText()).toString();
            Database db =new Database(getApplicationContext(),"healthcare",null,1);

            if(userFullName.length()==0||userEmail.length()==0|| userMobile.length()==0||userPassword.length()==0|| userConfirmPassword.length()==0){
                Toast.makeText(getApplicationContext(),"Please fill all details",Toast.LENGTH_SHORT).show();
            }else {
                if(userPassword.compareTo(userConfirmPassword)==0){
                    if(isValid(userPassword)){
                        db.register(userFullName,userMobile,userEmail,userPassword);
                        Toast.makeText(getApplicationContext(),"Successfully SignUp",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"Password must  be at least 6 characters",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Password and Confirm Password not match!",Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.alreadyLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

    }

    private boolean isValid(String password) {
        int f1=0 ,f2=0;
        if (password.length()<6){
            return false;

        }else{
            for (int p=0;p<password.length();p++ ){
                if(Character.isLetter(password.charAt(p))){
                    f1=1;
                }
            }
        }
        for (int r=0;r<password.length();r++ ){
            if(Character.isDigit(password.charAt(r))){
                f2=1;
            }
        }

        if (f1==1 && f2==1 )
            return true;
        return false;

    }
}