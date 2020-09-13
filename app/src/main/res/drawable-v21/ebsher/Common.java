package com.example.ebsher;

public class Common {

    public static final String baseURL = "https://googleapis.com";

    public static com.example.ebsher.Reomte.IGoogleApi getGoogleApi()
    {
        return com.example.ebsher.Reomte.RetrofitClient.getClient(baseURL).create(com.example.ebsher.Reomte.IGoogleApi.class);
    }
}
