package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

import dev.pawan.healthcare_app.databinding.ActivityBuyMedicineBookBinding;
import dev.pawan.healthcare_app.databinding.ActivityLabTestBookBinding;

public class BuyMedicineBookActivity extends AppCompatActivity {

    ActivityBuyMedicineBookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuyMedicineBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent =getIntent();
        String[] price =intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date =intent.getStringExtra("date");


        binding.bMBButtonBook.setOnClickListener(v -> {
            SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String userFullName =sharedPreferences.getString("userFullName","").toString();
            Database db =new Database(getApplicationContext(),"healthcare",null,1);

            assert date != null;
            db.addBook(
                    userFullName,
                    Objects.requireNonNull(binding.bMBBookInputFullName.getText()).toString(),
                    Objects.requireNonNull(binding.bMBBookInputMobile.getText()).toString(),
                    Objects.requireNonNull(binding.bMBBookInputAddress.getText()).toString(),
                    Integer.parseInt(Objects.requireNonNull(binding.bMBookInputPinCode.getText()).toString()),
                    date,"",
                    Float.parseFloat(price[1]),"medicine"
            );

            db.removedCart(userFullName,"medicine");
            Toast.makeText(getApplicationContext(),"Your Booking is done successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BuyMedicineBookActivity.this,OrderDetailsActivity.class));

        });
    }
}