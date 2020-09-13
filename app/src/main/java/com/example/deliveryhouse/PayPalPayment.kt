package com.example.deliveryhouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class PayPalPayment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_pal_payment)

        val btnPaypal = findViewById(R.id.btnPaypal) as Button
        btnPaypal.setOnClickListener {
            Toast.makeText(this,"Sorry this Service Not Available",Toast.LENGTH_LONG).show()

            val goback = Intent(this@PayPalPayment,MyCart::class.java)
            startActivity(goback)
        }
    }
}
