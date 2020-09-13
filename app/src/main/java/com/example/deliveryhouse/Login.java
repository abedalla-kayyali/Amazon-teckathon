package com.example.deliveryhouse;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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

    private Button _btnlogin;
    private EditText _lousername,_lopassword;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       if(SharedPrefManager.getInstance(this).isLoggedIn())
       {
           finish();
           startActivity(new Intent(this,Restaurants.class));
           return;
       }

        _lousername = (EditText) findViewById(R.id.lousername);
        _lopassword = (EditText) findViewById(R.id.lopassword);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait ...");

        _btnlogin = (Button) findViewById(R.id.btnlogin);
        _btnlogin.setOnClickListener(this);
    }
    private void userLogin()
    {
        progressDialog.show();

        final String username = _lousername.getText().toString().trim();
        final String password = _lopassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getBoolean("error"))
                    {
                        SharedPrefManager.getInstance(getApplicationContext())
                                .userLogin(
                                        obj.getInt("id"),
                                        obj.getString("UserName"),
                                        obj.getString("EmailUser"),
                                        obj.getString("PointsCustomer")
                                );
                        //Toast.makeText(getApplicationContext(),"User Login Successful ..!",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), Restaurants.class));
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
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == _btnlogin)
        {
            userLogin();
        }
    }
}
