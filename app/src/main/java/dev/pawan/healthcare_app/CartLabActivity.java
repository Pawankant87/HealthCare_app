package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import dev.pawan.healthcare_app.databinding.ActivityCartLabBinding;

public class CartLabActivity extends AppCompatActivity {

    ActivityCartLabBinding binding;

    private  String[][] packages ={};

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartLabBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String userFullName =sharedPreferences.getString("userFullName","").toString();

        Database db =new Database(getApplicationContext(),"healthcare",null,1);

        float totalAmount =0;
        ArrayList dbData = db.getCartData(userFullName,"lab");

        packages =new String[dbData.size()][];
        for(int i=0;i<packages.length;i++){
            packages[i]=new String[5];
        }
        for(int i=0;i<dbData.size();i++){
            String arrData =dbData.get(i).toString();
            String[] strData =arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strData[0];
            packages[i][4]=strData[1];
            totalAmount =totalAmount+Float.parseFloat(strData[1]);
            binding.cartLabTotalPrice.setText("Total Cost :"+totalAmount);

        }
        list =new ArrayList();
        for (int i=0;i<packages.length;i++){
            item= new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5", "Cons Fees "+packages[i][4]+"/-");
            list.add(item);
        }
        sa =new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        binding.listViewsCartLab.setAdapter(sa);


        MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();
        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            Date date = new Date(selection);
            String dateString = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault()).format(date);
            binding.CartLabPickDateButton.setText(dateString);
        });

        //date_picker
        binding.CartLabPickDateButton.setOnClickListener(v ->
                materialDatePicker.show(getSupportFragmentManager(), "MY_DATE-PICKER_TAG"));

        //Time_picker
        binding.CartLabPickTimeButton.setOnClickListener(new View.OnClickListener() {
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
                            binding.CartLabPickTimeButton.setText(String.valueOf(hour-12)+ ":"+(String.valueOf(minute)+" pm"));
                        } else if(hour==12) {
                            binding.CartLabPickTimeButton.setText("12"+ ":"+(String.valueOf(minute)+" pm"));
                        } else if(hour<12) {
                            if(hour!=0) {
                                binding.CartLabPickTimeButton.setText(String.valueOf(hour) + ":" + (String.valueOf(minute) + " am"));
                            } else {
                                binding.CartLabPickTimeButton.setText("12" + ":" + (String.valueOf(minute) + " am"));
                            }
                        }

                    }
                });

                timePicker.show(getSupportFragmentManager(), "timePicker");

            }
        });

    }
    public void onStart() {
        super.onStart();
        binding.CartLabBack.setOnClickListener(v -> {
            startActivity(new Intent(this,LabTestActivity.class));
        });
        binding.CartLabCheckout.setOnClickListener(v -> {
            Intent it =new Intent(this,LabTestBookActivity.class);
            it.putExtra("price",binding.cartLabTotalPrice.getText());
            it.putExtra("date",binding.CartLabPickDateButton.getText());
            it.putExtra("time",binding.CartLabPickTimeButton.getText());
            startActivity(it);
        });
    }
}