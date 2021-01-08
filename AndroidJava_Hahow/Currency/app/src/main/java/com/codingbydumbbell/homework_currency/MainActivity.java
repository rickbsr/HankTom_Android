package com.codingbydumbbell.homework_currency;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private float exchangeRatio_NTDUSD = 30.9f;
    private EditText edNtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews() {

        edNtd = findViewById(R.id.ntd);
    }

    public void go(View view) {

        String ntd = edNtd.getText().toString();

        if (!ntd.isEmpty()) {

            String usd = String.format("%.4f", Float.parseFloat(ntd) / exchangeRatio_NTDUSD);

            new AlertDialog.Builder(this)
                    .setTitle(R.string.result)
                    .setMessage(getString(R.string.usd_is) + usd)
                    .setPositiveButton(R.string.ok, null)
                    .show();
        } else {

            new AlertDialog.Builder(this)
                    .setTitle(R.string.problem)
                    .setMessage(R.string.please_enter_ntd)
                    .setPositiveButton(R.string.ok, null)
                    .show();
        }
    }
}
