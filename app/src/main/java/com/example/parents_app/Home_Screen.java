package com.example.parents_app;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home_Screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button buttonMealPlanner = findViewById(R.id.buttonMealPlanner);
        Button buttonHealthDiary = findViewById(R.id.buttonHealthDiary);
        Button buttonExit = findViewById(R.id.buttonExit);

        buttonMealPlanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Screen.this, MainActivity.class));
            }
        });

        buttonHealthDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Screen.this, Health_Care.class));
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
