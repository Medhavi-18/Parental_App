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

public class Health_Care extends AppCompatActivity {
    private EditText editTextHealthInfo;
    private Button buttonAddHealthInfo;
    private TextView textViewHealthInfo;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_care);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        editTextHealthInfo = findViewById(R.id.editTextHealthInfo);
        buttonAddHealthInfo = findViewById(R.id.buttonAddHealthInfo);
        textViewHealthInfo = findViewById(R.id.textViewHealthInfo);

        // Load previously stored health information
        textViewHealthInfo.setText(sharedPreferences.getString("healthInfo", ""));

        buttonAddHealthInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String healthInfo = editTextHealthInfo.getText().toString().trim();
                if (!healthInfo.isEmpty()) {
                    // Add health information with current time
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentTime = sdf.format(new Date());
                    String existingHealthInfo = textViewHealthInfo.getText().toString();
                    String newHealthInfo = existingHealthInfo + currentTime + ": " + healthInfo + "\n";
                    textViewHealthInfo.setText(newHealthInfo);
                    // Save health information to SharedPreferences
                    sharedPreferences.edit().putString("healthInfo", newHealthInfo).apply();
                    // Clear the EditText after adding health info
                    editTextHealthInfo.setText("");
                }
            }
        });
    }
}
