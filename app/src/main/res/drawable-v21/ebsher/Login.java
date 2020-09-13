package com.example.ebsher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button buttonLogin,btn_driver;
    private EditText _txtusername,_txtpassword;
    private TextView _activitylogUp;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(com.example.ebsher.SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(this, com.example.ebsher.MyProfile.class));
            return;
        }

        _txtusername = (EditText) findViewById(R.id.txtusername);
        _txtpassword = (EditText) findViewById(R.id.txtpassword);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait ...");

        btn_driver = (Button) findViewById(R.id.btn_driver);
        btn_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.ebsher.Login.this, com.example.ebsher.DriverLogin.class);
                startActivity(intent);
            }
        });

        buttonLogin = (Button) findViewById(R.id.signIn);
        buttonLogin.setOnClickListener(this);

        _activitylogUp = (TextView) findViewById(R.id.activitylogUp);
        _activitylogUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.ebsher.Login.this, com.example.ebsher.MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void userLogin()
    {
        progressDialog.show();

        final String username = _txtusername.getText().toString().trim();
        final String password = _txtpassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, com.example.ebsher.Constants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getBoolean("error"))
                    {
                        com.example.ebsher.SharedPrefManager.getInstance(getApplicationContext())
                                .userLogin(
                                        obj.getInt("id"),
                                        obj.getString("UserName"),
                                        obj.getString("EmailUser"),
                                        obj.getString("PointsCustomer")
                                );
                        //Toast.makeText(getApplicationContext(),"User Login Successful ..!",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), Resturants.class));
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
                params.put("UserName",username);
                params.put("Password_Col",password);

                return params;
            }
        };
        com.example.ebsher.RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogin)
        {
            userLogin();
        }
    }
}
