package com.example.parents_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMeal;
    private Button buttonAddMeal;
    private TextView textViewMealSchedule;
    private EditText editTextPolioDoseDate;
    private Button buttonAddPolioDose;
    private TextView textViewPolioDose;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        editTextMeal = findViewById(R.id.editTextMeal);
        buttonAddMeal = findViewById(R.id.buttonAddMeal);
        textViewMealSchedule = findViewById(R.id.textViewMealSchedule);
        editTextPolioDoseDate = findViewById(R.id.editTextPolioDoseDate);
        buttonAddPolioDose = findViewById(R.id.buttonAddPolioDose);
        textViewPolioDose = findViewById(R.id.textViewPolioDose);

        // Load previously stored meal schedule and polio dose dates
        textViewMealSchedule.setText(sharedPreferences.getString("mealSchedule", ""));
        textViewPolioDose.setText(sharedPreferences.getString("polioDose", ""));

        buttonAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String meal = editTextMeal.getText().toString().trim();

                // Append meal to the schedule with current time
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(new Date());
                String mealSchedule = textViewMealSchedule.getText().toString();
                mealSchedule += currentTime + ": " + meal + "\n";
                textViewMealSchedule.setText(mealSchedule);
                // Save meal schedule to SharedPreferences
                sharedPreferences.edit().putString("mealSchedule", mealSchedule).apply();
                // Clear the EditText after adding meal
                editTextMeal.setText("");
            }
        });

        buttonAddPolioDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String polioDoseDate = editTextPolioDoseDate.getText().toString().trim();
                if (!polioDoseDate.isEmpty()) {
                    // Add Polio Dose Date with current time
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentTime = sdf.format(new Date());
                    String polioDose = textViewPolioDose.getText().toString();
                    polioDose += currentTime + ": Polio Dose Date: " + polioDoseDate + "\n";
                    textViewPolioDose.setText(polioDose);
                    // Save polio dose date to SharedPreferences
                    sharedPreferences.edit().putString("polioDose", polioDose).apply();
                    // Clear the EditText after adding the polio dose date
                    editTextPolioDoseDate.setText("");
                }
            }
        });
    }

}
