package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import dev.pawan.healthcare_app.databinding.ActivityBookAppointmentBinding;

public class BookAppointmentActivity extends AppCompatActivity {

    ActivityBookAppointmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.BookAppointInputFullName.setKeyListener(null);
        binding.BookAppointInputMobile.setKeyListener(null);
        binding.BookAppointInputAddress.setKeyListener(null);
        binding.BookAppointInputFees.setKeyListener(null);


        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullName = it.getStringExtra("text2");
        String mobile = it.getStringExtra("text4");
        String address = it.getStringExtra("text3");
        String price = it.getStringExtra("text5");

        binding.textViewBookAppot.setText(title);
        binding.BookAppointInputFullName.setText(fullName);
        binding.BookAppointInputMobile.setText(mobile);
        binding.BookAppointInputAddress.setText(address);
        binding.BookAppointInputFees.setText("Cons Fees: " + price + "/-");



        MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();
        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            Date date = new Date(selection);
            String dateString = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date);
            binding.pickDateButton.setText(dateString);
        });

        //date_picker
        binding.pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "MY_DATE-PICKER_TAG");
            }

        });

        //Time_picker
        binding.pickTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H) // Use CLOCK_24H for 24-hour format
                        .setHour(12) // Initial hour
                        .setMinute(0) // Initial minute
                        .setTitleText("Pick a Time") // Optional title text
                        .build();

                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int hour = timePicker.getHour();
                        int minute = timePicker.getMinute();
                        if(hour>12) {
                            binding.pickTimeButton.setText(String.valueOf(hour-12)+ ":"+(String.valueOf(minute)+" pm"));
                        } else if(hour==12) {
                            binding.pickTimeButton.setText("12"+ ":"+(String.valueOf(minute)+" pm"));
                        } else if(hour<12) {
                            if(hour!=0) {
                                binding.pickTimeButton.setText(String.valueOf(hour) + ":" + (String.valueOf(minute) + " am"));
                            } else {
                                binding.pickTimeButton.setText("12" + ":" + (String.valueOf(minute) + " am"));
                            }
                        }

                    }
                });
                timePicker.show(getSupportFragmentManager(), "timePicker");

            }
        });
        binding.buttonBookAppointment.setOnClickListener(v -> {
            SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String userFullName =sharedPreferences.getString("userFullName","").toString();
            Database db =new Database(getApplicationContext(),"healthcare",null,1);

            if(db.checkBookAppointment(userFullName,title+" =>"+fullName,
                    mobile,address,binding.pickDateButton.getText().toString(), binding.pickTimeButton.getText().toString())==1){
                Toast.makeText(getApplicationContext(),"Appointment already booked",Toast.LENGTH_LONG).show();
        }else {
                db.addBook(userFullName, title+ " =>"+ fullName, mobile, address, 0, binding.pickDateButton.getText().toString(),
                        binding.pickTimeButton.getText().toString(), Float.parseFloat(price),"appointment");
                 Toast.makeText(getApplicationContext(),"Your appointment  is  done successfully",Toast.LENGTH_LONG).show();
                 startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));
               }
        });

    }

    public void onStart() {
        super.onStart();
        binding.backAppointment.setOnClickListener(v -> {
            startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class));
            finish();
        });
    }
}