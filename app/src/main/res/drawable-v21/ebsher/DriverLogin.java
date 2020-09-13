package com.example.ebsher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DriverLogin extends AppCompatActivity implements View.OnClickListener{

    private Button login_driver;
    private EditText txt_dr_username,txt_dr_password;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        txt_dr_username = (EditText) findViewById(R.id.txt_dr_username);
        txt_dr_password = (EditText) findViewById(R.id.txt_dr_password);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait ...");

        login_driver = (Button) findViewById(R.id.login_driver);
        login_driver.setOnClickListener(this);
    }

    private void adminLogin()
    {
        progressDialog.show();

        final String adminname = txt_dr_username.getText().toString().trim();
        final String password = txt_dr_password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, com.example.ebsher.Constants.URL_DRIVER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getBoolean("error"))
                    {
                        com.example.ebsher.SharedPrefManager.getInstance(getApplicationContext())
                                .adminLogin(
                                        obj.getInt("id"),
                                        obj.getString("UserName"),
                                        obj.getString("Status_Col")
                                );
                        //Toast.makeText(getApplicationContext(),"User Login Successful ..!",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), com.example.ebsher.NotifyDriver.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please Try Again ..!",Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("UserName",adminname);
                params.put("Password_Col",password);

                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
     }

    @Override
    public void onClick(View view) {
        if(view == login_driver)
        {
            adminLogin();
        }
   }
}
