package com.codingbydumbbell.app_bmi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView result = findViewById(R.id.result);
        float bmi = getIntent().getFloatExtra("BMI", 0);
        result.setText(getString(R.string.your_bmi_is) + bmi);
    }
}
