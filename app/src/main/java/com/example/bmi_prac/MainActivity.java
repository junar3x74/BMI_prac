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

        EditText bmiHeight = findViewById(R.id.height);
        EditText bmiWeight = findViewById(R.id.weight);
        Button calculateResults = findViewById(R.id.calculate_button);
        Button eraseButton = findViewById(R.id.erase);
        TextView resultsBmi = findViewById(R.id.results);

        calculateResults.setOnClickListener(v -> {
            String heightStr = bmiHeight.getText().toString().trim();
            String weightStr = bmiWeight.getText().toString().trim();

            if (heightStr.isEmpty() || weightStr.isEmpty()) {
                showToast("Please fill in both fields.");
                return;
            }

            try {
                double height = Double.parseDouble(heightStr);
                double weight = Double.parseDouble(weightStr);


                if (height > 10) {
                    showToast("Height seems too large. Use meters, e.g., 1.75.");
                    return;
                }

                double bmi = weight / (height * height);


                StringBuilder result = new StringBuilder("Your BMI is " + String.format("%.2f", bmi) + "\n");

                if (bmi < 16.0) {
                    result.append("You are severely malnourished.");
                } else if (bmi < 18.5) {
                    result.append("You are underweight.");
                } else if (bmi < 24.9) {
                    result.append("You have a normal weight.");
                } else if (bmi < 29.9) {
                    result.append("You are overweight.");
                } else {
                    result.append("You are obese.");
                }

                resultsBmi.setText(result.toString());

            } catch (NumberFormatException e) {
                showToast("Invalid input. Please enter numeric values.");
            }
        });

        eraseButton.setOnClickListener(v -> {
            bmiHeight.setText("");
            bmiWeight.setText("");
            resultsBmi.setText("");
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
