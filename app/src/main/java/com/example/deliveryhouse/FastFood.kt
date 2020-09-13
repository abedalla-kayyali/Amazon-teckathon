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
import kotlinx.android.synthetic.main.content_fastfood.*
import kotlinx.android.synthetic.main.database_meals.view.*
import org.json.JSONObject

class FastFood : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var vol:RequestQueue ?= null
    private val url = "http://192.168.0.110/DeliveryHouse/ViewMeals.php"
    var arrrr = ArrayList<Databasemeals>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fast_food)

        vol = Volley.newRequestQueue(this)
        val jes = JsonObjectRequest(Request.Method.GET,url,Response.Listener <JSONObject> {
            response ->
            try {
                val j = response.getJSONArray("alldata")
                for (i in 0 until j.length())
                {
                    val js = j.getJSONObject(i)
                    val id = js.getInt("id")
                    val Image_Path = js.getString("Image_Path")
                    val NameRestaurant = js.getString("NameRestaurant")
                    val NameMeal = js.getString("NameMeal")
                    val PriceMeal = js.getDouble("PriceMeal")

                    arrrr.add(Databasemeals(id,Image_Path,NameRestaurant,NameMeal,PriceMeal))
                    val a = meals(arrrr,this)

                    lv_fast.adapter = a
                }
            }
            catch (e:Exception)
            {
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
            }
        },Response.ErrorListener {
            error ->
            Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
        })


        vol!!.add(jes)

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

    class meals:BaseAdapter
    {
        var adaa = ArrayList<Databasemeals>()
        var context:Context ?= null

        constructor(arrr:ArrayList<Databasemeals>,conn:Context)
        {
            adaa = arrr
            context = conn
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val c = adaa[position]
            val inff = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val ff = inff.inflate(R.layout.database_meals,null)

            val nm = c.NameMeal
            val pm = " JD " + c.PriceMeal

            ff.textmeal.text = nm
            ff.textprice.text = pm.toString()

            Picasso.with(context).load("http://192.168.0.110/DeliveryHouse/dashboard/"+c.Image_Path).into(ff.imagemeals)

            ff.imagemeals.setOnClickListener {
                val intent = Intent(context!!,MyMeals::class.java)

                intent.putExtra("NameMeal",c.NameMeal)
                intent.putExtra("Image_Path",c.Image_Path)

                context!!.startActivity(intent)
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
                val intent = Intent(this@FastFood, MyCart::class.java)
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
                val intent = Intent(this@FastFood,Home::class.java)
                startActivity(intent)
            }
            R.id.nav_restaurants -> {
                val intent = Intent(this@FastFood,Restaurants::class.java)
                startActivity(intent)
            }
            R.id.nav_popular -> {
                val intent = Intent(this@FastFood,FastFood::class.java)
                startActivity(intent)
            }
            R.id.nav_fruits -> {
                val intent = Intent(this@FastFood, Fruits::class.java)
                startActivity(intent)
            }
            R.id.nav_meats -> {
                val intent = Intent(this@FastFood, Meats::class.java)
                startActivity(intent)
            }
            R.id.nav_cart -> {
                val intent = Intent(this@FastFood,MyCart::class.java)
                startActivity(intent)
            }
            R.id.nav_send -> {
                val intent = Intent(this@FastFood,ContactUs::class.java)
                startActivity(intent)
            }
            R.id.nav_profile -> {
                val intent = Intent(this@FastFood, MyProfile::class.java)
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
