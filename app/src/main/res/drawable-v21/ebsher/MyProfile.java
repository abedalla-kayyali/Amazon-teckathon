package com.example.ebsher;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MyProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private EditText textviewUsername,textviewUserEmail,textviewUserPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(!SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(this, Login.class));
        }

        textviewUsername = (EditText) findViewById(R.id.textviewUsername);
        textviewUserEmail = (EditText) findViewById(R.id.textviewUserEmail);
        textviewUserPoint = (EditText) findViewById(R.id.textviewUserPoint);

        textviewUsername.setText(SharedPrefManager.getInstance(this).getUsername());
        textviewUserEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        textviewUserPoint.setText(SharedPrefManager.getInstance(this).getUserPoint());


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(com.example.ebsher.MyProfile.this,StartActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(com.example.ebsher.MyProfile.this, com.example.ebsher.Contact.class);
            startActivity(intent);
        } else if (id == R.id.nav_restaurant) {
            Intent intent = new Intent(com.example.ebsher.MyProfile.this,Resturants.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_profile) {
            Intent intent = new Intent(com.example.ebsher.MyProfile.this, com.example.ebsher.MyProfile.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_LogOut) {
            SharedPrefManager.getInstance(this).logout();
            finish();
            startActivity(new Intent(this, Login.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
