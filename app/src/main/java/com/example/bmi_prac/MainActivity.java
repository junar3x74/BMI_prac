package com.example.bmi_prac;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText bmi_height = findViewById(R.id.height);
        EditText bmi_weight = findViewById(R.id.weight);
        Button caculate_results = findViewById(R.id.calculate_button);
        TextView results_bmi = findViewById(R.id.results);

        caculate_results.setOnClickListener(v ->{
            String height = bmi_height.getText().toString();
            String weight = bmi_weight.getText().toString();

            if(height.isEmpty() || weight.isEmpty()){
                showtoast("Enter fields");
            }






            double h = Double.parseDouble(height);
            double w = Double.parseDouble(weight);

            double bmi = w / (h * h);

            String result = "Your Bmi is " + String.format("%.2f" , bmi) + "\n";
            if(bmi <18.8){
                result += "you are underwieght";
            } else if (bmi <24.9) {
                result+= "you have a normal weight";
            } else if (bmi <29.9) {
                result+= "you are overwieght";
            }else {
                result+="you are obese";
            }

            results_bmi.setText(result);

        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
    private void showtoast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }


}