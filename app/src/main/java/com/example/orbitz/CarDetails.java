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
    CarDb car

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        savebt = findViewById(R.id.btnadd1);
        typeb = findViewById(R.id.btntype);
        nextb = findViewById(R.id.btnnext);
        editman = findViewById(R.id.txtman);
        editmod = findViewById(R.id.txtmod);
        editreg = findViewById(R.id.txtreg);

        cardb = new CarDb();

        AddData();
        UserProf();
        Next();

    }

    private void Next() {
        nextb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarDetails.this, PersonalDetails.class));
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
