package com.example.ebsher;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,String,String> {
    Context context;
    BackgroundTask(Context ctx)
    {
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        String type = strings[0];
        String ContactURL = "http://192.168.1.124/AbsherControlPanel/ContactUs.php";
        String LoginURL = "http://192.168.1.124/AbsherControlPanel/Login.php";
        String regURL = "http://192.168.1.4/AbsherControlPanel/Register.php";

        if(type.equals("contact"))
        {
            String UName = strings[1];
            String UPhone = strings[2];
            String UEmail = strings[3];
            String message = strings[4];
            try
            {
                URL url = new URL(ContactURL);
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String message_user = URLEncoder.encode("UserName","UTF-8")+"="+URLEncoder.encode(UName,"UTF-8")+
                            "&"+URLEncoder.encode("UserPhone","UTF-8")+"="+URLEncoder.encode(UPhone,"UTF-8")+
                            "&"+URLEncoder.encode("UserEmail","UTF-8")+"="+URLEncoder.encode(UEmail,"UTF-8")+
                            "&"+URLEncoder.encode("UserMessage","UTF-8")+"="+URLEncoder.encode(message,"UTF-8");
                    bufferedWriter.write(message_user);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"ISO-8859-1");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String result = "";
                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while((line = bufferedReader.readLine()) != null)
                    {
                        stringBuilder.append(line).append("\n");
                    }
                    result = stringBuilder.toString();
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
        }
        if(type.equals("Register"))
        {
            String FirstName = strings[1];
            String LastName = strings[2];
            String UserName = strings[3];
            String Password = strings[4];
            String UserEmail = strings[5];
            String UserPhone = strings[6];
            String Location = strings[7];
            try
            {
                URL url = new URL(regURL);
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String register_user = URLEncoder.encode("FirstName","UTF-8")+"="+URLEncoder.encode(FirstName,"UTF-8")+
                            "&"+URLEncoder.encode("LastName","UTF-8")+"="+URLEncoder.encode(LastName,"UTF-8")+
                            "&"+URLEncoder.encode("UserName","UTF-8")+"="+URLEncoder.encode(UserName,"UTF-8")+
                            "&"+URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(Password,"UTF-8")+
                            "&"+URLEncoder.encode("UserEmail","UTF-8")+"="+URLEncoder.encode(UserEmail,"UTF-8")+
                            "&"+URLEncoder.encode("UserPhone","UTF-8")+"="+URLEncoder.encode(UserPhone,"UTF-8")+
                            "&"+URLEncoder.encode("Location","UTF-8")+"="+URLEncoder.encode(Location,"UTF-8");
                    bufferedWriter.write(register_user);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"ISO-8859-1");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String result = "";
                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    while((line = bufferedReader.readLine()) != null)
                    {
                        stringBuilder.append(line).append("\n");
                    }
                    result = stringBuilder.toString();
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }
}