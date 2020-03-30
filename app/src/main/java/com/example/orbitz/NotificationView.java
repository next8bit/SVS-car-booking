package com.example.orbitz;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationView extends Activity {

    String title;
    String text;
    TextView txttitle;
    TextView txttext;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificationview);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(0);

        Intent i = getIntent();

        title = i.getStringExtra("title");
        text = i.getStringExtra("text");

        txttext = (TextView)findViewById(R.id.text);
        txttitle = (TextView)findViewById(R.id.title);
    }

}
