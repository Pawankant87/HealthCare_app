package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dev.pawan.healthcare_app.databinding.ActivityFindDoctorBinding;
import dev.pawan.healthcare_app.databinding.ActivityLabTestDetailsBinding;

public class LabTestDetailsActivity extends AppCompatActivity {

    ActivityLabTestDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLabTestDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ldTextEdit.setKeyListener(null);

        Intent it = getIntent();
        binding.labTestLdTitle.setText(it.getStringExtra("text1"));
        binding.ldTextEdit.setText(it.getStringExtra("text2"));
        binding.textViewTotalCost.setText("Total Cost: "+it.getStringExtra("text3")+"/-");

      binding.ldGtCart.setOnClickListener(new View.OnClickListener() {
        @Override
       public void onClick(View v) {
          SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
          String userFullName =sharedPreferences.getString("userFullName","").toString();
          String product =binding.labTestLdTitle.getText().toString();
          float price =Float.parseFloat(getIntent().getStringExtra("text3").toString());

          Database db =new Database(getApplicationContext(),"healthcare",null,1);

          if(db.checkCart(userFullName,product)==1){
              Toast.makeText(getApplicationContext(),"Product Already Added",Toast.LENGTH_SHORT).show();
          }else{
              db.addCart(userFullName,product,price,"lab");
              Toast.makeText(getApplicationContext(),"Record Inserted to Cart",Toast.LENGTH_SHORT).show();
              startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));

          }

      }
     });
    }
    public void onStart() {
        super.onStart();
        binding.ldBack.setOnClickListener(v -> {
            startActivity(new Intent(this,LabTestActivity.class));
            finish();
        });
    }
}