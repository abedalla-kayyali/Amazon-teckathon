package com.example.deliveryhouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_contactus.*
import org.json.JSONObject

class ContactUs : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        val btnMessage = findViewById(R.id.btnsend) as Button
        btnMessage.setOnClickListener {
            val sendMes = Response.Listener <String> {
                response ->
                try {
                    val json = JSONObject(response)
                    val success:Boolean = json.getBoolean("Message")

                    if(success)
                    {
                        Toast.makeText(this,"Success , Thank You .!",Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@ContactUs,ContactUs::class.java))
                    }
                    else
                    {
                        Toast.makeText(this,"Failed , Please Try Again ..!",Toast.LENGTH_LONG).show()
                    }
                }
                catch (e:Exception)
                {
                    Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
                }
            }

            val send:Message = Message(edttxtuser.text.toString(),edttxtmail.text.toString(),edttxtmessage.text.toString(),sendMes)
            val request:RequestQueue = Volley.newRequestQueue(this)
            request.add(send)
        }
    }

   class Message:StringRequest
   {
       companion object
       {
           private val Url = "http://192.168.0.110/DeliveryHouse/SubmitMessage.php"
       }

       private var map:MutableMap<String,String> = HashMap()
       constructor(txt_username:String,txt_email:String,txt_comment:String, listener : Response.Listener<String>)
       :super(Request.Method.POST, Url,listener,null)
       {
           map.put("txt_username",txt_username)
           map.put("txt_email",txt_email)
           map.put("txt_comment",txt_comment)
       }

       override fun getParams(): MutableMap<String, String> {
           return map
       }
   }

   override fun onBackPressed() {
            val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_addtocart -> {
                val intent = Intent(this@ContactUs, MyCart::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

        override fun onNavigationItemSelected(p0: MenuItem): Boolean {
            // Handle navigation view item clicks here.
            when (p0.itemId) {
                R.id.nav_slideshow -> {
                    val intent = Intent(this@ContactUs, Home::class.java)
                    startActivity(intent)
                }
                R.id.nav_restaurants -> {
                    val intent = Intent(this@ContactUs, Restaurants::class.java)
                    startActivity(intent)
                }
                R.id.nav_popular -> {
                    val intent = Intent(this@ContactUs, FastFood::class.java)
                    startActivity(intent)
                }
                R.id.nav_fruits -> {
                    val intent = Intent(this@ContactUs, Fruits::class.java)
                    startActivity(intent)
                }
                R.id.nav_meats -> {
                    val intent = Intent(this@ContactUs, Meats::class.java)
                    startActivity(intent)
                }
                R.id.nav_cart -> {
                    val intent = Intent(this@ContactUs, MyCart::class.java)
                    startActivity(intent)
                }
                R.id.nav_send -> {
                    val intent = Intent(this@ContactUs, ContactUs::class.java)
                    startActivity(intent)
                }
                R.id.nav_profile -> {
                    val intent = Intent(this@ContactUs, MyProfile::class.java)
                    startActivity(intent)
                }
                R.id.nav_logout -> {
                    SharedPrefManager.getInstance(this).logout()
                    finish()
                    startActivity(Intent(this, Login::class.java))
                }
            }

            val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        }
    }