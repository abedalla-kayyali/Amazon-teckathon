package com.example.deliveryhouse

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_my_orders.*
import org.json.JSONException
import org.json.JSONObject

class MyOrders : AppCompatActivity() {

    var idd:Int ?= null
    var quantity = 5
    var price:Int ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)

        txtquantity.setText("" + quantity)
        txtpluse.setOnClickListener {
            txtquantity.setText("" + ++quantity)
        }

        txtminus.setOnClickListener {
            txtquantity.setText("" + --quantity)
        }

        val _SessionName = findViewById(R.id.mysession) as TextView
        _SessionName.setText(SharedPrefManager.getInstance(this).userEmail)

        val b: Bundle? = intent.extras
        idd = b!!.getInt("id")
        price = b!!.getInt("TypeFood")
        tvtprice.text = price.toString()
        tvtmeal.setText(b.getString("NameRestaurant"))
        Picasso.with(this).load("http://192.168.0.110/DeliveryHouse/dashboard/" + b.getString("Image_Path")).into(imgeorder)
        btnbuy.setOnClickListener {
            val lis = Response.Listener<String>
            {
                response ->
                try
                {
                    val json = JSONObject(response)
                    val success:Boolean = json.getBoolean("success")
                    if(success) {
                        startActivity(Intent(this@MyOrders,Restaurants::class.java))
                    }
                    else
                    {
                        Toast.makeText(this,"Failed , Please Try Again ..!", Toast.LENGTH_LONG).show()
                    }

                }catch (e: JSONException)
                {
                    Toast.makeText(this,e.toString(), Toast.LENGTH_LONG).show()
                }
            }
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Are You Sure")
            builder.setMessage("Do You Want Confirmed")
            builder.setPositiveButton("Yes Sure",{ dialogInterface: DialogInterface?, i: Int ->
                val booking:Booking = Booking(tvtmeal.text.toString(),tvtprice.text.toString(),txtquantity.text.toString(),mysession.text.toString(),lis)
                val request: RequestQueue = Volley.newRequestQueue(this)
                request.add(booking)
            })
            builder.setNegativeButton("Cancel",{ dialogInterface: DialogInterface?, i: Int ->
                finish()
            })
            builder.show()
        }
    }

    class Booking: StringRequest
    {
        companion object
        {
            private val Url = "http://192.168.0.110/DeliveryHouse/BookingNow.php"
        }
        private var map:MutableMap<String,String> = HashMap()
        constructor(ItenName:String,Pricing:String,Quantity:String,pincode:String, listener : Response.Listener<String>)
                :super(Request.Method.POST, Url,listener,null)
        {
            map.put("ItenName",ItenName)
            map.put("Pricing",Pricing)
            map.put("Quantity",Quantity)
            map.put("pincode",pincode)
        }
        override fun getParams(): MutableMap<String, String> {
            return map
        }
    }
}