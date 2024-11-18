package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextHeight, editTextWeight;
    private Button buttonCalculate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        // Set button click listener
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        // Get input values
        String heightStr = editTextHeight.getText().toString();
        String weightStr = editTextWeight.getText().toString();

        // Check if inputs are valid
        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both height and weight", Toast.LENGTH_SHORT).show();
            return;
        }

        float height = Float.parseFloat(heightStr);
        float weight = Float.parseFloat(weightStr);

        // Check for valid height
        if (height <= 0) {
            Toast.makeText(this, "Height should be greater than 0", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate BMI
        float bmi = weight / (height * height);

        // Determine BMI category
        String bmiCategory;
        if (bmi < 18.5) {
            bmiCategory = "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            bmiCategory = "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            bmiCategory = "Overweight";
        } else {
            bmiCategory = "Obese";
        }

        // Display result
        String result = String.format("BMI: %.2f\nCategory: %s", bmi, bmiCategory);
        textViewResult.setText(result);
    }
}
