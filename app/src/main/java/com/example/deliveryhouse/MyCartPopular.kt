package com.example.deliveryhouse

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_my_cart_popular.*
import kotlinx.android.synthetic.main.database_cart_popular.view.*
import org.json.JSONException
import org.json.JSONObject

class MyCartPopular : AppCompatActivity() {

    var vol: RequestQueue?= null
    private val url = "http://192.168.0.110/DeliveryHouse/ViewCartPopular.php?CustomerSession="+SharedPrefManager.getInstance(this).userEmail+""
    var arrraa = ArrayList<DatabasePopular>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart_popular)

        val mycodename = findViewById(R.id.myname) as TextView
        mycodename.setText(SharedPrefManager.getInstance(this).userEmail)

        vol = Volley.newRequestQueue(this)

        val jess = JsonObjectRequest(Request.Method.GET,url,Response.Listener <JSONObject> {
            response ->
            try {
                val j = response.getJSONArray("alldata")
                for (i in 0 until j.length())
                {
                    val js = j.getJSONObject(i)
                    val id = js.getInt("id")
                    val ItemOne = js.getString("ItemOne")
                    val ItemTwo = js.getString("ItemTwo")
                    val ItemThree = js.getString("ItemThree")
                    val ItemFour = js.getString("ItemFour")
                    val ItemFive = js.getString("ItemFive")
                    val ItemSix = js.getString("ItemSix")
                    val ItemSeven = js.getString("ItemSeven")
                    val ItemEight = js.getString("ItemEight")
                    val CustomerSession = js.getString("CustomerSession")

                    arrraa.add(DatabasePopular(id,ItemOne,ItemTwo, ItemThree, ItemFour, ItemFive, ItemSix, ItemSeven, ItemEight, CustomerSession))
                    val a = cartPopular(arrraa,this)
                    lv_cart.adapter = a

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

        vol!!.add(jess)
    }

    class cartPopular:BaseAdapter
    {
        var adaa = ArrayList<DatabasePopular>()
        var contexxt:Context ?= null
        constructor(arr:ArrayList<DatabasePopular>,con:Context)
        {
            adaa = arr
            contexxt = con
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val cc = adaa[position]
            val inff = contexxt!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val ff = inff.inflate(R.layout.database_cart_popular,null)

            val one = cc.ItemOne
            val two = cc.ItemTwo
            val three = cc.ItemThree
            val four = cc.ItemFour
            val five = cc.ItemFive
            val six = cc.ItemSix
            val seven = cc.ItemSeven
            val eight = cc.ItemEight

            ff.txtPopOne.text = one
            ff.txtPopTwo.text = two
            ff.txtPopThree.text = three
            ff.txtPopFour.text = four
            ff.txtPopFive.text = five
            ff.txtPopSix.text = six
            ff.txtPopSeven.text = seven
            ff.txtPopEight.text = eight

            ff.imagePopremove.setOnClickListener {
                val lis = Response.Listener<String> { response ->
                    try {
                        val json = JSONObject(response)
                        val success: Boolean = json.getBoolean("success")

                        if (success) {
                            val int = Intent(contexxt!!, MyCartPopular::class.java)
                            contexxt!!.startActivity(int)
                        } else {
                            Toast.makeText(contexxt!!, "Failed Try Again", Toast.LENGTH_LONG).show()
                        }
                    } catch (e: JSONException) {
                        Toast.makeText(contexxt!!, e.toString(), Toast.LENGTH_LONG).show()
                    }
                }
                val deleteData:DeleteData = DeleteData(cc.id, lis)
                val request:RequestQueue = Volley.newRequestQueue(contexxt!!)
                request.add(deleteData)
            }

            return ff
        }

        override fun getItem(position: Int): Any {
            return adaa[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return adaa.size
        }
    }

    class DeleteData: StringRequest
    {
        companion object
        {
            private val Url = "http://192.168.0.110/DeliveryHouse/deletePop.php"
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
