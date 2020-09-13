package com.example.ebsher;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class NotifyDriver extends AppCompatActivity {

    private Button mapsactivity;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_driver);

        mapsactivity = (Button) findViewById(R.id.mapsactivity);
        mapsactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.ebsher.NotifyDriver.this, com.example.ebsher.MapsActivity.class);
                startActivity(intent);
            }
        });
    }
    public void button(View view)
    {
        NotificationManager mm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(),R.drawable.logo);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("I'm Ebsher Driver")
                .setContentText("Just 5 Minute")
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(bmp)
                .setAutoCancel(true)
                .setNumber(1);

//        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
//        builder.setVibrate(new long[]{500,1000,500,1000,500});

        mm.notify(1,builder.build());
    }
}
