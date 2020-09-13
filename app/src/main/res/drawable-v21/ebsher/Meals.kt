package com.example.ebsher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
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
import org.json.JSONException
import org.json.JSONObject

class Meals : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var vol:RequestQueue ?= null
    private val url = "http://192.168.1.124/AbsherControlPanel/ViewFood.php"
    var arr = ArrayList<Databasemeals>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        vol = Volley.newRequestQueue(this)
        val jes = JsonObjectRequest(Request.Method.GET,url,Response.Listener <JSONObject>{
            response ->
            try {
                val j = response.getJSONArray("alldata")
                for (i in 0 until j.length())
                {
                    val js = j.getJSONObject(i)
                    val id = js.getInt("id")
                    val Image_Path = js.getString("Image_Path")
                    val MealName = js.getString("MealName")
                    val MealPrice = js.getInt("MealPrice")

                    arr.add(Databasemeals(id,Image_Path,MealName,MealPrice))
                    val a = meals(arr,this)
                    lv_meals.adapter = a
                }
            }catch (e:JSONException)
            {
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
            }
        },Response.ErrorListener {
            error ->
            Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
        })

        vol!!.add(jes)

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
        var ada = ArrayList<Databasemeals>()
        var context:Context ?= null
        constructor(arr:ArrayList<Databasemeals>,con:Context)
        {
            ada=arr
            context=con
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            val c = ada[p0]
            val inff = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val ff = inff.inflate(R.layout.database_meals,null)

            val mp = "JD " + c.MealPrice
            val mn = c.MealName

            Picasso.with(context).load("http://192.168.1.124/AbsherControlPanel/"+c.Image_Path).into(ff.igemeals)

            ff.txtmeal.text = mn
            ff.txtprice.text = mp.toString()

            ff.igemeals.setOnClickListener {
                val intent = Intent(context!!,MyOrder::class.java)

                intent.putExtra("MealName",c.MealName)
                intent.putExtra("MealPrice",c.MealPrice).toString()
                intent.putExtra("Image_Path",c.Image_Path)

                context!!.startActivity(intent)
            }
            return ff
        }

        override fun getItem(p0: Int): Any {
            return ada[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {

            }
            R.id.nav_slideshow -> {
                startActivity(Intent(this@Meals,StartActivity::class.java))
            }
            R.id.nav_send -> {
                startActivity(Intent(this@Meals,Contact::class.java))
            }
            R.id.nav_restaurant -> {
                startActivity(Intent(this@Meals,Resturants::class.java))
            }
            R.id.nav_profile -> {
                startActivity(Intent(this@Meals,MyProfile::class.java))
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
