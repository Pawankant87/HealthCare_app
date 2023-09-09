package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import dev.pawan.healthcare_app.databinding.ActivityDoctorDetailsBinding;
import dev.pawan.healthcare_app.databinding.ActivityHealthArticlesBinding;

public class HealthArticlesActivity extends AppCompatActivity {
    private String[][] health_details =
            {
                    {"Walking Daily", "", " ","", "Click More Details"},
                    {"Home care of COVID-19", "", " ","", "Click More Details"},
                    {"Stop Smoking", "", " ", " ","Click More Details"},
                    {"Menstrual Cramps", "", " ","", "Click More Details"},
                    {"Healthy Gut", "", " ","", "Click More Details"},
            };
    private  int[] image ={
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5,
    };
    HashMap<String ,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ActivityHealthArticlesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHealthArticlesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list =new ArrayList();
        for (int i=0;i<health_details.length;i++){
            item= new HashMap<String,String>();
            item.put("line1",health_details[i][0]);
            item.put("line2",health_details[i][1]);
            item.put("line3",health_details[i][2]);
            item.put("line4",health_details[i][3]);
            item.put("line5",health_details[i][4]);
            list.add(item);

        }
        sa =new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        binding.healthArticlesList.setAdapter(sa);
        binding.healthArticlesList.setOnItemClickListener((parent, view, i, l) -> {
            Intent it =new Intent(HealthArticlesActivity.this,HealthArticlesDetailsActivity.class);
            it.putExtra("text1",health_details[i][0]);
            it.putExtra("text2",image[i]);
            startActivity(it);
        });
    }
    protected void onStart() {
        super.onStart();
        binding.healthArticlesBack.setOnClickListener(v -> {
            startActivity(new Intent(this,HomeActivity.class));
        });
    }
}