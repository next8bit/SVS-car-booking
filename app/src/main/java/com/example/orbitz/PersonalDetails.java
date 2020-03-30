package com.example.orbitz;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import android.support.v7.app.AppCompatActivity;

public class PersonalDetails extends AppCompatActivity {

    Button searschid,deletebt,updatebt;
    EditText editid,editdate,editcat,editqty;
    DatabaseReference dbref;
    DatabaseReference viewref;
    DatabaseReference delref;
    RentDb rentdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        searschid = findViewById(R.id.btnsearch);
        deletebt = findViewById(R.id.btnreset);
        updatebt = findViewById(R.id.btnupdate1);
        editid = findViewById(R.id.txtid);
        editdate = findViewById(R.id.txtdate);
        editcat = findViewById(R.id.txtcat);
        editqty = findViewById(R.id.txtqty);

        rentdb = new RentDb();

        ViewData();
        DeleteData();
        SaveData();

    }

    public void ViewData() {
        searschid.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                viewref = FirebaseDatabase.getInstance().getReference().child("RentDb").child("rentdb1");
                viewref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            editid.setText(dataSnapshot.child("id").getValue().toString());
                            editdate.setText(dataSnapshot.child("date").getValue().toString());
                            editcat.setText(dataSnapshot.child("cat").getValue().toString());
                            editqty.setText(dataSnapshot.child("qty").getValue().toString());

                        } else
                            Toast.makeText(getApplicationContext(), "No Source to display", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled( DatabaseError databaseError) {
                    }

                });
            }
        });
    }


    public void DeleteData() {
        deletebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delref = FirebaseDatabase.getInstance().getReference().child("RentDb");

                delref.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChild("rentdb1")) {

                            dbref = FirebaseDatabase.getInstance().getReference().child("RentDb").child("rentdb1");

                            dbref.removeValue();

                            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();

                            editid.setText(null);

                            editdate.setText(null);

                            editcat.setText(null);

                            editqty.setText(null);

                        }
                        else {

                            Toast.makeText(getApplicationContext(), "No Source to delete", Toast.LENGTH_LONG).show();

                        }

                    }


                    @Override
                    public void onCancelled( DatabaseError databaseError) {


                    }


                });
            }
        });
    }

            public void SaveData() {
                updatebt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dbref = FirebaseDatabase.getInstance().getReference().child("RentDb");

                        if (TextUtils.isEmpty(editid.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Please enter your ID", Toast.LENGTH_LONG).show();
                        else if (TextUtils.isEmpty(editdate.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Please enter your Name", Toast.LENGTH_LONG).show();
                        else if (TextUtils.isEmpty(editcat.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Please enter a email address", Toast.LENGTH_LONG).show();
                        else if (TextUtils.isEmpty(editqty.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Please enter a vehicle number", Toast.LENGTH_LONG).show();

                        rentdb.setId(editid.getText().toString().trim());


                        rentdb.setDate(editdate.getText().toString().trim());

                        rentdb.setCat(editcat.getText().toString().trim());

                        rentdb.setQty(Integer.parseInt(editqty.getText().toString().trim()));


                        dbref.push().setValue(rentdb);

                        dbref.child("rentdb1").setValue(rentdb);

                        Toast.makeText(getApplicationContext(), "Info Saved Successfully", Toast.LENGTH_LONG).show();

                        editid.setText(null);

                        editdate.setText(null);

                        editcat.setText(null);

                        editqty.setText(null);


                    }
                });
            }
        }
