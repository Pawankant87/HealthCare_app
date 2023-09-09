package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import dev.pawan.healthcare_app.databinding.ActivityLabTestDetailsBinding;
import dev.pawan.healthcare_app.databinding.ActivityOrderDetailsBinding;

public class OrderDetailsActivity extends AppCompatActivity {

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    private  String[][] order_details ={};
    ActivityOrderDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String userFullName =sharedPreferences.getString("userFullName","").toString();
        Database db =new Database(getApplicationContext(),"healthcare",null,1);
        ArrayList dbData =db.getOrderData(userFullName);

        order_details =new String[dbData.size()][];
        for (int i=0;i<order_details.length;i++){
            order_details[i]=new String[5];
            String arrData =dbData.get(i).toString();
            String[] strData =arrData.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0] = strData[1];
            order_details[i][1] = "Mobile: "+strData[2]+ "\nAddress:  " +strData[3];
            if (strData[strData.length-1].compareTo("medicine")==0){
                order_details[i][3] ="Date: "+ strData[5];
            }else {
                order_details[i][3] ="Date: "+ strData[5]+" "+ strData[6];
            }
            order_details[i][2] ="Rs.: "+ strData[7];
            order_details[i][4] = strData[8];
        }
        list =new ArrayList();
        for (int i=0;i<order_details.length;i++){
            item= new HashMap<String,String>();
            item.put("line1",order_details[i][0]);
            item.put("line2",order_details[i][1]);
            item.put("line3",order_details[i][2]);
            item.put("line4",order_details[i][3]);
            item.put("line5",order_details[i][4]);
            list.add(item);

        }
        sa =new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        binding.listViewsOrderDetails.setAdapter(sa);



    }
    public void onStart() {
        super.onStart();
        binding.orderDetailsBack.setOnClickListener(v -> {
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        });
    }
}