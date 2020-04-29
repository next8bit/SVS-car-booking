package com.example.orbitz;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import android.support.v7.app.AppCompatActivity;

public class CarDetails extends AppCompatActivity {

    Button savebt,typeb,nextb;
    EditText editman,editmod,editreg;
    DatabaseReference dbref;
    CarDb cardb;



    private void Next() {
        nextb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarDetails.this, PersonalDetails.class));
            }
        });
    }

    private void UserProf() {
        typeb.setOnClickListener(new View.OnClickListener() {
            /*Intent intent = new Intent(this,PersonalDetails.class);
            startActivity(intent);*/
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarDetails.this, UserProfile.class));
            }
        });
    }

    public void AddData(){
        savebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //boolean isInserted = c1.insertData(
                dbref = FirebaseDatabase.getInstance().getReference().child("CarDb");
            if(TextUtils.isEmpty(editman.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter a manufacturer name", Toast.LENGTH_LONG).show();
            else if(TextUtils.isEmpty(editmod.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter a model name", Toast.LENGTH_LONG).show();
            else if(TextUtils.isEmpty(editreg.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please a registration no", Toast.LENGTH_LONG).show();


            else {

                cardb.setManu(editman.getText().toString().trim());

                cardb.setModel(editmod.getText().toString().trim());

                cardb.setRegNo(editreg.getText().toString().trim());


                dbref.push().setValue(cardb);

                dbref.child("cardb1").setValue(cardb);

                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();


                editman.setText(null);

                editmod.setText(null);

                editreg.setText(null);
            }





            }
        });

    }
}
