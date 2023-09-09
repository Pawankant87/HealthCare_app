package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import dev.pawan.healthcare_app.databinding.ActivityBookAppointmentBinding;
import dev.pawan.healthcare_app.databinding.ActivityBuyMedicineDetailsBinding;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    ActivityBuyMedicineDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuyMedicineDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bMDTextEdit.setKeyListener(null);

        Intent it = getIntent();
        binding.bMDTitle.setText(it.getStringExtra("text1"));
        binding.bMDTextEdit.setText(it.getStringExtra("text2"));
        binding.bMDTotalCost.setText("Total Cost: "+it.getStringExtra("text3")+"/-");

        binding.bMDGtCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String userFullName =sharedPreferences.getString("userFullName","").toString();
                String product =binding.bMDTitle.getText().toString();
                float price =Float.parseFloat(getIntent().getStringExtra("text3").toString());

                Database db =new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkCart(userFullName,product)==1){
                    Toast.makeText(getApplicationContext(),"Product Already Added",Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(userFullName,product,price,"medicine");
                    Toast.makeText(getApplicationContext(),"Record Inserted to Cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));

                }

            }
        });
    }
    public void onStart() {
        super.onStart();
        binding.bMDBack.setOnClickListener(v -> {
            startActivity(new Intent(this,BuyMedicineActivity.class));
            finish();
        });
    }
}