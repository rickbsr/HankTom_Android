package com.codingbydumbbell.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "secret: " + secretNumber.secret)
    }

    // onClick 屬性的對應方法
    fun check(view: View) {
//        resources.getString(R.string.yes_you_got_it) // 因為取得字串很使用，所以可以省略 getResources()
        val n = number.text.toString().toInt()
        println("number: $n")
        Log.d(TAG, "number: " + n)
        val diff = secretNumber.validate(n)
        var message = getString(R.string.yes_you_got_it)
        if (diff < 0) {
            message = getString(R.string.bigger)
        } else if (diff > 0) {
            message = getString(R.string.smaller)
        }
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_tilte))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }
}
