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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private Button _signUp;
    private EditText _userName,_password,_usermail;
    private TextView _activitylogin;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if(SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(this,Resturants.class));
            return;
        }

        _userName = (EditText) findViewById(R.id.userName);
        _password = (EditText) findViewById(R.id.password);
        _usermail = (EditText) findViewById(R.id.usermail);
        _signUp = (Button) findViewById(R.id.signUp);
        _signUp.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

        _activitylogin = (TextView) findViewById(R.id.activitylogin);
        _activitylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.ebsher.Register.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser()
    {
        final String email = _usermail.getText().toString().trim();
        final String password = _password.getText().toString().trim();
        final String username = _userName.getText().toString().trim();

        progressDialog.setMessage("Registration User ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, com.example.ebsher.Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("UserName",username);
                params.put("Password_Col",password);
                params.put("EmailUser",email);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if(view == _signUp)
        {
            registerUser();
        }
    }
}
