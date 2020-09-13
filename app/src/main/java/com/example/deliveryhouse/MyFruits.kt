package com.example.deliveryhouse

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_my_fruits.*
import org.json.JSONException
import org.json.JSONObject

class MyFruits : AppCompatActivity() {

    var id:Int ?= null
    var quant = 1
    var Price:Double ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_fruits)

        txtfruitsquantity.setText("" + quant)
        txtfruitspluse.setOnClickListener {
            txtfruitsquantity.setText("" + ++quant)
        }

        txtfruitsquantity.setText("" + quant)
        txtfruitsminus.setOnClickListener {
            txtfruitsquantity.setText("" + --quant)
        }

        val _SessionName = findViewById(R.id.fruitsmysession) as TextView
        _SessionName.setText(SharedPrefManager.getInstance(this).userEmail)

        val b:Bundle ?= intent.extras
        id = b!!.getInt("id")
        Price = b!!.getDouble("ItemPrice")
        tvtfruitsprice.text = Price.toString()
        tvtfruitsname.setText(b.getString("ItemName"))
        Picasso.with(this).load("http://192.168.0.110/DeliveryHouse/dashboard/"+ b.getString("Image_Path")).into(imgefruit)

        val btnsubmit = findViewById(R.id.btnfruitsbuy) as Button
        btnsubmit.setOnClickListener {
            val lis = Response.Listener<String> { response ->
                try {
                    val json = JSONObject(response)
                    val success: Boolean = json.getBoolean("success")

                    if (success) {
                        startActivity(Intent(this@MyFruits, Restaurants::class.java))
                    } else {
                        Toast.makeText(this, "Failed Try Again", Toast.LENGTH_LONG).show()
                    }
                } catch (e: JSONException) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
                }
            }
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Are You Sure")
            builder.setMessage("Do You Want Confirmed")
            builder.setPositiveButton("Yes Sure",{ dialogInterface: DialogInterface?, i: Int ->

            val sendFruits:SendFruits = SendFruits(tvtfruitsname.text.toString(),tvtfruitsprice.text.toString(),txtfruitsquantity.text.toString(),fruitsmysession.text.toString(),lis)
            val request: RequestQueue = Volley.newRequestQueue(this)
            request.add(sendFruits)
            })
            builder.setNegativeButton("Cancel",{ dialogInterface: DialogInterface?, i: Int ->
                finish()
            })
            builder.show()
        }
    }

    class SendFruits:StringRequest
    {
        companion object
        {
            private val Url = "http://192.168.0.110/DeliveryHouse/BookingNow.php"
        }
        private var map:MutableMap<String,String> = HashMap()
        constructor(ItenName:String, Pricing:String, Quantity:String,pincode:String, listenet:Response.Listener<String>)
                :super(Request.Method.POST, Url,listenet,null)
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
