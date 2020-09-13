package com.example.deliveryhouse

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import android.widget.Toast
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
import kotlinx.android.synthetic.main.content_meats.*
import kotlinx.android.synthetic.main.database_meats.view.*
import org.json.JSONException
import org.json.JSONObject

class Meats : AppCompatActivity()  , NavigationView.OnNavigationItemSelectedListener{

    var vol: RequestQueue?= null
    private val url = "http://192.168.0.110/DeliveryHouse/ViewMeats.php"
    var arrra = ArrayList<DatabaseMeats>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meats)

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
        val jess = JsonObjectRequest(Request.Method.GET,url,Response.Listener <JSONObject>{
            response ->
            try {
                val j = response.getJSONArray("alldata")
                for (i in 0 until j.length()) {
                    val js = j.getJSONObject(i)
                    val id = js.getInt("id")
                    val Image_Path = js.getString("Image_Path")
                    val ProductPrice = js.getInt("ProductPrice")
                    val ProductName = js.getString("ProductName")

                    arrra.add(DatabaseMeats(id,Image_Path,ProductPrice,ProductName))
                    val a = meats(arrra,this)
                    lv_meats.adapter = a
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

    class meats:BaseAdapter
    {
        var adaa = ArrayList<DatabaseMeats>()
        var contexxxt:Context ?= null
        constructor(arr:ArrayList<DatabaseMeats>,con:Context)
        {
            adaa = arr
            contexxxt = con
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val cc = adaa[position]
            val infff = contexxxt!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val ff = infff.inflate(R.layout.database_meats,null)

            val namepro = cc.ProductName
            val pricepro = "JD " + cc.ProductPrice

            ff.textmeatsname.text = namepro
            ff.textmeatsprice.text = pricepro.toString()

            Picasso.with(contexxxt).load("http://192.168.0.110/DeliveryHouse/dashboard/"+cc.Image_Path).into(ff.imagemeats)

            ff.imagemeats.setOnClickListener {
                val intent = Intent(contexxxt!!,MyMeats::class.java)

                intent.putExtra("ProductName",cc.ProductName)
                intent.putExtra("ProductPrice",cc.ProductPrice)
                intent.putExtra("Image_Path",cc.Image_Path)

                contexxxt!!.startActivity(intent)
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
                val intent = Intent(this@Meats, MyCart::class.java)
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
                val intent = Intent(this@Meats, Home::class.java)
                startActivity(intent)
            }
            R.id.nav_restaurants -> {
                val intent = Intent(this@Meats, Restaurants::class.java)
                startActivity(intent)
            }
            R.id.nav_popular -> {
                val intent = Intent(this@Meats, FastFood::class.java)
                startActivity(intent)
            }
            R.id.nav_fruits -> {
                val intent = Intent(this@Meats, Fruits::class.java)
                startActivity(intent)
            }
            R.id.nav_meats -> {
                val intent = Intent(this@Meats, Meats::class.java)
                startActivity(intent)
            }
            R.id.nav_cart -> {
                val intent = Intent(this@Meats, MyCart::class.java)
                startActivity(intent)
            }
            R.id.nav_send -> {
                val intent = Intent(this@Meats, ContactUs::class.java)
                startActivity(intent)
            }
            R.id.nav_profile -> {
                val intent = Intent(this@Meats, MyProfile::class.java)
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
