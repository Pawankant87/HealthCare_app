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

import dev.pawan.healthcare_app.databinding.ActivityLabTestBookBinding;
import dev.pawan.healthcare_app.databinding.ActivityLabTestDetailsBinding;

public class LabTestBookActivity extends AppCompatActivity {

    ActivityLabTestBookBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLabTestBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent =getIntent();
        String[] price =intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date =intent.getStringExtra("date");
        String time =intent.getStringExtra("time");

        binding.buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String userFullName =sharedPreferences.getString("userFullName","").toString();
                Database db =new Database(getApplicationContext(),"healthcare",null,1);

                assert date != null;
                assert time != null;
                db.addBook(
                       userFullName,
                       Objects.requireNonNull(binding.BookInputFullName.getText()).toString(),
                       Objects.requireNonNull(binding.BookInputMobile.getText()).toString(),
                       Objects.requireNonNull(binding.BookInputAddress.getText()).toString(),
                       Integer.parseInt(Objects.requireNonNull(binding.BookInputPicode.getText()).toString()),
                        date, time,
                       Float.parseFloat(price[1]),"lab"
               );

                db.removedCart(userFullName,"lab");
                Toast.makeText(getApplicationContext(),"Your Booking is done successfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBookActivity.this,OrderDetailsActivity.class));

            }
        });
    }
}