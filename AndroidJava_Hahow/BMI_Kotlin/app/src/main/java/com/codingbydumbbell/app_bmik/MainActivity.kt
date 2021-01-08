package com.codingbydumbbell.app_bmik

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        help.setOnClickListener {
            AlertDialog.Builder(this@MainActivity)
                    .setMessage("The body mass index (BMI) or Quetelet index is a value derived from the mass (weight) and height of an individual")
                    .setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->
                        Toast.makeText(this, "Testing", Toast.LENGTH_LONG).show()
                    }.show()
        }
        bmi.setOnClickListener {
            val w = weight.text.toString().toFloat()
            val h = height.text.toString().toFloat()
            val bmi = w / (h * h)
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("BMI", bmi)
            startActivity(intent)
        }
    }
}
