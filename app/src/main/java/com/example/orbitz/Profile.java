package com.example.orbitz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {



    Button update,back,edit,delete;
    EditText name,email,password;

    String userName;
    String un,pw,em;

    User us;

    DatabaseReference Dbupdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent i = getIntent();
        userName = i.getStringExtra("userName");
        String msg = "Welcome "+userName;
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();;




        update = findViewById(R.id.btn_update);
        back = findViewById(R.id.btn_back);
        name = findViewById(R.id.txt_name);
        email = findViewById(R.id.txt_email);

        delete = findViewById(R.id.btn_del);



        DatabaseReference Dbupdate = FirebaseDatabase.getInstance().getReference("Sign up").child(userName);
        Dbupdate.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){

                    un = dataSnapshot.child("userName").getValue().toString();
                    pw = dataSnapshot.child("password").getValue().toString();
                    em = dataSnapshot.child("password").getValue().toString();

                    name.setText(dataSnapshot.child("userName").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    password.setText(dataSnapshot.child("password").getValue().toString());

                }else{
                    Toast.makeText(getApplicationContext(),"No Source",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });






        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upref = FirebaseDatabase.getInstance().getReference("Sign up");
                upref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(un)){

                            try {
                                us.setUserName(un);
                                us.setEmail(em);
                                us.setPassword(pw);



                                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Sign up").child(un);
                                dbref.setValue(us);

                                Toast.makeText(Profile.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                            }

                            catch (NumberFormatException e){

                                Toast.makeText(Profile.this, "Not updated Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }


                        else {
                            Toast.makeText(Profile.this, "Not updated Successfully ", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference("Sign up").child(userName);
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.hasChild(userName)){
                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Sign up").child(userName);
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"No Source to delete",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent bac = new Intent(getApplicationContext(),DashBoard.class);
//                startActivity(back);
//            }
//        });
    }
}
