package com.example.deliveryhouse

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.*
import android.widget.ViewFlipper
import kotlinx.android.synthetic.main.content_home.*
import java.util.*

class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    internal lateinit var timer: Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(this@Home, Restaurants::class.java)
                startActivity(intent)
            }
        }, 5000)

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
                val intent = Intent(this@Home, MyCart::class.java)
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
                val intent = Intent(this@Home,Home::class.java)
                startActivity(intent)
            }
            R.id.nav_restaurants -> {
                val intent = Intent(this@Home,Restaurants::class.java)
                startActivity(intent)
            }
            R.id.nav_popular -> {
                val intent = Intent(this@Home,FastFood::class.java)
                startActivity(intent)
            }
            R.id.nav_fruits -> {
                val intent = Intent(this@Home, Fruits::class.java)
                startActivity(intent)
            }
            R.id.nav_meats -> {
                val intent = Intent(this@Home, Meats::class.java)
                startActivity(intent)
            }
            R.id.nav_cart -> {
                val intent = Intent(this@Home,MyCart::class.java)
                startActivity(intent)
            }
            R.id.nav_send -> {
                val intent = Intent(this@Home,ContactUs::class.java)
                startActivity(intent)
            }
            R.id.nav_profile -> {
                val intent = Intent(this@Home, MyProfile::class.java)
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
