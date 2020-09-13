package com.example.deliveryhouse

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_my_meals.*
import org.json.JSONException
import org.json.JSONObject

class MyMeals : AppCompatActivity() {

    var id:Int ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_meals)

        counterAll()

        val _SessionName = findViewById(R.id.mysession) as TextView
        _SessionName.setText(SharedPrefManager.getInstance(this).userEmail)

        val b:Bundle ?= intent.extras
        id = b!!.getInt("id")
        NameMeal.setText(b.getString("NameMeal"))
        Picasso.with(this).load("http://192.168.0.110/DeliveryHouse/dashboard/"+ b.getString("Image_Path")).into(imgeMeal)

        btn_buy.setOnClickListener {
            val lis = Response.Listener<String> {
                response ->
                try {
                    val json = JSONObject(response)
                    val success:Boolean = json.getBoolean("success")

                    if(success)
                    {
                        startActivity(Intent(this@MyMeals,Restaurants::class.java))
                    }
                    else
                    {
                        Toast.makeText(this,"Failed Try Again",Toast.LENGTH_LONG).show()
                    }
                }
                catch (e:JSONException)
                {
                    Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
                }
            }
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Are You Sure")
            builder.setMessage("Do You Want Confirmed")
            builder.setPositiveButton("Yes Sure",{ dialogInterface: DialogInterface?, i: Int ->
            val sendData:SendData = SendData(txtcheckFreezing.text.toString(),txtcheckpistachio.text.toString(),txtcheckoil.text.toString(),txtcheckRice.text.toString(),txtcheckMeat.text.toString(),txtcheckMilk.text.toString(),txtcheckMaggie.text.toString(),txtcheckchickens.text.toString(),mysession.text.toString(),lis)
            val request:RequestQueue = Volley.newRequestQueue(this)
            request.add(sendData)
            })
            builder.setNegativeButton("Cancel",{ dialogInterface: DialogInterface?, i: Int ->
                finish()
            })
            builder.show()
        }
    }

    class SendData:StringRequest
    {
        companion object
        {
            private val Url = "http://192.168.0.110/DeliveryHouse/BookingOrderPopular.php"
        }
        private var map:MutableMap<String,String> = HashMap()
        constructor(ItemOne:String,ItemTwo:String,ItemThree:String,ItemFour:String,ItemFive:String,ItemSix:String,ItemSeven:String,ItemEight:String,CustomerSession:String, listenet:Response.Listener<String>)
        :super(Request.Method.POST, Url,listenet,null)
        {
            map.put("ItemOne",ItemOne)
            map.put("ItemTwo",ItemTwo)
            map.put("ItemThree",ItemThree)
            map.put("ItemFour",ItemFour)
            map.put("ItemFive",ItemFive)
            map.put("ItemSix",ItemSix)
            map.put("ItemSeven",ItemSeven)
            map.put("ItemEight",ItemEight)
            map.put("CustomerSession",CustomerSession)
        }

        override fun getParams(): MutableMap<String, String> {
            return map
        }
    }

    fun counterAll()
    {
        val checkchickens = findViewById(R.id.checkchickens) as CheckBox
        val checkMaggie = findViewById(R.id.checkMaggie) as CheckBox
        val checkMilk = findViewById(R.id.checkMilk) as CheckBox
        val checkMeat = findViewById(R.id.checkMeat) as CheckBox
        val checkRice = findViewById(R.id.checkRice) as CheckBox
        val checkoil = findViewById(R.id.checkoil) as CheckBox
        val checkpistachio = findViewById(R.id.checkpistachio) as CheckBox
        val checkFreezing = findViewById(R.id.checkFreezing) as CheckBox

        checkchickens.setOnClickListener {
            txtcheckchickens.text = checkchickens.text.toString()
        }
        checkMaggie.setOnClickListener {
            txtcheckMaggie.text = checkMaggie.text.toString()
        }
        checkMilk.setOnClickListener {
            txtcheckMilk.text = checkMilk.text.toString()
        }
        checkMeat.setOnClickListener {
            txtcheckMeat.text = checkMeat.text.toString()
        }
        checkRice.setOnClickListener {
            txtcheckRice.text = checkRice.text.toString()
        }
        checkoil.setOnClickListener {
            txtcheckoil.text = checkoil.text.toString()
        }
        checkpistachio.setOnClickListener {
            txtcheckpistachio.text = checkpistachio.text.toString()
        }
        checkFreezing.setOnClickListener {
            txtcheckFreezing.text = checkFreezing.text.toString()
        }
    }
}