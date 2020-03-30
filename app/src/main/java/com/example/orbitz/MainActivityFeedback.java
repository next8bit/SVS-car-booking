package com.example.orbitz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivityFeedback extends AppCompatActivity {


    private static final String TAG = "MainActivityFeedback";
    EditText name, email, feed;
    Button add;
    //show,update,delete;
    DatabaseReference mRef;
    FeedbackDB feedbackDB;

    public void clearData() {
        name.setText("");
        email.setText("");
        feed.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfeedback);

        name = (EditText) findViewById(R.id.txtName);
        email = (EditText) findViewById(R.id.txtEmail);
        feed = (EditText) findViewById(R.id.txtFeedback);
        feedbackDB = new FeedbackDB();
//        addData();
//        showData();
//        updateData();
//        deleteData();

        add = (Button) findViewById(R.id.button_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification();
                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(feed.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please Fill All The Fields", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("nav", "test");
                    Intent intent = new Intent(MainActivityFeedback.this, FeedbackView.class);
                    startActivity(intent);

                    mRef = FirebaseDatabase.getInstance().getReference().child("FeedbackDB");
                    feedbackDB.setName(name.getText().toString().trim());
                    feedbackDB.setEmail(email.getText().toString().trim());
                    feedbackDB.setFeed(feed.getText().toString().trim());

                    mRef.child("feed1").setValue(feedbackDB);
                    Toast.makeText(getApplicationContext(), "FeedbackDB Success", Toast.LENGTH_LONG).show();
                    clearData();
                }
            }
        });


    }
    public void Notification(){
        String strtitle = getString(R.string.notificationtitle);
        String strtext = getString(R.string.notificationtext);
        Intent intent = new Intent(this, FeedbackView.class);
        intent.putExtra("title",strtitle);
        intent.putExtra("text",strtext);

        PendingIntent pIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_announcement_black_24dp).setTicker(getString(R.string.notificationticker)).setContentTitle(getString(R.string.notificationtitle)).setContentText(getString(R.string.notificationtext)).addAction(R.drawable.ic_launcher,"Action Button",pIntent).setContentIntent(pIntent).setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }
}

//    public void addData(){
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                boolean isInserted = feedbackDB.insertData(name.getText().toString(),email.getText().toString(),feedbackDB.getText().toString());
//                if(isInserted == true){
//                    Toast.makeText(MainActivityFeedback.this,"Data Inserted",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(MainActivityFeedback.this,"Data Not Inserted",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    public void showData(){
//        show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Cursor res = feedbackDB.getAllData();
//                if (res.getCount() == 0){
//                    showMessage("Error, Nothing Found");
//                    return;
//                }
//
//                StringBuffer buffer = new StringBuffer();
//                while (res.moveToNext()){
//                    buffer.append("Name: "+res.getString(0)+"\n");
//                    buffer.append("Email: "+res.getString(1)+"\n");
//                    buffer.append("FeedbackDB: "+res.getString(2)+"\n");
//                }
//
//                showMessage("Data",buffer.toString());
//            }
//        });
//    }
//
//    public void showMessage(String title,String Message){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(Message);
//        builder.show();
//    }
//
//    public void updateData(){
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                boolean isUpdate = FeedbackDB.updateData(feedbackDB.getText().toString(),email.getText().toString(),name.getText().toString());
//                if (isUpdate == true){
//                    Toast.makeText(MainActivityFeedback.this,"Data Update",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(MainActivityFeedback.this,"Data Not Updated",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    public void deleteData(){
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Integer deletedRows = feedbackDB.deleteData(name.getText().toString());
//                if (deletedRows > 0){
//                    Toast.makeText(MainActivityFeedback.this,"Data Deleted",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(MainActivityFeedback.this,"Data Not Deleted",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//
//
//}
