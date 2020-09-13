package com.example.ebsher;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Contact extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button _btnsend;
    EditText _edttxtuser,_edttxtmail,_edttxtphone,_edttxtmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _edttxtuser = (EditText) findViewById(R.id.edttxtuser);
        _edttxtmail = (EditText) findViewById(R.id.edttxtmail);
        _edttxtphone = (EditText) findViewById(R.id.edttxtphone);
        _edttxtmessage = (EditText) findViewById(R.id.edttxtmessage);

        _btnsend = (Button) findViewById(R.id.btnsend);
        _btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UName = _edttxtuser.getText().toString().trim();
                String UPhone = _edttxtphone.getText().toString().trim();
                String UEmail = _edttxtmail.getText().toString().trim();
                String message = _edttxtmessage.getText().toString().trim();

                if(UName.isEmpty())
                {
                    Toast.makeText(com.example.ebsher.Contact.this,"Please Enter Your Name",Toast.LENGTH_LONG).show();
                }

                if(UPhone.isEmpty())
                {
                    Toast.makeText(com.example.ebsher.Contact.this,"Please Enter Your Phone",Toast.LENGTH_LONG).show();
                }

                if(UEmail.isEmpty())
                {
                    Toast.makeText(com.example.ebsher.Contact.this,"Please Enter Your Email",Toast.LENGTH_LONG).show();
                }

                if(message.isEmpty())
                {
                    Toast.makeText(com.example.ebsher.Contact.this,"Please Enter Your Message",Toast.LENGTH_LONG).show();
                }
                else
                {
                    String type = "contact";
                    BackgroundTask backgroundTask = new BackgroundTask(getApplicationContext());
                    backgroundTask.execute(type,UName,UPhone,UEmail,message);
                }
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_viewcont);
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
            Intent intent = new Intent(com.example.ebsher.Contact.this,StartActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(com.example.ebsher.Contact.this, com.example.ebsher.Contact.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_restaurant) {
            Intent intent = new Intent(com.example.ebsher.Contact.this,Resturants.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_profile) {
            Intent intent = new Intent(com.example.ebsher.Contact.this,MyProfile.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

        }
    }
