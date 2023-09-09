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

import dev.pawan.healthcare_app.databinding.ActivityBuyMedicineBinding;
import dev.pawan.healthcare_app.databinding.ActivityHomeBinding;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Uprise-03 1000IU Capsule", "", "", "", "50"},
                    {"HealthVit Chromium Picolinate 200mcg Capsule", "", "", "", "305"},
                    {"Vitamin B Complex Capsules", "", "", "", "448"},
                    {"Inlife Vitamin E Wheat Germ Oil Capsule", "", "", "", "539"},
                    {"Crocin 650 Advance Tablet", "", "", "", "50"},
                    {"Strepsils Medicated Lozenges for Sore Throat", "", "", "", "30"},
                    {"Tata ing Calcium+ Vitamin D3", "", "", "", "30"},
                    {"Feronia -XT Tablet", "", "", "", "130"}
            };

    private String[] package_details = {

            "Building and keeping the bones & teeth strong\n" + "Reducing Fatigue/stress and muscular pains\n" +
                    "Boosting inmunity and increasing resistance against infection",
            "Chromium is an essential trace mineral that plays an important role in helping insulin regula "
                    + "Provides relief from vitamin B deficiencies\n" +
                    "Helps in formation of red blood cells\n" +
                    "Maintains healthy nervous system",
            "It promotes health as well as skin benefit.\n" +
                    "It helps reduce skin blemish and pigmentation.\n" +
                    "It act as safeguard the skin from the harsh UVA and UVB sun rays.",
            "Dolo 658 Tablet helps relieve pain and fever by blocking the release of certain chemical mess",
            "Helps relieve fever and bring down a high temperature\n" + "Suitable for people with a heart condition or high blood pressure",
            "Relieves the symptoms of a bacterial throat invection and soothes the recovery processin "
                    + "Provides a warm and comforting feeling during sore throat",
            "Reduces the risk of calcium deficiency, Rickets, and Osteoporosis\n" +
                    "Promotes mobility and flexibility of joints",
            "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron"
    };


    ActivityBuyMedicineBinding binding;

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuyMedicineBinding.inflate(getLayoutInflater());
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
        binding.listViewsBm.setAdapter(sa);

        binding.listViewsBm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it =new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);

            }
        });



    }
    public void onStart() {
        super.onStart();
        binding.bmBack.setOnClickListener(v -> {
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        });
        binding.bmGtCart.setOnClickListener(v -> {
            startActivity(new Intent(this,CartBuyMedicineActivity.class));
            finish();
        });

    }
}