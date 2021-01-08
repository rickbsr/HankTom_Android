package com.codingbydumbbell.app_bmik

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val bmi = intent.getFloatExtra("BMI", 0f);
        result.text = "Your BMI is $bmi"
    }
}
