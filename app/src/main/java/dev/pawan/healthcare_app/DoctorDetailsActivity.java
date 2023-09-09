package dev.pawan.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import dev.pawan.healthcare_app.databinding.ActivityDoctorDetailsBinding;
import dev.pawan.healthcare_app.databinding.ActivityFindDoctorBinding;

public class DoctorDetailsActivity extends AppCompatActivity {

    private  String [][] doctors_details1 = {
            {"Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989898","900"},
            {"Doctor Name: Ajit Kumar", "Hospital Address: Kolkata", "Exp: 10yrs", "Mobile No:7815357860","800"},
            {"Doctor Name: Salini shah", "Hospital Address: Howrah", "Exp: 5yrs", "Mobile No:7898981598","600"},
            {"Doctor Name: Sonam Gupta", "Hospital Address: Ranchi", "Exp: 17yrs", "Mobile No:789223898","900"},
            {"Doctor Name: Nidhi Kumari", "Hospital Address: Dhanbad", "Exp: 3yrs", "Mobile No:7898969898","900"},

    };
    private  String [][] doctors_details2 = {
            {"Doctor Name: Abhijit Roy", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989898","900"},
            {"Doctor Name: Aman saw", "Hospital Address: Kolkata", "Exp: 10yrs", "Mobile No:7815357860","800"},
            {"Doctor Name: Arvind Kumar", "Hospital Address: Howrah", "Exp: 5yrs", "Mobile No:7898981598","600"},
            {"Doctor Name: Mohan Gupta", "Hospital Address: Ranchi", "Exp: 17yrs", "Mobile No:789223898","900"},
            {"Doctor Name: Ritika Kumari", "Hospital Address: Dhanbad", "Exp: 3yrs", "Mobile No:7898969898","900"},

    };
    private  String [][] doctors_details3 = {
            {"Doctor Name: Sulekha Kumari", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989898","900"},
            {"Doctor Name: Raj  Kumar", "Hospital Address: Kolkata", "Exp: 10yrs", "Mobile No:7815357860","800"},
            {"Doctor Name: Sunil shah", "Hospital Address: Howrah", "Exp: 5yrs", "Mobile No:7898981598","600"},
            {"Doctor Name: Sonam Gupta", "Hospital Address: Ranchi", "Exp: 17yrs", "Mobile No:789223898","900"},
            {"Doctor Name: Nidhi Kumari", "Hospital Address: Dhanbad", "Exp: 3yrs", "Mobile No:7898969898","900"},

    };
    private  String [][] doctors_details4 = {
            {"Doctor Name: kajal Soni", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989898","900"},
            {"Doctor Name: Pawan Kumar", "Hospital Address: Kolkata", "Exp: 10yrs", "Mobile No:7815357860","800"},
            {"Doctor Name: Solani shah", "Hospital Address: Howrah", "Exp: 5yrs", "Mobile No:7898981598","600"},
            {"Doctor Name: Sonam Gupta", "Hospital Address: Ranchi", "Exp: 17yrs", "Mobile No:789223898","900"},
            {"Doctor Name: Nidhi Kumari", "Hospital Address: Dhanbad", "Exp: 3yrs", "Mobile No:7898969898","900"},

    };
    private  String [][] doctors_details5 = {
            {"Doctor Name: Kishor Das", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:7898989898","900"},
            {"Doctor Name: Rahul Kumar", "Hospital Address: Kolkata", "Exp: 10yrs", "Mobile No:7815357860","800"},
            {"Doctor Name: Soni shah", "Hospital Address: Howrah", "Exp: 5yrs", "Mobile No:7898981598","600"},
            {"Doctor Name: Sangita Gupta", "Hospital Address: Ranchi", "Exp: 17yrs", "Mobile No:789223898","900"},
            {"Doctor Name: Tanya Kumari", "Hospital Address: Dhanbad", "Exp: 3yrs", "Mobile No:7898969898","900"},

    };
    String[][] doctors_details ={};

    ArrayList list;
    SimpleAdapter sa;
    HashMap<String, String> item;
ActivityDoctorDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityDoctorDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent it =getIntent();
        String title =it.getStringExtra("title");
        binding.doctorDetailstitle.setText(title);

        if (title.compareTo("Family Physicians")==0)
            doctors_details =doctors_details1;
        else
        if (title.compareTo("Dietitian")==0)
            doctors_details =doctors_details2;
        else
        if (title.compareTo("Dentist")==0)
            doctors_details =doctors_details3;
        else
        if (title.compareTo("Surgeon")==0)
            doctors_details =doctors_details4;
        else
            doctors_details =doctors_details5;

        list =new ArrayList();
        for (int i=0;i<doctors_details.length;i++){
            item= new HashMap<String,String>();
            item.put("line1",doctors_details[i][0]);
            item.put("line2",doctors_details[i][1]);
            item.put("line3",doctors_details[i][2]);
            item.put("line4",doctors_details[i][3]);
            item.put("line5", "Cons Fees "+doctors_details[i][4]+"/-");
            list.add(item);

        }
        sa =new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        binding.listViewsDD.setAdapter(sa);

        binding.listViewsDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it =new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctors_details[i][0]);
                it.putExtra("text3",doctors_details[i][1]);
                it.putExtra("text4",doctors_details[i][3]);
                it.putExtra("text5",doctors_details[i][4]);
                startActivity(it);

            }
        });

    }


    protected void onStart() {
        super.onStart();
        binding.doctorDatailsBack.setOnClickListener(v -> {
            startActivity(new Intent(this,FindDoctorActivity.class));
        });
    }

}