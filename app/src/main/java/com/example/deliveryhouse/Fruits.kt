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
import kotlinx.android.synthetic.main.content_fruits.*
import kotlinx.android.synthetic.main.database_fruits.view.*
import org.json.JSONException
import org.json.JSONObject

class Fruits : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    var vol:RequestQueue ?= null
    //private val url = "http://192.168.0.110/DeliveryHouse/Viewfruits.php"
    var arrda = ArrayList<DatabaseFruits>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruits)

        val elites = resources.getStringArray(R.array.ELITES)

        val spinner = findViewById<Spinner>(R.id.spinner2)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, elites)
            spinner.adapter = adapter

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

        vol = Volley.newRequestQueue(this)

        val jess = JsonObjectRequest(Request.Method.GET,"http://192.168.0.110/DeliveryHouse/Viewfruits.php?Elites=First",Response.Listener <JSONObject> {
            response ->
            try {

                spinner.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>,
                                                view: View, position: Int, id: Long) {
                        val j = response.getJSONArray("alldata")
                        for (i in 0 until j.length())
                        {
                            val js = j.getJSONObject(i)
                            val id = js.getInt("id")
                            val Image_Path = js.getString("Image_Path")
                            val ItemName = js.getString("ItemName")
                            val ItemPrice = js.getDouble("ItemPrice")

                            arrda.add(DatabaseFruits(id,Image_Path,ItemName,ItemPrice))
                            val a = fruit(arrda,this@Fruits)
                            lv_fruits.adapter = a
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        Toast.makeText(this@Fruits, "Please Select Item", Toast.LENGTH_SHORT).show()
                    }
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

    class fruit:BaseAdapter
    {
        var adda = ArrayList<DatabaseFruits>()
        var conteext:Context ?= null
        constructor(arr:ArrayList<DatabaseFruits>,con:Context)
        {
            adda = arr
            conteext = con
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val c = adda[position]
            val inff = conteext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val ff = inff.inflate(R.layout.database_fruits,null)

            val itemname = c.ItemName
            val itemprice = "JD " + c.ItemPrice

            ff.textNamefruits.text = itemname
            ff.textPricefruits.text = itemprice.toString()

            Picasso.with(conteext).load("http://192.168.0.110/DeliveryHouse/dashboard/"+c.Image_Path).into(ff.imagefruits)

            ff.imagefruits.setOnClickListener {
                val int = Intent(conteext!!,MyFruits::class.java)
                int.putExtra("ItemName",c.ItemName)
                int.putExtra("ItemPrice",c.ItemPrice)
                int.putExtra("Image_Path",c.Image_Path)
                conteext!!.startActivity(int)
            }

            return ff
        }

        override fun getItem(position: Int): Any {
            return adda[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return adda.size
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
                val intent = Intent(this@Fruits, MyCart::class.java)
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
                val intent = Intent(this@Fruits, Home::class.java)
                startActivity(intent)
            }
            R.id.nav_restaurants -> {
                val intent = Intent(this@Fruits, Restaurants::class.java)
                startActivity(intent)
            }
            R.id.nav_popular -> {
                val intent = Intent(this@Fruits, FastFood::class.java)
                startActivity(intent)
            }
            R.id.nav_fruits -> {
                val intent = Intent(this@Fruits, Fruits::class.java)
                startActivity(intent)
            }
            R.id.nav_meats -> {
                val intent = Intent(this@Fruits, Meats::class.java)
                startActivity(intent)
            }
            R.id.nav_cart -> {
                val intent = Intent(this@Fruits, MyCart::class.java)
                startActivity(intent)
            }
            R.id.nav_send -> {
                val intent = Intent(this@Fruits, ContactUs::class.java)
                startActivity(intent)
            }
            R.id.nav_profile -> {
                val intent = Intent(this@Fruits, MyProfile::class.java)
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
