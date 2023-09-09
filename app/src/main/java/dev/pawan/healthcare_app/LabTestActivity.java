package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import dev.pawan.healthcare_app.databinding.ActivityLabTestBinding;

public class LabTestActivity extends AppCompatActivity {
    ActivityLabTestBinding binding;

    private String[][] packages =
            {
                    {"Package 1: Full Body Checkup", "", "", "", "999"},
                    {"Package 2: Blood Glucose Fasting", "", "", "", "5652"},
                    {"Package 3: COVID-19 Antibody IgG", " ", "", "", "656"},
                    {"Package 4: Thyroid Check", "", "", "", "499"},
                    {"Package 5: Immunity Check", " ", "", "", "699"},
            };
    private String[] package_details =
            {
            "Blood Glucose Fasting\n"+
                "Complete Hemogram\n"+
                "HbA1c\n"+
                "Iron Studies \n"+
                "Kidney Function Test\n"+
                    "LDH Lactate Dehydrogenase, Serum\n"+
                    "Lipid Profile\n"+
                    "Liver Function Test",
            "Blood Glucose Fasting",
            "COVID-19 Antibody - IgG",
            "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
            "Complete Hemogram\n"+
                    "CRP (C Reactive Protein) Quantitative, Serumin"+
                    "Iron Studies\n" +
                    "Kidney Function Test\n"+
                    "Vitamin D Total-25 Hydroxy\n"+
            "Liver Function Testin"+
            "Lipid Profile"
            };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLabTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
            binding.listViewsLabTest.setAdapter(sa);

            binding.listViewsLabTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                    Intent it =new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                    it.putExtra("text1",packages[i][0]);
                    it.putExtra("text2",package_details[i]);
                    it.putExtra("text3",packages[i][4]);
                    startActivity(it);

                }
            });
    }
    protected  void  onStart(){
        super.onStart();
        binding.labTestBack.setOnClickListener(v ->  {
            startActivity(new Intent(this,HomeActivity.class));
        });
        binding.labTestGtCart.setOnClickListener(v ->  {
            startActivity(new Intent(this,CartLabActivity.class));

        });
    }
}