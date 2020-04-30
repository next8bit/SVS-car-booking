package com.example.orbitz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedbackView extends AppCompatActivity {

    EditText showName,showEmail,showFeed;
    Button btnshow,btnDelete;
    FeedbackDB feedbackDB;
    DatabaseReference mRef;


    public void clearData(){
        showName.setText("");
        showEmail.setText("");
        showFeed.setText("");
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_view);

        showName = findViewById(R.id.editText1);
        showEmail = findViewById(R.id.editText2);
        showFeed = findViewById(R.id.editText3);

        btnshow = findViewById(R.id.button);

        btnDelete = findViewById(R.id.button3);

        feedbackDB = new FeedbackDB();

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("FeedbackDB").child("feed1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            showName.setText(dataSnapshot.child("name").getValue().toString());
                            showEmail.setText(dataSnapshot.child("email").getValue().toString());
                            showFeed.setText(dataSnapshot.child("feed").getValue().toString());
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"No FeedbackDB to display",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("feed1")){
                            mRef = FirebaseDatabase.getInstance().getReference().child("FeedbackDB").child("feed1");
                            mRef.removeValue();
                            clearData();

                            Toast.makeText(getApplicationContext(),"Your FeedbackDB is deleted",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"No source to Delete",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

}
