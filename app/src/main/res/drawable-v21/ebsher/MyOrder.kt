package com.example.ebsher

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject


class MyOrder : AppCompatActivity(){

    var id:Int ?= null
    var quantity = 0
    var price:Int ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        txtquantity.setText("" + quantity)
        txtpluse.setOnClickListener {
            txtquantity.setText("" + ++quantity)
        }

        txtminus.setOnClickListener {
            txtquantity.setText("" + --quantity)
        }

        val b: Bundle? = intent.extras
        id = b!!.getInt("id")
        price = b!!.getInt("MealPrice")
        tvtprice.text = price.toString()
        tvtmeal.setText(b.getString("MealName"))
        Picasso.with(this).load("http://192.168.1.124/AbsherControlPanel/" + b.getString("Image_Path")).into(imgeorder)

        btnbuy.setOnClickListener {
            val lis = Response.Listener<String>
            {
                response ->
                try
                {
                    val json = JSONObject(response)
                    val success:Boolean = json.getBoolean("success")
                    if(success) {
                        startActivity(Intent(this@MyOrder,Informations::class.java))
                    }
                    else
                    {
                        Toast.makeText(this,"Failed , Please Try Again ..!", Toast.LENGTH_LONG).show()
                    }

                }catch (e: JSONException)
                {
                    Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
                }
            }
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Are You Sure")
            builder.setMessage("Do You Want Confirmed")
            builder.setPositiveButton("Yes Sure",{dialogInterface: DialogInterface?, i: Int ->
                val booking:Booking = Booking(tvtmeal.text.toString(),tvtprice.text.toString(),txtquantity.text.toString(),lis)
                val request: RequestQueue = Volley.newRequestQueue(this)
                request.add(booking)
            })
            builder.setNegativeButton("Cancel",{dialogInterface: DialogInterface?, i: Int ->
                finish()
            })
            builder.show()
        }
    }

    class Booking:StringRequest
    {
        companion object
        {
            private val Url = "http://192.168.1.124/AbsherControlPanel/BookingNow.php"
        }
        private var map:MutableMap<String,String> = HashMap()
        constructor(ItemName:String,ItemPrice:String,ItemQuantity:String, listener : Response.Listener<String>)
                :super(Request.Method.POST, Url,listener,null)
        {
            map.put("ItemName",ItemName)
            map.put("ItemPrice",ItemPrice)
            map.put("ItemQuantity",ItemQuantity)
        }

        override fun getParams(): MutableMap<String, String> {
            return map
        }
    }
}
