package com.example.ebsher

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class Informations : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informations)

        val sendinfo = findViewById(R.id.btnnote) as Button
        sendinfo.setOnClickListener {
            val donnne = Response.Listener<String>
            {
                response ->
                try
                {
                    val json = JSONObject(response)
                    val success:Boolean = json.getBoolean("Information")
                    if(success)
                    {
                        Toast.makeText(this, "The Order Will be Delivered in 25 Minutes", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@Informations,Resturants::class.java))
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
            val send:Send = Send(edfname.text.toString(),edphone.text.toString(),ednote.text.toString(),donnne)
            val request: RequestQueue = Volley.newRequestQueue(this)
            request.add(send)
        }
    }
    class Send: StringRequest
    {
        companion object
        {
            private val Url = "http://192.168.1.124/AbsherControlPanel/SubmitInformation.php"
        }
        private var map:MutableMap<String,String> = HashMap()
        constructor(FullName:String,PhoneNumber:String,Note_Col:String, listener : Response.Listener<String>)
                :super(Request.Method.POST, Url,listener,null)
        {
            map.put("FullName",FullName)
            map.put("PhoneNumber",PhoneNumber)
            map.put("Note_Col",Note_Col)
        }
        override fun getParams(): MutableMap<String, String> {
            return map
        }
    }
}
