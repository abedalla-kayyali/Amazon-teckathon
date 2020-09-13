package com.example.ebsher;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static com.example.ebsher.SharedPrefManager instance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_EMAIL = "useremail";
    private static final String KEY_USER_POINT = "userpoint";
    private static final String KEY_USER_ID = "userid";

    private static final String KEY_ADMIN_NAME = "adminname";
    private static final String KEY_ADMIN_STATUS = "adminstatus";
    private static final String KEY_ADMIN_ID = "adminid";


    public static synchronized com.example.ebsher.SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new com.example.ebsher.SharedPrefManager(context);
        }
        return instance;
    }

    public boolean userLogin(int id,String UserName,String EmailUser,String PointsCustomer)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID,id);
        editor.putString(KEY_USERNAME,UserName);
        editor.putString(KEY_USER_EMAIL,EmailUser);
        editor.putString(KEY_USER_POINT,PointsCustomer);

        editor.apply();

        return true;
    }

    public boolean adminLogin(int id,String UserName,String Status_Col)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_ADMIN_ID,id);
        editor.putString(KEY_ADMIN_NAME,UserName);
        editor.putString(KEY_ADMIN_STATUS,Status_Col);

        editor.apply();

        return true;
    }

    public boolean isLoggedIn()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME,null) != null)
        {
            return true;
        }
        return false;
    }
    public boolean logout()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();

        return true;
    }

    public String getUsername()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME,null);
    }
    public String getUserEmail()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL,null);
    }
    public String getUserPoint()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_POINT,null);
    }
}
