package com.example.deliveryhouse

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_my_cart.*
import kotlinx.android.synthetic.main.activity_my_cart.view.*
import kotlinx.android.synthetic.main.database_cart.view.*
import org.json.JSONException
import org.json.JSONObject

class MyCart : AppCompatActivity() {

    var vol: RequestQueue?= null
    private val url = "http://192.168.0.110/DeliveryHouse/ViewCart.php?pincode="+SharedPrefManager.getInstance(this).userEmail+""
    var arrrrrr = ArrayList<DatabaseCart>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart)

        val mycodename = findViewById(R.id.mycodename) as TextView
        mycodename.setText(SharedPrefManager.getInstance(this).userEmail)

        val sensor = findViewById(R.id.idsensor) as WebView
        sensor.loadUrl("https://www.Google.com")

        val OtherOrder = findViewById(R.id.OtherOrder) as Button
        OtherOrder.setOnClickListener {
            val int = Intent(this,MyCartPopular::class.java)
            startActivity(int)
        }

        val mycode = findViewById(R.id.checkOrder) as Button
        mycode.setOnClickListener {
            val lisss = Response.Listener<String> { response ->
                try {
                    val json = JSONObject(response)
                    val success: Boolean = json.getBoolean("success")

                    if (success) {
                        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                        val int = Intent(this, PayPalPayment::class.java)
                        startActivity(int)
                    } else {
                        Toast.makeText(this, "Failed Try Again", Toast.LENGTH_LONG).show()
                    }
                } catch (e: JSONException) {
                    val int = Intent(this, PayPalPayment::class.java)
                    startActivity(int)
                }
            }

            val sendCode:SendPincode = SendPincode(mycodename.text.toString(),lisss)
            val request:RequestQueue = Volley.newRequestQueue(this)
            request.add(sendCode)
        }

        vol = Volley.newRequestQueue(this)
        val jes = JsonObjectRequest(Request.Method.GET,url,Response.Listener {
            response ->
            try {
                val j = response.getJSONArray("alldata")
                for (i in 0 until j.length())
                {
                    val js = j.getJSONObject(i)
                    val id = js.getInt("id")
                    val ItenName = js.getString("ItenName")
                    val Pricing = js.getString("Pricing")
                    val Quantity = js.getString("Quantity")
                    val pincode = js.getString("pincode")

                    arrrrrr.add(DatabaseCart(id,ItenName,Pricing,Quantity,pincode))
                    val a = cart(arrrrrr,this)
                            listcart.adapter = a
                }
            }
            catch (e:JSONException)
            {
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
            }
        },Response.ErrorListener {
            error ->
            Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
        })

        vol!!.add(jes)
    }
    
    class cart:BaseAdapter
    {
        var addda = ArrayList<DatabaseCart>()
        var context: Context ?= null
        constructor(arr:ArrayList<DatabaseCart>,conn:Context)
        {
            addda = arr
            context = conn
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val c = addda[position]
            val inff = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val ff = inff.inflate(R.layout.database_cart,null)

            val namePro = c.ItenName
            val PricPro = "JD " + c.Pricing
            val Quantity = "Quantity " + c.Quantity

            ff.txtmeals.text = namePro
            ff.txtpricing.text = PricPro
            ff.txtquantity.text = Quantity

            ff.imageremove.setOnClickListener {
                val lis = Response.Listener<String> { response ->
                    try {
                        val json = JSONObject(response)
                        val success: Boolean = json.getBoolean("success")

                        if (success) {
                            val int = Intent(context!!, MyCart::class.java)
                            context!!.startActivity(int)
                        } else {
                            Toast.makeText(context!!, "Failed Try Again", Toast.LENGTH_LONG).show()
                        }
                    } catch (e: JSONException) {
                        Toast.makeText(context!!, e.toString(), Toast.LENGTH_LONG).show()
                    }
                }
                val sendData:SendData = SendData(c.id, lis)
                val request:RequestQueue = Volley.newRequestQueue(context!!)
                request.add(sendData)
            }

            return ff
        }

        override fun getItem(position: Int): Any {
            return addda[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return addda.size
        }
    }

    class SendPincode: StringRequest
    {
        companion object
        {
            private val PathUrl = "http://192.168.0.110/DeliveryHouse/PinCode.php"
        }
        private var mappp:MutableMap<String,String> = HashMap()
        constructor(CustomerName:String,listenet:Response.Listener<String>)
                :super(Request.Method.POST, PathUrl,listenet,null)
        {
            mappp.put("CustomerName",CustomerName)

        }

        override fun getParams(): MutableMap<String, String> {
            return mappp
        }
    }

    class SendData: StringRequest
    {
        companion object
        {
            private val Url = "http://192.168.0.110/DeliveryHouse/delete.php"
        }
        private var map:MutableMap<String,String> = HashMap()
        constructor(id:Int,listenet:Response.Listener<String>)
                :super(Request.Method.POST, Url,listenet,null)
        {
            map.put("id",id.toString())
        }

        override fun getParams(): MutableMap<String, String> {
            return map
        }
    }
}
