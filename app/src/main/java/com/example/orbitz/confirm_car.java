package com.example.orbitz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class confirm_car extends AppCompatActivity {

    EditText showBrand,showModel,showTime,showDate,showLocation,showAmount;
    Button btnConfirm,btnUpdate,showbtn,btnDelete;
    DatabaseReference myRef;
    BookingDB bookingDB;



    }




//    private  void clearControls(){
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_car);

                showBrand    = findViewById(R.id.showBrand);
                showModel    = findViewById(R.id.showModel);
                showTime     = findViewById(R.id.showTime);
                showDate     = findViewById(R.id.showDate);
                showLocation = findViewById(R.id.showLocation);
                showAmount   =  findViewById(R.id.showAmount);


                btnConfirm   = findViewById(R.id.btnConfirm);

                showbtn      = findViewById(R.id.showbtn);
                btnDelete    = findViewById(R.id.btnDelete);


                bookingDB = new BookingDB();


                showbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Booking").child("book1");
                        readRef.addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChildren()){
                                    showLocation.setText(dataSnapshot.child("location").getValue().toString());
                                    showDate.setText(dataSnapshot.child("date").getValue().toString());
                                    showTime.setText(dataSnapshot.child("time").getValue().toString());
                                    showBrand.setText(dataSnapshot.child("brand").getValue().toString());
                                    showModel.setText(dataSnapshot.child("model").getValue().toString());
                                    showAmount.setText(dataSnapshot.child("amount").getValue().toString());

                                }
                                else
                                    Toast.makeText(getApplicationContext(),"No Source to display",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });


             btnDelete.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {

                                                  DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Booking");
                                                  delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                      @Override
                                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                          if (dataSnapshot.hasChild("book1")){
                                                              myRef = FirebaseDatabase.getInstance().getReference().child("Booking").child("book1");
                                                              myRef.removeValue();
                                                              clearData();
                                                              Toast.makeText(getApplicationContext(),"You Booking is Deleted ",Toast.LENGTH_SHORT).show();
                                                          }
                                                          else
                                                              Toast.makeText(getApplicationContext(),"No Source To Delete",Toast.LENGTH_SHORT).show();
                                                      }

                                                      @Override
                                                      public void onCancelled(@NonNull DatabaseError databaseError) {

                                                      }
                                                  });

                                              }});




            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseReference conRef = FirebaseDatabase.getInstance().getReference().child("Booking").child("book1");
                    conRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()) {

                                Toast.makeText(getApplicationContext(), "Booking confirmed...", Toast.LENGTH_SHORT).show();
                                clearData();
                            } else
                                Toast.makeText(getApplicationContext(), "No Source To confirm", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            });

    }

}
