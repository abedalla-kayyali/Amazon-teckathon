package com.example.deliveryhouse

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_restaurants.*
import kotlinx.android.synthetic.main.database_restaurants.view.*
import org.json.JSONException
import org.json.JSONObject

class Restaurants : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    var vol:RequestQueue ?= null
    //private val url="http://192.168.0.110/DeliveryHouse/ViewRestaurants.php?NameRestaurant="+editsearch.text.toString()
    var arrrrr = ArrayList<Databaserestaurants>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)

        val search = findViewById(R.id.editsearch) as EditText
        val onclick = findViewById(R.id.button) as Button
        onclick.setOnClickListener {
            var url:String ="http://192.168.0.110/DeliveryHouse/ViewRestaurants.php?NameRestaurant="+search.text.toString()

            vol = Volley.newRequestQueue(this)
            val jes = JsonObjectRequest(Request.Method.GET,url,Response.Listener <JSONObject>{
                response ->
                try {

                    val j = response.getJSONArray("alldata")
                    for (i in 0 until j.length())
                    {
                        val js = j.getJSONObject(i)
                        val idd = js.getInt("id")
                        val Image_Path = js.getString("Image_Path")
                        val NameRestaurant = js.getString("NameRestaurant")
                        val TypeFood = js.getInt("TypeFood")

                        arrrrr.add(Databaserestaurants(idd,Image_Path,NameRestaurant,TypeFood))
                        val a = restaurant(arrrrr,this@Restaurants)

                        lvr.adapter = a
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

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    class restaurant:BaseAdapter
    {
        var ada = ArrayList<Databaserestaurants>()
        var contex:Context ?= null

        constructor(arr:ArrayList<Databaserestaurants>,con:Context)
        {
            ada = arr
            contex = con
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val c = ada[position]
            val inff = contex!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val ff = inff.inflate(R.layout.database_restaurants, null)

            val nr = c.NameRestaurant
            val tf = "JD" + c.TypeFood

            ff.textFood.text = tf.toString()
            ff.textName.text = nr

            Picasso.with(contex).load("http://192.168.0.110/DeliveryHouse/dashboard/" + c.Image_Path).into(ff.imagerestaurants)
            ff.imagerestaurants.setOnClickListener {
                val intent = Intent(contex!!,MyOrders::class.java)

                intent.putExtra("NameRestaurant",c.NameRestaurant)
                intent.putExtra("TypeFood",c.TypeFood)
                intent.putExtra("Image_Path",c.Image_Path)

                contex!!.startActivity(intent)
            }
            return ff
        }

        override fun getItem(position: Int): Any {
            return ada[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return ada.size
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
                val intent = Intent(this@Restaurants, MyCart::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_slideshow -> {
                val intent = Intent(this@Restaurants,Home::class.java)
                startActivity(intent)
            }
            R.id.nav_restaurants -> {
                val intent = Intent(this@Restaurants,Restaurants::class.java)
                startActivity(intent)
            }
            R.id.nav_popular -> {
                val intent = Intent(this@Restaurants,FastFood::class.java)
                startActivity(intent)
            }
            R.id.nav_fruits -> {
                val intent = Intent(this@Restaurants, Fruits::class.java)
                startActivity(intent)
            }
            R.id.nav_meats -> {
                val intent = Intent(this@Restaurants, Meats::class.java)
                startActivity(intent)
            }
            R.id.nav_cart -> {
                val intent = Intent(this@Restaurants,MyCart::class.java)
                startActivity(intent)
            }
            R.id.nav_send -> {
                val intent = Intent(this@Restaurants,ContactUs::class.java)
                startActivity(intent)
            }
            R.id.nav_profile -> {
                val intent = Intent(this@Restaurants, MyProfile::class.java)
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